package hi.userauthenticationsystem.entities.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("/roles")
    public List<Role> FindAllRoles() {
        return service.GetAllRoles();
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> CreateNewRole(@RequestBody RoleDTO role) { 
        Optional<Role> result = service.CreateNewRole(role);
        if(result.isPresent()) return ResponseEntity.ok(result.get());
        return ResponseEntity.internalServerError().build();
    }
}
