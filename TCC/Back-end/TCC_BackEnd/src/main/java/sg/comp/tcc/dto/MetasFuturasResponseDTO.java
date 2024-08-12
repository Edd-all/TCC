package sg.comp.tcc.dto;

import sg.comp.tcc.entity.MetasFuturas;
import sg.comp.tcc.entity.Usuario;

public class MetasFuturasResponseDTO {
	private Long id;
	private String nome;
	private Double valorGuardar;
	private Usuario usuario;
	
	public MetasFuturasResponseDTO() {
	}

	public MetasFuturasResponseDTO(MetasFuturas meta) {
		super();
		this.id = meta.getId();
		this.nome = meta.getNome();
		this.valorGuardar = meta.getValorGuardar();
		this.usuario = meta.getUsuario();
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

	public Double getValorGuardar() {
		return valorGuardar;
	}

	public void setValorGuardar(Double valorGuardar) {
		this.valorGuardar = valorGuardar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
