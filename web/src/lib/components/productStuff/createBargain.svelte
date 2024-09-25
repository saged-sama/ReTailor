<script lang="ts">
    import BargainAndMaterial from "./bargainAndMaterial.svelte";

    export let forder: any = {};
    let bargain = {
        id: "",
        materialSuggestions: "",
        customerProposal: 0,
        tailorProposal: 0
    }

    const createBargain = () => {
        console.log(bargain);
    }

</script>

<BargainAndMaterial bind:bargain={bargain} customer_edits={false} tailor_edits={true}/>
<div class="p-4">
    <button class="btn btn-primary btn-sm" on:click={createBargain}>Create Bargain</button>
</div>