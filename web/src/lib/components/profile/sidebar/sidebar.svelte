<script lang="ts">
    import { page } from "$app/stores";
    import { Settings } from "lucide-svelte";
    import { currentUser } from "$lib/stores/currentUser";
    let currentUrl = "";

    $:{
        currentUrl = $page.url.pathname.split("/").pop() || "";
    }
</script>

<div class="flex flex-col gap-2 pt-10 shadow-2xl border-r border-gray-800 p-2 h-full z-50">
    <h1 class="flex gap-2 items-center sm:px-2 md:px-5 lg:px-10 font-bold font-gupter mb-3"> <Settings class="w-4 h-4"/> Settings</h1>
    <a href={`/app/${$currentUser.id}/profile`} class="p-3 sm:px-2 md:px-5 lg:px-10 rounded-r-lg flex items-center {currentUrl === "profile" ? " bg-primary text-neutral": ""}">Profile</a>
    <a href={`/app/${$currentUser.id}/measurements`} class="p-3 sm:px-2 md:px-5 lg:px-10 rounded-r-lg flex items-center {currentUrl === "measurements" ? " bg-primary text-neutral": ""}">Measurements</a>
    <a href={`/app/${$currentUser.id}/paymentInfo`} class="p-3 sm:px-2 md:px-5 lg:px-10 rounded-r-lg flex items-center {currentUrl === "paymentInfo" ? " bg-primary text-neutral": ""}">Payment Information</a>
</div>