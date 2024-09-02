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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.comp.tcc.dto.SaldoMensalRequestDTO;
import sg.comp.tcc.dto.SaldoMensalResponseDTO;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.service.SaldoMensalService;
import sg.comp.tcc.service.UsuarioService;

@RestController
@RequestMapping("/saldoMensal")
public class SaldoMensalController {
	
	@Autowired
	private SaldoMensalService service;
	
	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping("/listar")
	public List<SaldoMensalResponseDTO> listarSaldosMensais(){
		return service.listarSaldo();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<SaldoMensalResponseDTO> cadastrarSaldoMensal(
			@Valid @RequestBody SaldoMensalRequestDTO saldoMensalRequestDTO){
		return ResponseEntity.created(null).body(service.cadastrarSaldoMensal(saldoMensalRequestDTO));
	}
	
	@PutMapping("/atualizar/{id}")
	public SaldoMensalResponseDTO atualizarSaldoMensal(
			@Valid @PathVariable Long id, 
			@RequestBody SaldoMensalRequestDTO saldoMensalRequestDTO) {
		return service.atualizarSaldoMensal(id, saldoMensalRequestDTO);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarSaldoMensal(@PathVariable Long id){
		service.deletarSaldoMensal(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/calcular")
    public ResponseEntity<String> calcularSaldoMensal(@RequestParam Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        String mensagem = service.calcularSaldoMensal(usuario);
        return ResponseEntity.ok(mensagem);
    }
	 
}
