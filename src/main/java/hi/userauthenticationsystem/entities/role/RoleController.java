package hi.userauthenticationsystem.entities.role;

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
@RequestMapping("/roles")
@RestController
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<Role>> ReadAllRoles() { 
        try {
            List<Role> result = service.GetAllRoles();
            if(result != null)  return ResponseEntity.ok().body(result);
            else return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Role> CreateNewRole(@RequestBody RoleDTO role) { 
        try {
            Role result = service.CreateNewRole(role);
            if(result != null) return ResponseEntity.ok(result);
            else return ResponseEntity.badRequest().build();  
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<String> DeleteARoleById(@RequestParam("id") Integer id) {
        try {
            Boolean result = service.DeleteARoleById(id);
            if(result == true) return ResponseEntity.accepted().body("Role deleted");
            if(result == false) return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
            else return ResponseEntity.notFound().build(); 
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
