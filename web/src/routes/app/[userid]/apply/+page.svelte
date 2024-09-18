<script lang="ts">
    import TailorApplicationForm from "$lib/components/apply/tailorApplicationForm.svelte";
    import { page } from "$app/stores";
    import AboutApplying from "$lib/components/apply/aboutApplying.svelte";
    import { fly } from "svelte/transition";
    let applicationForm = true;

    $: currentUserId = $page.params.userid;
</script>

<div class="flex flex-col gap-5 p-2 md:gap-10 md:p-10">
    <div class="flex items-center justify-center md:hidden rounded-md">
        <button class="btn {applicationForm ? "btn-neutral" : "btn-ghost border border-neutral"} btn-md rounded-md rounded-r-none" on:click={() => applicationForm = true}>Application</button>
        <button class="btn {applicationForm ? "btn-ghost border border-neutral" : "btn-neutral"} btn-md rounded-md rounded-l-none" on:click={() => applicationForm = false}>What About?</button>
    </div>
    
    <div class="hidden md:flex items-center justify-start">
        <div class="md:w-3/5">
            <TailorApplicationForm />
        </div>
        <div class="md:w-2/5">
            <AboutApplying />
        </div>
    </div>

    <div class="w-full overflow-hidden">
        <div class="max-md:grid grid-cols-1 grid-flow-row hidden items-center justify-start">
            <div>
                <TailorApplicationForm />
            </div>
            <div>
                <AboutApplying />
            </div>
        </div>
    </div>
</div>