package sarahguarneri.BEU2W3L2.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sarahguarneri.BEU2W3L2.entities.User;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${spring.jwt.secret}")
    private String secret;


    public String createToken(User user){
        return Jwts.builder().subject(String.valueOf(user.getId()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * 24))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String accessToken){
        try{
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(accessToken);
        } catch (Exception e) {
            throw new RuntimeException("Problemi con ilò token, effettuare di nuovo il login");
        }
    }

    public String extractIdFromToken(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
