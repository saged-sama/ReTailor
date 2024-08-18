<script lang="ts">
    import { User } from "lucide-svelte";
    import { pocketbase } from "$lib/utils/pocketbase";

    let isLoggedIn = false;

    $: {
        isLoggedIn = pocketbase.authStore.isValid;
    }
</script>

{#if isLoggedIn}
    <a class="rounded-lg" href="/">
        <div class="rounded-full">
            <User />
        </div>
        <div>
            Username
        </div>
    </a>
    <a href="/logout" class="text-warning">
        Logout
    </a>
{:else}
    <a class="btn btn-primary btn-sm md:btn-md" href="/auth/register">
        Get Started
    </a>
{/if}