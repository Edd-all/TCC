import React, { useState } from 'react';
import { postAtivacao } from '../../api/auth';  // Ajuste o caminho conforme necessário
import './style.css';
import { useNavigate } from 'react-router-dom';

export function Ativacao() {
    const [codigo, setCodigo] = useState(''); // Para armazenar o UUID
    const [error, setError] = useState<string | null>(null); // Para armazenar erros
    const [success, setSuccess] = useState(false); // Para armazenar sucesso
    const navigate = useNavigate(); // Inicializa o useNavigate

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault(); // Evita o comportamento padrão do formulário

        try {
            const response = await postAtivacao(codigo); // Chama a função com o UUID
            console.log(response);
            setSuccess(true); // Define o sucesso como true
            setError(null); // Limpa erros
            navigate('/login');// Navega para outra página
        } catch (error) {
            console.error(error);
            setError("Erro ao ativar a conta Tente novamente."); 
            setSuccess(false); // Define sucesso como false
        }
    };

    return (
        <div className="ativacao-container">
            <h2>Ativação de Conta</h2>
            <form onSubmit={handleSubmit} className="ativacao-form">
                {success && <p style={{ color: "green" }}>Conta ativada com sucesso!</p>}
                {error && <p style={{ color: "red" }}>{error}</p>}
                
                <label htmlFor="codigo">Verifique seu email de cadastro</label>
                <input 
                    type="text" 
                    id="codigo" 
                    value={codigo} 
                    onChange={(e) => setCodigo(e.target.value)} 
                    placeholder="Digite o código de ativação"
                    required
                />

                <button type="submit" className="ativacao-btn">Ativar Conta</button>
            </form>
        </div>
    );
}