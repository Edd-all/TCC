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
import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;
import sg.comp.tcc.enums.EnumTipoAgendamento;
import sg.comp.tcc.repository.LancamentoFinanceiroRepository;

@Service
public class LancamentoFincanceiroService {
	
	@Autowired
	private LancamentoFinanceiroRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	

	
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
	
	public LancamentoFinanceiro buscarPorTipoLancamento(EnumReceitaDespesa tipoLancamento) {
		return repository.findByTipoLancamento(tipoLancamento).orElse(null);
	}
	
	public List<LancamentoFinanceiro> buscarPorDiaEspecifico(LocalDate diaEspecifico) {
		return repository.findByDiaEspecifico(diaEspecifico);
	}
	
	public List<LancamentoFinanceiro> buscarPorDiaSemana(EnumDiaSemana diaSemana) {
		return repository.findByDiaSemana(diaSemana);
	}
	public List<LancamentoFinanceiro> buscarPorDiaMes(int diaMes) {
		return repository.findByDiaMes(diaMes);
	}
	
	
	
	public LancamentoFinanceiroResponseDTO cadastrarLancamentoFinanceiro(LancamentoFinanceiroRequestDTO lancamentoFinanceiroRequestDTO) {
		
		Usuario usuario = usuarioService.buscarPorLogin(lancamentoFinanceiroRequestDTO.getUsuario());
		System.out.println(lancamentoFinanceiroRequestDTO.toString());
		
		LancamentoFinanceiro lancamento = new LancamentoFinanceiro(
				lancamentoFinanceiroRequestDTO.getNome(),
				lancamentoFinanceiroRequestDTO.getDescricao(),
				lancamentoFinanceiroRequestDTO.getValor(),
				lancamentoFinanceiroRequestDTO.getDataCriacao(),
				lancamentoFinanceiroRequestDTO.getTipoLancamento(),
				
				lancamentoFinanceiroRequestDTO.getTipoAgendamento(),
				lancamentoFinanceiroRequestDTO.getDiaEspecifico(),
				lancamentoFinanceiroRequestDTO.getDiaSemana(),
				lancamentoFinanceiroRequestDTO.getDiaMes(),
				usuario
		);
		
		System.out.println(lancamento);
		
		LancamentoFinanceiro lancamentoSalvo = repository.save(lancamento);
		return new LancamentoFinanceiroResponseDTO(lancamentoSalvo);
	}
	
	public LancamentoFinanceiroResponseDTO atualizarLancamentoFinanceiro(Long id, LancamentoFinanceiroRequestDTO lancamentoFinanceiroRequestDTO) {
		Optional<LancamentoFinanceiro> lancamento = repository.findById(id);
		Usuario usuario = usuarioService.buscarPorLogin(lancamentoFinanceiroRequestDTO.getUsuario());
		
		if(lancamento.isPresent()) {
			LancamentoFinanceiro lancamentoFinanceiro = new LancamentoFinanceiro(
					lancamentoFinanceiroRequestDTO.getNome(),
					lancamentoFinanceiroRequestDTO.getDescricao(),
					lancamentoFinanceiroRequestDTO.getValor(),
					lancamentoFinanceiroRequestDTO.getDataCriacao(),
					lancamentoFinanceiroRequestDTO.getTipoLancamento(),
					
					lancamentoFinanceiroRequestDTO.getTipoAgendamento(),
					lancamentoFinanceiroRequestDTO.getDiaEspecifico(),
					lancamentoFinanceiroRequestDTO.getDiaSemana(),
					lancamentoFinanceiroRequestDTO.getDiaMes(),
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
	
	
	  public void executaAgendamentos() {
		  
	        LocalDate hoje = LocalDate.now();
	        DayOfWeek diaSemanaHoje = hoje.getDayOfWeek();
	        int diaDoMesHoje = hoje.getDayOfMonth();
	        
	        // Processa todos os lançamentos com agendamento
	        List<LancamentoFinanceiro> lancamentosAgendados = repository.findAll();
	        
	        for (LancamentoFinanceiro lancamento : lancamentosAgendados) {
	            EnumTipoAgendamento tipoAgendamento = lancamento.getTipoAgendamento();

	            if (tipoAgendamento != null) {
	                switch (tipoAgendamento) {
	                    case DIAESPECIFICO:
	                        if (hoje.equals(lancamento.getDiaEspecifico())) {
	                            criarLancamento(lancamento);
	                        }
	                        break;
	                    
	                    case DIASEMANA:
	                        if (diaSemanaHoje.equals(lancamento.getDiaSemana().getDayOfWeek())) {
	                            criarLancamento(lancamento);
	                        }
	                        break;
	                    
	                    case DIAMES:
	                        if (diaDoMesHoje == lancamento.getDiaMes()) {
	                            criarLancamento(lancamento);
	                        }
	                        break;

	                    default:
	                        break;
	                }
	            }
	        }
	    }

	    private void criarLancamento(LancamentoFinanceiro lancamentoOriginal) {
	        LancamentoFinanceiro novoLancamento = new LancamentoFinanceiro(
	            lancamentoOriginal.getNome(),
	            lancamentoOriginal.getDescricao(),
	            lancamentoOriginal.getValor(),
	            LocalDate.now(),
	            lancamentoOriginal.getTipoLancamento(),
	            lancamentoOriginal.getTipoAgendamento(),
	            lancamentoOriginal.getDiaEspecifico(),
	            lancamentoOriginal.getDiaSemana(),
	            lancamentoOriginal.getDiaMes(),
	            lancamentoOriginal.getUsuario()
	        );
	        repository.save(novoLancamento);
	    }
	
	
	
}
