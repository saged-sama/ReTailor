<script lang="ts">
    import { Circle, Edit, ImageUp } from "lucide-svelte";
    import { pocketbase } from "$lib/utils/pocketbase";
    import { PUBLIC_POCKETBASE_URL } from "$env/static/public";

    export let user: any;
    let isEditing = false;
    let currentAvatar: string = `${PUBLIC_POCKETBASE_URL}/api/files/_pb_users_auth_/${user?.id}/${user?.avatar}`;


    console.log(user);
    const handleChange = (e: any) => {
        const file = e.target.files[0];
        currentAvatar = URL.createObjectURL(file) || `${PUBLIC_POCKETBASE_URL}/api/files/_pb_users_auth_/${user?.id}/${user?.avatar}`;
    }

    const updateBasicInfo = async (e: any) => {
        try {
            e.preventDefault();
            const form = e.target;
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());
            if(currentAvatar === `${PUBLIC_POCKETBASE_URL}/api/files/_pb_users_auth_/${user?.id}/${user?.avatar}`){
                data.avatar = user.avatar;
            }
            user = await pocketbase.collection("users").update(user.id, data);
            isEditing = false;
            form.reset();
            currentAvatar = `${PUBLIC_POCKETBASE_URL}/api/files/_pb_users_auth_/${user?.id}/${user?.avatar}`;
        } catch (err) {
            console.error("Could not edit basic info: ", err);
        }
    };
</script>

<div
    class="flex md:flex-row flex-col items-center md:justify-between container gap-3 w-full md:w-2/3 shadow-lg p-5"
>
    {#if isEditing}
        <form on:submit={updateBasicInfo} class="{pocketbase.authStore.model?.id !== user.id ? "hidden": "flex"} flex-col gap-5 w-full">
            <div class="flex md:flex-row flex-col gap-5 items-center justify-center">
                <div class="flex flex-col items-center gap-3 md:w-1/6">
                    <label for="avatar" class=" cursor-pointer">
                        <div class="flex parent w-36 h-36 items-center max-md:opacity-50 md:hover:opacity-50 justify-center rounded-full">
                            <img
                                src={currentAvatar}
                                alt="Avatar"
                                class="w-full h-full rounded-full object-cover"
                            />
                            <div class="absolute child px-2 py-1 md:opacity-0 text-white">
                                <ImageUp class="md:h-10 md:w-10" />
                            </div>
                        </div>
                        <input
                            type="file"
                            name="avatar"
                            id="avatar"
                            class="hidden"
                            accept="image/*"
                            on:change={handleChange}
                        />
                    </label>
                </div>

                <table cellpadding="10" class="w-full md:w-5/6 text-left text-sm">
                    <tr class="max-md:flex max-md:flex-col">
                        <th class=" w-24">First Name:</th>
                        <td colspan="7">
                            <input
                                type="text"
                                name="firstName"
                                class="input input-bordered input-sm w-full"
                                value={user.firstName}
                            />
                        </td>
                    </tr>
                    <tr class="max-md:flex max-md:flex-col">
                        <th class=" w-24">Last Name:</th>
                        <td colspan="7">
                            <input
                                type="text"
                                name="lastName"
                                class="input input-bordered input-sm w-full"
                                value={user.lastName}
                            />
                        </td>
                    </tr>
                    <tr class="max-md:flex max-md:flex-col">
                        <th class=" w-24">Username:</th>
                        <td colspan="7">
                            <input
                                type="text"
                                name="username"
                                class="input input-bordered input-sm w-full"
                                value={user.username}
                            />
                        </td>
                    </tr>
                    <tr class="max-md:flex max-md:flex-col">
                        <th class=" w-24">Email:</th>
                        <td colspan="7">
                            <input
                                type="text"
                                name="email"
                                class="input input-bordered input-disabled input-sm w-full"
                                disabled
                                value={user.email}
                            />
                        </td>
                    </tr>
                </table>
            </div>
            <div class="flex items-center justify-center gap-3">
                <button class="btn btn-primary btn-sm" type="submit"
                    >Save</button
                >
                <button class="btn btn-sm" on:click={() => {
                    isEditing = false;
                    currentAvatar = `${PUBLIC_POCKETBASE_URL}/api/files/_pb_users_auth_/${user?.id}/${user?.avatar}`;
                }}>
                Cancel</button
                >
            </div>
        </form>
    {:else}
        <div class="flex md:flex-row flex-col items-center justify-center gap-5">
            <div class="md:w-20 md:h-20 w-36 h-36 rounded-full overflow-hidden">
                <img
                    src={`${PUBLIC_POCKETBASE_URL}/api/files/_pb_users_auth_/${user?.id}/${user?.avatar}`}
                    alt="Avatar"
                    class="w-full h-full object-cover"
                />
            </div>
            <div class="flex flex-col items-center md:items-start justify-center">
                <h1 class="font-bold font-platypi text-xl">
                    {[user?.firstName || "", user?.lastName || ""].join(" ")}
                </h1>
                {#if user?.role.toLowerCase() === "tailor"}
                    <h1 class="text-xs text-warning font-bona-nova-sc">Tailor</h1>
                {:else if user?.role.toLowerCase() === "customer"}
                    <h1 class="text-xs text-info font-bona-nova-sc">Customer</h1>
                {:else if user?.role.toLowerCase() === "admin"}
                    <h1 class="text-xs text-error font-bona-nova-sc">Admin</h1>
                {/if}
                <h2 class="flex gap-2 items-center text-sm">
                    <Circle class="w-2 h-2 fill-black" />
                    {user.username}
                    {#if user.email}
                        <Circle class="w-2 h-2 fill-black" />
                        {user.email}
                    {/if}
                </h2>
            </div>
        </div>
        <button on:click={() => (isEditing = true)} class="{pocketbase.authStore.model?.id !== user.id ? "hidden": "block"} flex items-center justify-center btn btn-sm max-md:w-full">
            <Edit class="w-4 h-4" /> Edit
        </button>
    {/if}
</div>

<style>
    .parent:hover .child {
        opacity: 1;
    }
</style>