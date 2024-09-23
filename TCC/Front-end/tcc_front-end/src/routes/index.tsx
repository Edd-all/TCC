// import React from 'react';
import {Routes, Route, BrowserRouter as Router} from "react-router-dom";
import {Home} from "../pages/Home";
import {Cadastro} from "../pages/Cadastro";
import {Login} from "../pages/Login";

export const AppRoutes = () => {
    return(
        <Router>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/cadastro" element={<Cadastro/>}/>
                <Route path="/login" element={<Login/>}/>
            </Routes>
        </Router>

    )
} 
