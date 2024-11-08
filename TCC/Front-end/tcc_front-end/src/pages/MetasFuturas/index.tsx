import { Navbar } from '../../components/Navbar'
import { Footer } from '../../components/Footer'
import './style.css'
import { useState } from 'react'
//import axios from 'axios'
import { getUserIdFromToken } from '../../api/auth';
import { postMetaFutura } from '../../service/metasFuturas';

export function MetasFuturas() {
    const [nome, setNome] = useState('')
    const [valorGuardar, setValorGuardar] =  useState(0);


    const handleAddMeta = async (e: React.FormEvent) => {
        e.preventDefault()

        const userId = getUserIdFromToken();
        const userInfo = getUserIdFromToken();
        console.log(userInfo);

        if (!userId) {
            console.error('Usuário não está logado ou token inválido');
            return;
        }

        const metaFuturaData = {
            nome,
            valorGuardar,
            usuario: userInfo ? userInfo.userId : ""
        };

        try {
            await postMetaFutura(metaFuturaData);
            alert('Meta adicionada com sucesso!');
        } catch (error) {
            console.error("Erro ao adicionar meta", error)
            alert("Erro ao adicionar Meta. Tente novamente.")
        }
    }


    return (
        <div className="home-container">
            <Navbar />
            <div className="content">
                <form className="add-meta-form" onSubmit={handleAddMeta}>
                    <h1>Adicionar Nova Meta</h1>
                    
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
                    
                    <button type="submit" className="submit-btn">Adicionar Meta</button>
                </form>
            </div>
            <Footer />
        </div>
    )
}