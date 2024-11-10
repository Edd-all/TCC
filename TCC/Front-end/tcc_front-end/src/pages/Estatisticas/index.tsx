import { Navbar } from '../../components/Navbar';
import { Footer } from '../../components/Footer';
import './style.css';
import { useEffect, useState } from 'react';
import { getLancamentosFinanceirosByLogin } from '../../service/lacancamentoFinanceiro';
import { getMetasFuturasByLogin } from '../../service/metasFuturas';
import { getUserIdFromToken } from '../../api/auth';
import { getSaldoMensalByLogin } from '../../service/saldoMensal';

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

interface MetaFutura {
    nome: string;
    valorGuardar: number;
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

const tipoLancamentoMapeado: { [key: string]: string } = {
    "R": "Receita",
    "D": "Despesa"
};

export function Estatisticas() {
    const [lancamentos, setLancamentos] = useState<LancamentoFinanceiro[]>([]);
    const [metasFuturas, setMetasFuturas] = useState<MetaFutura[]>([]);
    const [saldoTotal, setSaldoTotal] = useState<number | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            const userInfo = getUserIdFromToken();

            if (userInfo) {
                try {
                    const [dadosLancamentos, dadosMetas] = await Promise.all([
                        getLancamentosFinanceirosByLogin(userInfo.userId),
                        getMetasFuturasByLogin(userInfo.userId)
                    ]);
                    setLancamentos(dadosLancamentos);
                    setMetasFuturas(dadosMetas);
                } catch (error) {
                    console.error("Erro ao buscar dados:", error);
                } finally {
                    setLoading(false);
                }
            } else {
                console.error("Usuário não está logado ou token inválido");
            }
        };

        fetchData();
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

    const calcularSaldoTotal = async () => {
        try {
            const login = getUserIdFromToken();
            if (login && login.userId) {
                
                const response = await getSaldoMensalByLogin(login.userId);
                // console.log("Resposta do backend:", response);
                // console.log("Tipo de resposta:", typeof response);
    
                // Usando uma expressão regular para extrair o número da string
                const match = response.match(/[\d.-]+/);  // Regex para encontrar números e ponto (para valores decimais)
                if (match) {
                    const saldo = parseFloat(match[0]); // Pega o número da primeira correspondência
                    //console.log("Saldo após conversão:", saldo);
                    setSaldoTotal(saldo);
                } else {
                    console.error("Não foi possível extrair o número da resposta");
                    setSaldoTotal(0); 
                }
            } else {
                console.error("Login do usuário não encontrado.");
                setSaldoTotal(0); 
            }
        } catch (error) {
            console.error("Erro ao calcular o saldo total", error);
            setSaldoTotal(0); 
        }
    };


    const receitas = lancamentos.filter(lancamento => lancamento.tipoLancamento === "R");
    const despesas = lancamentos.filter(lancamento => lancamento.tipoLancamento === "D");

    return (
        <div className="home-container">
            <Navbar />
            <div className="content">
                <div className="lancamentos-container">
                    <div className="top-row">
                        <div className="receitas">
                            <h2>Receitas</h2>
                            {loading ? (
                                <p>Carregando...</p>
                            ) : receitas.length > 0 ? (
                                <ul>
                                    {receitas.map((lancamento, index) => (
                                        <li key={index}>
                                            <strong>{lancamento.nome}</strong>: <br />
                                            {lancamento.descricao} <br />
                                            R${lancamento.valor.toFixed(2)} ({tipoLancamentoMapeado[lancamento.tipoLancamento]}) <br />
                                            {formatarDataAgendamento(lancamento)}
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <p>Não há receitas para exibir.</p>
                            )}
                        </div>

                        <div className="despesas">
                            <h2>Despesas</h2>
                            {loading ? (
                                <p>Carregando...</p>
                            ) : despesas.length > 0 ? (
                                <ul>
                                    {despesas.map((lancamento, index) => (
                                        <li key={index}>
                                            <strong>{lancamento.nome}</strong>: <br />
                                            {lancamento.descricao} <br />
                                            R${lancamento.valor.toFixed(2)} ({tipoLancamentoMapeado[lancamento.tipoLancamento]}) <br />
                                            {formatarDataAgendamento(lancamento)}
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <p>Não há despesas para exibir.</p>
                            )}
                        </div>
                    </div>

                    <div className="metas-futuras">
                        <h2>Metas Futuras</h2>
                        {loading ? (
                            <p>Carregando...</p>
                        ) : metasFuturas.length > 0 ? (
                            <ul>
                                {metasFuturas.map((meta, index) => (
                                    <li key={index}>
                                        <strong>{meta.nome}</strong> <br/>
                                        R${meta.valorGuardar.toFixed(2)}
                                    </li>
                                ))}
                            </ul>
                        ) : (
                            <p>Não há metas futuras para exibir.</p>
                        )}
                    </div>

                    <div className="saldo-total-container">
                        <button className="calcular-saldo-btn" onClick={calcularSaldoTotal}>
                            Calcular Saldo Total
                        </button>
                        {saldoTotal !== null && saldoTotal !== undefined && (
                            <span className="saldo-total">
                                Saldo Total: R${saldoTotal.toFixed(2)}
                            </span>
                        )}
                    </div>

                </div>
            </div>
            <Footer />
        </div>
    );
}