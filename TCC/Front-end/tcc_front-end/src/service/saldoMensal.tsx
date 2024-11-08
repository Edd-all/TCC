import { axiosInstance } from "../api/axiosInstance";

export const getSaldoMensal = async () => {
    // terei que mudar para pegar o id do usuario
    try {
        const response = await axiosInstance.get(`/saldoMensal/calcular`); 
        return response.data;
    } catch (error) {
        console.error("Erro ao calcular o saldo mensal", error);
        throw error;
    }
};

export const getSaldosMensais = async () => {
    try {
        const response = await axiosInstance.get(`/saldoMensal/listar`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar os saldos Mensais", error);
        throw error;
    }
};


