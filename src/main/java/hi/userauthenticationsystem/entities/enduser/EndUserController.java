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
import java.util.Optional;

@Controller
@RestController
public class EndUserController {
    @Autowired
    private EndUserService service;
    private Logger Log = LogManager.getFormatterLogger("EndUserController");

    @GetMapping("/users")
    public ResponseEntity<List<EndUser>> GetAllUsers() {
        Log.info("GET Request. GetAllUsers()");

        try {
            Optional<List<EndUser>> findAllUsers = service.FindAllUsers();
            if(findAllUsers.isPresent()) return ResponseEntity.ok(findAllUsers.get());
            else return ResponseEntity.noContent().build();
        } catch (Exception e) {
            Log.error("Failed to Complete GET Request");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<EndUser> CreateNewUser(@RequestBody EndUserDTO user) {
        Log.info("POST Request called. CreateNewUser()");

        try {
            Optional<EndUser> createdUser = service.CreateNewUser(user);
            if(createdUser.isPresent()) {
                Log.info("POST Request Completed. created user: " + createdUser.get().getUsername() + " with ID: " + createdUser.get().getId());
                return ResponseEntity.ok(createdUser.get());
            }
            else {
                Log.error("Failed to complete POST Request");
                return ResponseEntity.internalServerError().build();
            }
        } catch (Exception e) {
            Log.error("Failed to Complete POST Request");
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/users/search")
    public ResponseEntity<EndUser> GetOneByUsername(@RequestParam("username") String username) { 
        Log.info("GET Request Started. GetOneByUsername()");

        try {
            Optional<EndUser> findByUsername = service.FindByUsername(username);
            if(findByUsername.isEmpty()) {
                Log.info("GET Request Complete. user not found: " + username);
                return ResponseEntity.notFound().build();
            }
            Log.info("GET Request Completed. user found: " + username);
            return ResponseEntity.ok().body(findByUsername.get());
        } catch (Exception e) {
            Log.error("Failed to Complete GET Request");
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> DeleteAUserById(@RequestParam("id") Long id) {
        Log.info("DELETE Request called. DeleteAUserById()"); 
        
        try {
            Boolean result = service.DeleteAUserById(id);
            if(result == true) {
                Log.info("DELETE Request Completed. deleted id: " + id);
                return ResponseEntity.ok("Deleted ID: " + id);
            }
            else {
                Log.info("DELETE Request Completed. id not found " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Log.error("Failed to Complete DELETE Request");
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
