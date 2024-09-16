<script lang="ts">
    import Product from "$lib/components/store/product.svelte";
    import Sidebar from "$lib/components/store/sidebar/sidebar.svelte";
    import { springbase } from "$lib/utils/springbase";
    import { Logs } from "lucide-svelte";
    import { onMount } from "svelte";

    let products: any[] = [];
    let fetched = false;

    const fetchProducts = async (lower: number, upper: number) => {
        try {
            const newproducts = await springbase.collection("productView").getList(lower, upper);
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

<div class="flex max-md:flex-col max-mditems-center justify-center w-full h-full md:p-5 lg:p-8">
    <div class="max-md:hidden w-1/5 p-2 border-r border-gray-700">
        <Sidebar />
    </div>

    <div class="md:hidden drawer p-1 z-30">
        <input id="my-drawer" type="checkbox" class="drawer-toggle" />
        <div class="drawer-content">
          <!-- Page content here -->
          <label for="my-drawer" class="flex items-center gap-1 font-platypi drawer-button"> <Logs /> Filters </label>
        </div>
        <div class="drawer-side h-full mt-24">
          <label for="my-drawer" aria-label="close sidebar" class="drawer-overlay"></label>
          <Sidebar />
        </div>
      </div>

    <div class="flex flex-col gap-10 px-5 w-full md:w-4/5">
        <div class="flex flex-col h-full gap-2">
            <h1 class="text-lg md:text-2xl font-bold font-platypi p-2 border-b-2 border-gray-800">Brand New 🔥🔥</h1>
            {#if fetched}
                <Product {products}/>
            {:else}
                <div class="flex gap-1 items-center">Wait a second<span class="loading loading-dots loading-lg"></span></div>
            {/if}
        </div>

        <div class="flex flex-col h-full gap-2">
            <h1 class="text-lg md:text-2xl font-bold font-platypi p-2 border-b-2 border-gray-800">Most Popular 🐙</h1>
            {#if fetched}
                <Product {products}/>
            {:else}
                <div class="flex gap-1 items-center">Wait a second<span class="loading loading-dots loading-lg"></span></div>
            {/if}
        </div>

        <div class="flex flex-col h-full gap-2">
            <h1 class="text-lg md:text-2xl font-bold font-platypi p-2 border-b-2 border-gray-800">Trending 😎</h1>
            {#if fetched}
                <Product {products}/>
            {:else}
                <div class="flex gap-1 items-center">Wait a second<span class="loading loading-dots loading-lg"></span></div>
            {/if}
        </div>
    </div>

</div>