<script>
    import Footer from "$lib/components/footer/footer.svelte";
    import Navbar from "$lib/components/navbar/navbar.svelte";
    import "../app.css";
</script>

<div class="flex flex-col items-center justify-between min-w-screen min-h-screen">
    <Navbar />
    <div class="w-screen h-full mt-14">
        <slot></slot>
    </div>
    <Footer />
</div>