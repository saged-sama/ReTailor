<script lang="ts">
    import { PUBLIC_API_URL } from "$env/static/public";
    import { User } from "lucide-svelte";
    export let user: any;
    export let size: number = 10;
    export let rounded: string = "full";
    let classs;
    
    $: classs = `w-${size} h-${size} rounded-${rounded} overflow-hidden`;
</script>

{#if user?.avatar}
    <div class={classs}>
        <img
            src={`${PUBLIC_API_URL}/api/files/_pb_users_auth_/${user?.id}/${user?.avatar}`}
            alt="Avatar"
            class="w-full h-full object-cover"
        />
    </div>
{:else}
    <User class="w-4 h-4" />
{/if}
