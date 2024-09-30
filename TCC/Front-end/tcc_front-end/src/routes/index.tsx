// import React from 'react';
import {Routes, Route, BrowserRouter as Router} from "react-router-dom";
import {Home} from "../pages/Home";
import {Cadastro} from "../pages/Cadastro";
import {Login} from "../pages/Login";
import { LancamentosFinanceiros } from "../pages/LancamentosFinanceiros";
import { MetasFuturas } from "../pages/MetasFuturas";
import { Estatisticas } from "../pages/Estatisticas";

export const AppRoutes = () => {
    return(
        <Router>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/cadastro" element={<Cadastro/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/lancamentosFinanceiros" element={<LancamentosFinanceiros/>}/>
                <Route path="/metasFuturas" element={<MetasFuturas/>}/>
                <Route path="/estatisticas" element={<Estatisticas/>}/>
            </Routes>
        </Router>

    )
} 
