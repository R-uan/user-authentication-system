package hi.userauthenticationsystem.entities.enduser;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hi.userauthenticationsystem.entities.role.Role;
import hi.userauthenticationsystem.entities.role.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EndUserService {
    @Autowired
    private EndUserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder BCrypt;

    public List<EndUser> FindAllUsers() {
        return userRepository.findAll();
    }
    
    @Transactional
    public EndUser CreateNewUser(EndUserDTO user) {
        Role role = roleRepository.getReferenceByName("USER");
        String ecryptedPassword = BCrypt.encode(user.password());
        EndUser userToSave = new EndUser(user.email(), user.username(), ecryptedPassword, role);
        EndUser savedUser = userRepository.save(userToSave);
        return savedUser;
    }

    public EndUser FindByUsername(String username) {
        Optional<EndUser> result = userRepository.findUserByUsername(username);
        if(result.isPresent()) return result.get();
        else return null;
    }
}
