<script lang="ts">
    import Measurements from "$lib/components/measurements/measurements.svelte";
    import DeliveryInformation from "$lib/components/productStuff/deliveryInformation.svelte";
    import OrderOptions from "$lib/components/productStuff/orderOptions.svelte";
    import PaymentInfo from "$lib/components/payment/paymentInfo.svelte";
    import ProductDescription from "$lib/components/productStuff/productDescription.svelte";
    import ProductImageUpload from "$lib/components/productStuff/productImageUpload.svelte";
    import { getCurrentUser } from "$lib";
    import { onMount } from "svelte";
    import { springbase } from "$lib/utils/springbase";
    import { currentUser } from "$lib/stores/currentUser";
    import { goto } from "$app/navigation";

    let images: any[] = [];
    let description: string = "";
    let paymentInfo: any = {
        id: "",
        cardNumber: "",
        cardHolderName: "",
        expiryDate: "",
        cvv: ""
    }
    let measurements: any = {
        id: "",
        gender: "",
        age: "",
        weight: "",
        height: "",
        neck: "",
        shoulder: "",
        chest: "",
        upperBust: "",
        lowerBust: "",
        armhole: "",
        sleeveLength: "",
        bicep: "",
        elbow: "",
        wrist: "",
        backWidth: "",
        frontLength: "",
        backLength: "",
        verticalTrunk: "",
        bustToWaist: "",
        waist: "",
        hip: "",
        rise: "",
        waistToKnee: "",
        knee: "",
        calf: "",
        inseam: "",
        outseam: "",
        waistToHem: ""
    };

    let orderingOptions: any = {
        title: "",
        category: "",
        privacy: "public",
        tailors: [],
        initialBargain: 0
    }

    let deliveryInformation: any = {
        id: $currentUser.id || "",
        name: $currentUser.name || "",
        email: $currentUser.email || "",
        phone: $currentUser.phone || "",
        address: $currentUser.address || ""
    }

    const publishOrder = async () => {
        let formData = new FormData();

        images.forEach((image, index) => {
            formData.append(`orderImages`, image.file, image.file.name);
        });

        formData.append("description", description);

        for (const key in paymentInfo) {
            if(key === "id"){
                continue;
            }
            if (paymentInfo.hasOwnProperty(key)) {
                formData.append(key, paymentInfo[key]);
            }
        }

        for (const key in measurements) {
            if(key === "id"){
                continue;
            }
            if (measurements.hasOwnProperty(key)) {
                formData.append(key, measurements[key]);
            }
        }

        for (const key in orderingOptions) {
            if(key === "tailors"){
                continue;
            }
            if(key === "id"){
                continue;
            }
            if (orderingOptions.hasOwnProperty(key)) {
                formData.append(key, Array.isArray(orderingOptions[key]) ? JSON.stringify(orderingOptions[key]) : orderingOptions[key]);
            }
        }

        for (const key in deliveryInformation) {
            if(key === "id"){
                continue;
            }
            if (deliveryInformation.hasOwnProperty(key)) {
                formData.append(key, deliveryInformation[key]);
            }
        }

        formData.append("usersId", $currentUser.id);

        console.log({
            ...orderingOptions,
            ...deliveryInformation,
            ...measurements,
            ...paymentInfo,
            userId: $currentUser.id
        });

        const res = await springbase.collection("forder").create(formData);

        goto("/app/orders");
    }

    const getMeasurements = async () => {
        const customerId = $currentUser.customerId;
        const res = await springbase.collection("measurements").getList(1, 5, {
            filter: `customer_id = ${customerId}`
        });
        measurements = { ...res.content[0] };
    }

    onMount(async () => {
        await getCurrentUser();
        paymentInfo = await springbase.collection("paymentInfo").getOne($currentUser.id);
        await getMeasurements();
    })
</script>

<div class="flex max-md:flex-col w-full h-full p-2 md:p-3 gap-5">
   <div class="flex flex-col gap-10  md:w-3/6 md:h-3/6 min-h-2/5 ">
        <div class="flex items-center justify-center">
            <ProductImageUpload isEditing={true} bind:images={images}/>
        </div>
        
        <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
            <Measurements edits={true} bind:measurements={measurements} gridCols={2}/>
        </div>
   </div>

    <div class="flex flex-col gap-3 p-2 md:p-3 md:w-3/6">
        <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
            <ProductDescription edits={true} isEditing={true} bind:description={description}/>
        </div>
        <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
            <OrderOptions edits={true} isEditing={true} bind:orderingOptions={orderingOptions}/>
        </div>
        <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
            <PaymentInfo edits={true} bind:cardInfo={paymentInfo} />
        </div>
        <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
            <DeliveryInformation edits={true} bind:deliveryInformation={deliveryInformation}/>
        </div>
        <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
            <button class="btn btn-primary" on:click={publishOrder}>Publish Order</button>
        </div>
    </div>
</div>