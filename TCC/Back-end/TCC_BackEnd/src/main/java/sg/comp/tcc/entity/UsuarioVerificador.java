package sg.comp.tcc.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_verificador")
public class UsuarioVerificador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private UUID uuid;
	
	@Column(nullable = false)
	private Instant dataExpiracao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
	private Usuario usuario;
	
	public UsuarioVerificador(Long id, UUID uuid, Instant dataExpiracao, Usuario usuario) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.dataExpiracao = dataExpiracao;
		this.usuario = usuario;
	}
	
	public UsuarioVerificador() {
		super();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Instant getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Instant dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	

	
	
}
