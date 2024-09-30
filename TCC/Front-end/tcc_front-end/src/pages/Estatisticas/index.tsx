import { Navbar } from '../../components/Navbar'
import { Footer } from '../../components/Footer'
import './style.css';

export function Estatisticas(){
    return(
        <div className="home-container">
            <Navbar />
            <div className="content">
            <div className="description">
                <h1>Area de Estatisticas</h1>
                <p>
                    Aqui você pode calcular seu Saldo Mensal, bem como ver graficos dos seus lançamentos, e o quanto falta pare realizar suas metas
                </p>
            </div>
            {/* <div className="image-container">
               
            </div> */}

            </div>
            <Footer />
        </div>
    )
}