import { NavbarDeslogado } from '../../components/NavbarDeslogado'
import { Footer } from '../../components/Footer'
import './style.css';
import piggy from '../../img/piggy-bank.png'; // Coloque a imagem correta no caminho apropriado


export function TelaInicial(){
    return(
        <div className="home-container">
        <NavbarDeslogado />
        <div className="content">
            <div className="description">
                <h1>Bem vindo a Financial Insights</h1>
                <p>
                    Somos uma plataforma de organização financeira pessoal para ajudar você com a visibilidade e controle dos seus gastos.
                </p>
                <p>
                    Faça seu login ou cadastre-se gratuitamente para começar a se organizar financeiramente. 
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