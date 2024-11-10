package sg.comp.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.comp.tcc.entity.SaldoMensal;

public interface SaldoMensalRepository extends JpaRepository<SaldoMensal, Long> {
	List<SaldoMensal> findByUsuarioLogin(String login);
}
