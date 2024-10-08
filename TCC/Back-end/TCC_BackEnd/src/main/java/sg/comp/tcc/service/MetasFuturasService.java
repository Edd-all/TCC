package sg.comp.tcc.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.comp.tcc.dto.MetasFuturasRequestDTO;
import sg.comp.tcc.dto.MetasFuturasResponseDTO;
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
	
	public MetasFuturasResponseDTO cadastrarMetasFuturas(MetasFuturasRequestDTO metasFuturasRequestDTO) {
		Usuario usuario = usuarioService.buscarPorId(metasFuturasRequestDTO.getUsuario());
		
		MetasFuturas metas = new MetasFuturas(
				metasFuturasRequestDTO.getNome(),
				metasFuturasRequestDTO.getValorGuardar(),
				usuario
		);
			
		MetasFuturas metasSalvas = repository.save(metas);
		return new MetasFuturasResponseDTO(metasSalvas);
	}
	
	public MetasFuturasResponseDTO atualizarMetasFuturas(Long id, MetasFuturasRequestDTO metasFuturasRequestDTO) {
		Optional<MetasFuturas> metas = repository.findById(id);
		Usuario usuario = usuarioService.buscarPorId(metasFuturasRequestDTO.getUsuario());
		
		if(metas.isPresent()) {
			MetasFuturas metasFuturas = new MetasFuturas(
					metasFuturasRequestDTO.getNome(),
					metasFuturasRequestDTO.getValorGuardar(),
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
	
}
