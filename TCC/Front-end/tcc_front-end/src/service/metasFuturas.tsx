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

export const getMetasFuturasByLogin = async (login: string) => {
    try {
        const response = await axiosInstance.get(`/metasFuturas/listarPorLogin?login=${login}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar os Lançamentos Financeiros por login", error);
        throw error;
    }
};

export const deleteMetasFuturasByLogin = async (login: string) => {
    try {
        const response = await axiosInstance.delete(`/metasFuturas/deletarPorLogin?login=${login}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar as metas por login", error);
        throw error;
    }
};

export const postMetaFutura = async (metaFuturaData: {
    nome: string,
    valorGuardar: number,
    usuario: string //login do usuario 
}) => {
    try {
        console.log(metaFuturaData)
        const response = await axiosInstance.post("/metasFuturas/cadastrar", metaFuturaData);
        return response.data;  // Retorna a resposta da API
    } catch (error) {
        console.error("Erro ao cadastrar a meta", error);
        throw error;
    }
};


export const getMetaFuturaByLoginAndId = async (login: string, id: number) => {
    try {
        const response = await axiosInstance.get(`/metasFuturas/listarPorLoginEId/${id}?login=${login}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar a Meta por login e ID", error);
        throw error;
    }
};

export const updateMetaFuturaByLoginAndId = async (
    login: string,
    id: number,
    metaFuturaData: {
        nome?: string,
        valorGuardar?: number
    }
) => {
    try {
        const response = await axiosInstance.put(
            `/metasFuturas/atualizarPorLoginEId/${id}?login=${login}`,
            metaFuturaData
        );
        return response.data;
    } catch (error) {
        console.error("Erro ao atualizar a Meta por login e ID", error);
        throw error;
    }
};

export const deleteMetaFuturaByLoginAndId = async (login: string, id: number) => {
    try {
        const response = await axiosInstance.delete(`/metasFuturas/deletarPorLoginEId/${id}?login=${login}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao deletar a Meta por login e ID", error);
        throw error;
    }
};