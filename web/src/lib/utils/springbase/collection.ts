import { jwtDecode } from "jwt-decode";
import type AuthStore from "./authStore";
import crud from "./crud";

function urlSearchParametersFromObject(obj: { [key: string]: any }) {
    return Object.keys(obj).map((key) => {
        return encodeURIComponent(key) + '=' + encodeURIComponent(obj[key]);
    }).join('&');
}

export default class Collection{
    private collectionName: string;
    private baseurl: string;
    private authStore: AuthStore;
    private webSocketUrl: string;

    constructor (baseUrl:string, collectionName: string, authStore: AuthStore){
        if(baseUrl.endsWith("/")){
            baseUrl = baseUrl.slice(0, -1);
        }
        this.collectionName = collectionName;
        this.authStore = authStore;
        this.baseurl = baseUrl + "/api/collections/" + collectionName;
        this.webSocketUrl = this.baseurl.replace("http", "ws");
    }

    async create(data: object | FormData){
        let headers: any = {
            "Access-Control-Request-Method": "POST"
        };
        if(this.authStore.isValid){
            headers["Authorization"] = `Bearer ${this.authStore.token}`;
        }
        return await crud(headers).POST(`${this.baseurl}/records`, data);
    }

    async update(id: string, data: object){
        let headers: any = {
            "Access-Control-Request-Method": "PATCH"
        }
        if(this.authStore.isValid){
            headers["Authorization"] = `Bearer ${this.authStore.token}`;
        }
        return await crud(headers).PATCH(`${this.baseurl}/records/${id}`, data);
    }

    async delete(id: string){
        let headers: any = {
            "Access-Control-Request-Method": "PATCH"
        }
        if(this.authStore.isValid){
            headers["Authorization"] = `Bearer ${this.authStore.token}`;
        }
        return await crud(headers).DELETE(`${this.baseurl}/records/${id}`);
    }

    async getOne(id: string, options?: object){
        let url = `${this.baseurl}/records/${id}`;
        if(options){
            url += "?" + urlSearchParametersFromObject(options);
        }
        let headers: any = {
            "Access-Control-Request-Method": "GET"
        }
        if(this.authStore.isValid){
            headers["Authorization"] = `Bearer ${this.authStore.token}`;
        }
        return await crud(headers).GET(url);
    }

    async getList(page: number, perPage: number, options?: object){
        let url = `${this.baseurl}/records?page=${page}&perPage=${perPage}`;
        if(options){
            url += "&" + urlSearchParametersFromObject(options);
        }
        let headers: any = {
            "Access-Control-Request-Method": "GET"
        }
        if(this.authStore.isValid){
            headers["Authorization"] = `Bearer ${this.authStore.token}`;
        }
        return await crud(headers).GET(url);
    }

    async getFullList(options?: object){
        return await this.getList(1, 5000, options);
    }

    async getFirstListItem(filter: string, options?: object){
        options = { ...options, filter, skipTotal: 1 };
        return (await this.getList(1, 1, options));
    }

    subscribe() {
        return new WebSocket(this.webSocketUrl);
    }

    async authWithPassword(email: string, password: string){
        const resp = await crud().POST(`${this.baseurl}/auth-with-password`, { email, password });
        if(!resp){
            this.authStore.token = undefined;
            this.authStore.model = undefined;
            this.authStore.isValid = false;
            this.authStore.isAdmin = false;
        }
        localStorage.setItem("springbase_auth", resp.access_token);

        this.authStore.token = resp.access_token;
        const model: any = jwtDecode(this.authStore.token as string);
        this.authStore.model = {
            id: model?.userId,
            email: model?.sub,
            role: model?.authorities,
        }
        this.authStore.isValid = true;
        this.authStore.isAdmin = this.authStore.model?.role === "ADMIN";

        return resp.springbase_auth;
    }
}