<script lang="ts">
    import { springbase } from "$lib/utils/springbase";
    import { onMount } from "svelte";
    import { page } from "$app/stores";
    import BasicInfo from "$lib/components/profile/basicInfo.svelte";
    import PersonalInfo from "$lib/components/profile/personalInfo/personalInfo.svelte";

    let user: any = {};

    onMount(async () => {
        const userDet = await springbase.collection("users").getOne($page.params.userid);
        // console.log(userDet)
        const customerDet = await springbase.collection("customers").getFirstListItem("user_id", userDet.id);
        user = { ...customerDet, ...userDet, customerId: customerDet.id, id: userDet.id };
    });
</script>

<svelte:head>
    <title>Retailor | Profile{user?.id ? ` | ${user.firstName} ${user.lastName}`: ""}</title>
</svelte:head>

{#if user.id}
    <div class="flex flex-col items-center justify-center p-2 md:p-10 md:gap-10 gap-5">
        <BasicInfo {user}/>
        <PersonalInfo {user}/>
    </div>
{/if}