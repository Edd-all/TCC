import { Navbar } from '../../components/Navbar';
import { Footer } from '../../components/Footer';
import './style.css';
import { useEffect, useState } from 'react';
import { getLancamentosFinanceirosByLogin } from '../../service/lacancamentoFinanceiro';
import { getMetasFuturasByLogin } from '../../service/metasFuturas';

import { getUserIdFromToken, getUserLoginFromToken } from '../../api/auth';

import { getSaldoMensalByLogin } from '../../service/saldoMensal';

import { deleteLancamentoFinanceiroByLoginAndId } from '../../service/lacancamentoFinanceiro';
import { deleteMetaFuturaByLoginAndId } from '../../service/metasFuturas';

import { useNavigate } from 'react-router-dom';

import React, { ChangeEvent } from 'react';


interface LancamentoFinanceiro {
    id: number; 
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
    id: number; 
    nome: string;
    valorGuardar: number;
    prioridade: string;
}

const diasDaSemanaMapeados: { [key: string]: string } = {
    "null": "Selecione",
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

const tipoPrioridadeMapeada: { [key: string]: string } = {
    "A": "Alta",
    "M": "Média",
    "B": "Baixa"
};

export function Estatisticas() {
    const [lancamentos, setLancamentos] = useState<LancamentoFinanceiro[]>([]);
    const [metasFuturas, setMetasFuturas] = useState<MetaFutura[]>([]);
    const [saldoTotal, setSaldoTotal] = useState<number | null>(null);
    const [loading, setLoading] = useState(true);

        const [filtro, setFiltro] = useState<string>(''); // Define o tipo de filtro: 'data', 'diaSemana', 'diaMes'
        const [valorFiltro, setValorFiltro] = useState<string | number | Date>(''); // Valor do filtro selecionado
        const [lancamentosFiltrados, setLancamentosFiltrados] = useState<LancamentoFinanceiro[]>([]);

    const navigate = useNavigate();

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
            const dataFormatada = new Date(lancamento.diaEspecifico + 'T00:00:00').toLocaleDateString();
            return `Data específica: ${dataFormatada}`;
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


    const handleEditarLancamento = (id: number) => {
        console.log("Editar Lançamento ID:", id);
        // Redirecionar para a página de edição ou abrir modal
        navigate(`/editar-lancamento/${id}`);
    };
    
    const handleEditarMeta = (id: number) => {
        console.log("Editar Meta ID:", id);
        // Redirecionar para a página de edição ou abrir modal
        navigate(`/editar-meta/${id}`);
    };

    const handleDeletarLancamento = async (id: number) => {
        try {
            const login = getUserLoginFromToken(); // Obtém o login do token
            if (!login) {
                alert("Erro: Login não encontrado.");
                return;
            }
    
            await deleteLancamentoFinanceiroByLoginAndId(login, id); // Passa login e ID
            alert("Lançamento deletado com sucesso!");
    
            // Atualiza a lista de lançamentos localmente
            setLancamentos((prevLancamentos) =>
                prevLancamentos.filter((l) => l.id !== id)
            );
        } catch (error) {
            console.error("Erro ao deletar lançamento:", error);
            alert("Erro ao deletar lançamento.");
        }
    };
    
    const handleDeletarMeta = async (id: number) => {
        try {
            const login = getUserLoginFromToken(); // Obtém o login do token
            if (!login) {
                alert("Erro: Login não encontrado.");
                return;
            }
    
            await deleteMetaFuturaByLoginAndId(login, id); // Passa login e ID
            alert("Meta deletada com sucesso!");
    
            // Atualiza a lista de metas localmente
            setMetasFuturas((prevMetas) =>
                prevMetas.filter((meta) => meta.id !== id)
            );
        } catch (error) {
            console.error("Erro ao deletar meta:", error);
            alert("Erro ao deletar meta.");
        }
    };




    const aplicarFiltro = () => {
        if (!filtro) {
            // Sem filtro, mostrar todos os lançamentos
            setLancamentosFiltrados([]);
            return;
        }
    
        // Filtra os lançamentos com base no tipo de filtro selecionado
        const resultadosFiltrados = lancamentos.filter((lancamento) => {
            if (filtro === 'data') {
                // Filtro por data específica
                return valorFiltro && lancamento.diaEspecifico
                    ? new Date(lancamento.diaEspecifico).toLocaleDateString() ===
                      new Date(valorFiltro).toLocaleDateString()
                    : lancamento.tipoAgendamento === 'D'; // Apenas tipo
            } else if (filtro === 'diaSemana') {
                // Filtro por dia da semana
                return valorFiltro && lancamento.diaSemana
                    ? lancamento.diaSemana.dayOfWeek === valorFiltro
                    : lancamento.tipoAgendamento === 'S'; // Apenas tipo
            } else if (filtro === 'diaMes') {
                // Filtro por dia do mês
                return valorFiltro
                    ? lancamento.diaMes === Number(valorFiltro)
                    : lancamento.tipoAgendamento === 'M'; // Apenas tipo
            }
            return false;
        });
    
        // Reorganiza os lançamentos: os que atendem ao filtro vão para o início
        const organizados = [
            ...resultadosFiltrados,
            ...lancamentos.filter((lancamento) => !resultadosFiltrados.includes(lancamento)),
        ];
    
        setLancamentosFiltrados(organizados);
    };

// Dados para exibição: usa os filtrados ou o original
const dadosParaExibir = lancamentosFiltrados.length > 0 ? lancamentosFiltrados : lancamentos;

// Define receitas e despesas com base nos dados filtrados
const receitas = dadosParaExibir.filter(
    (lancamento) => lancamento.tipoLancamento === 'R'
);
const despesas = dadosParaExibir.filter(
    (lancamento) => lancamento.tipoLancamento === 'D'
);
    
    // Função para atualizar o valor do filtro
    const handleFiltroChange = (event: ChangeEvent<HTMLSelectElement | HTMLInputElement>) => {
        setFiltro(event.target.value);
        setValorFiltro('');
    };
    
    // Atualiza o valor do filtro
    const handleValorFiltroChange = (event: ChangeEvent<HTMLSelectElement | HTMLInputElement>) => {
        setValorFiltro(event.target.value);
    };


    return (
        <div className="home-container">
            <Navbar />
            <div className="content">
                
                        <div className="filtro-container">
                <label htmlFor="filtro">Filtrar por:</label>
                <select id="filtro" value={filtro} onChange={handleFiltroChange}>
                    <option value="">Selecione</option>
                    <option value="data">Data específica</option>
                    <option value="diaSemana">Dia da Semana</option>
                    <option value="diaMes">Dia do Mês</option>
                </select>

                {filtro === 'data' && (
                    <input
                        type="date"
                        value={valorFiltro as string}
                        onChange={handleValorFiltroChange}
                    />
                )}

                {filtro === 'diaSemana' && (
                    <select value={valorFiltro as string} onChange={handleValorFiltroChange}>
                        {Object.entries(diasDaSemanaMapeados).map(([key, value]) => (
                            <option key={key} value={key}>
                                {value}
                            </option>
                        ))}
                    </select>
                )}

                {filtro === 'diaMes' && (
                    <input
                        type="number"
                        min="1"
                        max="31"
                        value={valorFiltro as number}
                        onChange={handleValorFiltroChange}
                    />
                )}

                <button onClick={aplicarFiltro}>Aplicar Filtro</button>
        </div>

            
                <div className="lancamentos-container">
                    <div className="top-row">
                        <div className="receitas">
                            <h2>Receitas</h2>
                            {loading ? (
                                <p>Carregando...</p>
                            ) : receitas.length > 0 ? (

                                <ul>
                                    {receitas.map((lancamento) => (
                                        <li key={lancamento.id}>
                                            <strong>{lancamento.nome}</strong>: <br />
                                            {lancamento.descricao} <br />
                                            R${lancamento.valor.toFixed(2)} ({tipoLancamentoMapeado[lancamento.tipoLancamento]}) <br />
                                            {formatarDataAgendamento(lancamento)} <br />
                                            <button
                                                className="editar-btn"
                                                onClick={() => handleEditarLancamento(lancamento.id)}
                                            >
                                                Editar
                                            </button>
                                            <button
                                                className="deletar-btn"
                                                onClick={() => handleDeletarLancamento(lancamento.id)}
                                            >
                                                Deletar
                                            </button>
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
                                        {despesas.map((lancamento) => (
                                            <li key={lancamento.id}>
                                                <strong>{lancamento.nome}</strong>: <br />
                                                {lancamento.descricao} <br />
                                                R${lancamento.valor.toFixed(2)} ({tipoLancamentoMapeado[lancamento.tipoLancamento]}) <br />
                                                {formatarDataAgendamento(lancamento)} <br />
                                                <button
                                                    className="editar-btn"
                                                    onClick={() => handleEditarLancamento(lancamento.id)}
                                                >
                                                    Editar
                                                </button>
                                                <button
                                                    className="deletar-btn"
                                                    onClick={() => handleDeletarLancamento(lancamento.id)}
                                                >
                                                    Deletar
                                                </button>
                                            </li>
                                        ))}
                                    </ul>
                            ) : (
                                <p>Não há despesas para exibir.</p>
                            )}
                        </div>
                    </div>

                    <div className="metas-futuras">
                        <h2>Metas</h2>
                        {loading ? (
                            <p>Carregando...</p>
                        ) : metasFuturas.length > 0 ? (
                            <ul>

                                {metasFuturas.map((meta) => (

                                        <li key={meta.id} className={`meta-item prioridade-${meta.prioridade.toLowerCase()}`} >

                                            <strong>{meta.nome}</strong>: <br />
                                            R${meta.valorGuardar.toFixed(2)} <br />
                                            Prioridade: {tipoPrioridadeMapeada[meta.prioridade]} <br />
                                            <button
                                                className="editar-btn"
                                                onClick={() => handleEditarMeta(meta.id)}
                                            >
                                                Editar
                                            </button>
                                            <button
                                                className="deletar-btn"
                                                onClick={() => handleDeletarMeta(meta.id)}
                                            >
                                                Deletar
                                            </button>
                                        </li>
                                    ))}
                            </ul>
                        ) : (
                            <p>Não há metas para exibir.</p>
                        )}
                    </div>                        
                    <div className="saldo-total-container">
                        <button className="calcular-saldo-btn" onClick={calcularSaldoTotal}>
                            Calcular Saldo Total
                        </button>
                        <br/>
                        <br/>
                        {saldoTotal !== null && saldoTotal !== undefined && (
                            <span  className={`saldo-total ${saldoTotal > 0 ? 'positivo' : saldoTotal < 0 ? 'negativo' : ''}`}>
                                
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