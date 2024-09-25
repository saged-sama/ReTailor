<script lang="ts">
    import SearchBar from "./searchBar.svelte";
    import { page } from "$app/stores";
    import { Home, Menu, ShoppingBag, Store } from "lucide-svelte";
    import Options from "./options.svelte";

    let currentUrl = $page.url;

    $: currentUrl = $page.url;
</script>

<details class="block md:hidden dropdown dropdown-end       ">
    <summary class="btn btn-sm"> <Menu /> </summary>
    <ul class="menu dropdown-content rounded-md z-[1] w-72 p-2 gap-2 shadow-xl bg-primary-content">
        <caption class="mb-3"><SearchBar /></caption>
        <li><a class="flex gap-2 items-center justify-start p-2 rounded-lg {currentUrl.pathname === "/" ? "bg-base-100 text-secondary": ""}" href="/"> <Home class="w-5 h-5" /> Home </a> </li>
        <li><a class="flex gap-2 items-center justify-start p-2 rounded-lg {currentUrl.pathname.startsWith("/app/tailors") ? "bg-base-100 text-secondary": ""}" href="/app/tailors"> <Store class="w-5 h-5" /> Store </a> </li>
        <li><a class="flex gap-2 items-center justify-start p-2 rounded-lg {currentUrl.pathname.startsWith("/app/orders") ? "bg-base-100 text-secondary": ""}" href="/app/orders"> <ShoppingBag class="w-5 h-5" /> Cart </a> </li>
        <hr class="border-base-100 m-2">
        <li><Options /></li>
    </ul>
</details>

<div class="hidden md:flex items-center justify-center md:gap-10">
    <!-- <SearchBar /> -->
    <div class="flex items-center justify-center rounded-lg gap-1">
        <a class="btn btn-sm {currentUrl.pathname === "/" ? "bg-primary-content text-secondary": "btn-ghost"}" href="/"> <Home class="w-5 h-5" /> </a>
        <a class="btn btn-sm {currentUrl.pathname.startsWith("/app/tailors") ? "bg-primary-content text-secondary": "btn-ghost"}" href="/app/tailors"> <Store class="w-5 h-5" /> </a>
        <a class="btn btn-sm flex gap-0 {currentUrl.pathname.startsWith("/app/orders") ? "bg-primary-content text-secondary": "btn-ghost"}" href="/app/orders"> <ShoppingBag class="w-5 h-5" /></a>
    </div>
    <Options />
</div>
