import axios from "axios";

export const axiosInstance = axios.create({
    baseURL: "http://localhost:8080",
});

axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("myAppName_token");
        const expirationTime = localStorage.getItem("myAppName_token_expiration");

        if (token && expirationTime && new Date().getTime() < parseInt(expirationTime)) {
            config.headers["Authorization"] = `Bearer ${token}`;
        } else {
            // Redirecionar para login ou dar erro de token expirado
            console.error("Token expirado");
        }

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);