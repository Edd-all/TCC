package sg.comp.tcc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import sg.comp.tcc.enums.EnumTipoSituacaoUsuario;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String login; //username
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String senha; 
	
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saldoMensal_id", referencedColumnName = "id")
	private SaldoMensal saldoMensal;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<MetasFuturas> metas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<LancamentoFinanceiro> lancamentoFinanceiro;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumTipoSituacaoUsuario situacao;
	
	public Usuario(String nome, String login, String email, String senha, SaldoMensal saldoMensal, 
			EnumTipoSituacaoUsuario situacao,List<MetasFuturas> metas, List<LancamentoFinanceiro> lancamentoFinanceiro) {
		super();
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.saldoMensal = saldoMensal;
		this.metas = metas;
		this.lancamentoFinanceiro = lancamentoFinanceiro;
		this.situacao = situacao;
	}

	public Usuario() {
		
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

	
	
	public SaldoMensal getSaldoMensal() {
		return saldoMensal;
	}

	public void setSaldoMensal(SaldoMensal saldoMensal) {
		this.saldoMensal = saldoMensal;
	}

	
	
	public List<MetasFuturas> getMetas() {
		return metas;
	}

	public void setMetas(List<MetasFuturas> metas) {
		this.metas = metas;
	}

	public List<LancamentoFinanceiro> getLancamentoFinanceiro() {
		return lancamentoFinanceiro;
	}

	public void setLancamentoFinanceiro(List<LancamentoFinanceiro> lancamentoFinanceiro) {
		this.lancamentoFinanceiro = lancamentoFinanceiro;
	}

	public EnumTipoSituacaoUsuario getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumTipoSituacaoUsuario situacao) {
		this.situacao = situacao;
	}
	
	
	
}
