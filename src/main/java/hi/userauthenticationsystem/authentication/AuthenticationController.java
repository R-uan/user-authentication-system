package hi.userauthenticationsystem.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @GetMapping("/login")
    public String LogIn(@RequestBody AuthenticationDTO credentials) { 
        return service.LogIn(credentials);
    }
}
