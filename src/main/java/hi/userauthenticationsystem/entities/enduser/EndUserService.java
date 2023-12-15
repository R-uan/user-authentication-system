package hi.userauthenticationsystem.entities.enduser;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private Logger Log = LogManager.getLogger("EndUserService");

    public List<EndUser> FindAllUsers() {
        try {
            List<EndUser> result = userRepository.findAll();
            if(result.isEmpty()) return null;
            return result;
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw e;
        }
    }
    
    @Transactional
    public EndUser CreateNewUser(EndUserDTO user) {
        try {
            Role role = roleRepository.getReferenceByName("USER");
            String ecryptedPassword = BCrypt.encode(user.password());
            EndUser userToSave = new EndUser(user.email(), user.username(), ecryptedPassword, role);
            EndUser savedUser = userRepository.save(userToSave);
            if(savedUser != null) return savedUser;
            else return null;
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw e;
        }
    }
    
    @Transactional
    public Optional<EndUser> CreateDefaultAdmin() {
        try {
            EndUserDTO admin = new EndUserDTO("admin@gmail.com", "admin", "admin");
            Role role = roleRepository.getReferenceByName("ADMIN");
            String ecryptedPassword = BCrypt.encode(admin.password());
            EndUser userToSave = new EndUser(admin.email(), admin.username(), ecryptedPassword, role);
            EndUser savedUser = userRepository.save(userToSave);
            if(savedUser != null) return Optional.of(savedUser);
            else return Optional.empty();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw e;
        }
    }

    public EndUser FindByUsername(String username) {
        try {
            Optional<EndUser> result = userRepository.findUserByUsername(username);
            if(result.isEmpty()) return null;
            else return result.get();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw e;
        }
    }

    public Boolean DeleteAUserById(Long id) { 
        try {
            Optional<EndUser> exists = userRepository.findById(id);
            if(exists.isEmpty()) return null;
            userRepository.deleteById(id);
            Optional<EndUser> check = userRepository.findById(id);
            return check.isEmpty();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw e;
        }
    }
}
