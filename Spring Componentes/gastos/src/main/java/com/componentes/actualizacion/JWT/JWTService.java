package com.componentes.actualizacion.JWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.componentes.actualizacion.modelos.UsuariosModel;
import com.componentes.actualizacion.services.VariablesGlobalesService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

    @Autowired
    private VariablesGlobalesService variablesGlobalesService;

    private SecretKey key;

    private void initKey() {
        String secret = variablesGlobalesService.buscarId(2L).getValor();
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public String createToken(Map<String, Object> claims, String username) {
        initKey();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 10000 * 6000 * 30))
                .signWith(key, Jwts.SIG.HS512) // Nueva forma recomendada
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UsuariosModel userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getCorreo()) && !isTokenExpired(token));
    }

}
