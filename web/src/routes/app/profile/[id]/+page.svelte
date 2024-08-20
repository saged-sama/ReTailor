<script lang="ts">
    import { pocketbase } from "$lib/utils/pocketbase";
    import { onMount } from "svelte";
    import { page } from "$app/stores";
    import BasicInfo from "$lib/components/profile/basicInfo.svelte";
    import PersonalInfo from "$lib/components/profile/personalInfo/personalInfo.svelte";

    let user: any = undefined;

    onMount(async () => {
        user = await pocketbase.collection("users").getOne($page.params.id);
    });
</script>

{#if user}
    <div class="flex flex-col items-center justify-center p-2 md:p-10 md:gap-10 gap-5">
        <BasicInfo {user}/>
        <PersonalInfo {user}/>
    </div>
{/if}