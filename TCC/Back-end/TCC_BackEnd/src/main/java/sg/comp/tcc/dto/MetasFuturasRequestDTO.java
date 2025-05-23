package sg.comp.tcc.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import sg.comp.tcc.enums.EnumPrioridadeMeta;


public class MetasFuturasRequestDTO {
	@Id
	private Long id;
	@NotBlank
	private String nome;
	private Double valorGuardar;
	private EnumPrioridadeMeta prioridade;
	private String usuario;
	
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
	public Double getValorGuardar() {
		return valorGuardar;
	}
	public void setValorGuardar(Double valorGuardar) {
		this.valorGuardar = valorGuardar;
	}
	
	
	public EnumPrioridadeMeta getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(EnumPrioridadeMeta prioridade) {
		this.prioridade = prioridade;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
