<script lang="ts">
    import { getCurrentUser } from "$lib";
    import Measurements from "$lib/components/measurements/measurements.svelte";
    import { currentUser } from "$lib/stores/currentUser";
    import { springbase } from "$lib/utils/springbase";
    import { onMount } from "svelte";
    
    let measurements: any[] = [];
    let loading = false;
    let currrentMeasurements: any = {
        id: "",
        gender: "",
        age: "",
        weight: "",
        height: "",
        neck: "",
        shoulder: "",
        chest: "",
        upperBust: "",
        lowerBust: "",
        armhole: "",
        sleeveLength: "",
        bicep: "",
        elbow: "",
        wrist: "",
        backWidth: "",
        frontLength: "",
        backLength: "",
        verticalTrunk: "",
        bustToWaist: "",
        waist: "",
        hip: "",
        rise: "",
        waistToKnee: "",
        knee: "",
        calf: "",
        inseam: "",
        outseam: "",
        waistToHem: ""
    };

    const getMeasurements = async () => {
        const customerId = $currentUser.customerId;
        const res = await springbase.collection("measurements").getList(1, 5, {
            filter: `customer_id = ${customerId}`
        });
        measurements = [...measurements, ...res.content];
    }

    const saveMeasurements = async () => {
        loading = true;
        const res = await springbase.collection("measurements").create(currrentMeasurements);
        // console.log(res);
        currrentMeasurements = {...res};
        loading = false;
    }

    const updateMeasurements = async () => {
        loading = true;
        const res = await springbase.collection("measurements").update(currrentMeasurements.id, currrentMeasurements);
        // console.log(res);
        currrentMeasurements = {...res};
        loading = false;
    }

    const deleteMeasurements = async () => {
        loading = true;
        const res = await springbase.collection("measurements").delete(currrentMeasurements.id);
        // console.log(res);
        measurements = { ...res };
        currrentMeasurements = {...measurements[0]};
        loading = false;
    }

    onMount(async () => {
        await getCurrentUser();
        await getMeasurements();
        if(measurements.length > 0){
            currrentMeasurements = {...measurements[0]};
        }
    });
</script>

<div class="flex flex-col gap-5 p-2 m-2 md:p-5 md:m-5 shadow-xl">
    <Measurements gridCols={4} bind:measurements={currrentMeasurements} />
    <div class="w-full flex justify-end gap-2">
        {#if !currrentMeasurements.id}
            <button class="btn btn-sm btn-primary" on:click={saveMeasurements}>
                {#if loading}
                    <span class="loading loading-spinner loading-sm"></span>
                {:else}
                    Save
                {/if}
            </button>
        {:else}
            <button class="btn btn-sm btn-primary" on:click={updateMeasurements}>
                {#if loading}
                    <span class="loading loading-spinner loading-sm"></span>
                {:else}
                    Update
                {/if}
            </button>
        {/if}
        <!-- <button class="btn btn-sm btn-warning" on:click={deleteMeasurements}>
            {#if loading}
                    <span class="loading loading-spinner loading-sm"></span>
            {:else}
                Reset
            {/if}
        </button> -->
    </div>
</div>