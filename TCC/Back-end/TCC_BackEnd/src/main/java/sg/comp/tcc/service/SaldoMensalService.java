package sg.comp.tcc.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.comp.tcc.dto.SaldoMensalRequestDTO;
import sg.comp.tcc.dto.SaldoMensalResponseDTO;
import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.entity.SaldoMensal;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.repository.LancamentoFinanceiroRepository;
import sg.comp.tcc.repository.SaldoMensalRepository;

@Service
public class SaldoMensalService {
	@Autowired
	private SaldoMensalRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<SaldoMensalResponseDTO> listarSaldo(){
		List<SaldoMensal> saldosMensais = repository.findAll();
		return saldosMensais.stream().map(SaldoMensalResponseDTO::new).collect(Collectors.toList());
	}
	
	public SaldoMensalResponseDTO cadastrarSaldoMensal(SaldoMensalRequestDTO saldoMensalRequestDTO) {
		Usuario usuario = usuarioService.buscarPorLogin(saldoMensalRequestDTO.getUsuario());
		
		SaldoMensal saldoMensal = new SaldoMensal(
				saldoMensalRequestDTO.getValor(),
				saldoMensalRequestDTO.getDiaDoMes(),
				usuario
		);
		SaldoMensal saldoSalvo = repository.save(saldoMensal);
		return new SaldoMensalResponseDTO(saldoSalvo);
	}
	
	public SaldoMensalResponseDTO atualizarSaldoMensal(Long id, SaldoMensalRequestDTO saldoMensalRequestDTO) {
		Optional<SaldoMensal> saldo = repository.findById(id);
		Usuario usuario = usuarioService.buscarPorLogin(saldoMensalRequestDTO.getUsuario());
		
		if(saldo.isPresent()) {
			SaldoMensal saldoMensal = new SaldoMensal(
					saldoMensalRequestDTO.getValor(),
					saldoMensalRequestDTO.getDiaDoMes(),
					usuario
			);
			saldoMensal.setId(id);
			return new SaldoMensalResponseDTO(repository.save(saldoMensal));
		}else{
			throw new NoSuchElementException("Saldo não encontrado!");
		}
	}
	
	public void deletarSaldoMensal(Long id) {
		Optional<SaldoMensal> saldo = repository.findById(id);
		if(saldo.isPresent()) {
			 repository.deleteById(id);
		}else {
			throw new NoSuchElementException("Saldo não encontrado!");
		}
	}
	
	
	 @Autowired
	    private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;
	 
	 public String calcularSaldoMensal(Usuario usuario) {
	        // Buscar todos os lançamentos financeiros do usuário
	        List<LancamentoFinanceiro> lancamentos = lancamentoFinanceiroRepository.findByUsuario(usuario);

	        // Criar uma instância de SaldoMensal
	        SaldoMensal saldoMensal = new SaldoMensal();
	        saldoMensal.setUsuario(usuario);
	        saldoMensal.setLancamentosFinanceiros(lancamentos);

	        // Calcular o saldo e salvar no repositório
	        String mensagem = saldoMensal.calcularSaldoMensal();
	        repository.save(saldoMensal);

	        return mensagem;
	    }
	 
}
