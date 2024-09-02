package sg.comp.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.entity.Usuario;

public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Long>{
	List<LancamentoFinanceiro> findByUsuario(Usuario usuario);
}
