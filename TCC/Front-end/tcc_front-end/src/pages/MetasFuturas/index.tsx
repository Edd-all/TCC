import { Navbar } from '../../components/Navbar'
import { Footer } from '../../components/Footer'
import './style.css';

export function MetasFuturas(){
    return(
        <div className="home-container">
            <Navbar />
            <div className="content">
            <div className="description">
                <h1>Area de Metas</h1>
                <p>
                    Aqui você pode ver as metas que deseja alcançar com o feedback da plataforma, assim como adiciona-las, edita-las ou deleta-las
                </p>
            </div>
            {/* <div className="image-container">
               
            </div> */}

            </div>
            <Footer />
        </div>
    )
}