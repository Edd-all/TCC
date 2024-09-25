import React, { useState } from 'react';
import './style.css';

export function Login() {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        // Aqui você pode fazer a chamada para o backend para autenticar
        console.log({ email, senha });
    };

    return (
        <div className="login-container">
            <h2>Login</h2>
            <form onSubmit={handleSubmit} className="login-form">
                <label htmlFor="email">Email</label>
                <input 
                    type="email" 
                    id="email" 
                    value={email} 
                    onChange={(e) => setEmail(e.target.value)} 
                    placeholder="Digite seu email"
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