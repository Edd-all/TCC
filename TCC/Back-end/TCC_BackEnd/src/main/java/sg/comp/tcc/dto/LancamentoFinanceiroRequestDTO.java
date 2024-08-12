package sg.comp.tcc.dto;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import sg.comp.tcc.enums.EnumReceitaDespesa;

public class LancamentoFinanceiroRequestDTO {
	@Id
	private Long id;
	@NotBlank
	private String nome;
	private Double valor;
	private LocalDate dataCriacao;
	private Boolean efetivada; 								
	private EnumReceitaDespesa tipo; 						
	private Long usuario;
	
	
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
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	
	
}
