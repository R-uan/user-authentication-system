package hi.userauthenticationsystem.entities.enduser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Controller
@RestController
@RequestMapping("/users")
public class EndUserController {
    @Autowired
    private EndUserService service;

    @GetMapping
    public ResponseEntity<List<EndUser>> GetAllUsers() {
        try {
            List<EndUser> findAllUsers = service.FindAllUsers();
            if(findAllUsers != null) return ResponseEntity.ok(findAllUsers);
            else return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<EndUser> CreateNewUser(@RequestBody EndUserDTO user) {
        try {
            EndUser createdUser = service.CreateNewUser(user);
            if(createdUser != null) return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
 
    @GetMapping("/search")
    public ResponseEntity<EndUser> GetOneByUsername(@RequestParam("u") String username) { 
        try {
            EndUser findByUsername = service.FindByUsername(username);
            if(findByUsername == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok().body(findByUsername);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> DeleteAUserById(@RequestParam("id") Long id) {
        try {
            Boolean result = service.DeleteAUserById(id);
            if(result == true) return ResponseEntity.ok("Deleted ID: " + id);
            if(result == false) return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
            else return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
