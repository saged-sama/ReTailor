<script lang="ts">
    import { CircleChevronLeft, CircleChevronRight, PlusCircle, X } from "lucide-svelte";

    export let images: any[] = [];
    export let isEditing: boolean = false;

    let currentImage = 0;

    const handleFileUpload = (e: any) => {
        const files = e.target.files;
        for (let i = 0; i < files.length; i++) {
            if(images.length == 5) {
                alert("You are only allowed to upload 5 images");
                break;
            }
            images = [...images, {
                name: files[i].name,
                file: files[i],
                path: URL.createObjectURL(files[i] as Blob)
            }];
        }
    }

    const removeImage = (index: number) => {
        images = images.filter((_, i) => i !== index);
        if (currentImage === index) {
            currentImage = 0;
        }
    }
</script>

<div class="flex max-md:flex-col-reverse w-full h-full items-center justify-center p-3 gap-3">
    <div class="flex items-center md:w-1/6 w-full h-full">
        <div class="grid grid-cols-1 max-md:grid-cols-5 w-full gap-2">
            {#each images as image, i}
                <button class="w-full h-16 relative rounded-lg {currentImage === i ? "border border-info": ""} hover:opacity-50 parent hover:scale-105" on:click={() => {
                    currentImage = i;
                }}>
                    <img
                        class="w-full h-full object-cover" 
                        src={image?.path}
                        alt={image?.name}
                    >

                    {#if isEditing}
                        <button class="absolute top-0 right-0 bg-primary-content rounded-full hover:text-red-400 hover:scale-125 child" on:click={() => removeImage(i)}> <X /> </button>
                    {/if}

                </button>
            {/each}
            
            {#if images.length < 5 && isEditing}
                <label class=" h-16 w-full flex items-center justify-center bg-neutral rounded-lg cursor-pointer hover:scale-105">
                    <input type="file" class="hidden" accept="image/*" multiple on:change={handleFileUpload}>
                    <PlusCircle />
                </label>
            {/if}
        </div>
    </div>
    <div class="relative flex items-center justify-center md:w-5/6 w-full h-full">
        {#if images.length > 0}
            <img
                class="w-full h-96 object-contain" 
                src={images[currentImage]?.path}
                alt={images[currentImage]?.name}
            >

            <button class="{currentImage === 0 ? "hidden": "block"} absolute left-1 bg-primary-content rounded-full" on:click={() => {
                currentImage = currentImage - 1;
            }}>
                <CircleChevronLeft />
            </button>

            <button class="{currentImage === images.length - 1 ? "hidden": "block"} absolute right-1 bg-primary-content rounded-full" on:click={() => {
                currentImage = currentImage + 1;
            }}>
                <CircleChevronRight />
            </button>
        {:else}
            <div class="text-xl h-96 max-md:text-sm flex items-center justify-center text-warning font-bona-nova">No Images Selected Yet</div>
        {/if}
    </div>
</div>

<style>
    .parent:hover .child {
        opacity: 1;
    }
</style>