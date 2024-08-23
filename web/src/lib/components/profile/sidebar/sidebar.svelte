<script lang="ts">
    import { pocketbase } from "$lib/utils/pocketbase";
    import { page } from "$app/stores";
    import { Settings } from "lucide-svelte";
    let currentUrl = "";

    $:{
        currentUrl = $page.url.pathname.split("/")[2];
        console.log(currentUrl);
    }
</script>
<div class="flex flex-col gap-2 pt-10 shadow-2xl border-r border-gray-800 p-2 h-full">
    <h1 class="flex gap-2 items-center px-10 font-bold font-gupter mb-3"> <Settings class="w-4 h-4"/> Settings</h1>
    <a href={`/app/profile/${pocketbase.authStore.model?.id}`} class="p-3 px-10 rounded-r-lg flex items-center {currentUrl === "profile" ? " bg-primary text-neutral": ""}">Profile</a>
    <a href={`/app/measurements/${pocketbase.authStore.model?.id}`} class="p-3 px-10 rounded-r-lg flex items-center {currentUrl === "measurements" ? " bg-primary text-neutral": ""}">Measurements</a>
    <a href={`/app/paymentInfo/${pocketbase.authStore.model?.id}`} class="p-3 px-10 rounded-r-lg flex items-center {currentUrl === "paymentInfo" ? " bg-primary text-neutral": ""}">Payment Information</a>
</div>