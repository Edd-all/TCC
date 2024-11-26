import { Routes, Route, BrowserRouter as Router } from "react-router-dom";
import { Home } from "../pages/Home";
import { Cadastro } from "../pages/Cadastro";
import { Login } from "../pages/Login";
import { LancamentosFinanceiros } from "../pages/LancamentosFinanceiros";
import { MetasFuturas } from "../pages/MetasFuturas";
import { Estatisticas } from "../pages/Estatisticas";
import { Ativacao } from '../pages/Ativacao';
import { TelaInicial } from '../pages/TelaInicial';
import { PrivateRoute } from './PrivateRoute'; 

import EditarLancamentoFinanceiro from '../pages/EditarLancamentoFinanceiro';
import EditarMetaFutura from '../pages/EditarMetaFutura';

export const AppRoutes = () => {
    return (
        <Router>
            <Routes>
                {/* Rotas abertas */}
                <Route path="/" element={<TelaInicial />} />
                <Route path="/cadastro" element={<Cadastro />} />
                <Route path="/ativacao" element={<Ativacao />} />
                <Route path="/login" element={<Login />} />

                {/* Rotas protegidas */}
                <Route path="/home" element={<PrivateRoute element={<Home />} />} />
                <Route path="/lancamentosFinanceiros" element={<PrivateRoute element={<LancamentosFinanceiros />} />} />
                <Route path="/metasFuturas" element={<PrivateRoute element={<MetasFuturas />} />} />
                <Route path="/estatisticas" element={<PrivateRoute element={<Estatisticas />} />} />

                {/* Outras rotas */}
                <Route path="/editar-lancamento/:id" element={<PrivateRoute element={<EditarLancamentoFinanceiro />} />} />
                <Route path="/editar-meta/:id" element={ <PrivateRoute element={<EditarMetaFutura />} />} />
            </Routes>
        </Router>
    );
};
