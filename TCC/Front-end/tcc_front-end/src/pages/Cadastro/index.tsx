import React, { useState } from 'react';
import './style.css';

export function Cadastro() {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        // Aqui você pode fazer a chamada para o backend para enviar os dados
        console.log({ nome, email, senha });
    };

    return (
        
        <div className="cadastro-container">
            <h2>Cadastro</h2>
            <form onSubmit={handleSubmit} className="cadastro-form">
                <label htmlFor="nome">Nome</label>
                <input 
                    type="text" 
                    id="nome" 
                    value={nome} 
                    onChange={(e) => setNome(e.target.value)} 
                    placeholder="Digite seu nome"
                />

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

                <button type="submit" className="cadastro-btn">Cadastrar</button>
            </form>
        </div>
    );
}