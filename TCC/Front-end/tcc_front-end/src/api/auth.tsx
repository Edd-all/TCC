import { axiosInstance } from "./axiosInstance";

export const postLogin = async (loginData: { username: string, password: string }) => {
    try {
        const response = await axiosInstance.post("/auth/login", loginData);
        const token = response.headers["authorization"];
        localStorage.setItem("token", token);  // Salva o token no localStorage
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
        const response = await axiosInstance.get(`/auth/verificarCadastro/${uuid}`, {headers: {
            Authorization: `Bearer ${uuid}`,
        },});
        return response.data; // Retorna a resposta da API
        
    } catch (error) {
        console.error('Erro ao ativar a conta', error);
        throw error;
    }
};

