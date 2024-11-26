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

  useEffect(() => {
    const fetchMeta = async () => {
      const login = getUserLoginFromToken();
      if (login && id) {
        const meta = await getMetaFuturaByLoginAndId(login, parseInt(id));
        setNome(meta.nome);
        setValorGuardar(meta.valorGuardar);
      }
    };
    fetchMeta();
  }, [id]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const login = getUserLoginFromToken();
    if (login) {
      const updatedMeta = { id, nome, valorGuardar };
      
      await updateMetaFutura(login, id, updatedMeta);
      alert('Meta atualizada com sucesso!');
      navigate('/estatisticas');
    }
  };

  return (
    <div className="home-container">
      <Navbar />
      <div className="content">
        <h2>Editar Meta</h2>
        <form onSubmit={handleSubmit} className="meta-form">
          <label>Nome</label>
          <input value={nome} onChange={(e) => setNome(e.target.value)} />

          <label>Valor a Guardar</label>
          <input type="number" value={valorGuardar} onChange={(e) => setValorGuardar(Number(e.target.value))} />

          <button type="submit">Salvar</button>
        </form>
      </div>
      <Footer />
    </div>
  );
}