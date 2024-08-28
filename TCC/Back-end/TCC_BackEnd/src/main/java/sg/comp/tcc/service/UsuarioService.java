package sg.comp.tcc.service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sg.comp.tcc.config.EmailConfig;
import sg.comp.tcc.dto.UsuarioResponseDTO;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.entity.UsuarioVerificador;
import sg.comp.tcc.enums.EnumTipoSituacaoUsuario;
import sg.comp.tcc.repository.UsuarioRepository;
import sg.comp.tcc.repository.UsuarioVerificadorRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailConfig emailConfig;
	
	@Autowired
	private UsuarioVerificadorRepository usuarioVerificadorRepository; 
	
	
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
		repository.save(usuario);
		
		try {
			UsuarioVerificador verificador = new UsuarioVerificador();
			verificador.setUsuario(usuario);
			verificador.setUuid(UUID.randomUUID());
			verificador.setDataExpiracao(Instant.now().plusMillis(900000));
			usuarioVerificadorRepository.save(verificador);
			
			emailConfig.sendEmailUsuario(usuario, verificador);
			
		}catch(Exception e) {
        	System.out.println("Erro ao enviar email: " + e.getMessage());
        }
		
		return new UsuarioResponseDTO(repository.save(usuario));
	}
	
	
	public String verificarCadastro(String uuid) {
		UsuarioVerificador usuarioVerificacao = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid)).get();
		
		if(usuarioVerificacao != null) {
			if(usuarioVerificacao.getDataExpiracao().compareTo(Instant.now()) >= 0) {
				Usuario u = usuarioVerificacao.getUsuario();
				u.setSituacao(EnumTipoSituacaoUsuario.ATIVO);
				repository.save(u);
				return "Usuário Verificado!";
			}else {
				usuarioVerificadorRepository.delete(usuarioVerificacao);
				return "Tempo de verificação expirado!";
			}
		}else {
			return "Usuario não verificado.";
		}
		
	}
}
