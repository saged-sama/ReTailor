<script lang="ts">
    import { PUBLIC_API_URL } from "$env/static/public";
    import { Circle } from "lucide-svelte";

    export let orders: any[] = [];
</script>

<div class="flex flex-col w-full gap-3">
    {#each orders as order, index}
        <a class="flex justify-between border border-gray-700 rounded-md p-3" href="/app/orders/{order.id}">
            <div class="flex gap-3">
                <div class="w-20 h-20 rounded-lg">
                    <img src={`${PUBLIC_API_URL}/api/files/orders/${order.id}/${order.images[0]}`} alt={order.name} class="w-full h-full object-cover rounded-lg" />
                </div>
                <div class="flex flex-col gap-3">
                    <div class="prose prose-h1:text-xl prose-h2:text-lg prose-p:sm font-bona-nova text-xl">
                        {order.title}
                    </div>
                    <div class="flex md:items-center max-md:flex-col gap-2">
                        <div class="text-info">
                            {order?.customer?.name}
                        </div>
                        <div class="max-md:hidden block">
                            <Circle class={"w-3 h-3 fill-black"}/>
                        </div>
                        <div>
                            {order.category}
                        </div>
                        <div class="max-md:hidden block">
                            <Circle class={"w-3 h-3 fill-black"}/>
                        </div>
                        <div class="text-warning">
                            {order.initialBargain} <span class=" font-shadows-into-light">tk</span>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <div>
                    {order.status}
                </div>
            </div>
        </a>
    {/each}
</div>