<script lang="ts">
    import { onMount } from "svelte";
    import BargainAndMaterial from "./bargainAndMaterial.svelte";
    import { getCurrentUser } from "$lib";
    import { currentUser } from "$lib/stores/currentUser";
    import { PUBLIC_API_URL } from "$env/static/public";

    export let forder: any = {};

    let bargains: any[] = [
        {
            id: "",
            tailor: "John Doe",
            materialSuggestions: "# Need Some Dingo Pelts\nI might need some dingo pelts",
            customerProposal: "1293",
            tailorProposal: "4000"
        },
        {
            id: "",
            tailor: "Magnus Carlsen",
            materialSuggestions: "This is a material suggestion",
            customerProposal: "1293",
            tailorProposal: "4000"
        },
        {
            id: "",
            tailor: "Alireza Firoza",
            materialSuggestions: "This is a material suggestion",
            customerProposal: "1293",
            tailorProposal: "4000"
        }
    ];

    onMount(async () => {
        await getCurrentUser();
        if($currentUser.id === forder.userId.id){
            const res = await fetch(`${PUBLIC_API_URL}/api/collections/bargain/forder/${forder.id}`);
            bargains = await res.json();
        }
    });
</script>

{#if bargains}<div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
    <div class="flex flex-col">
        <h1 class="font-bona-nova p-3 text-xl">Bargains</h1>
        <div class="flex flex-col gap-4">
            {#each bargains as bargain}
                <div class="border border-gray-700 p-3">
                    <BargainAndMaterial {bargain} customer_edits={true} tailor_edits={false} />
                    <a class="flex gap-3 mb-3" href="/app/tailors/">
                        <span>Tailor:</span> <span class="text-warning">{bargain.tailor}</span>
                    </a>
                    <div class="flex gap-2">
                        <button class="btn btn-sm btn-primary">Accept Bargain</button>
                        <button class="btn btn-sm btn-secondary">Propose New Bargain</button>
                        <button class="btn btn-sm btn-warning">Cancel Bargain</button>
                    </div>
                </div>
            {/each}
        </div>
    </div>
    
</div>
{/if}