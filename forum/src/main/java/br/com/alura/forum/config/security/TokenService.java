package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private Long expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {

		Usuario principal = (Usuario) authenticate.getPrincipal();
		Date hoje = new Date();
		Date dataExpiration = new Date(hoje.getTime() + expiration);

		return Jwts.builder().setIssuer("Api do Fórum da Alura").setSubject(principal.getId().toString())
				.setIssuedAt(hoje).setExpiration(dataExpiration).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValido(String token) {

		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(body.getSubject());
	}

}
