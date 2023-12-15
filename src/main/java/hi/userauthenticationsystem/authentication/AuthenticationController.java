package hi.userauthenticationsystem.authentication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;
    private Logger Log = LogManager.getLogger("AuthenticationController");

    @GetMapping("/login")
    public ResponseEntity<String> LogIn(@RequestBody AuthenticationDTO credentials) { 
        Log.info("Authentication Attempt. LogIn(): " + credentials.username());

        try {
            String jwToken = service.Login(credentials);
            Log.info("Authentication Completed. user: " + credentials.username());
            return ResponseEntity.accepted().body(jwToken);
        } catch (Exception e) {
            Log.error("Authentication Failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
