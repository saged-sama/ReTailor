<script lang="ts">
    import { Edit, SquareArrowOutUpRight } from "lucide-svelte";
    import { pocketbase } from "$lib/utils/pocketbase";

    let isEditing = false;
    export let user: any;

    const updatePersonalInfo = async (e: any) => {
        try {
            e.preventDefault();
            const form = e.target;
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());
            user = await pocketbase.collection("users").update(user.id, data);
            isEditing = false;
            form.reset();
        } catch (err) {
            console.error("Could not edit basic info: ", err);
        }
    };
</script>

<div class="flex flex-col w-full p-5 gap-3 shadow-lg">
    <h1 class=" font-platypi font-bold">
        Personal Information
    </h1>
    {#if isEditing}
        <form class="flex flex-col items-start justify-start w-full text-sm p-5 gap-3" on:submit={updatePersonalInfo}>
            <div class="flex max-md:flex-col gap-2 justify-between w-full">
                <div class="flex items-center justify-center w-5/6">
                    <div class="w-1/5 font-bold">
                        Role:
                    </div>
                    <div class="w-4/5 md:ml-7">
                        {user.role.toLowerCase()}
                    </div>
                </div>
                {#if user.role.toLowerCase() === "customer"}
                    <a href={`/app/${user.id}/apply`} class="btn btn-success btn-sm max-md:btn-xs">
                        Apply to Become a Tailor <SquareArrowOutUpRight class="w-4 h-4" /> 
                    </a>
                {/if}
            </div>

            <div class="flex max-md:flex-col gap-2 md:items-center justify-start w-full">
                <div class="w-1/6 font-bold">
                    Gender: 
                </div>
                <select name="gender" id="gender" value={user.gender || "Male"} class="select select-bordered select-sm">
                    <option value="" disabled>Select your gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            
            <div class="flex max-md:flex-col gap-2 md:items-center justify-start w-full">
                <div class="w-1/6 font-bold">
                    Phone:
                </div>
                <div class="flex items-center">
                    <div class="flex items-center justify-center p-2 rounded-l-lg bg-primary-content">+880</div> <input type="text" name="phone" class="input input-bordered input-sm rounded-l-none max-md:w-full" value={user.phone || ""} placeholder="Phone Number">
                </div>
            </div>
            <div class="flex max-md:flex-col gap-2 md:items-center justify-start w-full">
                <div class="w-1/6 font-bold">
                    Address:
                </div>
                <textarea name="address" class="textarea textarea-bordered textarea-sm md:w-5/6" value={user.address || ""} placeholder="Give your current Address"></textarea>
            </div>

            <div class="flex items-center justify-center mt-5 gap-3 w-full">
                <button type="submit" class="btn btn-primary btn-sm">Save</button>
                <button on:click={() => (isEditing = false)} class="btn btn-sm">Cancel</button>
            </div>
        </form>
    {:else}
        <div class="flex max-md:flex-col items-start md:justify-between w-full text-sm p-1 md:p-5 gap-3">
            <div class="flex flex-col gap-3 w-full md:w-5/6">
                <div class="flex items-center justify-center w-full gap-6">
                    <div class="w-1/6 font-bold">
                        Role:
                    </div>
                    <div class="w-5/6 ">
                        {user.role.toLowerCase()}
                    </div>
                </div>
        
                <div class="flex items-center justify-center w-full gap-6">
                    <div class="w-1/6 font-bold">
                        Gender:
                    </div>
                    <div class="w-5/6 ">
                        {user.gender || "N/A"}
                    </div>
                </div>
        
                <div class="flex items-center justify-center w-full gap-6">
                    <div class="w-1/6 font-bold">
                        Phone:
                    </div>
                    <div class="w-5/6 ">
                        {user.phone || "N/A"}
                    </div>
                </div>
        
                <div class="flex justify-center w-full gap-6">
                    <div class="w-1/6 font-bold">
                        Address:
                    </div>
                    <div class="w-5/6 ">
                        {user.address || "N/A"}
                    </div>
                </div>
            </div>
            <button on:click={() => (isEditing = true)} class="{pocketbase.authStore.model?.id !== user.id ? "hidden": "block"} flex items-center justify-center btn btn-sm max-md:w-full">
                <Edit class="w-4 h-4" /> Edit
            </button>
        </div>
    {/if}
</div>