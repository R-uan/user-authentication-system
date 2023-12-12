package hi.userauthenticationsystem.enduser;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import hi.userauthenticationsystem.entities.enduser.EndUser;
import hi.userauthenticationsystem.entities.enduser.EndUserRepository;
import hi.userauthenticationsystem.entities.role.Role;
import hi.userauthenticationsystem.entities.role.RoleRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class EndUserRepositoryTest {
    @Autowired
    private EndUserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeAll @Transactional public void CreateRoles() { 
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
    }
    
    @Test public void ChekcIfRolesExistWithExpectedIds() {
        Optional<Role> getUser = roleRepository.findById(1);
        Optional<Role> getAdmin = roleRepository.findById(2);
        Assertions.assertThat(getUser.get()).isEqualTo(new Role(1, "USER"));
        Assertions.assertThat(getAdmin.get()).isEqualTo(new Role(2, "ADMIN"));
    }
    @Test @Transactional public void CreatesUserAndChecksIfEnabled() {
        Optional<Role> getUser = roleRepository.findById(1);
        EndUser user = new EndUser("email","user","password", getUser.get());
        EndUser save = userRepository.save(user);
        Assertions.assertThat(save.isEnabled()).isTrue();
    }
}
