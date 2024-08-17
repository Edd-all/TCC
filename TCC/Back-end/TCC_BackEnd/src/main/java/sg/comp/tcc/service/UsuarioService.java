package sg.comp.tcc.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sg.comp.tcc.config.EmailConfig;
import sg.comp.tcc.dto.UsuarioResponseDTO;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.enums.EnumTipoSituacaoUsuario;
import sg.comp.tcc.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailConfig emailConfig;
	
	
	
	public List<Usuario> listarUsuario(){
		return repository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Usuario buscarPorNome(String nome) {
		return repository.findByNome(nome).orElse(null);
	}
	
	public UsuarioResponseDTO cadastrarUsuario(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return new UsuarioResponseDTO(repository.save(usuario));
	}
	
	public UsuarioResponseDTO atualizarUsuario(Long id, Usuario usuario) {
		Usuario u = repository.findById(id).orElse(null);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		if(u == null) {
			throw new NoSuchElementException("Não foi possível achar o Usuario");
		}
		usuario.setId(id);
		return new UsuarioResponseDTO(repository.save(usuario)); 
	}
	
	public void deletarUsuarioPorId(Long id) {
		repository.deleteById(id);
	}
	
	//inserir usuario pra a posterior verificaçã ode email
	public UsuarioResponseDTO inserirNovoUsuario(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuario.setSituacao(EnumTipoSituacaoUsuario.PENDENTE);
		usuario.setId(null);
		
		try {
			emailConfig.sendEmailUsuario(usuario);
		}catch(Exception e) {
        	System.out.println("Erro ao enviar email: " + e.getMessage());
        }
		
		return new UsuarioResponseDTO(repository.save(usuario));
	}
	
}
