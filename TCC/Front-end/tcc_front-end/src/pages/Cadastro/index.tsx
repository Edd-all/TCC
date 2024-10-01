import React, { useState } from 'react';
import { postCadastro } from '../../api/usuarios';  // Importa a função postCadastro
import './style.css';
import { useNavigate } from 'react-router-dom';

export function Cadastro() {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [login, setLogin] = useState('');  // Adicionando o campo login
    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState(false);
    const navigate = useNavigate(); // Inicializa o useNavigate

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        
        const usuarioData = {
            nome,
            login,  // Adicionando login nos dados
            senha,
            email,
        };

        try {
            // Faz a requisição para cadastrar o usuário
            await postCadastro(usuarioData);  
            // Se a requisição for bem-sucedida, redireciona
            setSuccess(true);
            setError(null);
            navigate('/ativacao'); // Redireciona para a página de ativação
        } catch (err) {
            console.error(err); // Log do erro para depuração
            setError("Erro ao cadastrar. Tente novamente.");
            setSuccess(false);
        }
    };

    return (
        <div className="cadastro-container">
            <h2>Cadastro</h2>
            <form onSubmit={handleSubmit} className="cadastro-form">
                {success && <p style={{ color: "green" }}>Usuário cadastrado com sucesso!</p>}
                {error && <p style={{ color: "red" }}>{error}</p>}
                
                <label htmlFor="nome">Nome</label>
                <input 
                    type="text" 
                    id="nome" 
                    value={nome} 
                    onChange={(e) => setNome(e.target.value)} 
                    placeholder="Digite seu nome"
                    required
                />

                <label htmlFor="login">Login</label>
                <input 
                    type="text" 
                    id="login" 
                    value={login} 
                    onChange={(e) => setLogin(e.target.value)} 
                    placeholder="Digite seu login"
                    required
                />

                <label htmlFor="email">Email</label>
                <input 
                    type="email" 
                    id="email" 
                    value={email} 
                    onChange={(e) => setEmail(e.target.value)} 
                    placeholder="Digite seu email"
                    required
                />

                <label htmlFor="senha">Senha</label>
                <input 
                    type="password" 
                    id="senha" 
                    value={senha} 
                    onChange={(e) => setSenha(e.target.value)} 
                    placeholder="Digite sua senha"
                    required
                />

                <button type="submit" className="cadastro-btn">Cadastrar</button>
            </form>
        </div>
    );
}