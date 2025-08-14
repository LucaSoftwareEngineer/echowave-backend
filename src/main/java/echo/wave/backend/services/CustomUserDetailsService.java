package echo.wave.backend.services;

import echo.wave.backend.dto.UserDetailsResponse;
import echo.wave.backend.exceptions.UserNotFound;
import echo.wave.backend.models.User;
import echo.wave.backend.repositories.UserRepository;
import echo.wave.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword()).roles(user.getRole()).build();
    }

    public UserDetailsResponse getUserDetails(String token) {
        token = token.substring(7);
        String username = jwtUtil.extractUsername(token);
        User user = userRepository.findByUsername(username).get();
        if (user == null) {
            new UserNotFound("Utente non trovato");
        }
        UserDetailsResponse res = new UserDetailsResponse();
        res.setId(user.getIdUser());
        res.setUsername(user.getUsername());
        res.setRole(user.getRole());
        return res;
    }

}
