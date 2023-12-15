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
    private Logger Log = LogManager.getFormatterLogger("RoleService");

    public Optional<List<Role>> GetAllRoles() { 
        try {
            List<Role> result = repository.findAll();
            if(result.isEmpty()) return Optional.empty();
            else return Optional.of(result);
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw e;
        }
    }

    public Optional<Role> CreateNewRole(RoleDTO role) {
        try {
            Role createdRole = new Role(role.name());
            Role savedRole = repository.save(createdRole);
            return Optional.of(savedRole);
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw e;
        }
    }

    public Boolean DeleteARoleById(Integer id) {
        try {
            Optional<Role> exists = repository.findById(id);
            if(exists.isEmpty()) return false; 
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw e;
        }
    }
}
