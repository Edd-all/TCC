package sg.comp.tcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.comp.tcc.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByNome(String nome);
	Optional<Usuario> findByLogin(String Login);
	
}
