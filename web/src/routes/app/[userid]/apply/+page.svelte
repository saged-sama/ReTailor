<script lang="ts">
    import TailorApplicationForm from "$lib/components/tailorApplicationForm.svelte";
    import { page } from "$app/stores";

    $: currentUserId = $page.params.userid;
</script>

<div class="md:p-5">
    <TailorApplicationForm />
</div>