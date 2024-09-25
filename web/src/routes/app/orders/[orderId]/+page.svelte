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
    import BargainAndMaterial from "$lib/components/productStuff/bargainAndMaterial.svelte";
    import { page } from "$app/stores"
    import ProductImagePreview2 from "$lib/components/productStuff/productImagePreview2.svelte";
    import CreateBargain from "$lib/components/productStuff/createBargain.svelte";
    import Bargains from "$lib/components/productStuff/bargains.svelte";

    let order: any;

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

    const getAllObjects = () => {
        images = order.images;
        description = order.description;

        paymentInfo = {
            id: order.id,
            cardNumber: order.cardNumber,
            cardHolderName: order.cardHolderName,
            expiryDate: order.expiryDate,
            cvv: order.cvv
        };

        measurements = {
            id: order.id,
            gender: order.gender,
            age: order.age,
            weight: order.weight,
            height: order.height,
            neck: order.neck,
            shoulder: order.shoulder,
            chest: order.chest,
            upperBust: order.upperBust,
            lowerBust: order.lowerBust,
            armhole: order.armhole,
            sleeveLength: order.sleeveLength,
            bicep: order.bicep,
            elbow: order.elbow,
            wrist: order.wrist,
            backWidth: order.backWidth,
            frontLength: order.frontLength,
            backLength: order.backLength,
            verticalTrunk: order.verticalTrunk,
            bustToWaist: order.bustToWaist,
            waist: order.waist,
            hip: order.hip,
            rise: order.rise,
            waistToKnee: order.waistToKnee,
            knee: order.knee,
            calf: order.calf,
            inseam: order.inseam,
            outseam: order.outseam,
            waistToHem: order.waistToHem
        };

        orderingOptions = {
            title: order.title,
            category: order.category,
            privacy: order.privacy,
            tailors: order.tailors,
            initialBargain: order.initialBargain
        };

        deliveryInformation = {
            receiverName: order.receiverName,
            email: order.email,
            phone: order.phone,
            address: order.address
        };
    }

    onMount(async () => {
        order = await springbase.collection("forder").getOne($page.params.orderId);
        console.log(order);
        getAllObjects();
    })
</script>

{#if order}
    <div class="flex justify-around w-full font-bona-nova md:hidden items-center">
        <div class="text-info text-xl">{order?.customer?.name}</div>
        <div class="text-warning">{order.status}</div>
    </div>

    <div class="flex max-md:flex-col w-full h-full p-2 md:p-3 gap-5">
    <div class="flex flex-col gap-10  md:w-3/6 md:h-3/6 min-h-2/5 ">
            <div class="flex items-center justify-center">
                <ProductImagePreview2 images={images}/>
            </div>
            
            <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
                <Measurements edits={ $currentUser.id === order.userId.id ? true : false } measurements={measurements} gridCols={2}/>
            </div>
    </div>

        <div class="flex flex-col gap-3 p-2 md:p-3 md:w-3/6">
            <div class="flex justify-around items-center w-full font-bona-nova">
                <div class="text-info text-xl"><span class="text-gray-300">Orderer:</span> {order?.customer?.name}</div>
                <div class="text-warning"><span class="text-gray-300">Current Stat:</span> {order.status}</div>
            </div>        
            <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
                <ProductDescription edits={ $currentUser.id === order.userId.id ? true : false } isEditing={ $currentUser.id === order.userId.id ? true : false } description={description}/>
            </div>
            <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
                <OrderOptions edits={ $currentUser.id === order.userId.id ? true : false } isEditing={ $currentUser.id === order.userId.id ? true : false } orderingOptions={orderingOptions}/>
            </div>
            {#if $currentUser.id === order.userId.id}
                <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
                    <PaymentInfo edits={ $currentUser.id === order.userId.id ? true : false } cardInfo={paymentInfo} />
                </div>
                <div class="w-full border border-gray-600 p-2 md:p-3 rounded-md">
                    <DeliveryInformation edits={ $currentUser.id === order.userId.id ? true : false } deliveryInformation={deliveryInformation}/>
                </div>
            {/if}
            {#if $currentUser.id !== order.userId.id}
                <div class="w-full border border-gray-600 p-2 rounded-md">
                    <CreateBargain forder={order} />
                </div>
            {/if}
            
            <Bargains forder={order} />
        </div>
    </div>
{/if}