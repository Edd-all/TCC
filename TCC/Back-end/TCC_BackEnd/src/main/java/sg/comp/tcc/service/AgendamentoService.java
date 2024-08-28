package sg.comp.tcc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.comp.tcc.dto.AgendamentoRequestDTO;
import sg.comp.tcc.dto.AgendamentoResponseDTO;
import sg.comp.tcc.entity.Agendamento;
import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;
import sg.comp.tcc.repository.AgendamentoRepository;
import sg.comp.tcc.repository.LancamentoFinanceiroRepository;

@Service
public class AgendamentoService {
	@Autowired
	private AgendamentoRepository repository;
	
	@Autowired
	private LancamentoFinanceiroRepository lancamentoRepository;
	
	
	public List<Agendamento> listarAgendamento(){
		return repository.findAll();
	}
	
	
	public Agendamento buscarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Agendamento buscarPorTipoLancamento(EnumReceitaDespesa tipoLancamento) {
		return repository.findByTipoLancamento(tipoLancamento).orElse(null);
	}
	
	public List<Agendamento> buscarPorData(LocalDate data) {
		return repository.findByData(data);
	}
	
	public List<Agendamento> buscarPorDiaSemana(EnumDiaSemana diaSemana) {
		return repository.findByDiaSemana(diaSemana);
	}
	public List<Agendamento> buscarPorDiaMes(int diaMes) {
		return repository.findByDiaMes(diaMes);
	}
	
	//lembrar de retornar agendamento response em todos os metodos
	public AgendamentoResponseDTO inserirAgendamento(AgendamentoRequestDTO agendamentoRequest) {
		Agendamento agendamento = new Agendamento(agendamentoRequest);
		LancamentoFinanceiro lancamento = lancamentoRepository.findById(agendamentoRequest.getLancamentoFinanceiro()).orElseThrow(()->new  RuntimeException("Lancamento não encontrado!"));
		agendamento.setLancamentoFinanceiro(lancamento);
		return new AgendamentoResponseDTO(repository.save(agendamento));
	}
	
	
	public AgendamentoResponseDTO atualizarAgendamento(Long id, Agendamento agendamento) {
		Agendamento a = repository.findById(id).orElse(null);
		
		if(a == null) {
			throw new NoSuchElementException("Não foi possível achar o agendamento");
		}
		agendamento.setId(id);
		return new AgendamentoResponseDTO(repository.save(agendamento));
	}
	
	
	public void deletarAgendamentoPorId(Long id) {
		repository.deleteById(id);
	}
}
