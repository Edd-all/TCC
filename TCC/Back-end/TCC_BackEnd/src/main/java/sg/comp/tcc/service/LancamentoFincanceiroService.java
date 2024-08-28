package sg.comp.tcc.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.comp.tcc.dto.LancamentoFinanceiroRequestDTO;
import sg.comp.tcc.dto.LancamentoFinanceiroResponseDTO;
import sg.comp.tcc.entity.Agendamento;
import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.repository.AgendamentoRepository;
import sg.comp.tcc.repository.LancamentoFinanceiroRepository;

@Service
public class LancamentoFincanceiroService {
	
	@Autowired
	private LancamentoFinanceiroRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private AgendamentoRepository agendamentoRepository;
	
	
	
	public List<LancamentoFinanceiroResponseDTO> listarLancamentosFinanceiros(){
		List<LancamentoFinanceiro> lancamentoFinanceiro = repository.findAll();
		return lancamentoFinanceiro.stream().map(LancamentoFinanceiroResponseDTO::new).collect(Collectors.toList());
	}
	
	public LancamentoFinanceiroResponseDTO listarLancamentoFinanceiroPorId(Long id) {
		LancamentoFinanceiro lancamento;
		
		if(repository.existsById(id)) {
			lancamento = repository.findById(id).get();
			return new LancamentoFinanceiroResponseDTO(lancamento);
		}else {
			throw new NoSuchElementException("Lançamento financeiro não encontrado!");
		}
	}
	
	public LancamentoFinanceiroResponseDTO cadastrarLancamentoFinanceiro(LancamentoFinanceiroRequestDTO lancamentoFinanceiroRequestDTO) {
		Usuario usuario = usuarioService.buscarPorId(lancamentoFinanceiroRequestDTO.getUsuario());
		
		LancamentoFinanceiro lancamento = new LancamentoFinanceiro(
				lancamentoFinanceiroRequestDTO.getNome(),
				lancamentoFinanceiroRequestDTO.getValor(),
				lancamentoFinanceiroRequestDTO.getDataCriacao(),
				lancamentoFinanceiroRequestDTO.getTipo(),
				usuario
		);
		
		LancamentoFinanceiro lancamentoSalvo = repository.save(lancamento);
		return new LancamentoFinanceiroResponseDTO(lancamentoSalvo);
	}
	
	public LancamentoFinanceiroResponseDTO atualizarLancamentoFinanceiro(Long id, LancamentoFinanceiroRequestDTO lancamentoFinanceiroRequestDTO) {
		Optional<LancamentoFinanceiro> lancamento = repository.findById(id);
		Usuario usuario = usuarioService.buscarPorId(lancamentoFinanceiroRequestDTO.getUsuario());
		
		if(lancamento.isPresent()) {
			LancamentoFinanceiro lancamentoFinanceiro = new LancamentoFinanceiro(
					lancamentoFinanceiroRequestDTO.getNome(),
					lancamentoFinanceiroRequestDTO.getValor(),
					lancamentoFinanceiroRequestDTO.getDataCriacao(),
					lancamentoFinanceiroRequestDTO.getTipo(),
					usuario
			);
			lancamentoFinanceiro.setId(id);
			return new LancamentoFinanceiroResponseDTO(repository.save(lancamentoFinanceiro));
		}else {
			throw new NoSuchElementException("Lancamento financeiro não encontrado!");
		}
	}
	
	public void deletarLancamentoFinanceiro(Long id) {
		Optional<LancamentoFinanceiro> lancamento = repository.findById(id);
		if(lancamento.isPresent()) {
			repository.deleteById(id);
        } else {
            throw new NoSuchElementException("Lancamento financeiro não encontrado!");
        }
	}
	
	
	public void executaAgendamentos(LocalDate data) {
		
	    DayOfWeek today = LocalDate.now().getDayOfWeek();
	    int diaMesAtual = data.getDayOfMonth();
	    EnumDiaSemana enumDiaSemana = EnumDiaSemana.fromDayOfWeek(today);
	    
	    
	    // Filtra os agendamentos por tipo de agendamento
	    List<Agendamento> agendamentosData = agendamentoRepository.findByData(data);
	    
	    // Obtenha os agendamentos para o dia da semana atual
	    List<Agendamento> agendamentosDiaSemana = agendamentoRepository.findByDiaSemana(enumDiaSemana);
	 	
	    List<Agendamento> agendamentosDiaMes = agendamentoRepository.findByDiaMes(diaMesAtual);

        
	 // Executa agendamentos para uma data específica
	    for (Agendamento agendamento : agendamentosData) {
	        LancamentoFinanceiro lancamento = new LancamentoFinanceiro(
	            agendamento.getDescricao(),
	            agendamento.getValor(),
	            data,
	            agendamento.getTipoLancamento(),
	            agendamento.getLancamentoFinanceiro().getUsuario()
	        );
	        repository.save(lancamento); // repository == LancamentoFinanceiroRepository
	        agendamentoRepository.save(agendamento);
	    }

	    // Executa agendamentos para o dia da semana
	    for (Agendamento agendamento : agendamentosDiaSemana) {
	        LancamentoFinanceiro lancamento = new LancamentoFinanceiro(
	            agendamento.getDescricao(),
	            agendamento.getValor(),
	            data,
	            agendamento.getTipoLancamento(),
	            agendamento.getLancamentoFinanceiro().getUsuario()
	        );
	        repository.save(lancamento); // repository == LancamentoFinanceiroRepository
	        agendamentoRepository.save(agendamento);
	    }

	    // Executa agendamentos para o dia do mês
	    for (Agendamento agendamento : agendamentosDiaMes) {
	    	
	        LancamentoFinanceiro lancamento = new LancamentoFinanceiro(
	            agendamento.getDescricao(),
	            agendamento.getValor(),
	            data,
	            agendamento.getTipoLancamento(),
	            agendamento.getLancamentoFinanceiro().getUsuario()
	        );
	        
	        repository.save(lancamento); // repository == LancamentoFinanceiroRepository
	        agendamentoRepository.save(agendamento);
	    }
	    
    }
	
	
}
