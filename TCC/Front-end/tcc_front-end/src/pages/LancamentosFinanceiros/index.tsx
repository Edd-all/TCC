import { Navbar } from '../../components/Navbar'
import { Footer } from '../../components/Footer'
import './style.css';
import React, { useState } from 'react';
import { postLancamentoFinanceiro } from '../../service/lacancamentoFinanceiro';
import { postAgendamento } from '../../service/agendamento';

export function LancamentosFinanceiros() {
    const [nome, setNome] = useState('');
    const [valor, setValor] = useState(0);
    const [tipo, setTipo] = useState('');
    const [descricaoAgendamento, setDescricaoAgendamento] = useState('');
    const [tipoAgendamento, setTipoAgendamento] = useState('');
    const [data, setData] = useState('');
    const [diaSemana, setDiaSemana] = useState('');
    const [diaMes, setDiaMes] = useState(0);

    const handleAddLancamento = async (event: React.FormEvent) => {
        event.preventDefault();
        const lancamentoData = { nome, valor, tipo, usuario: 1 }; // Substitua o id do usuário conforme necessário
        try {
            await postLancamentoFinanceiro(lancamentoData);
            alert('Lançamento adicionado com sucesso!');
        } catch (error) {
            console.error('Erro ao adicionar o lançamento financeiro', error);
        }
    };

    const handleAddAgendamento = async (event: React.FormEvent) => {
        event.preventDefault();
        const agendamentoData = {
            tipoAgendamento,
            tipoLancamento: tipo,
            descricao: descricaoAgendamento,
            valor,
            data: new Date(data),
            diaSemana,
            diaMes,
            lancamentoFinanceiro: 5 // ID do lançamento financeiro, pode ser passado conforme necessário
        };
        try {
            await postAgendamento(agendamentoData);
            alert('Agendamento criado com sucesso!');
        } catch (error) {
            console.error('Erro ao agendar o lançamento financeiro', error);
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

                        <label htmlFor="valor">Valor</label>
                        <input 
                            type="number" 
                            id="valor" 
                            value={valor} 
                            onChange={(e) => setValor(Number(e.target.value))} 
                            placeholder="Valor do lançamento"
                        />

                        <label htmlFor="tipo">Tipo</label>
                        <select 
                            id="tipo" 
                            value={tipo} 
                            onChange={(e) => setTipo(e.target.value)}
                        >
                            <option value="">Selecione...</option>
                            <option value="R">Receita</option>
                            <option value="D">Despesa</option>
                        </select>

                        <button type="submit" className="submit-btn">Adicionar Lançamento</button>
                    </form>
                </div>

                <div className="agendar-lancamento">
                    <h2>Agendar Lançamento</h2>
                    <form onSubmit={handleAddAgendamento} className="agendamento-form">
                        <label htmlFor="descricaoAgendamento">Descrição</label>
                        <input 
                            type="text" 
                            id="descricaoAgendamento" 
                            value={descricaoAgendamento} 
                            onChange={(e) => setDescricaoAgendamento(e.target.value)} 
                            placeholder="Descrição do agendamento"
                        />

                        <label htmlFor="tipoAgendamento">Tipo de Agendamento</label>
                        <select 
                            id="tipoAgendamento" 
                            value={tipoAgendamento} 
                            onChange={(e) => setTipoAgendamento(e.target.value)}
                        >
                            <option value="">Selecione...</option>
                            <option value="especifica">Data Específica</option>
                            <option value="semanal">Dia da Semana</option>
                            <option value="mensal">Dia do Mês</option>
                            
                        </select>

                        {tipoAgendamento === 'especifica' && (
                            <div>
                                <label htmlFor="data">Data</label>
                                <input 
                                    type="date" 
                                    id="data" 
                                    value={data} 
                                    onChange={(e) => setData(e.target.value)} 
                                />
                            </div>
                        )}

                        {tipoAgendamento === 'semanal' && (
                            <div>
                                <label htmlFor="diaSemana">Dia da Semana</label>
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

                        {tipoAgendamento === 'mensal' && (
                            <div>
                                <label htmlFor="diaMes">Dia do Mês</label>
                                <input 
                                    type="number" 
                                    id="diaMes" 
                                    value={diaMes} 
                                    onChange={(e) => setDiaMes(Number(e.target.value))} 
                                    placeholder="Dia do mês"
                                />
                            </div>
                        )}

                        <button type="submit" className="submit-btn">Agendar Lançamento</button>
                    </form>
                </div>
            </div>
            <Footer />
        </div>
    );
}
