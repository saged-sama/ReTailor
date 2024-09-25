import { writable } from "svelte/store";

export let currentUser = writable({
    id: "",
    customerId: "",
    name: "",
    gender: "",
    address: "",
    email: "",
    phone: "",
    roles: "",
    avatar: ""
});


export const resetCurrentUser = () => {
    currentUser.set({
        id: "",
        customerId: "",
        name: "",
        gender: "",
        address: "",
        email: "",
        phone: "",
        roles: "",
        avatar: ""
    });
}