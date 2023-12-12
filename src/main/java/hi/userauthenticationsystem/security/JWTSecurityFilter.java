package hi.userauthenticationsystem.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService JWTS;
    @Autowired
    private CustomUserDetailsService CUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = GetBearerToken(request);
        if(token != null) { 
            String subject = JWTS.VerifyToken(token).getSubject();
            UserDetails subjectData = CUserDetailsService.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken authenticationToken;
            authenticationToken = new UsernamePasswordAuthenticationToken(subjectData.getUsername(), null, subjectData.getAuthorities());
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }

    private String GetBearerToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header == null) return null;
        return header.replace("Bearer ", "").trim();
    }

}
