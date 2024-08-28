package sg.comp.tcc.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import sg.comp.tcc.dto.AgendamentoRequestDTO;
import sg.comp.tcc.dto.AgendamentoResponseDTO;
import sg.comp.tcc.entity.Agendamento;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;
import sg.comp.tcc.service.AgendamentoService;
import sg.comp.tcc.service.LancamentoFincanceiroService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
	
	@Autowired
	public AgendamentoService service;
	
	@GetMapping("/listarPorId/{id}")
	public ResponseEntity<AgendamentoResponseDTO> buscarPorId(@PathVariable Long id){
		Agendamento agendamento = service.buscarPorId(id);
		if(agendamento == null) {
			return ResponseEntity.notFound().build();
		}
		System.out.println(agendamento.toString());
		return ResponseEntity.ok( new AgendamentoResponseDTO(agendamento));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<AgendamentoResponseDTO>> listarTodos(){
		List<Agendamento> agendamento = service.listarAgendamento();
		return ResponseEntity.ok(agendamento.stream().map((a) -> new AgendamentoResponseDTO(a)).collect(Collectors.toList()));
	}
	
	@GetMapping("/listarPorTipoLancamento/{tipoLancamento}")
	public ResponseEntity<Agendamento> buscarPorTipoLancamento(@PathVariable EnumReceitaDespesa tipoLancamento){
		Agendamento agendamento = service.buscarPorTipoLancamento(tipoLancamento);
		if(agendamento == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(agendamento);
		}
	}
	
	@GetMapping("/listarPorData/{data}")
	public ResponseEntity<List<Agendamento>> buscarPorData(@PathVariable LocalDate data){
		List<Agendamento>  agendamento = service.buscarPorData(data);
		
		if(agendamento == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(agendamento);
		}
	}
	
	@GetMapping("/listarPorDiaSemana/{diaSemana}")
	public ResponseEntity<List<AgendamentoResponseDTO>> buscarPorDiaSemana(@PathVariable EnumDiaSemana diaSemana){
		List<Agendamento> agendamento = service.buscarPorDiaSemana(diaSemana);
		if(agendamento == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(agendamento.stream().map((a) -> new AgendamentoResponseDTO(a)).collect(Collectors.toList()));
		}
	}
	
	@GetMapping("/listarPorDiaMes/{diaMes}")
	public ResponseEntity<List<Agendamento>> buscarPorDiaMes(@PathVariable int diaMes){
		List<Agendamento> agendamento = service.buscarPorDiaMes(diaMes);
		if(agendamento == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(agendamento);
		}
	}
	
	
	@PostMapping("/adicionar")
	public ResponseEntity<AgendamentoResponseDTO> adicionarAgendamento(@Valid @RequestBody AgendamentoRequestDTO agendamento){
		System.out.println(agendamento.toString());
		AgendamentoResponseDTO a = service.inserirAgendamento(agendamento);
		
		if(a == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(a);
		}
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<AgendamentoResponseDTO> atualizarAgendamento(@PathVariable Long id,
			@Valid @RequestBody Agendamento agendamento){
		
		AgendamentoResponseDTO a = service.atualizarAgendamento(id, agendamento);
		if(a != null) {
			return ResponseEntity.ok(a);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id){
		service.deletarAgendamentoPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	 @Autowired
	 private LancamentoFincanceiroService lancamentoService;

	    @PostMapping("/executar")
	    public ResponseEntity<String> executarAgendamentos() {
	        lancamentoService.executaAgendamentos(LocalDate.now());
	        return ResponseEntity.ok("Agendamentos executados com sucesso!");
	    }
	
}
