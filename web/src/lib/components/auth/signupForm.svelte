<script lang="ts">
    import FormInput from "./formInput.svelte";
    import Title from "../navbar/title.svelte";
    import { springbase } from "$lib/utils/springbase";
    import { goto } from "$app/navigation";
    import { ImageUp } from "lucide-svelte";

    let isChecked: boolean = false;
    let avatar: File | null = null;
    let avatarPreview: string | null = null;

    const handleFileChange = (e: Event) => {
        const input = e.target as HTMLInputElement;
        avatar = input.files?.[0] || null;
        if (avatar) {
            const reader = new FileReader();
            reader.onload = () => {
                avatarPreview = reader.result as string;
            };
            reader.readAsDataURL(avatar);
        }
    };

    const isFormFilled = (data: any) => {
        if (
            data.firstName === "" ||
            data.lastName === "" ||
            data.email === "" ||
            data.phone === "" ||
            data.password === "" ||
            data.passwordConfirm === ""
        ) {
            return false;
        }
        return true;
    };

    const isEmailUsed = async (email: string) => {
        console.log(email);
        const r = await springbase.collection("users").getList(1, 1, {
            filter: `email = "${email}"`
        })

        console.log(r.content.length);

        if(r.content.length > 0) {
            return true;
        }
        return false;
    };

    const isDataValid = async (data: any) => {
        if (!isFormFilled(data)) {
            alert("Please fill all the fields");
            return false;
        }

        if (data.password !== data.passwordConfirm) {
            alert("Passwords do not match");
            return false;
        }

        if ((await isEmailUsed(data.email)) === true) {
            alert("Email already in use");
            return false;
        }

        return true;
    };

    const handleSubmit = async (e: Event) => {
        let record: any = null;
        try {
            e.preventDefault();
            const form = e.target as HTMLFormElement;
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());

            if (!(await isDataValid(data))) {
                return;
            }

            record = await springbase.collection("users").create(formData);
        } catch (err) {
            console.error("Error signing up: ", err);
        }

        if (record) {
            goto("/auth/login");
        } else {
            alert("Something went wrong");
        }
    };
</script>

<div
    class="flex flex-col items-center justify-center gap-5 shadow-2xl p-5 bg-primary-content rounded-lg"
>
    <div class="flex flex-col items-center">
        <h1 class="text-xl font-bold font-bona-nova-sc text-primary">
            Sign Up
        </h1>
        <h2 class="text-sm">
            Have an account? <a href="/auth/login" class="text-primary">Login</a
            >
        </h2>
    </div>

    <form
        class="flex flex-col items-center justify-center gap-3"
        on:submit|preventDefault={handleSubmit}
    >
        <label for="avatar" class="flex flex-col items-center justify-center gap-1">
            {#if avatar}
                <img src={avatarPreview} alt="User Avatar" class="w-20 h-20 rounded-full">
            {:else}
                <div class="border border-gray-500 p-3 rounded-full">
                    <ImageUp class="w-10 h-10"/>
                </div>
            {/if}
            <div class="bg-base-100 px-3 py-2 rounded-lg text-xs">Upload Avatar</div>
            <input type="file" accept="images/*" name="avatar" id="avatar" on:change={handleFileChange} class="hidden">
        </label>

        <div class="flex gap-3">
            <FormInput
                caption="First Name"
                placeholder="First Name"
                name="firstName"
            />
            <FormInput
                caption="Last Name"
                placeholder="Last Name"
                name="lastName"
            />
        </div>
        <FormInput caption="Email" placeholder="Email" name="email" />
        <div class="flex flex-col w-full gap-1">
            <div>
                <h1 class="text-sm">Mobile Number</h1>
            </div>
            <div class="flex gap-0">
                <h1 class="rounded-l-lg bg-base-200 p-1">+880</h1>
                <input
                    type="text"
                    placeholder="Mobile Number"
                    name="phone"
                    class="input input-sm input-bordered rounded-l-none w-full"
                />
            </div>
        </div>

        <FormInput
            caption="Password"
            placeholder="Password"
            type="password"
            name="password"
        />
        <FormInput
            caption="Confirm Password"
            placeholder="Confirm Password"
            type="password"
            name="passwordConfirm"
        />

        <label class="text-sm">
            <input type="checkbox" bind:checked={isChecked} />
                I agree to the
            <a href="/termsandconitions" class="text-primary"
                >Terms of Service</a
            >
            and <a href="/privacypolicy" class="text-primary">Privacy Policy</a>
        </label>
        <button
            type="submit"
            class="btn {isChecked
                ? 'btn-primary'
                : 'btn-disabled'} w-full btn-sm"
        >
            Sign Up
        </button>
    </form>
</div>
