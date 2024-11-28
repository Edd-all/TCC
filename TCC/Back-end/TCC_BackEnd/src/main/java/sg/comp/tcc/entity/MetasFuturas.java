package sg.comp.tcc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import sg.comp.tcc.enums.EnumPrioridadeMeta;

@Entity
@Table(name = "metas_futuras")
public class MetasFuturas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double valorGuardar;
	private EnumPrioridadeMeta prioridade;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public MetasFuturas(String nome, Double valorGuardar,EnumPrioridadeMeta prioridade ,Usuario usuario) {
		super();
		this.nome = nome;
		this.valorGuardar = valorGuardar;
		this.prioridade = prioridade;
		this.usuario = usuario;
	}

	public MetasFuturas() {
		
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
	
	

	public EnumPrioridadeMeta getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(EnumPrioridadeMeta prioridade) {
		this.prioridade = prioridade;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
