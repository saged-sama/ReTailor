<script>
    import Footer from "$lib/components/footer/footer.svelte";
    import Navbar from "$lib/components/navbar/navbar.svelte";
    import "../app.css";
    import { page } from "$app/stores";
    import { capitalizeFirstLetter } from "$lib";
    import { onMount } from "svelte";
    import { springbase } from "$lib/utils/springbase";
    import { currentUser } from "$lib/stores/currentUser";

    let currentpage = "home";

    $: {
        currentpage = $page.url.pathname.split("/").pop() || "";
    }
    onMount(async () => {
        springbase.authStore.loadFromStorage();
        if(springbase.authStore.isValid){
            const cuserDet = await springbase.collection("users").getOne(springbase.authStore.model.id);
            const customerDet = await springbase.collection("customers").getFirstListItem("user_id", cuserDet.id);
            
            $currentUser = {
                ...customerDet,
                ...cuserDet,
                id: cuserDet.id,
                customerId: customerDet.id
            };
            // console.log("Layout: ", $currentUser);
        }
    });
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