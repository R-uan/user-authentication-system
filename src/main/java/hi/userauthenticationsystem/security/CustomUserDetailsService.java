package hi.userauthenticationsystem.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hi.userauthenticationsystem.entities.enduser.EndUser;
import hi.userauthenticationsystem.entities.enduser.EndUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EndUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EndUser> result = repository.findUserByUsername(username);
        if (result.isPresent()) return result.get();
        else throw new UsernameNotFoundException("Username not found");
    }

}
