import { Navbar } from '../../components/Navbar'
import { Footer } from '../../components/Footer'
import './style.css'
import { useState } from 'react'
import axios from 'axios'

export function MetasFuturas() {
    const [nome, setNome] = useState('')
    const [valorGuardar, setValorGuardar] = useState<number | ''>('')

    const handleAddMeta = async (e: React.FormEvent) => {
        e.preventDefault()
        try {
            await axios.post('/metas/add', {
                nome,
                valorGuardar,
            })
            alert("Meta adicionada com sucesso!")
            setNome('')
            setValorGuardar('')
        } catch (error) {
            console.error("Erro ao adicionar meta", error)
            alert("Erro ao adicionar meta. Tente novamente.")
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