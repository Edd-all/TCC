import React from 'react';
import { Navigate } from 'react-router-dom';

interface PrivateRouteProps {
    element: JSX.Element;
}

export const PrivateRoute: React.FC<PrivateRouteProps> = ({ element }) => {
    const token = localStorage.getItem('myAppName_token');
    const tokenExpiration = localStorage.getItem('myAppName_token_expiration');
    const currentTime = new Date().getTime();

    // Verifica se o token existe e se ainda está dentro do prazo de validade
    if (!token || !tokenExpiration || currentTime > parseInt(tokenExpiration)) {
        console.log('Token expirado ou não encontrado, redirecionando para login');

        // Remove o token e a expiração caso tenha expirado
        localStorage.removeItem('myAppName_token');
        localStorage.removeItem('myAppName_token_expiration');

        return <Navigate to="/login" replace />;
    }

    // Se o token ainda for válido, renderiza o componente
    return element;
};