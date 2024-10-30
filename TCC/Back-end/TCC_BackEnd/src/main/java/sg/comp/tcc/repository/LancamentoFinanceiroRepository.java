package sg.comp.tcc.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;

public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Long>{
	List<LancamentoFinanceiro> findByUsuario(Usuario usuario);
	
	Optional<LancamentoFinanceiro> findByTipoLancamento(EnumReceitaDespesa tipoLancamento);
	
	List<LancamentoFinanceiro> findByDiaEspecifico(LocalDate diaEspecifico);
	List<LancamentoFinanceiro> findByDiaSemana(EnumDiaSemana diaSemana);
	List<LancamentoFinanceiro> findByDiaMes(int diaMes);
}
