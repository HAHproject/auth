package com.example.auth.config;

import com.example.auth.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String makeToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId().toString());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().name());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getId().toString())
                .setExpiration(new Date(
                        System.currentTimeMillis()
                                + (1000L * 60 * 60 * 24 * 30)
                ))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
        return token;
    }
    public TokenInfo parseToken(String token){
        Claims body = (Claims) Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parse(token)
                .getBody();
        return TokenInfo.builder()
                .id(UUID.fromString(body.get("id", String.class)))
                .email(body.get("email", String.class))
                .username(body.get("username", String.class))
                .role(body.get("role", String.class))
                .build();

        // 여긴 우리껄 parse 하는 곳이 아님.
    }


}
