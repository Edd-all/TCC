import { Link } from 'react-router-dom';
import './style.css';
import logo from '../../img/usd-circle.png';

export function Navbar() {
    return (
        <nav className='navbar'>
            <Link to="/Home">
                <img className='img' src={logo} alt="Logo" />
            </Link>
            <div className="nav-links">
                <Link className='ul' to="/Home">Home</Link>
                <Link className='ul' to="/lancamentosFinanceiros">Area de Lançamentos</Link>
                <Link className='ul' to="/metasFuturas">Area de Metas</Link>
                <Link className='ul' to="/estatisticas">Insights</Link>
            </div>
        </nav>
    );
}