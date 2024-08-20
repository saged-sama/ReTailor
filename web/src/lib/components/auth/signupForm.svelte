<script lang="ts">
    import FormInput from "./formInput.svelte";
    import Title from "../navbar/title.svelte";
    import { pocketbase } from "$lib/utils/pocketbase";
    import { goto } from "$app/navigation";

    let isChecked: boolean = false;

    const handleSubmit = async (e: Event) => {
        let record: any = null;
        try {
            e.preventDefault();
            const form = e.target as HTMLFormElement;
            const formData = new FormData(form);
            formData.append("role", "CUSTOMER");
            const data = Object.fromEntries(formData.entries());

            if (
                data.firstName === "" ||
                data.lastName === "" ||
                data.email === "" ||
                data.phone === "" ||
                data.password === "" ||
                data.passwordConfirm === ""
            ) {
                alert("All of the fields are required");
                return;
            }

            if (
                await pocketbase.collection("users").getList(1, 1, {
                    filter: `email = "${data.email.toString()}"`,
                })
            ) {
                alert("An account with this email already exists");
                return;
            }

            if (data.password !== data.passwordConfirm) {
                alert("Passwords do not match");
                return;
            }

            record = await pocketbase.collection("users").create(data);
        } catch (err) {
            console.error("Error signing up: ", err);
        }

        if (record.created) {
            goto("/auth/login");
        } else {
            alert("Something went wrong");
        }
    };
</script>

<div
    class="flex flex-col items-center justify-center gap-5 shadow-2xl p-5 bg-primary-content rounded-lg"
>
    <div>
        <Title />
        <div class="text-sm">Sign up to Get Your Favorite Design Realized</div>
    </div>

    <!-- <button class="btn w-full">
        Continue with Google <Mails />
    </button>

    <hr class="w-full"> -->

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
        on:submit={handleSubmit}
    >
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
