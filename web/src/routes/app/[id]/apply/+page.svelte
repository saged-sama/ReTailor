<script lang="ts">

    import { pocketbase } from "$lib/utils/pocketbase";
    import { onMount } from "svelte";

    let user: any;

    onMount(() => {
        user = pocketbase.authStore.model;
        console.log(user);
    });
</script>
<button class="btn btn-primary" on:click={() => {
    pocketbase.collection("users").update(user.id, { role: "TAILOR" });
}}>Change to Tailor</button>