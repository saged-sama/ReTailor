<script>

    import { goto } from "$app/navigation";

</script>
<div class="flex items-center justify-end gap-2">
    <button class="btn btn-success">Publish Order</button>
    <button class="btn btn-primary">Confirm Current Bargain</button>
    <button class="btn btn-warning">Cancel Current Bargain</button>
    <button class="btn btn-error" on:click={() => {
        goto("/app/orders")
    }}>Cancel Order</button>
</div>