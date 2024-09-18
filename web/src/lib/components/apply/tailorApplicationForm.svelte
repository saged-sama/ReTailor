<script lang="ts">
    import { ImageUp } from "lucide-svelte";

    let id1: string | null | undefined = null;
    let id2: string | null | undefined = null;

    const handleChange1 = (e: any) => {
        const file = e.target.files[0];
        id1 = URL.createObjectURL(file);
    }

    const handleChange2 = (e: any) => {
        const file = e.target.files[0];
        id2 = URL.createObjectURL(file);
    }
</script>

<form class="flex flex-col items-center shadow-lg w-full rounded-md p-2 px-5 gap-5">
    <div class="flex flex-col text-sm gap-2">
        <h1>
            Please provide all the necessary information to apply for the position of a Tailor with utmost care. You'll be notified once our team has verified your application.
        </h1>
        <h2 class="hidden md:block">For more information, please refer to the details on the right</h2>
        <h2 class="md:hidden block">For more information, please click on the "What About?" button</h2>
    </div>
    <div class="flex max-md:flex-col gap-2 justify-start w-full">
        <div class="md:w-1/6 font-bold">
            Name: 
        </div>
        <div class="flex flex-col gap-1 items-start justify-start w-full">
            <input type="text" name="name" placeholder="Name" class="input input-bordered input-sm w-full">
            <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> Your name should match with your National ID</h1>
        </div>
    </div>

    <div class="flex max-md:flex-col gap-2 justify-start w-full">
        <div class="md:w-1/6 font-bold">
            National ID:
        </div>
        <div class="flex flex-col gap-3 justify-center w-full">
            <input type="text" name="nationalId" placeholder="Your National ID number" class="input input-bordered input-sm w-full">
            <div class="flex flex-col md:grid md:grid-cols-2 gap-3">
                <label for="nationalIdFront" class="flex items-center justify-center cursor-pointer rounded-md bg-primary-content">
                    <div class="flex parent h-40 items-center {id1 ? "opacity-100": "max-md:opacity-50 md:hover:opacity-50"} justify-center w-full">
                        {#if id1}
                            <img src={id1} alt="National ID Front" class="w-full h-full object-cover rounded-md" />
                        {/if}
                        <div class="absolute flex flex-col items-center justify-center child px-2 py-1 {id1 ? "opacity-0" : "md:opacity-50"} text-white">
                            <h1>Your National ID Front Face Image</h1>
                            <ImageUp class="md:h-10 md:w-10" />
                        </div>
                    </div>
                    <input
                        type="file"
                        name="nationalIdFront"
                        id="nationalIdFront"
                        class="hidden"
                        accept="image/*"
                        on:change={handleChange1}
                    />
                </label>
                <label for="nationalIdBack" class="flex items-center justify-center cursor-pointer rounded-md bg-primary-content">
                    <div class="flex parent h-40 items-center {id2 ? "opacity-100": "max-md:opacity-50 md:hover:opacity-50"} justify-center w-full">
                        {#if id2}
                            <img src={id2} alt="National ID Front" class="w-full h-full object-cover rounded-md" />
                        {/if}
                        <div class="absolute flex flex-col items-center justify-center child px-2 py-1 {id2 ? "opacity-0" : "md:opacity-50"} text-white">
                            <h1>Your National ID Back Side Image</h1>
                            <ImageUp class="md:h-10 md:w-10" />
                        </div>
                    </div>
                    <input
                        type="file"
                        name="nationalIdBack"
                        id="nationalIdBack"
                        class="hidden"
                        accept="image/*"
                        on:change={handleChange2}
                    />
                </label>

                <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> We require the front and back side images of your National ID for verification</h1>
            </div>
        </div>
    </div>
</form>