<script lang="ts">
    import { Edit } from "lucide-svelte";

    export let isEditing: boolean = false;
    export let edits: boolean = false;
    export let gridCols = 4;
    export let measurements: any = {
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
</script>

<div class="flex justify-between">
    <h1 class="font-bona-nova p-3 text-xl">Measurements</h1>
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
<h1 class="text-xs p-3"> <span class="text-red-400">*</span> Please give all measurements in inches rounded to the nearest whole number or ceiling. Try to maintain accuracy. The measurements should not be the direct measurements of your body. They should be the precise approximations of your cloth's with respect to your body</h1>
<div class="grid grid-cols-1 md:grid-cols-{gridCols} gap-10 w-full">
    <!-- <div class="flex flex-col gap-10 md:w-1/2"> -->
        <div class="flex flex-col gap-2 w-full">
            <h1 class="font-gupter">General</h1>
            <div class="flex flex-col gap-2 w-full border border-gray-700 p-3 rounded-md">
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Gender</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.gender} class="input input-bordered input-sm w-4/6" name="gender" placeholder="Gender">
                    {:else}
                        <div class="w-4/6">{measurements.gender}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Age</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.age} class="input input-bordered input-sm w-4/6" name="age" min="0" placeholder="Age">
                    {:else}
                        <div class="w-4/6">{measurements.age}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Weight</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.weight} class="input input-bordered input-sm w-4/6" name="weight" placeholder="Weight in kg">
                    {:else}
                        <div class="w-4/6">{measurements.weight}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Height</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.height} class="input input-bordered input-sm w-4/6" name="height" placeholder="Height in Feet and Inches, eg 5'11''">
                    {:else}
                    <div class="w-4/6">{measurements.height}</div>
                    {/if}
                </label>
            </div>
        </div>

        <div class="flex flex-col gap-2 w-full">
            <h1 class="font-gupter">Neck, Bust and Chest</h1>
            <div class="flex flex-col gap-2 w-full border border-gray-700 p-3 rounded-md">
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Neck radius</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.neck} class="input input-bordered input-sm w-4/6" name="neck" placeholder="Neck radius">
                    {:else}
                        <div class="w-4/6">{measurements.neck}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Shoulder length</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.shoulder} class="input input-bordered input-sm w-4/6" name="shoulder" min="0" placeholder="Shoulder length">
                    {:else}
                        <div class="w-4/6">{measurements.shoulder}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Chest radius</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.chest} class="input input-bordered input-sm w-4/6" name="chest" placeholder="Chest radius">
                    {:else}
                        <div class="w-4/6">{measurements.chest}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Upper Bust</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.upperBust} class="input input-bordered input-sm w-4/6" name="upperBust" placeholder="Upper bust">
                    {:else}
                        <div class="w-4/6">{measurements.upperBust}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Lower Bust</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.lowerBust} class="input input-bordered input-sm w-4/6" name="lowerBust" placeholder="Upper bust">
                    {:else}
                        <div class="w-4/6">{measurements.lowerBust}</div>
                    {/if}
                </label>
            </div>
        </div>

        <div class="flex flex-col gap-2 w-full">
            <h1 class="font-gupter">Arm</h1>
            <div class="flex flex-col gap-2 w-full border border-gray-700 p-3 rounded-md">
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Armhole radius</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.armhole} class="input input-bordered input-sm w-4/6" name="armhole" placeholder="Armhole radius">
                    {:else}
                        <div class="w-4/6">{measurements.armhole}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Sleeve Length</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.sleeveLength} class="input input-bordered input-sm w-4/6" name="sleeveLength" min="0" placeholder="Sleeve Length">
                    {:else}
                        <div class="w-4/6">{measurements.sleeveLength}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Biceps</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.bicep} class="input input-bordered input-sm w-4/6" name="bicep" placeholder="Biceps">
                    {:else}
                        <div class="w-4/6">{measurements.bicep}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Elbow</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.elbow} class="input input-bordered input-sm w-4/6" name="elbow" placeholder="Elbow">
                    {:else}
                        <div class="w-4/6">{measurements.elbow}</div>
                    {/if}
                </label>
                
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Wrist radius</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.wrist} class="input input-bordered input-sm w-4/6" name="wrist" placeholder="Wrist radius">
                    {:else}
                        <div class="w-4/6">{measurements.wrist}</div>
                    {/if}
                </label>
            </div>
        </div>
    <!-- </div> -->


    <!-- <div class="flex flex-col gap-10 md:w-1/2"> -->

        <div class="flex flex-col gap-2 w-full">
            <h1 class="font-gupter">Top General</h1>
            <div class="flex flex-col gap-2 w-full border border-gray-700 p-3 rounded-md">
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Back width</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.backWidth} class="input input-bordered input-sm w-4/6" name="backWidth" placeholder="Back width">
                    {:else}
                        <div class="w-4/6">{measurements.backWidth}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Front length</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.frontLength} class="input input-bordered input-sm w-4/6" name="frontLength" min="0" placeholder="Front Length">
                    {:else}
                        <div class="w-4/6">{measurements.frontLength}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Back length</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.backLength} class="input input-bordered input-sm w-4/6" name="backLength" placeholder="Back Length">
                    {:else}
                        <div class="w-4/6">{measurements.backLength}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Vertical trunk</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.verticalTrunk} class="input input-bordered input-sm w-4/6" name="verticalTrunk" placeholder="Vertical Trunk">
                    {:else}
                        <div class="w-4/6">{measurements.verticalTrunk}</div>
                    {/if}
                </label>
                
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Bust to waist</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.bustToWaist} class="input input-bordered input-sm w-4/6" name="bustToWaist" placeholder="Vertical Trunk">
                    {:else}
                        <div class="w-4/6">{measurements.bustToWaist}</div>
                    {/if}
                </label>
            </div>
        </div>

        <div class="flex flex-col gap-2 w-full">
            <h1 class="font-gupter">Waist, and thighs</h1>
            <div class="flex flex-col gap-2 w-full border border-gray-700 p-3 rounded-md">
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Waist radius</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.waist} class="input input-bordered input-sm w-4/6" name="waist" placeholder="Waist radius">
                    {:else}
                        <div class="w-4/6">{measurements.waist}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Hip</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.hip} class="input input-bordered input-sm w-4/6" name="hip" min="0" placeholder="Hip">
                    {:else}
                        <div class="w-4/6">{measurements.hip}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Total Rise</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.rise} class="input input-bordered input-sm w-4/6" name="rise" placeholder="Total rise">
                    {:else}
                        <div class="w-4/6">{measurements.rise}</div>
                    {/if}
                </label>
                
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Waist to Knee</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.waistToKnee} class="input input-bordered input-sm w-4/6" name="waistToKnee" placeholder="Waist to Knee">
                    {:else}
                        <div class="w-4/6">{measurements.waistToKnee}</div>
                    {/if}
                </label>
            </div>
        </div>

        <div class="flex flex-col gap-2 w-full">
            <h1 class="font-gupter">Legs</h1>
            <div class="flex flex-col gap-2 w-full border border-gray-700 p-3 rounded-md">
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Knee radius</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.knee} class="input input-bordered input-sm w-4/6" name="knee" placeholder="Knee radius">
                    {:else}
                        <div class="w-4/6">{measurements.knee}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Calf</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.calf} class="input input-bordered input-sm w-4/6" name="calf" min="0" placeholder="Calf">
                    {:else}
                        <div class="w-4/6">{measurements.calf}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Inseam</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.inseam} class="input input-bordered input-sm w-4/6" name="inseam" placeholder="Inseam">
                    {:else}
                        <div class="w-4/6">{measurements.inseam}</div>
                    {/if}
                </label>
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Outseam</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.outseam} class="input input-bordered input-sm w-4/6" name="outseam" placeholder="Outseam">
                    {:else}
                        <div class="w-4/6">{measurements.outseam}</div>
                    {/if}
                </label>
                
                <label class="flex items-center text-sm gap-5 justify-start w-full">
                    <h1 class="w-2/6">Waist to hem</h1>
                    {#if isEditing}
                        <input type="text" bind:value={measurements.waistToHem} class="input input-bordered input-sm w-4/6" name="waistToHem" placeholder="Waist to Hem">
                    {:else}
                        <div class="w-4/6">{measurements.waistToHem}</div>
                    {/if}
                </label>
            </div>
        </div>
    <!-- </div> -->
</div>