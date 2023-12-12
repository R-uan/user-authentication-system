package hi.userauthenticationsystem.entities.enduser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Long> {
    Optional<EndUser> findUserByUsername(String username);
    EndUser findOneUserByUsername(String username);
}
