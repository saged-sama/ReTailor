<script lang="ts">
    export let description: string = "";
    import { Edit } from "lucide-svelte";
    import { Remarkable } from "remarkable";

    const md = new Remarkable();

    export let isEditing: boolean = false;
    export let edits: boolean = false;

    $: htmlContent = md.render(description);
</script>

<div class="w-full">
    <label for="description" class="flex flex-col gap-2 text-base-content w-full">
        <div class="flex justify-between">
            {#if edits}
                <h1 class="text-xl font-bona-nova p-3">Description</h1>
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

        <div class="prose prose-h1:text-2xl prose-h2:text-xl prose-p:text-sm text-gray-300 font-bona-nova mt-4">
            {#if edits}
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

        {#if isEditing}
            <textarea
                id="description"
                class="w-full textarea textarea-bordered rounded-lg p-2 pb-10"
                placeholder="Describe your order thoroughly so that everyone can understand"
                bind:value={description}
            ></textarea>

            <h1 class="w-full font-gupter text-xs">
                <span class="text-red-400">*</span> Description is markdown supported
            </h1>
        {/if}
    </label>
</div>
