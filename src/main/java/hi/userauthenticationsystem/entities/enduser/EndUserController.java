package hi.userauthenticationsystem.entities.enduser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class EndUserController {
    @Autowired
    private EndUserService service;
    private Logger Log = LogManager.getFormatterLogger(EndUserController.class);

    @GetMapping("/users")
    public ResponseEntity<List<EndUser>> GetAllUsers() {
        Log.info("GET ALL Request");
        return ResponseEntity.ok(service.FindAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<EndUser> PostNewUser(@RequestBody EndUserDTO user) {
        EndUser createdUser = service.CreateNewUser(user);
        if(createdUser != null) return ResponseEntity.ok(createdUser);
        else return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<String> DeleteAUserByUsername(@RequestParam String username) { 
        Boolean result = service.DeleteUserByUsername(username);
        if(result == true) return ResponseEntity.ok("Deleted");
        else if(result == false) return ResponseEntity.notFound().build();
        else return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/what")
    public String what() { 
        return "what";
    }
}
