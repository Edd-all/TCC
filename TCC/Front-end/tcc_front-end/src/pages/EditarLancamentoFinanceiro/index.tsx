import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Navbar } from '../../components/Navbar';
import { Footer } from '../../components/Footer';
import { getLancamentoFinanceiroByLoginAndId, updateLancamentoFinanceiro } from '../../service/lacancamentoFinanceiro';
import { getUserLoginFromToken } from '../../api/auth';
import './style.css';

export default function EditarLancamentoFinanceiro() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [nome, setNome] = useState('');
  const [descricao, setDescricao] = useState('');
  const [valor, setValor] = useState(0);
  const [tipoLancamento, setTipoLancamento] = useState('R'); // 'R' para Receita ou 'D' para Despesa
  const [tipoAgendamento, setTipoAgendamento] = useState(''); // 'D', 'S', ou 'M'
  const [data, setData] = useState('');
  const [diaSemana, setDiaSemana] = useState('');
  const [diaMes, setDiaMes] = useState<number | null>(null);

  useEffect(() => {
    const fetchLancamento = async () => {
      const login = getUserLoginFromToken();
      if (login && id) {
        const lancamento = await getLancamentoFinanceiroByLoginAndId(login, parseInt(id));
        setNome(lancamento.nome);
        setDescricao(lancamento.descricao);
        setValor(Math.abs(lancamento.valor));
        setTipoLancamento(lancamento.tipoLancamento);
        setTipoAgendamento(lancamento.tipoAgendamento);
        setData(lancamento.diaEspecifico || '');
        setDiaSemana(lancamento.diaSemana?.dayOfWeek || '');
        setDiaMes(lancamento.diaMes || null);
      }
    };
    fetchLancamento();
  }, [id]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const login = getUserLoginFromToken();
    
    if (login) {
      const updatedLancamento = {
        id,
        nome,
        descricao,
        valor: tipoLancamento === 'D' ? valor * -1 : valor, // Valores negativos para despesas
        tipoLancamento,
        tipoAgendamento,
        diaEspecifico: tipoAgendamento === 'D' ? new Date(data) : "2024-01-01",
        diaSemana: tipoAgendamento === 'S' ? { dayOfWeek: diaSemana } : "SEGUNDA_FEIRA",
        diaMes: tipoAgendamento === 'M' ? diaMes : 1
      };

      await updateLancamentoFinanceiro(login, id, updatedLancamento);
      alert('Lançamento atualizado com sucesso!');
      navigate('/estatisticas');
    }
  };

  return (
    <div className="home-container">
      <Navbar />

      <div className="content">
        <div className="add-lancamento">
          <h2>Editar Lançamento</h2>
          <form onSubmit={handleSubmit} className="lancamento-form">
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
              <option value="R">Receita</option>
              <option value="D">Despesa</option>
            </select>

            <label htmlFor="tipoAgendamento">Tipo de Agendamento</label>
            <select 
              id="tipoAgendamento" 
              value={tipoAgendamento} 
              onChange={(e) => setTipoAgendamento(e.target.value)}
            >
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
                  value={diaMes || 0} 
                  onChange={(e) => setDiaMes(Number(e.target.value))} 
                  placeholder="Dia do mês"
                />
              </div>
            )}

            <button type="submit" className="submit-btn">Salvar Alterações</button>
          </form>
        </div>
      </div>

      <Footer />
    </div>
  );
}