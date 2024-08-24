<script lang="ts">
    import Links from "./links.svelte";
    import SearchBar from "./searchBar.svelte";
    import Title from "./title.svelte";

</script>

<div class="fixed flex items-center z-50 bg-base-100 justify-between h-14 w-full p-3 md:p-6 shadow-xl">
    <Title />
    <div class="hidden md:block w-1/3">
        <SearchBar />
    </div>
    <Links />
</div>