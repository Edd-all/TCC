package sg.comp.tcc.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.comp.tcc.dto.LancamentoFinanceiroResponseDTO;
import sg.comp.tcc.dto.MetasFuturasRequestDTO;
import sg.comp.tcc.dto.MetasFuturasResponseDTO;
import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.entity.MetasFuturas;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.repository.MetasFuturasRepository;

@Service
public class MetasFuturasService {
	
	@Autowired
	private MetasFuturasRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<MetasFuturasResponseDTO> listarMetas(){
		List<MetasFuturas> metas = repository.findAll();
		return metas.stream().map(MetasFuturasResponseDTO::new).collect(Collectors.toList());
	}
	
	public MetasFuturasResponseDTO listarMetasFuturasPorId(Long id) {
		MetasFuturas metas;
		
		if(repository.existsById(id)) {
			metas = repository.findById(id).get();
			return new MetasFuturasResponseDTO(metas);
		}else {
			throw new NoSuchElementException("Meta não encontrada!");
		}
	}
	
	
	
		public List<MetasFuturasResponseDTO> listarMetasFuturasPorLogin(String login){
			List<MetasFuturas> metas = repository.findByUsuarioLogin(login);
		    
		    if (metas.isEmpty()) {
		        throw new NoSuchElementException("Nenhuma meta encontrada para o login fornecido!");
		    }
		    
		    return metas.stream()
		                      .map(MetasFuturasResponseDTO::new)
		                      .collect(Collectors.toList());
		}
		
		public void deletarMetasFuturasPorLogin(String login) {
	        List<MetasFuturas> metas = repository.findByUsuarioLogin(login);
	        
	        if (metas.isEmpty()) {
	            throw new NoSuchElementException("Nenhum lançamento financeiro encontrado para o login fornecido!");
	        }
	        
	        repository.deleteAll(metas);  // Deleta todos os lançamentos encontrados
	    }
		
	
	public MetasFuturasResponseDTO cadastrarMetasFuturas(MetasFuturasRequestDTO metasFuturasRequestDTO) {
		Usuario usuario = usuarioService.buscarPorLogin(metasFuturasRequestDTO.getUsuario());
		
		MetasFuturas metas = new MetasFuturas(
				metasFuturasRequestDTO.getNome(),
				metasFuturasRequestDTO.getValorGuardar(),
				metasFuturasRequestDTO.getPrioridade(),
				usuario
		);
			
		MetasFuturas metasSalvas = repository.save(metas);
		return new MetasFuturasResponseDTO(metasSalvas);
	}
	
	public MetasFuturasResponseDTO atualizarMetasFuturas(Long id, MetasFuturasRequestDTO metasFuturasRequestDTO) {
		Optional<MetasFuturas> metas = repository.findById(id);
		Usuario usuario = usuarioService.buscarPorLogin(metasFuturasRequestDTO.getUsuario());
		
		if(metas.isPresent()) {
			MetasFuturas metasFuturas = new MetasFuturas(
					metasFuturasRequestDTO.getNome(),
					metasFuturasRequestDTO.getValorGuardar(),
					metasFuturasRequestDTO.getPrioridade(),
					usuario
			);
			metasFuturas.setId(id);
			return new MetasFuturasResponseDTO(repository.save(metasFuturas));
		}else {
			throw new NoSuchElementException("Meta não encontrada!");
		}
	}
	
	public void deletarMetasFuturas(Long id) {
		Optional<MetasFuturas> metas = repository.findById(id);
		if(metas.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new NoSuchElementException("Meta não encontrada!");
		}
	}
	
	
	public MetasFuturasResponseDTO listarMetaFuturaPorLoginEId(String login, Long id) {
	    Optional<MetasFuturas> metaOptional = repository.findById(id);

	    if (metaOptional.isEmpty()) {
	        throw new NoSuchElementException("Meta não encontrada!");
	    }

	    MetasFuturas meta = metaOptional.get();
	    if (!meta.getUsuario().getLogin().equals(login)) {
	        throw new IllegalArgumentException("Login não corresponde ao usuário da meta!");
	    }

	    return new MetasFuturasResponseDTO(meta);
	}
	
	public MetasFuturasResponseDTO atualizarMetaFuturaPorLoginEId(Long id, String login, MetasFuturasRequestDTO metasFuturasRequestDTO) {
	    Optional<MetasFuturas> metaOptional = repository.findById(id);

	    if (metaOptional.isEmpty()) {
	        throw new NoSuchElementException("Meta não encontrada!");
	    }

	    MetasFuturas meta = metaOptional.get();
	    if (!meta.getUsuario().getLogin().equals(login)) {
	        throw new IllegalArgumentException("Login não corresponde ao usuário da meta!");
	    }

	    Usuario usuario = usuarioService.buscarPorLogin(login);
	    meta.setNome(metasFuturasRequestDTO.getNome());
	    meta.setValorGuardar(metasFuturasRequestDTO.getValorGuardar());
	    meta.setPrioridade(metasFuturasRequestDTO.getPrioridade());
	    meta.setUsuario(usuario);

	    return new MetasFuturasResponseDTO(repository.save(meta));
	}
	
	public void deletarMetaFuturaPorLoginEId(Long id, String login) {
	    Optional<MetasFuturas> metaOptional = repository.findById(id);

	    if (metaOptional.isEmpty()) {
	        throw new NoSuchElementException("Meta não encontrada!");
	    }

	    MetasFuturas meta = metaOptional.get();
	    if (!meta.getUsuario().getLogin().equals(login)) {
	        throw new IllegalArgumentException("Login não corresponde ao usuário da meta!");
	    }

	    repository.delete(meta);
	}
	
	
	
}
