export default function crud(incomingHeaders?: {[key: string]: string}){
    const headers: HeadersInit = {
        'Content-Type': 'application/json',
        "Access-Control-Request-Headers": "content-type",
        ...incomingHeaders
    }
    
    async function GET(url: string){
        const response = await fetch(url, {
            method: "GET",
            headers: headers
        });
    
        if(response.ok){
            return await response.json();
        } else {
            throw new Error(response.statusText);
        }
    }
    
    async function POST(url: string, body: any){
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body)
        });
    
        if(response.ok){
            return await response.json();
        } else {
            throw new Error(response.statusText);
        }
    }
    
    async function PATCH(url: string, body: any){
        const response = await fetch(url, {
            method: "PATCH",
            headers: headers,
            body: JSON.stringify(body)
        });
    
        if(response.ok){
            return await response.json();
        } else {
            throw new Error(response.statusText);
        }
    }
    
    async function DELETE(url: string){
        const response = await fetch(url, {
            method: "DELETE",
            headers: headers
        });
    
        if(response.ok){
            return await response.json();
        } else {
            throw new Error(response.statusText);
        }
    }

    return { GET, POST, PATCH, DELETE };
}