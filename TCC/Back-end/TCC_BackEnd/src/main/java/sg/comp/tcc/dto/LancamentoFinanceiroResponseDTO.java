package sg.comp.tcc.dto;

import java.time.LocalDate;

import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.enums.EnumReceitaDespesa;

public class LancamentoFinanceiroResponseDTO {
	private Long id;
	private String nome;
	private Double valor;
	private LocalDate dataCriacao;
	private Boolean efetivada; 								
	private EnumReceitaDespesa tipo; 						
	private Usuario usuario;
	
	public LancamentoFinanceiroResponseDTO() {
	}

	public LancamentoFinanceiroResponseDTO(LancamentoFinanceiro lancamentoFinanceiro) {
		super();
		this.id = lancamentoFinanceiro.getId();
		this.nome = lancamentoFinanceiro.getNome();
		this.valor = lancamentoFinanceiro.getValor();
		this.dataCriacao = lancamentoFinanceiro.getDataCriacao();
		this.efetivada = lancamentoFinanceiro.getEfetivada();
		this.tipo = lancamentoFinanceiro.getTipo();
		this.usuario = lancamentoFinanceiro.getUsuario();
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

	public Boolean getEfetivada() {
		return efetivada;
	}

	public void setEfetivada(Boolean efetivada) {
		this.efetivada = efetivada;
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
