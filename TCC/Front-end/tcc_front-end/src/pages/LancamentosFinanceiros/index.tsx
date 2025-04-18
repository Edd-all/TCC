import { Navbar } from '../../components/Navbar';
import { Footer } from '../../components/Footer';
import './style.css';
import React, { useState } from 'react';
import { postLancamentoFinanceiro } from '../../service/lacancamentoFinanceiro';
import { getUserIdFromToken } from '../../api/auth';

export function LancamentosFinanceiros() {
    const [nome, setNome] = useState('');
    const [descricao, setDescricao] = useState(''); 
    const [valor, setValor] = useState(0);
    const [tipoLancamento, setTipoLancamento] = useState('');
    const [tipoAgendamento, setTipoAgendamento] = useState('');
    const [data, setData] = useState('');
    const [diaSemana, setDiaSemana] = useState('');
    const [diaMes, setDiaMes] = useState(0);

    const handleAddLancamento = async (event: React.FormEvent) => {
        event.preventDefault();

        const userId = getUserIdFromToken();
        const userInfo = getUserIdFromToken();
        console.log(userInfo);

        if (!userId) {
            console.error('Usuário não está logado ou token inválido');
            return;
        }

        const lancamentoData = {
            nome,
            descricao,
            valor:tipoLancamento === 'D'? valor*-1: valor,
            tipoLancamento,
            tipoAgendamento,
            diaEspecifico: tipoAgendamento === 'D' ? new Date(data) : "2024-01-01",
            diaSemana: tipoAgendamento === 'S' ? diaSemana : "SEGUNDA_FEIRA",
            diaMes: tipoAgendamento === 'M' ? diaMes : 0,
            usuario: userInfo ? userInfo.userId : ""
        };

        try {
            await postLancamentoFinanceiro(lancamentoData);
            alert('Lançamento financeiro adicionado com sucesso!');
        } catch (error) {
            console.error('Erro ao adicionar o lançamento financeiro', error);
            alert("Erro ao adicionar Lançamento. Tente novamente.")
        }
    };

    return (
        <div className="home-container">
            <Navbar />

            <div className="content">
                <div className="add-lancamento">
                    <h2>Adicionar Lançamento</h2>
                    <form onSubmit={handleAddLancamento} className="lancamento-form">
                        <label htmlFor="nome">Nome</label>
                        <input 
                            type="text" 
                            id="nome" 
                            value={nome} 
                            onChange={(e) => setNome(e.target.value)} 
                            placeholder="Nome do lançamento"
                        />

                        <label htmlFor="descricao">Descrição</label>
                        <textarea
                            id="descricao"
                            value={descricao}
                            onChange={(e) => setDescricao(e.target.value)}
                            rows={3}
                            placeholder="Descrição do lançamento"
                            className="descricao-textarea"
                        ></textarea>

                        <label htmlFor="valor">Valor</label>
                        <input 
                            type="number" 
                            id="valor" 
                            value={valor} 
                            onChange={(e) => setValor(Number(e.target.value))} 
                            placeholder="Valor do lançamento"
                            min={0}
                        />

                        <label htmlFor="tipo">Tipo</label>
                        <select 
                            id="tipo" 
                            value={tipoLancamento} 
                            onChange={(e) => setTipoLancamento(e.target.value)}
                        >
                            <option value="">Selecione...</option>
                            <option value="R">Receita</option>
                            <option value="D">Despesa</option>
                        </select>

                        <label htmlFor="tipoAgendamento">Tipo de Agendamento</label>
                        <select 
                            id="tipoAgendamento" 
                            value={tipoAgendamento} 
                            onChange={(e) => setTipoAgendamento(e.target.value)}
                        >
                            <option value="">Selecione...</option>
                            <option value="D">Data Específica</option>
                            <option value="S">Dia da Semana</option>
                            <option value="M">Dia do Mês</option>
                        </select>

                        {tipoAgendamento === 'D' && (
                            <div>
                                <label htmlFor="data">Data</label>
                                <br/>
                                <input 
                                    type="date" 
                                    id="data" 
                                    value={data} 
                                    onChange={(e) => setData(e.target.value)} 
                                />
                            </div>
                        )}

                        {tipoAgendamento === 'S' && (
                            <div>
                                <label htmlFor="diaSemana">Dia da Semana</label>
                                <br/>
                                <select 
                                    id="diaSemana" 
                                    value={diaSemana} 
                                    onChange={(e) => setDiaSemana(e.target.value)}
                                >
                                    <option value="">Selecione...</option>
                                    <option value="DOMINGO">Domingo</option>
                                    <option value="SEGUNDA_FEIRA">Segunda-feira</option>
                                    <option value="TERCA_FEIRA">Terça-feira</option>
                                    <option value="QUARTA_FEIRA">Quarta-feira</option>
                                    <option value="QUINTA_FEIRA">Quinta-feira</option>
                                    <option value="SEXTA_FEIRA">Sexta-feira</option>
                                    <option value="SABADO">Sábado</option>
                                </select>
                            </div>
                        )}

                        {tipoAgendamento === 'M' && (
                            <div>
                                <label htmlFor="diaMes">Dia do Mês</label>
                                <br/>
                                <input 
                                    type="number" 
                                    id="diaMes" 
                                    value={diaMes} 
                                    onChange={(e) => setDiaMes(Number(e.target.value))} 
                                    placeholder="Dia do mês"
                                />
                            </div>
                        )}

                        <button type="submit" className="submit-btn">Adicionar Lançamento</button>
                    </form>
                </div>
            </div>
            <Footer />
        </div>
    );
}