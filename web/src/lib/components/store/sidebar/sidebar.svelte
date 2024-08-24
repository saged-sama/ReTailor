<script>
    import Categoryselect from "./categoryselect.svelte";
    import Genderselect from "./genderselect.svelte";
    import Pricerange from "./pricerange.svelte";

</script>

<div class="flex flex-col max-md:bg-base-100 p-2 gap-3 w-full h-full">
    <h1 class="font-gupter max-md:hidden text-xl font-bold">
        Filters
    </h1>

    <hr class="border-gray-600 max-md:hidden">

    <div class="flex flex-col gap-2 bg-base-100">
        <div class="flex flex-col gap-2 border-b-2 border-gray-800 p-3">
            <Pricerange />
        </div>
        <div class="flex flex-col gap-2 border-b-2 border-gray-800 p-3">
            <Genderselect />
        </div>
        <div class="flex flex-col gap-2 border-b-2 border-gray-800 p-3">
            <Categoryselect />
        </div>
    </div>
</div>