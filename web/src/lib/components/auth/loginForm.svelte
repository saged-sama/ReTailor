<script lang="ts">
    import { goto } from "$app/navigation";
    import { pocketbase } from "$lib/utils/pocketbase";
    import Title from "../navbar/title.svelte";
    import { KeyRound, Mail } from "lucide-svelte";

    const handleSubmit = async (e: Event) => {
        try{
            e.preventDefault();
            const form = e.target as HTMLFormElement;
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());
            await pocketbase.collection("users").authWithPassword(data.email as string, data.password as string);
        }
        catch(err){
            console.error("Error logging in: ", err);
        }
        
        if(!pocketbase.authStore.isValid){
            alert("Wrong Credentials");
        }
    };
</script>

<div class="flex flex-col items-center justify-center gap-5 shadow-2xl p-5 bg-primary-content rounded-lg mt-24 w-full">
    <div>
        <Title />
        <div class="text-sm">
            Log in and Order Your Favorite Design
        </div>
    </div>

    <div class="flex flex-col items-center">
        <h1 class="text-xl font-bold font-bona-nova-sc text-primary">
            Log In
        </h1>
        <h2 class="text-sm">
            Don't Have an account? <a href="/auth/register" class="text-primary">Sign up</a>
        </h2>
    </div>

    <form
        class="flex flex-col items-center justify-center gap-3 w-full"
        on:submit={handleSubmit}
    >
        <label class="input input-bordered input-sm flex items-center gap-2 w-full">
            <Mail class="w-4 h-4"/>
            <input type="email" placeholder="Email" name="email">
        </label>
        <label class="input input-bordered input-sm flex items-center gap-2 w-full">
            <KeyRound class="w-4 h-4"/>
            <input type="password" placeholder="Password" name="password">
        </label>
        <button type="submit" class="btn btn-primary w-full btn-sm">
            Log In
        </button>
    </form>
</div>