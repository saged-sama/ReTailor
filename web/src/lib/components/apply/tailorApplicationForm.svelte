<script lang="ts">
    import { goto } from "$app/navigation";
    import { currentUser } from "$lib/stores/currentUser";
    import { springbase } from "$lib/utils/springbase";
    import { ImageUp, ArrowRight, SquarePlus, ArrowUpFromLine, MoveUpRight } from "lucide-svelte";
    import { fly } from "svelte/transition";

    let application: boolean = true;
    let finishedUp: boolean = false;
    let loading: boolean = false;
    const emots = ["😎", "🤠", "😁", "😼", "🔥", "😃", "😄", "😀", "😋", "😍", "🥰", "🤩", "🫡", "🤭"];

    interface Work {
        workFormData: FormData;
        workImage: File | null;
        imagePath: string;
        description: string;
    }

    let title: string = "";
    let portfolioDescription: string = "";

    let works: Work[] = [];

    let workImage: string | null = null;
    let currentImage: File | null = null;
    let description: string = "";

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

    const handleSubmit = async (e: any) => {
        loading = true;
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);

        const resp = await springbase.collection("tailors").create(formData);
        application = false;
        loading = false;
    }

    const showModal = () => {
        const modalElement: any = document.getElementById("modal");
        modalElement.showModal();
    }

    const handleFileChange = (e: any) => {
        const file = e.target.files[0];
        currentImage = file;
        workImage = URL.createObjectURL(file);
    }

    const handleAddWork = () => {
        const workFormData = new FormData(document.getElementById("workImageForm") as HTMLFormElement);
        // console.log(workFormData.get("workImage"), workFormData.get("description"));
        works = [...works, { workFormData: workFormData, workImage: currentImage, imagePath: workImage as string, description: description}];
        workImage = null;
        currentImage = null;
        description = "";
        const modalElement: any = document.getElementById("modal");
        modalElement.close();
    }
    
    const editWork = (work: Work) => {
        deleteWork(work);
        workImage = work.imagePath;
        currentImage = work.workImage;
        description = work.description;
        const modalElement: any = document.getElementById("modal");
        modalElement.showModal();
    }

    const deleteWork = (work: Work) => {
        works = works.filter(w => w !== work);
    }
    
    const handleWorkSubmit = async () => {
        // console.log(title, portfolioDescription)
        loading = true;
        const portfolioReq = new FormData();
        portfolioReq.append("title", title);
        portfolioReq.append("description", portfolioDescription);

        const portfolio = await springbase.collection("portfolio").create(portfolioReq);



        works.forEach(async work => {
            const form = work.workFormData;
            form.append("portfolioId", portfolio.id);
            const resp = await springbase.collection("portfolioImages").create(form);
        });
        finishedUp = true;
        loading = false;
    }
</script>

{#if finishedUp}
    <div class="flex max-md:flex-col items-center justify-center w-full rounded-md p-5 gap-3">
        <div class="flex flex-col items-center justify-center gap-1">
            <h1 class="text-8xl">
                {emots[Math.round(Math.random()*(emots.length-1))]}
            </h1>
            <h2 class="text-xl text-success font-shadows-into-light">Congratulations...!!</h2>
        </div>
        <div class="flex flex-col gap-5 items-center">
            <h1 class=" text-center text-lg font-bold font-bona-nova">Your application has been submitted successfully. You'll be notified once our team has verified your application. Thanks for choosing us</h1>
            <button class="btn btn-info btn-sm w-1/2" on:click={() => {goto(`/app/${$currentUser.id}/profile`)}}>Go to Profile <MoveUpRight class="w-4 h-4" /> </button>    
        </div>
    </div>
{:else}
    {#if application}
        <form class="flex flex-col items-center shadow-lg w-full rounded-md p-2 px-5 gap-5" on:submit={handleSubmit} out:fly={{duration: 500, x: -300}}>
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
                    <input type="text" name="nationalIdNo" placeholder="Your National ID number" class="input input-bordered input-sm w-full">
                    <div class="flex flex-col md:grid md:grid-cols-2 gap-3">
                        <label for="nationalIdFront" class="flex items-center justify-center cursor-pointer rounded-md bg-primary-content">
                            <div class="flex parent h-40 items-center {id1 ? "opacity-100": "md:hover:opacity-50"} justify-center w-full">
                                {#if id1}
                                    <img src={id1} alt="National ID Front" class="w-full h-full object-cover rounded-md" />
                                {/if}
                                <div class="absolute flex flex-col items-center justify-center child px-2 py-1 {id1 ? "opacity-0" : "md:opacity-50"} text-white">
                                    <h1 class="text-xs">Your National ID Front Face Image</h1>
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
                            <div class="flex parent h-40 items-center {id2 ? "opacity-100": "md:hover:opacity-50"} justify-center w-full">
                                {#if id2}
                                    <img src={id2} alt="National ID Front" class="w-full h-full object-cover rounded-md" />
                                {/if}
                                <div class="absolute flex flex-col items-center justify-center child px-2 py-1 {id2 ? "opacity-0" : "md:opacity-50"} text-white">
                                    <h1 class="text-xs">Your National ID Back Side Image</h1>
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

            <div class="flex max-md:flex-col gap-2 justify-start w-full">
                <div class="md:w-1/6 font-bold">
                    Work Contact:
                </div>
                <div class="flex flex-col justify-center w-full gap-1">
                    <input name="contact" class="input input-bordered input-sm px-2 py-1" placeholder="How will we reach you?" />
                    <!-- <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> Your work address will be used in all subsequent work related interactions</h1>     -->
                </div>
            </div>
            
            <div class="flex max-md:flex-col gap-2 justify-start w-full">
                <div class="md:w-1/6 font-bold">
                    Work Email:
                </div>
                <div class="flex flex-col justify-center w-full gap-1">
                    <input name="email" class="input input-bordered input-sm px-2 py-1" placeholder="How will we reach you?" />
                    <!-- <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> Your work address will be used in all subsequent work related interactions</h1>     -->
                </div>
            </div>

            <div class="flex max-md:flex-col gap-2 justify-start w-full">
                <div class="md:w-1/6 font-bold">
                    Work Address:
                </div>
                <div class="flex flex-col justify-center w-full gap-1">
                    <textarea name="location" class="textarea textarea-bordered px-2 py-1" placeholder="Where will we find you for work related visits?"></textarea>
                    <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> Your work contacts, and address will be used in all subsequent work related interactions</h1>    
                </div>
            </div>

            <div class="flex max-md:flex-col gap-2 justify-start w-full">
                <div class="md:w-1/6 font-bold">
                    Bio:
                </div>
                <div class="flex flex-col justify-center w-full gap-1">
                    <textarea name="bio" class="textarea textarea-bordered px-2 py-1" placeholder="Tell us about Yourself, your work experience"></textarea>
                    <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> After your verification, this will be visible to public. But of course you can change it later</h1>    
                </div>
            </div>

            <div class="flex max-md:flex-col gap-2 justify-start w-full">
                <div class="md:w-1/6 font-bold">
                    Skills:
                </div>
                <div class="flex flex-col justify-center w-full gap-1">
                    <textarea name="skills" class="textarea textarea-bordered px-2 py-1" placeholder="Things related to Tailoring that you're good at, eg, embroidery, costume tailoring, leather and heavy fabric etc"></textarea>
                    <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> Your skills are the main criteria for approval. Also skills will help you find customers</h1>    
                </div>
            </div>

            <h1 class="font-gupter text-xs text-warning">Please make sure all your given information are correct before proceeding as it will affect the admin panel's decision</h1>

            <div class="flex items-center justify-end w-full gap-2">
                <button class="btn btn-neutral btn-sm" on:click={() => {goto(`/app/${$currentUser.id}/profile`)}}>Cancel</button>
                <button type="submit" class="btn btn-success btn-sm">
                    {#if loading}
                        <span class="loading loading-spinner loading-xs"></span>
                    {:else}
                        Next <ArrowRight class="w-4 h-4"/> 
                    {/if}
                </button>
            </div>
        </form>
    {:else}
        <div class="flex flex-col items-center shadow-lg w-full rounded-md p-2 gap-5">
            <div class="flex flex-col text-sm gap-2">
                <h1>
                    We need to see some of your works to verify your identity before you can apply for the position of a Tailor. Please add some of your works with descriptions and then on the "Apply" button to proceed.
                </h1>
                <h2 class="hidden md:block">For more information, please refer to the details on the right</h2>
                <h2 class="md:hidden block">For more information, please click on the "What About?" button</h2>
                <h3 class="text-warning font-gupter">Your works will be visible to public in your portfolio once you're verified. You will be able to edit, add, delete works in your portfolio to make it more presentable</h3>
            </div>

            <div class="flex max-md:flex-col gap-2 justify-start w-full">
                <div class="md:w-1/6 font-bold">
                    Title:
                </div>
                <div class="flex flex-col justify-center w-full gap-1">
                    <input bind:value={title} class="input input-bordered input-sm px-2 py-1" placeholder="Gice a nice title to your Works Presentation">
                    <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> Title will be visible on top of your portfolio page</h1>    
                </div>
            </div>
            <div class="flex max-md:flex-col gap-2 justify-start w-full">
                <div class="md:w-1/6 font-bold">
                    Description:
                </div>
                <div class="flex flex-col justify-center w-full gap-1">
                    <textarea bind:value={portfolioDescription} class="textarea textarea-bordered textarea-sm px-2 py-1" placeholder="Describe the aspects of your works you really love and enjoy"></textarea>
                    <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> Description is where you describe your works and your interests</h1>    
                </div>
            </div>

            <h1 class=" font-bona-nova text-info w-full">Now add as many of your works as you want:</h1>
            <form class="relative grid grid-cols-2 md:grid-cols-5 gap-1 rounded-lg h-full pb-20">
                {#each works as work}
                    <div class="relative flex w-40 h-40 flex-col items-center bg-neutral justify-center rounded-lg hover:opacity-50">
                        <img src={work.imagePath} alt={work.imagePath} class="w-full h-full object-cover rounded-lg">

                        <div class="absolute gap-2">
                            <button on:click={() => editWork(work)} class="btn btn-sm btn-info">Edit</button>
                            <button on:click={() => deleteWork(work)} class="btn btn-sm btn-accent">Delete</button>
                        </div>
                    </div>
                {/each}
                
                <button class="flex w-40 h-40 flex-col items-center bg-neutral justify-center rounded-lg" on:click={showModal}>
                    <SquarePlus class="w-10 h-10" />
                </button>
                <dialog id="modal" class="modal z-50">
                    <div class="modal-box flex flex-col gap-5">
                        <h3 class="text-lg font-bold">Your Work</h3>
                        <form id="workImageForm" class="flex max-md:flex-col items-start justify-start gap-5 w-full">
                            <label for="workImage" class="flex flex-col items-center justify-center gap-1">
                                {#if workImage}
                                    <img src={workImage} alt="User workImage" class="w-20 h-20 rounded-md object-cover">
                                {:else}
                                    <div class="border border-gray-500 p-3 rounded-md">
                                        <ImageUp class="w-20 h-20"/>
                                    </div>
                                {/if}
                                <div class="bg-base-100 px-3 py-2 rounded-lg text-xs">Upload workImage</div>
                                <input type="file" accept="images/*" name="workImage" id="workImage" on:change={handleFileChange} class="hidden">
                            </label>
                            <div class="flex flex-col gap-2 w-full h-40">
                                <textarea bind:value={description} name="description" class="textarea textarea-bordered textarea-lg w-full h-40 px-2 py-1" placeholder="Description of your work"></textarea>
                                <h1 class="text-xs font-gupter"> <span class="text-red-400">*</span> Your work description should be clear and concise</h1>
                            </div>
                        </form>
                        <div class="modal-action">
                            <button class="btn btn-sm btn-primary" on:click={handleAddWork}>Add Work</button>
                            <form method="dialog">
                                <button class="btn btn-sm">Cancel</button>
                            </form>
                        </div>
                    </div>
                </dialog>

                <div class="absolute bottom-5 right-5">
                    <button class="btn btn-primary btn-sm" type="submit" on:click={handleWorkSubmit}>
                        {#if loading}
                            <span class="loading loading-spinner loading-xs"></span>
                        {:else}
                            Finish & Apply <ArrowUpFromLine class="w-4 h-4" />
                        {/if}
                    </button>
                </div>
            </form>
        </div>
    {/if}
{/if}