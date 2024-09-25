<script lang="ts">
    import { PUBLIC_API_URL } from "$env/static/public";
    import { getRating } from "$lib";
    import { Circle, Star } from "lucide-svelte";
    export let tailors: any[];

    // console.log(tailors);
</script>

<div class="flex flex-col w-full gap-3">
    {#each tailors as tailor}
        <a class="flex justify-between border border-gray-700 rounded-md p-3" href="/app/tailors/{tailor.id}">
            <div class="flex gap-3">
                <div class="w-20 h-20 rounded-lg">
                    <!-- {#if tailor.cutomer} -->
                        <img src={`${PUBLIC_API_URL}/api/files/users/${tailor.users?.id}/${tailor.customer.avatar}`} alt={tailor?.name} class="w-full h-full object-cover rounded-lg" />
                    <!-- {/if} -->
                </div>
                <div class="flex flex-col gap-3">
                    <div class="prose prose-h1:text-xl prose-h2:text-lg prose-p:sm font-bona-nova text-accent text-xl">
                        {tailor.name}
                    </div>
                    <div class="flex md:items-center max-md:flex-col gap-2">
                        <div class="flex items-center gap-2 text-info">
                            <Star class="fill-yellow-400 w-4 h-4"/> {getRating(tailor.reviewAndRating)}
                        </div>
                    </div>
                </div>
            </div>
        </a>
    {/each}
</div>