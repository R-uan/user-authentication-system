package hi.userauthenticationsystem.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTService {
    private String secret = "im thinking neuro neuro ooo u oo";
    private Algorithm signature = Algorithm.HMAC256(secret);
    private String issuer = "nuero";

    public String GenerateToken(String username) {
        return JWT
                .create()
                .withIssuer(this.issuer)
                .withSubject(username)
                .withExpiresAt(GetExpiration())
                .sign(this.signature);
    }

    public DecodedJWT VerifyToken(String token) {
        JWTVerifier verifier = JWT
                .require(this.signature)
                .withIssuer(this.issuer)
                .build();
        return verifier.verify(token);
    }

    public Date GetExpiration() { 
        return new Date(new Date().getTime() + 2 * 60 * 1000);
    }
}
