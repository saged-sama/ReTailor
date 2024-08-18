export const POST = {
    logout: async ({ locals }: { locals: any }) => {
        locals.pb.authStore.clear();
    }
}