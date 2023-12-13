package hi.userauthenticationsystem.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import hi.userauthenticationsystem.entities.enduser.EndUser;
import hi.userauthenticationsystem.entities.enduser.EndUserDTO;
import hi.userauthenticationsystem.entities.enduser.EndUserRepository;
import hi.userauthenticationsystem.entities.enduser.EndUserService;
import hi.userauthenticationsystem.security.JWTService;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService JWT;

    @Autowired
    private EndUserService endUserService;

    public String Login(AuthenticationDTO credentials) { 
        UsernamePasswordAuthenticationToken authToken;
        authToken = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        authenticationManager.authenticate(authToken);
        String JWToken = JWT.GenerateToken(credentials.username());
        return JWToken;
    }

    public void Signin(EndUserDTO registration) { 
        endUserService.CreateNewUser(registration);
    }
}
