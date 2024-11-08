import { axiosInstance } from "./axiosInstance";
import { jwtDecode } from "jwt-decode";


export const postLogin = async (loginData: { username: string, password: string }) => {
    try {
        const response = await axiosInstance.post("/auth/login", loginData);
        const token = response.data.token;
        //const expirationTime = new Date().getTime() + 1 * 60 * 1000; // Define a expiração para 1 minuto para fins de teste
        const expirationTime = new Date().getTime() + 30 * 60 * 1000;  // Define a expiração para 30 minutos

        if (token) {
            localStorage.setItem("token", token);
            localStorage.setItem("token_expiration", expirationTime.toString());  // Salva a hora de expiração
            console.log('Token salvo:', token);
        } else {
            console.error("Token não foi encontrado no corpo da resposta");
        }
        return response.data;
    } catch (error) {
        console.error("Erro no login", error);
        throw error;
    }
};

export const logout = () => {
    localStorage.removeItem("token");  // Remove o token
};


export const postAtivacao = async (uuid: string) => {
    try {
        const token = localStorage.getItem("token"); // Obtenha o token do localStorage
        const response = await axiosInstance.get(`/auth/verificarCadastro/${uuid}`, {
            headers: {
                Authorization: `Bearer ${token}`, // Use o token aqui
            },
        });
        return response.data; // Retorna a resposta da API
    } catch (error) {
        console.error('Erro ao ativar a conta', error);
        throw error;
    }
};

interface JwtPayload {
    sub: string;  // Normalmente o ID do usuário está no 'sub' (subject) mas neste caso é o login
    username: string;
  }

  export const getUserIdFromToken = (): { userId: string, username: string } | null => {
    const token = localStorage.getItem('token');
    if (token) {
        try {
            const decodedToken = jwtDecode<JwtPayload>(token);
            console.log('(Login do usuário):', decodedToken.sub);
            return { userId: decodedToken.sub, username: decodedToken.username };
        } catch (error) {
            console.error('Erro ao decodificar o token:', error);
            return null;
        }
    }
    return null;
};
  

