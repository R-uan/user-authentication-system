package hi.userauthenticationsystem.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import hi.userauthenticationsystem.security.JWTService;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService JWT;

    public String LogIn(AuthenticationDTO credentials) { 
        UsernamePasswordAuthenticationToken authToken;
        authToken = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        authenticationManager.authenticate(authToken);
        String JWToken = JWT.GenerateToken(credentials.username());
        return JWToken;
    }
}
