package com.example.demo.security;

import com.example.demo.entity.AppUser;
import io.jsonwebtoken.*;
import java.util.Date;

public class JwtTokenProvider {

    private final String SECRET = "secret123";

    public String createToken(AppUser user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        getClaims(token);
        return true;
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token).getBody();
    }
}
