<script lang="ts">
    import { getCurrentUser } from "$lib";
    import PaymentInfo from "$lib/components/payment/paymentInfo.svelte";
    import { currentUser } from "$lib/stores/currentUser";
    import { springbase } from "$lib/utils/springbase";
    import { onMount } from "svelte";
    
    let paymentInfo: any = {
        id: "",
        cardNumber: "",
        cardHolderName: "",
        expiryDate: "",
        cvv: "",
    }

    const savePaymentInfo = async() => {
        if(paymentInfo.id && paymentInfo.id !== ""){
            const res = await springbase.collection("paymentInfo").update(paymentInfo.id, paymentInfo);
            paymentInfo = {...res};
        } else{
            paymentInfo.userId = $currentUser.id;
            const res = await springbase.collection("paymentInfo").create(paymentInfo);
            paymentInfo = {...res};
        }
    }

    onMount(async() => {
        await getCurrentUser();
        const pI = paymentInfo;
        paymentInfo = await springbase.collection("paymentInfo").getOne($currentUser.id);
        if(!paymentInfo){
            paymentInfo = pI;
        }
    })
</script>
<div class="flex flex-col gap-10 p-5 m-5 shadow-lg md:w-3/5">
    <PaymentInfo bind:cardInfo={paymentInfo}/>
    <div class="flex w-full justify-end gap-2">
        <button class="btn btn-primary btn-sm" on:click={savePaymentInfo}>Save</button>
    </div>
</div>