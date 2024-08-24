
export const serializeNonPOJOs = (obj: any) => {
    return structuredClone(obj);
}

export const capitalizeFirstLetter = (str: string = "store") => {
    return str.charAt(0).toUpperCase() + str.slice(1);
}