package hi.userauthenticationsystem.entities.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class RoleController {
    @Autowired
    private RoleRepository repository;

    @GetMapping("/roles")
    public List<Role> FindAllRoles() {
        return repository.findAll();
    }
}
