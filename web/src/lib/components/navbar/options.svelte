<script lang="ts">
    import { pocketbase } from "$lib/utils/pocketbase";
    import Avatar from "./avatar.svelte";
    import { PUBLIC_POCKETBASE_URL } from "$env/static/public";

    let isLoggedIn = false;
    let user = pocketbase.authStore.model;

    $: {
        isLoggedIn = pocketbase.authStore.isValid;
        user = pocketbase.authStore.model;
    }
</script>

{#if isLoggedIn}
    <a class="md:hidden rounded-lg" href={`/app/${user?.id}/profile`}>
        <div class="rounded-full">
            <Avatar user={user} size={5}/>
        </div>
        <div>
            {[user?.firstName || "", user?.lastName || ""].join(" ")}
        </div>
    </a>
    <a href="/auth/logout" class="md:hidden text-warning"> Logout </a>
    <details class="md:block hidden dropdown dropdown-end">
        <summary class="btn btn-ghost rounded-full p-0">
            <div class="w-10 h-10 rounded-full overflow-hidden">
                <img
                    src={`${PUBLIC_POCKETBASE_URL}/api/files/_pb_users_auth_/${user?.id}/${user?.avatar}`}
                    alt="Avatar"
                    class="w-full h-full object-cover"
                />
            </div>
        </summary>
        <ul
            class="menu dropdown-content rounded-md z-[1] w-52 p-2 gap-2 shadow-xl bg-primary-content text-sm"
        >
            <li>
                <a href={`/app/${user?.id}/profile`}>
                    <Avatar user={user} size={5} />
                    {[user?.firstName || "", user?.lastName || ""].join(" ")}
                </a>
            </li>
            <li>
                <button
                    on:click={() => pocketbase.authStore.clear()}
                    class="text-warning"
                >
                    Logout
                </button>
            </li>
        </ul>
    </details>
{:else}
    <a class="btn btn-primary btn-sm md:btn-md" href="/auth/register">
        Get Started
    </a>
{/if}
