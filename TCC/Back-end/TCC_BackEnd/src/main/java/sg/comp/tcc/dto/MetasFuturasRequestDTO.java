package sg.comp.tcc.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;


public class MetasFuturasRequestDTO {
	@Id
	private Long id;
	@NotBlank
	private String nome;
	private Double valorGuardar;
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
	public Double getValorGuardar() {
		return valorGuardar;
	}
	public void setValorGuardar(Double valorGuardar) {
		this.valorGuardar = valorGuardar;
	}
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	
	
}
