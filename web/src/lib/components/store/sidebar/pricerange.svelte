<div class="flex gap-2 items-center">
    <input type="checkbox" name="pricechekced" id="pricechecked" />
    <h1>Price range</h1>
</div>
<div class="flex md:flex-col lg:flex-row lg:justify-between">
    <div class="flex flex-col gap-2 lg:w-1/2 p-2">
        <h1>min:</h1>
        <input
            type="number"
            name="minprice"
            min="0"
            max="1000000"
            class="input input-bordered input-xs"
            value="0"
        />
    </div>
    <div class="flex flex-col gap-2 lg:w-1/2 p-2">
        <h1>max:</h1>
        <input
            type="number"
            name="maxprice"
            min="0"
            max="1000000"
            class="input input-bordered input-xs"
            value="10000"
        />
    </div>
</div>
