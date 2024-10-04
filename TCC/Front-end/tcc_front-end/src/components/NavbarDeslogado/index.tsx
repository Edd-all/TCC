import { Link } from 'react-router-dom';
import './style.css';
import logo from '../../img/usd-circle.png';

export function NavbarDeslogado() {
    return (
        <nav className='navbar'>
            <Link to="/">
                <img className='img' src={logo} alt="Logo" />
            </Link>
            <div className="nav-links">
                <Link className='ul' to="/cadastro">Cadastro</Link>
                <Link className='ul' to="/login">Login</Link>
            </div>
        </nav>
    );
}