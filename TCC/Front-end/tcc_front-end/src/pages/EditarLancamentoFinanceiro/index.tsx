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
        <h2>Editar Lançamento</h2>
        <form onSubmit={handleSubmit} className="lancamento-form">
          <label>Nome</label>
          <input value={nome} onChange={(e) => setNome(e.target.value)} />

          <label>Descrição</label>
          <textarea value={descricao} onChange={(e) => setDescricao(e.target.value)} />

          <label>Valor</label>
          <input
            type="number"
            value={valor}
            onChange={(e) => setValor(Number(e.target.value))}
          />

          <label>Tipo de Lançamento</label>
          <select
            value={tipoLancamento}
            onChange={(e) => setTipoLancamento(e.target.value)}
          >
            <option value="R">Receita</option>
            <option value="D">Despesa</option>
          </select>

          <label>Tipo de Agendamento</label>
          <select
            value={tipoAgendamento}
            onChange={(e) => setTipoAgendamento(e.target.value)}
          >
            <option value="">Sem agendamento</option>
            <option value="D">Dia Específico</option>
            <option value="S">Dia da Semana</option>
            <option value="M">Dia do Mês</option>
          </select>

          {tipoAgendamento === 'D' && (
            <>
              <label>Data</label>
              <input
                type="date"
                value={data}
                onChange={(e) => setData(e.target.value)}
              />
            </>
          )}

          {tipoAgendamento === 'S' && (
            <>
              <label>Dia da Semana</label>
              <select
                value={diaSemana}
                onChange={(e) => setDiaSemana(e.target.value)}
              >
                <option value="">Selecione</option>
                <option value="MONDAY">Segunda-feira</option>
                <option value="TUESDAY">Terça-feira</option>
                <option value="WEDNESDAY">Quarta-feira</option>
                <option value="THURSDAY">Quinta-feira</option>
                <option value="FRIDAY">Sexta-feira</option>
                <option value="SATURDAY">Sábado</option>
                <option value="SUNDAY">Domingo</option>
              </select>
            </>
          )}

          {tipoAgendamento === 'M' && (
            <>
              <label>Dia do Mês</label>
              <input
                type="number"
                value={diaMes || ''}
                onChange={(e) => setDiaMes(Number(e.target.value))}
                min="1"
                max="31"
              />
            </>
          )}

          <button type="submit" className="submit-btn">Salvar</button>
        </form>
      </div>
      <Footer />
    </div>
  );
}