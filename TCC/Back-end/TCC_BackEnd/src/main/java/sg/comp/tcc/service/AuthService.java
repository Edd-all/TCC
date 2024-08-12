package sg.comp.tcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import sg.comp.tcc.dto.AcessDTO;
import sg.comp.tcc.dto.AuthenticationDTO;
import sg.comp.tcc.security.jwt.JwtUtils;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public AcessDTO login(AuthenticationDTO authDto) {
		
		try {
			//cria mecanismo de credencial para spring
			UsernamePasswordAuthenticationToken userAuth = 
					new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
			
			//prepara para autenticação
			Authentication authentication = authenticationManager.authenticate(userAuth);
			
			//Busca usuario logado
			UserDetailsImpl userAutheticate = (UserDetailsImpl)authentication.getPrincipal();
			
			String token = jwtUtils.generateTokenFromUserDetailsImpl(userAutheticate);
			
			AcessDTO acessDTO = new AcessDTO(token); 
			
			return acessDTO;
			
		}catch(BadCredentialsException e) {
			//TODO
		}
		return new AcessDTO("Acesso negado!");
	}
}
