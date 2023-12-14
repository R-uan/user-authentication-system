package hi.userauthenticationsystem.authentication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import hi.userauthenticationsystem.entities.enduser.EndUserDTO;
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
    Logger Log = LogManager.getLogger("AuthenticationService");

    public String Login(AuthenticationDTO credentials) { 
        try {
            UsernamePasswordAuthenticationToken authToken;
            authToken = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
            authenticationManager.authenticate(authToken);
            String jwToken = JWT.GenerateToken(credentials.username());
            return jwToken;
        } catch (Exception e) {
            Log.error("Failed Authentication: " + e.getMessage());
            throw e;
        }
    }

    public void Signin(EndUserDTO registration) { 
        endUserService.CreateNewUser(registration);
    }
}
