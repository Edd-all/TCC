import { axiosInstance } from "../api/axiosInstance";

export const getMetaFutura = async (id: number) => {
    try {
        const response = await axiosInstance.get(`/metasFuturas/listarPorId/${id}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar a Meta", error);
        throw error;
    }
};

export const getMetasFuturas = async () => {
    try {
        const response = await axiosInstance.get(`/metasFuturas/listar`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar as Metas", error);
        throw error;
    }
};

export const postMetaFutura = async (metaFuturaData: {
    nome: string;
    valorGuardar: number;
    usuario: number; //usuario id
}) => {
    try {
        const response = await axiosInstance.post("/metasFuturas/cadastrar", metaFuturaData);
        return response.data;  // Retorna a resposta da API
    } catch (error) {
        console.error("Erro ao cadastrar a meta", error);
        throw error;
    }
};

