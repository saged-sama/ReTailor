<script lang="ts">
    import { PUBLIC_API_URL } from "$env/static/public";
    import { Edit, X } from "lucide-svelte";

    export let selectedtailors: any[] = [];

    export let isEditing: boolean = false;
    export let edits: boolean = false;
    export let orderingOptions: any = {
        title: "",
        
        category: "",
        privacy: "public",
        tailors: [],
        initialBargain: 0
    }
    
</script>

<div class="flex flex-col gap-3">
    <div class="flex justify-between">
        <h1 class="font-bona-nova p-3 text-xl">Order Options</h1>
        {#if edits}
            {#if !isEditing}
                <button class="btn btn-sm btn-ghost" on:click={() => isEditing = true}>
                    <Edit class="w-4 h-4"/> Edit
                </button>
            {:else}
                <button class="btn btn-sm btn-ghost" on:click={() => {
                    isEditing = false;
                }}>Done Editing</button>
            {/if}
        {/if}
    </div>

    <label class="flex flex-col gap-2 text-sm">
        <h1>Order Title</h1>
        {#if isEditing}
            <input type="text" class="input input-bordered input-sm rounded-md" placeholder="Title of the order" bind:value={orderingOptions.title}/>
        {:else}
            <div class="border border-gray-700 p-2 rounded-md">{orderingOptions.title || "No title set"}</div>
        {/if}
    </label>

    <label class="flex flex-col gap-2 text-sm">
        <h1>Order Category</h1>
        {#if isEditing}
            <input type="text" class="input input-bordered input-sm rounded-md" placeholder="Category of the order" bind:value={orderingOptions.category}/>
        {:else}
            <div class="border border-gray-700 p-2 rounded-md">{orderingOptions.category || "No category Set"}</div>
        {/if}
    </label>

    {#if isEditing}
        <label class="flex flex-col gap-2 text-sm">
            <h1>Order Privacy</h1>
            <select class="select select-bordered select-sm text-sm rounded-md" bind:value={orderingOptions.privacy}>
                <option value="public">Public</option>
                <option value="tailors">Specific Tailors</option>
            </select>
        </label>
    {:else}
        <div class="flex flex-col gap-2 text-sm">
            <h1>Order Privacy</h1>
            <div class="border border-gray-700 p-2 rounded-md">{orderingOptions.privacy || "No privacy set"}</div>
        </div>
    {/if}

    {#if orderingOptions.privacy === "tailors"}
        <label class="flex flex-col gap-2 text-sm">
            <h1>Search Tailors</h1>
            <input type="text" class="input input-bordered input-sm rounded-md" placeholder="Search Tailors"/>
        </label>

        <div class="flex flex-col">
            {#each selectedtailors as tailor}
                <div class="flex justify-between">
                    <div class="flex gap-3">
                        <img src="{PUBLIC_API_URL}/api/files/users/{tailor.userId}/{tailor.avatar}" alt="{tailor.name}" class="w-10 h-10">
                        <h1 class="font-bona-nova text-accent">{tailor.name}</h1>
                    </div>
                    <div>
                        <X class="w-4 h-4 text-red-400"/>
                    </div>
                </div>
            {/each}
        </div>
    {/if}

    <label class="flex flex-col gap-2 text-xl">
        <h1 class="text-sm">Initial Bargain</h1>
        <div class="flex items-center gap-2">
            {#if isEditing}
                <input type="number" min="0" max="100000" class="input input-bordered text-warning input-xl md:w-1/2 w-full" placeholder="Example: 8000" bind:value={orderingOptions.initialBargain}>
            {:else}
                <div class="border border-gray-700 text-xl text-warning p-2 rounded-md">{orderingOptions.initialBargain}</div>
            {/if}
            <h1>tk</h1>
        </div>
        <h1 class="text-xs font-gupter"><span class="text-red-400">*</span> All bargains are made in BDT </h1>
    </label>
</div>