<script lang="ts">
    import { PUBLIC_API_URL } from "$env/static/public";
    import TailorTile from "$lib/components/tailor/tailorTile.svelte";
    import { Search } from "lucide-svelte";
    import { onMount } from "svelte";

    let tailors: any[] | null = null;
    let pattern: string = "";

    const getCustomersOfTailors = async (tailors: any[]) => {
        const updatedTailors = await Promise.all(
            tailors.map(async (tailor) => {
                const res = await fetch(`${PUBLIC_API_URL}/api/collections/customers/records/${tailor.users.id}`);
                const customer = await res.json();
                return {
                    ...tailor,
                    customer: customer,
                };
            })
        );
        return updatedTailors;
    };


    const searchByPattern = async () => {
        const res = await fetch(`${PUBLIC_API_URL}/api/collections/tailors/records/verified?pattern=${pattern}`);
        let newTailors = await res.json();
        newTailors = [...await getCustomersOfTailors(newTailors)];
        tailors = [...newTailors];

        console.log(tailors);
    }

    onMount(async () => {
        const res = await fetch(`${PUBLIC_API_URL}/api/collections/tailors/records/verified?pattern=${pattern}`);
        let newTailors = await res.json();
        newTailors = [...await getCustomersOfTailors(newTailors)];
        tailors = [...newTailors];
    })
</script>

<div class="flex flex-col m-2 md:m-5 items-center">
    <div class="flex gap-2 max-md:flex-col items-center md:w-1/2 justify-between max-md:w-full border-b border-gray-700 pb-0">
        <div class="flex w-full p-3 gap-0 pb-0">
            <input type="text" placeholder="Search Tailors" class="input input-bordered w-full" bind:value={pattern} on:input={searchByPattern}>
        </div>
    </div>
    {#if tailors && tailors.length > 0}
        <div class="flex flex-col w-full md:w-1/2 items-center p-5 rounded-md">
            <TailorTile {tailors}/>
        </div>
    {:else}
        <div class="flex gap-3 p-10"><span class="loading loading-md"></span>Can't find anyone</div>
    {/if}
</div>