<script lang="ts">
    import { currentUser } from "$lib/stores/currentUser";
    import { Edit } from "lucide-svelte";
    export let isEditing: boolean = false;
    export let edits: boolean = false;

    export let deliveryInformation: any = {
        id: $currentUser.id || "",
        receiverName: $currentUser.name || "",
        email: $currentUser.email || "",
        phone: $currentUser.phone || "",
        address: $currentUser.address || ""
    };
    console.log("Delivery Information", deliveryInformation);
</script>
<div class="flex flex-col w-full gap-3">
    <div class="flex justify-between">
        <h1 class="font-bona-nova p-3 text-xl">Delivery Information</h1>
        {#if edits}
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

    <div class="flex max-md:flex-col gap-3 md:justify-between text-sm">
        <label class="flex flex-col gap-2 w-full">
            <h1 class="">Receiver Name</h1>
            {#if isEditing}
                <input type="text" class="input input-bordered input-sm w-full" placeholder="Name" bind:value={deliveryInformation.receiverName}>
            {:else}
                <div class="border border-gray-700 p-2 rounded-md w-full">{deliveryInformation.receiverName}</div>
            {/if}
        </label>
        <label class="flex flex-col gap-2 w-full">
            <h1>Email</h1>
            {#if isEditing}
                <input type="email" class="input input-bordered input-sm w-full" placeholder="Email" bind:value={deliveryInformation.email}>
            {:else}
                <div class="border border-gray-700 p-2 rounded-md w-full">{deliveryInformation.email}</div>
            {/if}
        </label>
    </div>

    <div class="flex max-md:flex-col gap-3 md:justify-between text-sm">
        <label class="flex flex-col gap-2 w-full">
            <h1 class="">Phone Number</h1>
            {#if isEditing}
                <input type="text" class="input input-bordered input-sm w-full" placeholder="phone" bind:value={deliveryInformation.phone}>
            {:else}
                <div class="border border-gray-700 p-2 rounded-md w-full">{deliveryInformation.phone}</div>
            {/if}
        </label>
        <label class="flex flex-col gap-2 w-full">
            <h1>Delivery Address</h1>
            {#if isEditing}
                <textarea class="textarea textarea-bordered textarea-sm w-full" placeholder="Shipping Address" bind:value={deliveryInformation.address}></textarea>
            {:else}
                <div class="border border-gray-700 min-h-20 p-2 rounded-md w-full">{deliveryInformation.address}</div>
            {/if}
        </label>
    </div>
</div>