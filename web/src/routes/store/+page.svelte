<script lang="ts">
    import Product from "$lib/components/store/product.svelte";
    import Sidebar from "$lib/components/store/sidebar/sidebar.svelte";
    import { pocketbase } from "$lib/utils/pocketbase";
    import { onMount } from "svelte";

    let products: any[] = [];
    let fetched = false;

    const fetchProducts = async (lower: number, upper: number) => {
        try {
            const newproducts = await pocketbase.collection("productView").getList(lower, upper);
            products = [...products, ...newproducts.items];
        } catch (err) {
            console.error("Could not fetch products: ", err);
        }
    };
    
    onMount(async () => {
        await fetchProducts(1, 100);
        fetched = true;
    });
</script>

<div class="flex justify-center w-full h-full md:p-5 lg:p-8">
    <div class="max-md:hidden w-1/5 p-2 border-r border-gray-700">
        <Sidebar />
    </div>

    <div class="flex flex-col gap-10 w-4/5 md:px-5">
        <div class="flex flex-col h-full gap-2">
            {#if fetched}
                <h1 class="text-lg md:text-2xl font-bold font-platypi p-2 border-b-2 border-gray-800">Brand New 🔥🔥</h1>
                <Product {products}/>
            {:else}
                Wait a second<span class="loading loading-dots loading-lg"></span>
            {/if}
        </div>

        <div class="flex flex-col h-full gap-2">
            {#if fetched}
                <h1 class="text-lg md:text-2xl font-bold font-platypi p-2 border-b-2 border-gray-800">Most Popular 🐙</h1>
                <Product {products}/>
            {:else}
                Wait a second<span class="loading loading-dots loading-lg"></span>
            {/if}
        </div>

        <div class="flex flex-col h-full gap-2">
            {#if fetched}
                <h1 class="text-lg md:text-2xl font-bold font-platypi p-2 border-b-2 border-gray-800">Trending 😎</h1>
                <Product {products}/>
            {:else}
                Wait a second<span class="loading loading-dots loading-lg"></span>
            {/if}
        </div>
    </div>

</div>