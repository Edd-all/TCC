package sg.comp.tcc.dto;

import sg.comp.tcc.entity.SaldoMensal;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.enums.EnumTipoSituacaoUsuario;

public class UsuarioResponseDTO {
	private Long id;
	private String nome;
	private String login;
	private String email;
	private String senha; 
	private EnumTipoSituacaoUsuario situacao;
	private SaldoMensal saldoMensal;
	
	public UsuarioResponseDTO() {
	}

	public UsuarioResponseDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.login = usuario.getLogin();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.saldoMensal = usuario.getSaldoMensal();
		this.situacao = usuario.getSituacao();
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

	public SaldoMensal getSaldo() {
		return saldoMensal;
	}

	public void setSaldo(SaldoMensal saldoMensal) {
		this.saldoMensal = saldoMensal;
	}

	public EnumTipoSituacaoUsuario getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumTipoSituacaoUsuario situacao) {
		this.situacao = situacao;
	}

	public SaldoMensal getSaldoMensal() {
		return saldoMensal;
	}

	public void setSaldoMensal(SaldoMensal saldoMensal) {
		this.saldoMensal = saldoMensal;
	}
	
	
	
}
