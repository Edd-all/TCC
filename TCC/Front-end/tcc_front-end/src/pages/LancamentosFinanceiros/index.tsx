import { Navbar } from '../../components/Navbar'
import { Footer } from '../../components/Footer'
import './style.css';

export function LancamentosFinanceiros(){
    return(
        <div className="home-container">
            <Navbar />
            <div className="content">
            <div className="description">
                <h1>Area de Lançamentos</h1>
                <p>
                    Aqui você pode ver seu histórico de Lançamentos financeiros, assim como adiciona-los, edita-los ou deleta-los
                </p>
            </div>
            {/* <div className="image-container">
               
            </div> */}

            </div>
            <Footer />
        </div>
    )
}