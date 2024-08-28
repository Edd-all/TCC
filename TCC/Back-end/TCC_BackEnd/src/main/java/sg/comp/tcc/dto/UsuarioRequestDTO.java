package sg.comp.tcc.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import sg.comp.tcc.enums.EnumTipoSituacaoUsuario;

public class UsuarioRequestDTO {
	@Id
	private Long id;
	@NotBlank
	private String nome;
	private String login;
	@Email
	private String email;
	@NotBlank
	private String senha; 
	private EnumTipoSituacaoUsuario situacao;
	
	
	
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public EnumTipoSituacaoUsuario getSituacao() {
		return situacao;
	}
	public void setSituacao(EnumTipoSituacaoUsuario situacao) {
		this.situacao = situacao;
	}
	
	
}
