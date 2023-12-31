package hi.userauthenticationsystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hi.userauthenticationsystem.security.JWTSecurityFilter;

@Configuration
@EnableWebSecurity
public class FilterChainConfig {
    @Autowired
    private JWTSecurityFilter JWTSecurityFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                            .requestMatchers("/authentication/**").permitAll()
                            .requestMatchers("/users/**", "/roles/**").hasAuthority("ADMIN")
                            )
                .addFilterBefore(JWTSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
