package com.mecano.authservice.security;

import com.mecano.authservice.entity.Utilisateur;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mecano_secret_key_2024_very_long_secret_key_mecano";
    private final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000; // 15 min
    private final long ROLE_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 jours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Token 1 — infos utilisateur
    public String generateAccessToken(Utilisateur user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("nom", user.getNom());
        claims.put("prenom", user.getPrenom());
        claims.put("email", user.getEmail());
        claims.put("telephone", user.getTelephone());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Token 2 — rôle
    public String generateRoleToken(Utilisateur user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("role", user.getRole() != null ? user.getRole().name() : "NONE");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ROLE_TOKEN_EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}