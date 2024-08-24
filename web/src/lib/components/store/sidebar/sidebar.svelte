<script>
    import Genderselect from "./genderselect.svelte";
    import Pricerange from "./pricerange.svelte";

</script>
<div class="flex flex-col gap-3 w-full h-full">
    <h1 class="font-gupter text-xl font-bold">
        Filters
    </h1>

    <hr class="border-gray-600">

    <div class="flex flex-col gap-2">
        <div class="flex flex-col gap-2 border-b-2 border-gray-800 p-3">
            <Pricerange />
        </div>
        <div class="flex flex-col gap-2 border-b-2 border-gray-800 p-3">
            <Genderselect />
        </div>
    </div>
</div>