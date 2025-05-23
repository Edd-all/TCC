import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Navbar } from '../../components/Navbar';
import { Footer } from '../../components/Footer';
import { getMetaFuturaByLoginAndId, updateMetaFutura } from '../../service/metasFuturas';
import { getUserLoginFromToken } from '../../api/auth';
import './style.css';

export default function EditarMetaFutura() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [nome, setNome] = useState('');
  const [valorGuardar, setValorGuardar] = useState(0);
  const [prioridade, setPrioridade] = useState('')

  useEffect(() => {
    const fetchMeta = async () => {
      const login = getUserLoginFromToken();
      if (login && id) {
        const meta = await getMetaFuturaByLoginAndId(login, parseInt(id));
        setNome(meta.nome);
        setValorGuardar(meta.valorGuardar);
        setPrioridade(meta.prioridade);
      }
    };
    fetchMeta();
  }, [id]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const login = getUserLoginFromToken();
    if (login) {
      const updatedMeta = { id, nome, valorGuardar, prioridade };
      
      await updateMetaFutura(login, id, updatedMeta);
      alert('Meta atualizada com sucesso!');
      navigate('/estatisticas');
    }
  };

  return (
    <div className="home-container">
      <Navbar />
      <div className="content">
        <form onSubmit={handleSubmit} className="add-meta-form">
          <h1>Editar Meta</h1>
          
          <label htmlFor="nome">Nome da Meta</label>
          <input
            id="nome"
            type="text"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
            required
          />
          
          <label htmlFor="valorGuardar">Valor a Guardar</label>
          <input
            id="valorGuardar"
            type="number"
            value={valorGuardar}
            onChange={(e) => setValorGuardar(Number(e.target.value))}
            required
          />

          <label htmlFor="prioridade">Prioridade</label>
          <select
            id="prioridade"
            value={prioridade}
            onChange={(e) => setPrioridade(e.target.value)}
            className="prioridade-select"
            required
          >
            <option value="A">Alta</option>
            <option value="M">Média</option>
            <option value="B">Baixa</option>
          </select>        
          
          <button type="submit" className="submit-btn">Salvar Alterações</button>
        </form>
      </div>
      <Footer />
    </div>
  );
}