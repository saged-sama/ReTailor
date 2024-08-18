import PocketBase from "pocketbase";
import { serializeNonPOJOs } from "$lib";
import { PUBLIC_POCKETBASE_URL } from "$env/static/public";

export const handle = async ({ event, resolve }: { event: any, resolve: any }) => {
    event.locals.pb = new PocketBase(PUBLIC_POCKETBASE_URL);
    event.locals.pb.authStore.loadFromCookie(event.request.headers.get("cookie") || "");

    if(event.locals.pb.authStore.isValid){
        event.locals.currentUser = serializeNonPOJOs(event.locals.pb.authStore.model);
    }
    else{
        event.locals.currentUser = undefined;
    }

    const response = await resolve(event);
    response.headers.set("set-cookie", event.locals.pb.authStore.exportToCookie({ secure: false }));

    return response;
}