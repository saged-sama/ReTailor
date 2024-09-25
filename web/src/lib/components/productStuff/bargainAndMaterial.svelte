<script lang="ts">
    import { Remarkable } from "remarkable";
    export let customer_edits: boolean = false;
    export let tailor_edits: boolean = false;

    const md = new Remarkable();

    export let bargain: any;

    $: htmlContent = md.render(bargain.materialSuggestions);
</script>

{#if bargain}
    <div class="flex flex-col w-full">
        <div class="flex">
            <h1 class="font-bona-nova p-3 text-xl">Bargain</h1>
        </div>

        <div class="flex flex-col">
            {#if tailor_edits}
                <textarea name="materialSuggestions" bind:value={bargain.materialSuggestions} class="textarea textarea-bordered textarea-sm"></textarea>
            {/if}

            <div class="prose prose-h1:text-2xl prose-h2:text-xl prose-p:text-sm text-gray-300 font-bona-nova mt-4">
                {#if tailor_edits}
                    <h1 class="text-xl font-bona-nova p-3"> Preview </h1>
                {/if}
                <div class="p-2">
                    {#if htmlContent}
                        {@html htmlContent}
                    {:else}
                        [Markdown previews will be shown here]
                    {/if}
                </div>
            </div>
        </div>
        
        <div class="flex gap-3 max-md:flex-col md:justify-between">
            <div class="w-1/2 max-md:w-full">
                <label class="flex flex-col gap-2 w-full">
                    <h1 class="">Customer Bargain</h1>
                    {#if customer_edits}
                        <input type="number" class="input input-bordered input-sm w-full" placeholder="Bargain" bind:value={bargain.customerProposal} />
                    {:else}
                        <div class="border border-gray-700 p-2 rounded-md w-full">{bargain.customerProposal}</div>
                    {/if}
                </label>
            </div>
            <div class="w-1/2 max-md:w-full">
                <label class="flex flex-col gap-2 w-full">
                    <h1 class="">Tailor Bargain</h1>
                    {#if tailor_edits}
                        <input type="number" class="input input-bordered input-sm w-full" placeholder="Bargain" bind:value={bargain.tailorProposal} />
                    {:else}
                        <div class="border border-gray-700 p-2 rounded-md w-full">{bargain.tailorProposal}</div>
                    {/if}
                </label>
            </div>
        </div>
    </div>
{:else}
<div class="flex items-center justify-center p-5">
    <span class="loading loading-sm"></span> Please wait
</div>
{/if}