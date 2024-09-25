<script lang="ts">
    import { page } from '$app/stores';
    import { PUBLIC_API_URL } from '$env/static/public';
    import { getRating } from '$lib';
    import { currentUser } from '$lib/stores/currentUser';
    import { springbase } from '$lib/utils/springbase';
    import { ImageUp, PlusCircle, Star } from 'lucide-svelte';
    import { onMount } from 'svelte';

    interface Work {
        workFormData: FormData;
        workImage: File | null;
        imagePath: string;
        description: string;
    }
    let currentPortImage: string | null = null;
    let currentPortDesc: string | null = null;
    let tailor: any = null;
    let portfolio: any = null;
    let portfolioImages: any[] = [];
    let works: Work[] = [];
    let workImage: string | null = null;
    let currentImage: File | null = null;
    let description: string = "";

    const getCustomersOfTailors = async (tailor: any) => {
            const res = await fetch(`${PUBLIC_API_URL}/api/collections/customers/records/${tailor.users.id}`);
            const customer = await res.json();
            return {
                ...tailor,
                customer: customer,
            };
    };

    const getTailor = async (tailorId: string) => {
        const newTailor = await springbase.collection("tailors").getOne(tailorId);
        tailor = {...await getCustomersOfTailors(newTailor)};
    }

    const getPortfolio = async(tailorId: string) => {
        const newPortfolio = await springbase.collection("portfolio").getOne(tailorId);
        portfolio = {...newPortfolio};
    }

    const getPortfolioImages = async(portfolioId: string) => {
        const newPortfolioImages = await springbase.collection("portfolioImages").getOne(portfolioId);
        portfolioImages = [...newPortfolioImages];
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

    const handleAddWork = async() => {
        const workFormData = new FormData(document.getElementById("workImageForm") as HTMLFormElement);
        // console.log(workFormData.get("workImage"), workFormData.get("description"));
        works = [...works, { workFormData: workFormData, workImage: currentImage, imagePath: workImage as string, description: description}];
        workFormData.append("portfolioId", portfolio.id);
        const res = await springbase.collection("portfolioImages").create(workFormData);
        await getPortfolioImages(portfolio.id);
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

    const showWork = (portfolioImage: any) => {
        currentPortImage = portfolioImage.image;
        currentPortDesc = portfolioImage.description;
        const modalElement: any = document.getElementById("workModal");
        modalElement.showModal();
    }

    const deleteWork = (work: Work) => {
        works = works.filter(w => w !== work);
        portfolioImages = portfolioImages.filter(w => w !== work);
    }
    

    $: tailorId = $page.params.tailorid;
    onMount(async () => {
        await getTailor(tailorId);
        await getPortfolio(tailorId);
        await getPortfolioImages(portfolio.id);

        console.log(portfolio);
        console.log(portfolioImages)
    });
    
</script>

{#if tailor}
    <div class="flex flex-col gap-10 p-5 md:p-10 items-center justify-center">
        <div class="flex flex-col md:w-1/2 gap-3 md:flex-row max-md:items-center border border-gray-700 p-5 rounded-md">
            <div class="w-32 h-32 rounded-lg">
                <!-- {#if tailor.cutomer} -->
                    <img src={`${PUBLIC_API_URL}/api/files/users/${tailor.users?.id}/${tailor.customer.avatar}`} alt={tailor?.name} class="w-full h-full object-cover rounded-lg" />
                <!-- {/if} -->
            </div>
            <div class="flex flex-col max-md:items-center gap-5">
                <div class="flex flex-col max-md:items-center">
                    <h1 class="text-2xl font-bold text-warning font-bona-nova-sc">{tailor.name}</h1>
                    <p class="flex items-center gap-2 text-info font-bold"> <Star class="fill-yellow-300"/> {getRating(tailor.reviewAndRating)}</p>    
                </div>
                <p class="prose">{tailor.bio}</p>
                <p class="text-gupter">Skills: {tailor.skills}</p>
            </div>
        </div>

        <div class="flex flex-col md:w-1/2 gap-3 border border-gray-700 p-2 md:p-5 rounded-md">
            {#if portfolio}
                <div class="flex flex-col gap-2 p-2 border-b border-gray-800">
                    <h1 class="text-xl text-secondary font-bona-nova">{portfolio.title}</h1>
                    <p class="prose">{portfolio.description}</p>
                </div>

                <div class="grid md:grid-cols-4 gap-2 grid-cols-2">
                    {#each portfolioImages as portfolioImage}
                        <button class="md:h-44" on:click={() => showWork(portfolioImage)}>
                            <img src={`${PUBLIC_API_URL}/api/files/portfolioImages/${portfolioImage.id}/${portfolioImage.image}`} alt={portfolioImage.title} class="w-full h-full object-cover rounded-lg" />
                        </button>
                    {/each}
                    {#if tailor.users.id === $currentUser.id}
                        <button class="btn btn-neutral md:h-44" on:click={showModal}>
                            <PlusCircle />
                        </button>
                    {/if}
                </div>
            {:else}
                <div class="flex items-center gap-3 p-10"><span class="loading loading-md"></span>Wait wait wait</div>
            {/if}
        </div>
    </div>
{:else}
    <div class="flex flex-col w-screen h-screen items-center justify-center gap-2">
        <span class="loading loading-md"></span>
        <span>Wait a moment please</span>
    </div>
{/if}

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

<dialog id="workModal" class="modal">
  <div class="modal-box w-11/12 max-w-5xl">
    <div class="flex gap-3 max-md:flex-col py-4 overflow-auto">
        <img src="{PUBLIC_API_URL}/api/files/portfolio/habi/{currentPortImage}" class="md:w-1/2 rounded-lg" alt="{currentPortImage}">
        <div class="prose md:w-1/2">
            {currentPortDesc}
        </div>
    </div>
    <div class="modal-action">
      <form method="dialog">
        <button class="btn">Close</button>
      </form>
    </div>
  </div>
</dialog>