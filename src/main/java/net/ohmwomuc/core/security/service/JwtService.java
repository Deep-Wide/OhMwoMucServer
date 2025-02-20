package net.ohmwomuc.core.security.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private static MacAlgorithm algorithm = Jwts.SIG.HS512;
    public static final String BEARER = "Bearer ";

    @Value("${jwt.atk-expired-time}")
    private long atkExpiredTime;

    @Value("${jwt.secret-key}")
    private String secretKey;

    public String createAccessToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("exp", Instant.now().getEpochSecond() + atkExpiredTime)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), algorithm)
                .compact();
    }

    public String verifyToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }


}
