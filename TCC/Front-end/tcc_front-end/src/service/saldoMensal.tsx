import { axiosInstance } from "../api/axiosInstance";

export const getMetaFutura = async () => {
    // terei que mudar para pegar o id do usuario
    try {
        const response = await axiosInstance.get(`/saldoMensal/calcular`); 
        return response.data;
    } catch (error) {
        console.error("Erro ao calcular o saldo mensal", error);
        throw error;
    }
};

export const getMetasFuturas = async () => {
    try {
        const response = await axiosInstance.get(`/saldoMensal/listar`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar os saldos Mensais", error);
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
        console.error("Erro ao cadastrar o Lançamento Financeiro", error);
        throw error;
    }
};

