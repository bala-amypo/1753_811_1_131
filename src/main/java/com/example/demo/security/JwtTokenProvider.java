package com.example.demo.security;

import com.example.demo.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtTokenProvider {

    // Minimum 256-bit key required for HS256
    private static final Key KEY =
            Keys.hmacShaKeyFor("ThisIsASecretKeyForJwtTokenProvider123456".getBytes());

    public String createToken(AppUser user) {

        return Jwts.builder()
                .setSubject(user.getEmail())          // subject = email
                .claim("role", user.getRole())        // role claim
                .claim("userId", user.getId())        // userId claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        getClaims(token); // will throw exception if invalid
        return true;
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
