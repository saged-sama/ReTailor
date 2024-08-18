<script>
    import Avatar from "./avatar.svelte";
    import SearchBar from "./searchBar.svelte";
    import { page } from "$app/stores";
    import { Home, Menu, ShoppingCart, Store, User } from "lucide-svelte";

    let currentUrl = $page.url;

    $: {
        currentUrl = $page.url;
    }

</script>

<details class="block md:hidden dropdown dropdown-end">
    <summary class="btn btn-sm"> <Menu /> </summary>
    <ul class="menu dropdown-content rounded-md z-[1] w-72 p-2 gap-2 shadow-xl bg-primary-content">
        <caption class="mb-3"><SearchBar /></caption>
        <li><a class="flex gap-2 items-center justify-start p-2 rounded-lg {currentUrl.pathname === "/" ? "bg-base-100 text-secondary": ""}" href="/"> <Home class="w-5 h-5" /> Home </a> </li>
        <li><a class="flex gap-2 items-center justify-start p-2 rounded-lg {currentUrl.pathname === "/store" ? "bg-base-100 text-secondary": ""}" href="/store"> <Store class="w-5 h-5" /> Store </a> </li>
        <li><a class="flex gap-2 items-center justify-start p-2 rounded-lg {currentUrl.pathname === "/cart" ? "bg-base-100 text-secondary": ""}" href="/cart"> <ShoppingCart class="w-5 h-5" /> Cart </a> </li>
        <hr class="border-base-100 m-2">
        <li><Avatar /></li>
    </ul>
</details>

<div class="hidden md:flex items-center justify-center gap-5">
    <SearchBar />
    <div class="flex items-center justify-center rounded-lg gap-1">
        <a class="btn btn-sm {currentUrl.pathname === "/" ? "bg-primary-content text-secondary": "btn-ghost"}" href="/"> <Home class="w-5 h-5" /> </a>
        <a class="btn btn-sm {currentUrl.pathname === "/store" ? "bg-primary-content text-secondary": "btn-ghost"}" href="/store"> <Store class="w-5 h-5" /> </a>
        <a class="btn btn-sm {currentUrl.pathname === "/cart" ? "bg-primary-content text-secondary": "btn-ghost"}" href="/cart"> <ShoppingCart class="w-5 h-5" /> </a>
    </div>
    <Avatar />
</div>
