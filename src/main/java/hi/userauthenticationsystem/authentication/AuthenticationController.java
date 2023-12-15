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

import hi.userauthenticationsystem.entities.enduser.EndUser;

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
    
    @GetMapping("/486217935")
    public ResponseEntity<EndUser> CreateDefaultAdmin() { 
        Log.info("Creation of Default Admin Started. CreateDefaultAdmin()");
        try {
            EndUser result = service.CreateDefaultAdmin();
            if(result != null) {
                Log.info("Creation of Default Admin Completed. 200 OK");
                return ResponseEntity.ok(result);
            } else {
                Log.info("Creation of Default Admin Completed. 500 Internal Server Error");
                return ResponseEntity.internalServerError().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
