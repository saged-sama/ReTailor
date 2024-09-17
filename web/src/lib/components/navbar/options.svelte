<script lang="ts">
    import { springbase } from "$lib/utils/springbase";
    import Avatar from "./avatar.svelte";
    import { PUBLIC_API_URL } from "$env/static/public";
    import { currentUser, resetCurrentUser } from "$lib/stores/currentUser";
    import { goto } from "$app/navigation";
    let user = $currentUser;

    $: {
        user = $currentUser;
    }
</script>

{#if user.id}
    <a class="md:hidden rounded-lg" href={`/app/${user?.id}/profile`}>
        <div class="rounded-full">
            <Avatar user={user} size={5}/>
        </div>
        <div>
            {user?.name}
        </div>
    </a>
    <button on:click={() => {
        springbase.authStore.clear();
        resetCurrentUser();
        goto("/");
    }} class="md:hidden text-warning"> Logout </button>
    <details class="md:block hidden dropdown dropdown-end">
        <summary class="btn btn-ghost rounded-full p-0">
            <div class="w-10 h-10 rounded-full overflow-hidden">
                <img
                    src={`${PUBLIC_API_URL}/api/files/users/${user?.id}/${user?.avatar}`}
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
                    {user.name}
                </a>
            </li>
            <li>
                <button
                    on:click={() => {
                        springbase.authStore.clear();
                        resetCurrentUser();
                        goto("/");
                    }}
                    class="text-warning"
                >
                    Logout
                </button>
            </li>
        </ul>
    </details>
{:else}
    <div class="flex md:flex-row-reverse gap-2 w-full">
        <a class="btn btn-primary btn-sm md:btn-md max-md:w-1/2" href="/auth/register">
            Get Started
        </a>
        <a class="btn btn-neutral btn-sm md:btn-md w-1/2" href="/auth/login">Login</a>
    </div>
{/if}
