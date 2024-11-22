package sg.comp.tcc.controller;

import java.time.LocalDate;
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
import sg.comp.tcc.dto.LancamentoFinanceiroRequestDTO;
import sg.comp.tcc.dto.LancamentoFinanceiroResponseDTO;
import sg.comp.tcc.service.LancamentoFincanceiroService;

@RestController
@RequestMapping("/lancamentoFinanceiro")
//sujeito a mudança de porta
@CrossOrigin(origins = "http://localhost:5173")
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
	
	
	
	@GetMapping("/listarPorLogin")
    public List<LancamentoFinanceiroResponseDTO> listarLancamentosPorLogin(@RequestParam String login) {
        return service.listarLancamentoFinanceiroPorLogin(login);
    }
	
	 @DeleteMapping("/deletarPorLogin")
	    public ResponseEntity<String> deletarLancamentosPorLogin(@RequestParam String login) {
	        try {
	            service.deletarLancamentosFinanceirosPorLogin(login);
	            return ResponseEntity.ok("Lançamentos financeiros deletados com sucesso!");
	        } catch (NoSuchElementException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
	 
	 
	 
	 
	 @GetMapping("/listarPorLoginEId/{id}")
	 public ResponseEntity<LancamentoFinanceiroResponseDTO> listarLancamentoFinanceiroPorLoginEId(
	         @PathVariable Long id,
	         @RequestParam String login) {
	     try {
	         return ResponseEntity.ok(service.listarLancamentoFinanceiroPorLoginEId(login, id));
	     } catch (NoSuchElementException e) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	     } catch (IllegalArgumentException e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	     }
	 }
	 
	 @PutMapping("/atualizarPorLogin/{id}")
	 public ResponseEntity<LancamentoFinanceiroResponseDTO> atualizarLancamentoPorLoginEId(
	         @RequestParam String login,
	         @PathVariable Long id,
	         @Valid @RequestBody LancamentoFinanceiroRequestDTO lancamentoFinanceiroRequestDTO) {
	     try {
	         LancamentoFinanceiroResponseDTO atualizado = service.atualizarLancamentoPorLoginEId(login, id, lancamentoFinanceiroRequestDTO);
	         return ResponseEntity.ok(atualizado);
	     } catch (IllegalArgumentException e) {
	         return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Ação proibida
	     } catch (NoSuchElementException e) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Lançamento não encontrado
	     }
	 }

	 @DeleteMapping("/deletarPorLoginEId/{id}")
	 public ResponseEntity<String> deletarLancamentoPorLoginEId(
	         @RequestParam String login,
	         @PathVariable Long id) {
	     try {
	         service.deletarLancamentoPorLoginEId(login, id);
	         return ResponseEntity.ok("Lançamento financeiro deletado com sucesso!");
	     } catch (IllegalArgumentException e) {
	         return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O lançamento financeiro não pertence ao usuário com o login fornecido.");
	     } catch (NoSuchElementException e) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lançamento financeiro não encontrado!");
	     }
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
