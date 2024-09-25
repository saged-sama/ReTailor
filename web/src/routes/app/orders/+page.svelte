<script lang="ts">
    import { PUBLIC_API_URL } from "$env/static/public";
    import { getCurrentUser } from "$lib";
    import OrderTile from "$lib/components/productStuff/orderTile.svelte";
    import { currentUser } from "$lib/stores/currentUser";
    import { Plus } from "lucide-svelte";
    import { onMount } from "svelte";
    
    let orderMode = "myOrders";
    
    let orders: any[] = [];

    const getOrders = async () => {
        const res = await fetch(`${PUBLIC_API_URL}/api/collections/forder/records?userId=${$currentUser.id}`);
        const data = await res.json();
        console.log(data);
        orders = [...data];
    }

    onMount( async () => {
        await getCurrentUser();
        await getOrders();
    })
</script>

<div class="flex flex-col w-full m-2 md:m-5 items-center">
    <div class="flex gap-2 max-md:flex-col items-center w-1/2 justify-between max-md:w-full border-b border-gray-700 pb-0">
        <div class="flex p-3 gap-0 pb-0">
            <button class="btn rounded-b-none {orderMode === "myOrders" ? "bg-gray-700": "btn-ghost"}">
                My Orders
            </button>
            <button class="btn rounded-b-none {orderMode === "toMe" ? "bg-gray-700": "btn-ghost"}">
                To me
            </button>
            <button class="btn rounded-b-none {orderMode === "completed" ? "bg-gray-700": "btn-ghost"}">
                Completed
            </button>
        </div>
        <div class="flex pb-0">
            <a class="btn md:btn-primary btn-sm md:rounded-b-none pb-0" href="/app/orders/new"> <Plus class="w-4 h-4"/> New Order</a>
        </div>
    </div>
    <div class="flex flex-col w-full md:w-1/2 items-center p-5 rounded-md">
        <OrderTile {orders}/>
    </div>
</div>