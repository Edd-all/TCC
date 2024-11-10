import { Navbar } from '../../components/Navbar';
import { Footer } from '../../components/Footer';
import './style.css';
import { useEffect, useState } from 'react';
import { getLancamentosFinanceirosByLogin } from '../../service/lacancamentoFinanceiro';
import { getUserIdFromToken } from '../../api/auth';

interface LancamentoFinanceiro {
    nome: string;
    descricao: string;
    valor: number;
    tipoLancamento: string;
    tipoAgendamento: string;
    diaEspecifico: Date;
    diaSemana: {
        dayOfWeek: string;
    } | null;
    diaMes: number;
}

const diasDaSemanaMapeados: { [key: string]: string } = {
    "MONDAY": "Segunda-feira",
    "TUESDAY": "Terça-feira",
    "WEDNESDAY": "Quarta-feira",
    "THURSDAY": "Quinta-feira",
    "FRIDAY": "Sexta-feira",
    "SATURDAY": "Sábado",
    "SUNDAY": "Domingo"
};

// Mapeamento de tipo de lançamento
const tipoLancamentoMapeado: { [key: string]: string } = {
    "R": "Receita",
    "D": "Despesa"
};

export function Estatisticas() {
    const [lancamentos, setLancamentos] = useState<LancamentoFinanceiro[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchLancamentos = async () => {
            const userInfo = getUserIdFromToken();

            if (userInfo) {
                try {
                    const dados = await getLancamentosFinanceirosByLogin(userInfo.userId);
                    setLancamentos(dados);
                } catch (error) {
                    console.error("Erro ao buscar lançamentos financeiros:", error);
                } finally {
                    setLoading(false);
                }
            } else {
                console.error("Usuário não está logado ou token inválido");
            }
        };

        fetchLancamentos();
    }, []);

    const formatarDataAgendamento = (lancamento: LancamentoFinanceiro) => {
        if (lancamento.tipoAgendamento === 'D' && lancamento.diaEspecifico) {
            return `Data específica: ${new Date(lancamento.diaEspecifico).toLocaleDateString()}`;
        } else if (lancamento.tipoAgendamento === 'S' && lancamento.diaSemana) {
            const diaSemanaNome = diasDaSemanaMapeados[lancamento.diaSemana.dayOfWeek] || "Dia da semana desconhecido";
            return `Dia da semana: ${diaSemanaNome}`;
        } else if (lancamento.tipoAgendamento === 'M') {
            return `Dia do mês: ${lancamento.diaMes}`;
        }
        return '';
    };

    return (
        <div className="home-container">
            <Navbar />
            <div className="content">
                <div className="description">
                    <h1>Área de Estatísticas</h1>
                    <p>Aqui você pode ver gráficos dos seus lançamentos e acompanhar suas metas.</p>
                </div>

                <div className="lancamentos">
                    <h2>Lançamentos Financeiros</h2>
                    {loading ? (
                        <p>Carregando...</p>
                    ) : lancamentos.length > 0 ? (
                        <ul>
                            {lancamentos.map((lancamento, index) => (
                                <li key={index}>
                                    <strong>{lancamento.nome}</strong>: {lancamento.descricao} - R${lancamento.valor.toFixed(2)} ({tipoLancamentoMapeado[lancamento.tipoLancamento] || lancamento.tipoLancamento})
                                    <br />
                                    {formatarDataAgendamento(lancamento)}
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>Não há lançamentos financeiros para exibir.</p>
                    )}
                </div>
            </div>
            <Footer />
        </div>
    );
}