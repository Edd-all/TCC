package sg.comp.tcc.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import sg.comp.tcc.enums.EnumReceitaDespesa;

@Entity
public class LancamentoFinanceiro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome; //descricao
	private Double valor;
	private LocalDate dataCriacao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumReceitaDespesa tipo; 		//receita ou despesa
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
		
	public LancamentoFinanceiro(String nome, Double valor, LocalDate dataCriacao,
			EnumReceitaDespesa tipo, Usuario usuario) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.tipo = tipo;
		this.usuario = usuario;
	}
	public LancamentoFinanceiro() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
	
	public EnumReceitaDespesa getTipo() {
		return tipo;
	}
	public void setTipo(EnumReceitaDespesa tipo) {
		this.tipo = tipo;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
