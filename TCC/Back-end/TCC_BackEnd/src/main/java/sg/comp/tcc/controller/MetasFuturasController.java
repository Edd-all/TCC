package sg.comp.tcc.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.comp.tcc.dto.LancamentoFinanceiroResponseDTO;
import sg.comp.tcc.dto.MetasFuturasRequestDTO;
import sg.comp.tcc.dto.MetasFuturasResponseDTO;
import sg.comp.tcc.service.MetasFuturasService;

@RestController
@RequestMapping("/metasFuturas")
//sujeito a mudança de porta
@CrossOrigin(origins = "http://localhost:5173")
public class MetasFuturasController {
	
	@Autowired
	private MetasFuturasService service;
	
	@GetMapping("/listar")
	public List<MetasFuturasResponseDTO> listarMetasFuturas(){
		return service.listarMetas();
	}
	
	@GetMapping("/listarPorId/{id}")
	public ResponseEntity<MetasFuturasResponseDTO> listarMetasFuturasPorId(@PathVariable Long id){
		return ResponseEntity.ok(service.listarMetasFuturasPorId(id));
	}
	
	
	
	
		@GetMapping("/listarPorLogin")
	    public List<MetasFuturasResponseDTO> listarMetasFuturasPorLogin(@RequestParam String login) {
	        return service.listarMetasFuturasPorLogin(login);
	    }
		@DeleteMapping("/deletarPorLogin")
	    public ResponseEntity<String> deletarMetasFuturasPorLogin(@RequestParam String login) {
	        try {
	            service.deletarMetasFuturasPorLogin(login);
	            return ResponseEntity.ok("Lançamentos financeiros deletados com sucesso!");
	        } catch (NoSuchElementException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
		
		
		
	
	@PostMapping("/cadastrar")
	public ResponseEntity<MetasFuturasResponseDTO> cadastrarMetasFuturas(
			@Valid @RequestBody MetasFuturasRequestDTO metasFuturasRequestDTO){
		return ResponseEntity.created(null).body(service.cadastrarMetasFuturas(metasFuturasRequestDTO));
	}
	
	@PutMapping("/atualizar/{id}")
	public MetasFuturasResponseDTO atualizarMetasFuturas(
			@Valid @PathVariable Long id, 
			@RequestBody MetasFuturasRequestDTO metasFuturasRequestDTO) {
		return service.atualizarMetasFuturas(id, metasFuturasRequestDTO);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarMetasFuturas(@PathVariable Long id){
		service.deletarMetasFuturas(id);
		return ResponseEntity.noContent().build();
	}
}
