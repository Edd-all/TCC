package sg.comp.tcc.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//nota: saldo mensal se refere a todo valor de um Mês das receitas e despesas do usuario

@Entity
@Table(name = "saldoMensal")
public class SaldoMensal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valor;
	private LocalDate diaDoMes; 
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public SaldoMensal(Double valor, LocalDate diaDoMes, Usuario usuario) {
		super();
		this.valor = valor;
		this.diaDoMes = diaDoMes;
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
	
	
}
