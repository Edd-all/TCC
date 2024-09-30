import { Link } from 'react-router-dom';
import './style.css';
import logo from '../../img/usd-circle.png';

export function Navbar() {
    return (
        <nav className='navbar'>
            <Link to="/">
                <img className='img' src={logo} alt="Logo" />
            </Link>
            <div className="nav-links">
                <Link className='ul' to="/">Home</Link>
                {/* <Link className='ul' to="/cadastro">Cadastro</Link>
                <Link className='ul' to="/login">Login</Link> */}
                <Link className='ul' to="/lancamentosFinanceiros">Area de Lançamentos</Link>
                <Link className='ul' to="/metasFuturas">Area de Metas</Link>
                <Link className='ul' to="/estatisticas">Estatisticas</Link>
            </div>
        </nav>
    );
}