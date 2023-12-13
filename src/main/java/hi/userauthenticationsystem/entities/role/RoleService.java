package hi.userauthenticationsystem.entities.role;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;
    private Logger Log = LogManager.getFormatterLogger(RoleService.class);

    public List<Role> GetAllRoles() { 
        return repository.findAll();
    }

    public Optional<Role> CreateNewRole(RoleDTO role) {
        try {
            Role createdRole = new Role(role.name());
            Role savedRole = repository.save(createdRole);
            Log.info("Created Role " + role.name());
            return Optional.of(savedRole);
        } catch (Exception e) {
            Log.error(e.getMessage());
            return null;
        }
    }
}
