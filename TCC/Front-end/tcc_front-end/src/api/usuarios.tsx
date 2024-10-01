import { axiosInstance } from "./axiosInstance";

export const getUsuario = async (id: number) => {
    try {
        const response = await axiosInstance.get(`/usuarios/listarPorId/${id}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar o usuário", error);
        throw error;
    }
};


export const postCadastro = async (usuarioData: {
    nome: string;
    login: string;
    senha: string;
    email: string;
}) => {
    try {
        const response = await axiosInstance.post("/auth/novoUsuario", usuarioData);
        return response.data;  // Retorna a resposta da API
    } catch (error) {
        console.error("Erro ao cadastrar o usuário", error);
        throw error;
    }
};