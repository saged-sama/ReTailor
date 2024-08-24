<script>
    import Footer from "$lib/components/footer/footer.svelte";
    import Navbar from "$lib/components/navbar/navbar.svelte";
    import "../app.css";
    import { page } from "$app/stores";
    import { capitalizeFirstLetter } from "$lib";

    let currentpage = "home";

    $: {
        currentpage = $page.url.pathname.split("/").pop() || "";
    }
</script>

<svelte:head>
    <title >Retailor{currentpage ? ` | ${capitalizeFirstLetter(currentpage)}`: ""}</title>
</svelte:head>

<div class="flex flex-col items-center justify-between min-w-screen min-h-screen">
    <Navbar />
    <div class="w-screen h-full mt-14">
        <slot></slot>
    </div>
    <Footer />
</div>