<script lang="ts">

    import { springbase } from "$lib/utils/springbase";
    import { onMount } from "svelte";

    let user: any;

    onMount(() => {
        user = springbase.authStore.model;
        console.log(user);
    });
</script>
<button class="btn btn-primary" on:click={() => {
    springbase.collection("users").update(user.id, { role: "TAILOR" });
}}>Change to Tailor</button>