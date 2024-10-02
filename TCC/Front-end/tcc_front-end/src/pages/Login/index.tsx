import React, { useState } from 'react';
import { postLogin } from '../../api/auth'; 
import './style.css';
import { useNavigate } from 'react-router-dom';

export function Login() {
    const [username, setUsername] = useState('');
    const [senha, setSenha] = useState('');
    const navigate = useNavigate(); // Inicializa o useNavigate

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        try {
            const loginData = {
                username,  // Enviar o username
                password: senha,  // E a senha
            };
            const response = await postLogin(loginData);
            console.log('Resposta do servidor:', response);
            navigate('/');// Navega para a home
        } catch (error) {
            console.error('Erro no login:', error);
        }
    };

    return (
        <div className="login-container">
            <h2>Login</h2>
            <form onSubmit={handleSubmit} className="login-form">
                <label htmlFor="username">Username</label>
                <input 
                    type="text" 
                    id="username" 
                    value={username} 
                    onChange={(e) => setUsername(e.target.value)} 
                    placeholder="Digite seu username"
                />

                <label htmlFor="senha">Senha</label>
                <input 
                    type="password" 
                    id="senha" 
                    value={senha} 
                    onChange={(e) => setSenha(e.target.value)} 
                    placeholder="Digite sua senha"
                />

                <button type="submit" className="login-btn">Entrar</button>
            </form>
        </div>
    );
}