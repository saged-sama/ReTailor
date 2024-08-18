<script lang="ts">
    import { page } from "$app/stores";

    const emots = ["😥", "😓", "😣", "😔", "😶", "🫥", "😶‍🌫️", "🤐", "😫", "🫠", "😖", "😤", "😟", "🥵", "🥶", "🥴", "🫢"];
</script>

<div class="flex flex-col md:flex-row items-center justify-center w-full gap-5 md:gap-10 mt-20">
    <div class="flex flex-col items-center justify-center gap-5">
        <h1 class="text-8xl">{emots[Math.round(Math.random()*(emots.length-1))]}</h1>
        <h1 class="font-shadows-into-light text-6xl text-error">Oopsie..</h1>
    </div>
    <div class="flex flex-col items-center p-10">
        <h1><i>Ah..oh...You shouldn't be seeing this</i></h1>
        <div class="text-2xl font-bold">
            <span class="font-gupter text-warning">{$page.status}</span>
            <span>|</span>
            <span class="font-bona-nova-sc">{$page.error?.message}</span>
        </div>
    </div>
</div>
