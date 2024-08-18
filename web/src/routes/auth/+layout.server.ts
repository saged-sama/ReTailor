import { redirect } from "@sveltejs/kit"

export const load = ({ locals }: {locals: any}) => {
    if(locals.currentUser !== undefined){
        throw redirect(303, "/store");
    }
    return {};
}