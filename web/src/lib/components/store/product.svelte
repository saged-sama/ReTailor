<script lang="ts">
    import { onMount } from "svelte";
    import ProductTile from "./productTile.svelte";
    import { CircleChevronLeft, CircleChevronRight } from "lucide-svelte";
    import { updateBreakpoint } from "$lib/utils/breakpoints";

    export let products: any[] = [];
    let productGrid: HTMLElement;
    let scrollQuotient = 4;
    let start = 0, end = scrollQuotient;
    let scrollPosition = 0;
    let breakpoint = '';

    const scrollRight = () => {
        end = Math.min(end + scrollQuotient, products.length);
        start = Math.max(0, end - scrollQuotient);
        const scrollamount = productGrid.scrollWidth / products.length;
        console.log(end, start, products.length)

        productGrid.style.overflow = "auto";
        if(end === products.length) {
            scrollPosition = 2;
            productGrid.scrollTo({
                left: productGrid.scrollWidth,
                behavior: "smooth"
            })
        }
        else{
            scrollPosition = 1;
            productGrid.scrollBy({
                left: scrollamount * scrollQuotient,
                behavior: "smooth"
            });
        }
        productGrid.style.overflow = "hidden";
    };

    const scrollLeft = () => {
        start = Math.max(0, start - scrollQuotient);
        end = Math.min(start + scrollQuotient, products.length);
        const scrollamount = productGrid.scrollWidth / products.length;

        productGrid.style.overflow = "auto";
        if(start === 0) {
            scrollPosition = 0;
            productGrid.scrollTo({
                left: 0,
                behavior: "smooth"
            })
        }
        else{
            scrollPosition = 1;
            productGrid.scrollBy({
                left: -scrollamount * scrollQuotient,
                behavior: "smooth"
            });
        }
        productGrid.style.overflow = "hidden";
    };

    const updateBpoint = () => {
        breakpoint = updateBreakpoint();
        if(breakpoint === 'max-sm' || breakpoint === 'max-md' || breakpoint === 'max-lg' || breakpoint === 'max-xl') {
            scrollQuotient = 2;
        }
        else {
            scrollQuotient = 4;
        }
    }

    onMount(() => {
        updateBpoint();
        start = 0;
        end = scrollQuotient;
        window.addEventListener('resize', updateBpoint);
    })
</script>

{#if products.length > 0}
    <div class="relative flex h-full items-center justify-center">
        <button class="hidden md:block absolute z-40 -left-5 rounded-full transition-all duration-500 {scrollPosition === 2 || scrollPosition === 1? "opacity-100": "opacity-0"}" on:click={scrollLeft}>
            <CircleChevronLeft class="w-12 h-12 fill-black"/>
        </button>
        <div bind:this={productGrid} class="grid grid-flow-col max-md:grid-rows-2 items-center h-full overflow-auto xl:overflow-hidden py-3 gap-2">
            {#each products as product}
                <ProductTile {product} />
            {/each}
        </div>
        
        <button class="hidden md:block absolute z-40 -right-5 rounded-full transition-all duration-500 {scrollPosition === 0 || scrollPosition === 1? "opacity-100": "opacity-0"}" on:click={scrollRight}>
            <CircleChevronRight class=" w-12 h-12 fill-black"/>
        </button>
    </div>
{:else}
    <div class="flex items-center justify-center gap-2 font-platypi">
        <p>Sorry...We got nothing</p>
    </div>
{/if}