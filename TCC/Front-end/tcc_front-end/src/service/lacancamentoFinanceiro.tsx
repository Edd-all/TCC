import { axiosInstance } from "../api/axiosInstance";

export const getLancamentoFinanceiro = async (id: number) => {
    try {
        const response = await axiosInstance.get(`/lancamentoFinanceiro/listarPorId/${id}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar o Lançamento Financeiro", error);
        throw error;
    }
};

export const getLancamentosFinanceiros = async () => {
    try {
        const response = await axiosInstance.get(`/lancamentoFinanceiro/listar`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar os Lançamentos Financeiros", error);
        throw error;
    }
};

export const getLancamentosFinanceirosByLogin = async (login: string) => {
    try {
        const response = await axiosInstance.get(`/lancamentoFinanceiro/listarPorLogin?login=${login}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar os Lançamentos Financeiros por login", error);
        throw error;
    }
};

export const deleteLancamentosFinanceirosByLogin = async (login: string) => {
    try {
        const response = await axiosInstance.delete(`/lancamentoFinanceiro/deletarPorLogin?login=${login}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar os Lançamentos Financeiros por login", error);
        throw error;
    }
};


export const postLancamentoFinanceiro = async (lancamentoData: {
            nome: string,
            descricao: string,
            valor: number,
            tipoLancamento: string,
            tipoAgendamento: string | null,
            diaEspecifico: Date | string,
            diaSemana: string | null,
            diaMes: number | null,
            usuario: string //login do usuario 
}) => {

    try {
        console.log(lancamentoData);
        const response = await axiosInstance.post("/lancamentoFinanceiro/cadastrar", lancamentoData);
        return response.data;  // Retorna a resposta da API
    } catch (error) {
        console.error("Erro ao cadastrar o Lançamento Financeiro", error);
        throw error;
    }
};