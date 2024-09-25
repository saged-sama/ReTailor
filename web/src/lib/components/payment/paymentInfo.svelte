<script lang="ts">
    import { Edit } from "lucide-svelte";

    let paymentMethod = "card";

    export let isEditing: boolean = false;
    export let edits: boolean = false;

    export let cardInfo: any = {
        id: "",
        cardNumber: "",
        cardHolderName: "",
        expiryDate: "",
        cvv: ""
    }
</script>

<div class="flex justify-between">
    <h1 class="font-bona-nova p-3 text-xl">
        Payment Information
    </h1>
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

<div class="flex gap-2 items-center w-full mb-5">
    <h1 class="text-sm">Choose a payment method</h1>
    <div>
        <select class="select select-bordered select-sm" bind:value={paymentMethod}>
            <option value="card">Credit card</option>
            <option value="paypal">paypal</option>
            <option value="cash">Cash on Delivery</option>
        </select>
    </div>
</div>

{#if paymentMethod === "card"}
    <div class="flex flex-col gap-3 w-full text-sm">
        <div class="w-full">
            <label class="flex flex-col gap-2 w-full">
                <span class="md:w-1/6">Card Number</span>
                {#if isEditing}
                    <input type="text" bind:value={cardInfo.cardNumber} class="input input-bordered input-sm w-5/6" placeholder="Card Number">
                {:else}
                    <div class="border border-gray-700 p-2 rounded-md w-5/6">{cardInfo.cardNumber}</div>
                {/if}
            </label>
        </div>

        <div class="w-full">
            <label class="flex flex-col gap-2 w-full">
                <span class="md:w-1/6">Card Holder</span>
                {#if isEditing}
                    <input type="text" bind:value={cardInfo.cardHolderName} class="input input-bordered input-sm w-5/6" placeholder="Card Holder's Name">
                {:else}
                    <div class="border border-gray-700 p-2 rounded-md w-5/6">{cardInfo.cardHolderName}</div>
                {/if}
            </label>
        </div>

        <div class="flex max-md:flex-col justify-between gap-3 h-full overflow-y-auto">
            <div class="md:w-1/2">
                <label class="flex gap-2 items-center w-full">
                    <span class="md:w-1/6">Expiry Date</span>
                    {#if isEditing}
                        <input type="text" bind:value={cardInfo.expiryDate} class="input input-bordered input-sm md:w-5/6" pattern="(0[1-9]|1[0-2])\/[0-9]{2}" maxlength="5" placeholder="MM/YY">
                    {:else}
                        <div class="md:w-5/6 border border-gray-700 p-2 rounded-md">{cardInfo.expiryDate}</div>
                    {/if}
                </label>
            </div>

            <div class="md:w-1/2">
                <label class="flex gap-2 items-center w-full">
                    <span class="">CVV</span>
                    {#if isEditing}
                        <input type="text" bind:value={cardInfo.cvv} class="input input-bordered input-sm w-full" placeholder="CVV">
                    {:else}
                        <div class="w-full border border-gray-700 p-2 rounded-md">{cardInfo.cvv}</div>
                    {/if}
                </label>
            </div>
        </div>
    </div>
{:else if paymentMethod === "paypal"}
    <div>
        <p class="text-sm">You will be redirected to paypal to complete the payment</p>
    </div>

{:else if paymentMethod === "cash"}
    <div>
        <p class="text-sm">You will pay when the product is delivered to you</p>
    </div>
{/if}