<script lang="ts">
    export let caption = "";
    export let placeholder = "";
    export let type = "text";
    export let value = "";
    export let name;
    export let width = "w-full";
</script>

<label class="flex flex-col w-full gap-1">
    <h1 class="text-sm">{caption}</h1>
    <input type={type} placeholder={placeholder} name={name} value={value} class="input input-bordered input-sm border-sm {width}">
</label>