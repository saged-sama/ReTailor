import { redirect } from '@sveltejs/kit';

export const actions = {
    login: async ({ request, locals }: { request: any, locals: any }) => {
        const body = Object.fromEntries(await request.formData());
        try{
            await locals.pb.collection("users").authWithPassword(
                body.email,
                body.password
            );
        } catch(err){
            console.log(err);
            throw Error("Could not login");
        }

        throw redirect(303, '/store');
    }
}