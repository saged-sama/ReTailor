<script lang="ts">
    import { PUBLIC_POCKETBASE_URL } from "$env/static/public";
    import { cart } from "$lib/stores/cart";
    import { ShoppingBasket } from "lucide-svelte";

    export let product: any;
    console.log(product)
    
    let imageurl: string = `${PUBLIC_POCKETBASE_URL}/api/files/product/${product.id}/${product.images}`;

    const addToCart = () => {
        cart.update(value => {
            return [...value, product]; 
        });
    }
</script>

<div class="flex flex-col rounded-md bg-base-100 h-full w-64 shadow-xl hover:scale-105 transition-all duration-300">
    <a class="relative w-full overflow-hidden h-64 bg-black flex items-center justify-end"
        style={`background-image: url(${imageurl}); background-size: cover; background-position: center;`}
        href={`/store/product/${product.id}`}>
        <div class="absolute inset-0 bg-black opacity-70"></div>
        <img src={imageurl} alt={product.name} class="hover:scale-110 transition-all duration-300 z-10">
        <div class="absolute bottom-0 right-0 text-sm bg-primary-content rounded-none font-platypi font-bold p-2 z-10">
            <div class="flex gap-2"><h1>Tk</h1> <h1 class="text-warning">{product.price}</h1></div>
        </div>
    </a>
    <div class="flex text-xs flex-col px-2 py-1 gap-2">
        <a class=" text-sm h-11 text-ellipsis overflow-hidden font-bold font-platypi hover:underline" href={`/store/product/${product.id}`} title={product.name}>{product.name}</a>
        <div class="flex flex-col gap-1 bg-primary-conte    nt border-2 border-gray-800 p-2 rounded-md">
            <div class="flex gap-1 h-8 overflow-clip" title={product.description}><h1 class="font-bold text-ellipsis font-platypi">Description:</h1> {product.description}</div>
            <div class="flex gap-1"><h1 class="font-bold font-platypi">Tailor:</h1> <a class="hover:text-primary" href={`/tailor/${product.tailorId}`}> {product.firstName} {product.lastName}</a></div>
            <div class="flex gap-1"><h1 class="font-bold font-platypi">Available:</h1> <h1 class="{product.stock === 0 ? "text-error": ""}">{product.stock ? `${product.stock} pcs` : "Out of Stock"}</h1> </div>
        </div>
        <div class="flex gap-2 justify-end border-t-2 border-gray-800 p-1">
            <button class="btn btn-ghost btn-sm" on:click={addToCart}><ShoppingBasket class="w-4 h-4"/> Add to Cart</button>
            <button class="btn btn-primary hover:btn-ghost btn-sm">Buy Now</button>
        </div>
    </div>
</div>
