import { axiosInstance } from "../api/axiosInstance";

export const getAgendamento = async (id: number) => {
    try {
        const response = await axiosInstance.get(`/agendamento/listarPorId/${id}`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar o Agendamento", error);
        throw error;
    }
};

export const getAgendamentos = async () => {
    try {
        const response = await axiosInstance.get(`/agendamento/listar`);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar os Agendamentos", error);
        throw error;
    }
};

export const postAgendamento = async (agendamentoData: {
    tipoAgendamento: string;
    tipoLancamento: string;
    descricao: string;
    valor: number; 
    data: Date; // confirmar se funciona
    diaSemana: string;
    diaMes: number;
    lancamentoFinanceiro: number; //lancamento id
}) => {
    try {
        const response = await axiosInstance.post("/agendamento/adicionar", agendamentoData);
        return response.data;  // Retorna a resposta da API
    } catch (error) {
        console.error("Erro ao cadastrar o agendamento", error);
        throw error;
    }
};