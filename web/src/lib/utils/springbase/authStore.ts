import { jwtDecode } from 'jwt-decode';

export default class AuthStore {
    public token: string | undefined;
    public model: Object | undefined;
    public isValid: boolean = false;
    public isAdmin: boolean = false;

    constructor() {
        const springbase_auth = localStorage.getItem('springbase_auth');
        if (springbase_auth) {
            const auth = JSON.parse(springbase_auth);
            this.token = auth.token;
            this.model = auth.model;
            this.isValid = true;
            this.isAdmin = auth.model.role === 'ADMIN';
        }
    }

    loadFromCookie(cookie: string) {
        const tokenCookie = cookie.split('; ').find(row => row.startsWith('token='));

        if (!tokenCookie) {
            this.token = undefined;
            this.model = undefined;
            this.isValid = false;
            return;
        }

        this.token = tokenCookie.split('=')[1];
        try {
            this.isValid = this.checkTokenValidity();
            if(!this.isValid) throw new Error("JWT invalid");
            this.model = jwtDecode(this.token as string);
        } catch (e) {
            console.error('Invalid token or decoding failed', e);
            this.isValid = false;
            this.model = undefined;
            this.isAdmin = false;
        }
    }

    private checkTokenValidity(): boolean {
        const decoded = jwtDecode(this.token as string);
        if (!decoded) return false;
        const exp = decoded.exp ? new Date(decoded.exp * 1000) : new Date(0);
        return exp > new Date();
    }

    exportToCookie(options?: {
        expires?: string,
        path?: string,
        domain?: string,
        secure?: boolean,
        httpOnly?: boolean,
        maxAge?: number,
        sameSite?: string
    }) {
        if (!this.token) return;

        let cookie = `token=${this.token};`;

        if (options) {
            if (options.expires) {
                cookie += ` expires=${options.expires};`;
            }
            if (options.path) {
                cookie += ` path=${options.path};`;
            }
            if (options.domain) {
                cookie += ` domain=${options.domain};`;
            }
            if (options.secure) {
                cookie += ` secure;`;
            }
            if (options.httpOnly) {
                cookie += ` HttpOnly;`;
            }
            if (options.maxAge) {
                cookie += ` max-age=${options.maxAge};`;
            }
            if (options.sameSite) {
                cookie += ` SameSite=${options.sameSite};`;
            }
        }
        return cookie;
    }

    save(options?: {
        expires?: string,
        path?: string,
        domain?: string,
        secure?: boolean,
        httpOnly?: boolean,
        maxAge?: number,
        sameSite?: string
    }) {
        this.exportToCookie(options);
    }

    clear() {
        document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/;';
        this.token = undefined;
        this.model = undefined;
        this.isValid = false;
        this.isAdmin = false;
    }
}
