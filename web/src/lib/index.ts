import { springbase } from "./utils/springbase";
import { currentUser } from "./stores/currentUser";

export const serializeNonPOJOs = (obj: any) => {
    return structuredClone(obj);
}

export const capitalizeFirstLetter = (str: string = "store") => {
    return str.charAt(0).toUpperCase() + str.slice(1);
}

export const getCurrentUser = async () => {
    springbase.authStore.loadFromStorage();
    if(springbase.authStore.isValid){
        const cuserDet = await springbase.collection("users").getOne(springbase.authStore.model.id);
        const customerDet = await springbase.collection("customers").getFirstListItem("user_id", cuserDet.id);
        
        currentUser.set({
            ...customerDet,
            ...cuserDet,
            id: cuserDet.id,
            customerId: customerDet.id
        });
    }
        
}

export const getUser = async (userId: string) => {
    const cuserDet = await springbase.collection("users").getOne(springbase.authStore.model.id);
    const customerDet = await springbase.collection("customers").getFirstListItem("user_id", cuserDet.id);
    
    return {
        ...customerDet,
        ...cuserDet,
        id: cuserDet.id,
        customerId: customerDet.id
    };
}

export const getRating = (ratingsAndReviews: any[]) => {
    if(ratingsAndReviews.length === 0) return 0;
    let rating = 0;
    for(let i = 0; i < ratingsAndReviews.length; i++){
        rating += ratingsAndReviews[i].rating;
    }
    return (Math.round(rating * 10 / ratingsAndReviews.length))/10;
}