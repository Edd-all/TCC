package sg.comp.tcc.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

//nota: coloquei o nome sem pensar, saldoTotal fsaria muito mais sentido
//TODO: trocar nome para SaldoTotal

@Entity
@Table(name = "saldoMensal")
public class SaldoMensal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valor;
	private LocalDate diaDoMes = LocalDate.now(); 
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Transient
    private List<LancamentoFinanceiro> lancamentosFinanceiros;
	
	public SaldoMensal(Double valor, LocalDate diaDoMes, Usuario usuario) {
		super();
		this.valor = valor;
		this.diaDoMes = (diaDoMes != null) ? diaDoMes : LocalDate.now();
		this.usuario = usuario;
	}

	public SaldoMensal() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDiaDoMes() {
		return diaDoMes;
	}

	public void setDiaDoMes(LocalDate diaDoMes) {
		this.diaDoMes = diaDoMes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	 public List<LancamentoFinanceiro> getLancamentosFinanceiros() {
	        return lancamentosFinanceiros;
	    }

	    public void setLancamentosFinanceiros(List<LancamentoFinanceiro> lancamentosFinanceiros) {
	        this.lancamentosFinanceiros = lancamentosFinanceiros;
	    }
	
	
	 public String calcularSaldoMensal() {
	        Double soma = lancamentosFinanceiros.stream()
	                                            .mapToDouble(LancamentoFinanceiro::getValor)
	                                            .sum();
	        
	        this.valor = soma;

	        if (soma > 0) {
	            return "Você está atualmente com uma receita de " + soma;
	        } else {
	            return "Você está atualmente com uma despesa de " + soma;
	        }
	    }
}
