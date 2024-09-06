// See https://kit.svelte.dev/docs/types#app
// for information about these interfaces
declare global {
	namespace App {
		// interface Error {}
		// interface Locals {}
		// interface PageData {}
		// interface PageState {}
		// interface Platform {}
		enum Role{
			CUSTOMER = "CUSTOMER",
			TAILOR = "TAILOR",
			ADMIN = "ADMIN",
		}
		interface User{
			id: string;
			firstName: string;
			lastName: string;
			email: string;
			emailVerified: boolean;
			phone: string;
			role: Role;
			avatar: string;
			created: string;
			updated: string;
		}
	}
}

export {};
