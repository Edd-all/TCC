import { Navbar } from '../../components/Navbar'
import { Footer } from '../../components/Footer'
import './style.css';
import piggy from '../../img/piggy-bank.png'; // Coloque a imagem correta no caminho apropriado


export function Home(){
    return(
        <div className="home-container">
        <Navbar />
        <div className="content">
            <div className="description">
                <h1>Bem vindo ao Financial Insights</h1>
                <p>
                    Somos uma plataforma de organização financeira pessoal para ajudar você com a visibilidade e controle dos seus gastos.
                </p>
            </div>
            <div className="image-container">
                <img src={piggy} alt="Organização Financeira" />
            </div>
        </div>
        <Footer />
    </div>
    )
}