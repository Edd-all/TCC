package sg.comp.tcc.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import sg.comp.tcc.entity.UsuarioVerificador;

public interface UsuarioVerificadorRepository extends JpaRepository<UsuarioVerificador, Long>{
	public Optional<UsuarioVerificador> findByUuid(UUID uuid);
}
