package sg.comp.tcc.service;

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
	
	public LancamentoFinanceiroResponseDTO cadastrarLancamentoFinanceiro(LancamentoFinanceiroRequestDTO lancamentoFinanceiroRequestDTO) {
		Usuario usuario = usuarioService.buscarPorId(lancamentoFinanceiroRequestDTO.getUsuario());
		
		LancamentoFinanceiro lancamento = new LancamentoFinanceiro(
				lancamentoFinanceiroRequestDTO.getNome(),
				lancamentoFinanceiroRequestDTO.getValor(),
				lancamentoFinanceiroRequestDTO.getDataCriacao(),
				lancamentoFinanceiroRequestDTO.getEfetivada(),
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
					lancamentoFinanceiroRequestDTO.getEfetivada(),
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
	
	
}
