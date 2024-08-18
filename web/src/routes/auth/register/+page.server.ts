import { redirect } from '@sveltejs/kit';


export const actions = {
    register: async ({ request, locals }: { request: any, locals: any }) => {
        const user = Object.fromEntries(await request.formData());
        try{
            await locals.pb.collection('users').create(user);
        }catch(err){
            console.log(err);
            throw Error("Failed to create new user");
        }
        throw redirect(303, '/auth/login');
    }
}