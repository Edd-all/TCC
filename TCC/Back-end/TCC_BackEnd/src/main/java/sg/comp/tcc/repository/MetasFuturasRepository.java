package sg.comp.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.comp.tcc.entity.MetasFuturas;

public interface MetasFuturasRepository extends JpaRepository<MetasFuturas, Long>{
	List<MetasFuturas> findByUsuarioLogin(String login);
	
}
