package sg.comp.tcc.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import sg.comp.tcc.dto.UsuarioResponseDTO;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
//sujeito a mudança de porta
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {
	
	@Autowired
	public UsuarioService service;
	
	@GetMapping("/listarPorId/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
		Usuario usuario = service.buscarPorId(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Usuario>> listarTodos(){
		List<Usuario> usuarios = service.listarUsuario();
		Collections.sort(usuarios, Comparator.comparing(Usuario::getNome));
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/listarPorNome/{nome}")
	public ResponseEntity<Usuario> buscarPorNome(@RequestParam String nome){
		Usuario usuario = service.buscarPorNome(nome);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(usuario);
		}
	}
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioResponseDTO> adicionarUsuario(@Valid @RequestBody Usuario usuario){
		UsuarioResponseDTO u = service.cadastrarUsuario(usuario);
		if(u == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(u);
		}
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id,
			@Valid @RequestBody Usuario usuario){
		
		UsuarioResponseDTO u = service.atualizarUsuario(id, usuario);
		if(u != null) {
			return ResponseEntity.ok(u);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
		service.deletarUsuarioPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
}
