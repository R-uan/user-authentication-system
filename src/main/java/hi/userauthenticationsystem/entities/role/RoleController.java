package hi.userauthenticationsystem.entities.role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Controller
@RequestMapping("/roles")
@RestController
public class RoleController {
    @Autowired
    private RoleService service;
    private Logger Log = LogManager.getFormatterLogger("RoleController");

    @GetMapping
    public ResponseEntity<List<Role>> ReadAllRoles() {
        Log.info("GET Request. ReadAllRoles().");
        
        try {
            Optional<List<Role>> result = service.GetAllRoles();
            if(result.isPresent()) {
                Log.info("GET Request Completed.");
                return ResponseEntity.ok().body(result.get());
            } else {
                Log.info("GET Request Completed.");
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            Log.error("Failed to complete GET Request.");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Role> CreateNewRole(@RequestBody RoleDTO role) { 
        Log.info("POST Request. CreateNewRole().");
        
        try {
            Optional<Role> result = service.CreateNewRole(role);
            if(result.isPresent()) {
                Log.info("POST Request Completed. created role: " + result.get().getName() + ".");
                return ResponseEntity.ok(result.get());
            } else {
                Log.info("POST Request Completed.");
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            Log.error("Failed to complete POST Request.");
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<String> DeleteARoleById(@RequestParam("i") Integer id) {
        Log.info("DELETE Request. DeleteARoleById().");
        
        try {
            Boolean result = service.DeleteARoleById(id);
            if(result == true){ 
                Log.info("POST Request Completed. 200 OK");
                return ResponseEntity.accepted().body("Role deleted");
            } else {
                Log.info("POST Request Completed. 404 NOT FOUND");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Log.error("Failed to complete DELETE Request.");
            return ResponseEntity.internalServerError().build();
        }
    }
}
