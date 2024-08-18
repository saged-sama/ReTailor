<script lang="ts">
    import FormInput from "./formInput.svelte";
    import Title from "../navbar/title.svelte";
</script>

<div class="flex flex-col items-center justify-center gap-5 shadow-2xl p-5 bg-primary-content rounded-lg">
    <div>
        <Title />
        <div class="text-sm">
            Sign up to Get Your Favorite Design Realized
        </div>
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
            Have an account? <a href="/auth/login" class="text-primary">Login</a>
        </h2>
    </div>

    <form
        action="?/register"
        method="post"
        class="flex flex-col items-center justify-center gap-3"
    >
        <div class="flex gap-3">
            <FormInput caption="First Name" placeholder="First Name" name="firstName"/>
            <FormInput caption="Last Name" placeholder="Last Name" name="lastName"/>
        </div>
        <FormInput caption="Email" placeholder="Email" name="email"/>
        <div class="flex flex-col w-full gap-1">
            <div>
                <h1 class="text-sm">Mobile Number</h1>
            </div>
            <div class="flex gap-0">
                <h1 class="rounded-l-lg bg-base-200 p-1">+880</h1>
                <input type="text" placeholder="Mobile Number" name="phone" class="input input-sm input-bordered rounded-l-none w-full">
            </div>
        </div>
        <FormInput caption="Password" placeholder="Password" type="password" name="password"/>
        <FormInput caption="Confirm Password" placeholder="Confirm Password" type="password" name="passwordConfirm"/>

        <button type="submit" class="btn btn-primary w-full btn-sm">
            Sign Up
        </button>
    </form>
</div>