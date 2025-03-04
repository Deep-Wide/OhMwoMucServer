package net.ohmwomuc.core.security.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import net.ohmwomuc.core.security.dto.JwtDTO;
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

    @Value("${jwt.rtk-expired-time}")
    private long rtkExpiredTime;

    @Value("${jwt.secret-key}")
    private String secretKey;

    public String createAccessToken(Authentication authentication) {
        return createToken(authentication, atkExpiredTime);
    }

    public String createRefreshToken(Authentication authentication) {
        return createToken(authentication, rtkExpiredTime);

    }

    private String createToken(Authentication authentication, long expiredTime) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("exp", Instant.now().getEpochSecond() + expiredTime)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), algorithm)
                .compact();
    }

    public JwtDTO verifyToken(String token) {
        try {
            String subject =
                    Jwts.parser()
                            .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                            .build()
                            .parseSignedClaims(token)
                            .getPayload()
                            .getSubject();
            return JwtDTO.builder()
                    .isVaildToken(true)
                    .subject(subject)
                    .build();
        } catch (Exception e) {
            return JwtDTO.builder()
                    .isVaildToken(false)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }


    public long getRtkExpiredTime() {
        return rtkExpiredTime;
    }
}
