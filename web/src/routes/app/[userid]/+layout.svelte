<script lang="ts">
    import Sidebar from "$lib/components/profile/sidebar/sidebar.svelte";
</script>

<div class="flex justify-end gap-20">
    <div class="hidden md:block fixed left-0 w-1/5 bg-red h-full">
        <Sidebar />
    </div>
    <div class="md:w-4/5 w-full"> 
        <slot></slot>
    </div>
</div>