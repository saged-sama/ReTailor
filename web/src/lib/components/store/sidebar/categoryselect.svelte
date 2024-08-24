<script lang="ts">
    const clothingCategories = [
        "T-Shirts",
        "Jeans",
        "Dresses",
        "Skirts",
        "Jackets",
        "Sweaters",
        "Shorts",
        "Blouses",
        "Suits",
        "Coats",
        "Pants",
        "Hoodies",
        "Cardigans",
        "Leggings",
        "Shirts",
        "Blazers",
        "Tank Tops",
        "Vests",
        "Overalls",
        "Jumpsuits"
    ];

</script>

<div class="flex gap-2 items-center">
    <input type="checkbox" name="pricechekced" id="pricechecked" />
    <h1>Categories</h1>
</div>
<div class="flex flex-col gap-2 p-3">
    {#each clothingCategories as category}
        <div class="flex gap-2">
            <input type="checkbox" name={category} id={category} />
            <h1>{category}</h1>
        </div>  
    {/each}
</div>
