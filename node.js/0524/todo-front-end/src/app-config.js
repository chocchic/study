let backendHost;
const hostname=window && window.location && window.location.hostname;

if(hostname === 'localhost'){
    backendHost = "http://localhost";
}

export const API_BASE_URL = `${backendHost}`