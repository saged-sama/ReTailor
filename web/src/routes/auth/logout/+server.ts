import { PUBLIC_POCKETBASE_URL } from "$env/static/public";
import PocketBase from "pocketbase";

export const GET = {
    default: async ({ cookie }: { cookie: any }) => {
        const pocketbase = new PocketBase(PUBLIC_POCKETBASE_URL);
        pocketbase.authStore.loadFromCookie(cookie);
        console.log(cookie);
        pocketbase.authStore.clear();
        pocketbase.authStore.exportToCookie();
        return { status: 200 };
    }
}