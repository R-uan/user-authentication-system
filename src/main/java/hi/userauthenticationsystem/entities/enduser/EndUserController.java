package hi.userauthenticationsystem.entities.enduser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class EndUserController {
    @Autowired
    private EndUserService service;

    @GetMapping("/users")
    public ResponseEntity<List<EndUser>> GetAllUsers() {
        return ResponseEntity.ok(service.FindAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<EndUser> PostNewUser(@RequestBody EndUserDTO user) {
        EndUser createdUser = service.CreateNewUser(user);
        if(createdUser != null) return ResponseEntity.ok(createdUser);
        else return ResponseEntity.internalServerError().build();
    }
}
