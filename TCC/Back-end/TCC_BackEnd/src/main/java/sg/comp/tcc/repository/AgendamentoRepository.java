package sg.comp.tcc.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.comp.tcc.entity.Agendamento;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	Optional<Agendamento> findByTipoLancamento(EnumReceitaDespesa tipoLancamento);
	List<Agendamento> findByData(LocalDate data);
	List<Agendamento> findByDiaSemana(EnumDiaSemana diaSemana);
	List<Agendamento> findByDiaMes(int diaMes);
}
