package hi.userauthenticationsystem.jwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.auth0.jwt.interfaces.DecodedJWT;
import hi.userauthenticationsystem.security.JWTService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class JWTServiceUnitTest {
    @Autowired
    private JWTService underTest;
    private String testSubject = "VOGEL IM KÄFIG";
    private String issuer = "nuero";
    private String testToken;

    @Test public void ShouldGenerateAJWTokenGivenTheTestSubject() { 
        String Token = underTest.GenerateToken(this.testSubject);
        Assertions.assertThat(Token).isNotNull();
        this.testToken = Token;
    }
    
    @Test public void ShouldValidateTheGivenTokenAndExtractTheSubjectAndIssuer() { 
        DecodedJWT Token = underTest.VerifyToken(testToken);
        String TokenSubject = Token.getSubject();
        String TokenIssuer = Token.getIssuer();
        Assertions.assertThat(TokenSubject).isEqualTo(this.testSubject);
        Assertions.assertThat(TokenIssuer).isEqualTo(this.issuer);
    }

    @Test public void ShouldTryToValidateAInvalidToken() {
        String invalidToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        DecodedJWT decodedToken = underTest.VerifyToken(invalidToken);
        Assertions.assertThat(decodedToken).isNull();
    }
}