import { Navbar } from '../../components/Navbar'
import { Footer } from '../../components/Footer'
import './style.css';
import piggy from '../../img/piggy-bank.png'; // Coloque a imagem correta no caminho apropriado


export function Home(){
    return(
        <div className="home-container">
        <Navbar/>
        <div className="content">
            <div className="description">
                <h1>Financial Insights</h1>
                <p>
                    Bem vindo!
                </p>
                <p>
                    Se ainda não conhece a plataforma comece adicionando suas receitas e despesas na área de lançamentos financeiros acima ou as metas que deseja alcançar.
                </p>
                <p>
                    A área de Estatisticas lhe fornecerá um feedback visual dos seus gastos após inseri-los no sistema.
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