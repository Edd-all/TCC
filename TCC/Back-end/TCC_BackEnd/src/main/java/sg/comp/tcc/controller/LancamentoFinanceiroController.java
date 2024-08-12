package sg.comp.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.comp.tcc.dto.LancamentoFinanceiroRequestDTO;
import sg.comp.tcc.dto.LancamentoFinanceiroResponseDTO;
import sg.comp.tcc.service.LancamentoFincanceiroService;

@RestController
@RequestMapping("/lancamentoFinanceiro")
public class LancamentoFinanceiroController {
	
	@Autowired
	private LancamentoFincanceiroService service;
	
	@GetMapping("/listar")
	public List<LancamentoFinanceiroResponseDTO> listarLancamentosFinanceiros(){
		return service.listarLancamentosFinanceiros();
	}
	@GetMapping("/listarPorId/{id}")
	public ResponseEntity<LancamentoFinanceiroResponseDTO> listarLancamentoFinanceiroPorId(
			@PathVariable Long id){
		return ResponseEntity.ok(service.listarLancamentoFinanceiroPorId(id));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<LancamentoFinanceiroResponseDTO> cadastrarLancamentoFinanceiro(
			@Valid @RequestBody LancamentoFinanceiroRequestDTO lancamentoFinanceiroRequestDTO){
		return ResponseEntity.created(null).body(service.cadastrarLancamentoFinanceiro(lancamentoFinanceiroRequestDTO));
	}
	
	@PutMapping("/atualizar/{id}")
	public LancamentoFinanceiroResponseDTO atualizarLancamentoFinanceiro(
			@Valid @PathVariable Long id, 
			@RequestBody LancamentoFinanceiroRequestDTO lancamentoFinanceiroRequestDTO) {
		return service.atualizarLancamentoFinanceiro(id, lancamentoFinanceiroRequestDTO);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarLancamentoFinanceiro(@PathVariable Long id){
		service.deletarLancamentoFinanceiro(id);
		return ResponseEntity.noContent().build();
	}
}
