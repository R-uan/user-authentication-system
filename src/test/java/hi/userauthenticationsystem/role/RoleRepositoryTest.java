package hi.userauthenticationsystem.role;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hi.userauthenticationsystem.entities.role.Role;
import hi.userauthenticationsystem.entities.role.RoleRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository underTest;

    @Test 
    public void ShouldCreateTwoRoles_Admin_and_User() { 
        Role createdUser = underTest.save(new Role("USER"));
        Role createdAdmin = underTest.save(new Role("ADMIN"));
        Assertions.assertEquals(new Role(1, "USER"), createdUser);
        Assertions.assertEquals(new Role(2, "ADMIN"), createdAdmin);
    }

    @Test 
    public void ShouldTestGettingReferenceByName() { 
        Role userRole = underTest.getReferenceByName("USER");
        Role adminRole = underTest.getReferenceByName("ADMIN");
        assertEquals(new Role(1, "USER"), userRole);
        assertEquals(new Role(2, "ADMIN"), adminRole);
    }

    @Test 
    @Transactional 
    public void ShouldTestGettingReferenceById() { 
        Role userRole = underTest.getReferenceById(1);
        Role adminRole = underTest.getReferenceById(2);
        assertEquals(new Role(1, "USER"), userRole);
        assertEquals(new Role(2, "ADMIN"), adminRole);
    }
}
