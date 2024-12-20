package sg.comp.tcc.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import sg.comp.tcc.service.UserDetailsImpl;

@Component
public class JwtUtils {
	
	@Value("${projeto.jwtSecret}")
	private String jwtSecret;
	
	@Value("${projeto.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetail) {
		
		
		return Jwts.builder()
				.setSubject(userDetail.getUsername())
				
				 //.setSubject(String.valueOf(userDetail.getId())) // Passa o ID do usuário como subject
				 //.claim("username", userDetail.getUsername()) // Armazena o username como claim adicional, se necessário
				  
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();
	}
	
	public Key getSigninKey() {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		return key;
	}
	
	public String getUsernameToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigninKey())
				.build().parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
			return true;
			
		}catch(MalformedJwtException e) {
			//System.out.println("Token inválido!" + e.getMessage()); //comentado pois estava sendo chamado com o token funcional
		}catch(ExpiredJwtException e) {
			System.out.println("Token Expirado!" + e.getMessage());
		}catch(UnsupportedJwtException e) {
			System.out.println("Token não suportado!" + e.getMessage());
		}catch(IllegalArgumentException e) {
			System.out.println("Token ilegal!" + e.getMessage());
		}
		
		return false;
	}
	
	
}
