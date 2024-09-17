import { writable } from "svelte/store";

export let currentUser = writable({
    id: "",
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
        name: "",
        gender: "",
        address: "",
        email: "",
        phone: "",
        roles: "",
        avatar: ""
    });
}