<script lang="ts">
    import { PUBLIC_API_URL } from "$env/static/public";

    let currentImage = 0;

    export let images: any[] = [];
</script>

<div class="flex w-full h-full max-md:flex-col-reverse gap-2">
    <div class="md:w-1/6">
        <div class="grid grid-cols-1 max-md:grid-cols-5 gap-2">
            {#each images as image, i}
                <button class="{currentImage === i ? "border border-info": ""}" on:click={() => {
                    currentImage = i;
                }}>
                    <img
                    class="w-full h-20 object-cover" 
                    src={`${PUBLIC_API_URL}/api/files/${image}/${image}/${image}`}
                    alt={`${image}`}
                >
                </button>
            {/each}
        </div>
    </div>
    <div class="md:w-5/6">
        <img 
            class="w-full h-full object-contain" 
            src={`${PUBLIC_API_URL}/api/files/${images[currentImage]}/${images[currentImage]}/${images[currentImage]}`}
            alt={`${images[currentImage]}`}
        >
    </div>
</div>