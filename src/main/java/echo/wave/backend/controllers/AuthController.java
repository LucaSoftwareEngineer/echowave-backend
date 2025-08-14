package echo.wave.backend.controllers;

import echo.wave.backend.dto.AuthRequest;
import echo.wave.backend.dto.AuthResponse;
import echo.wave.backend.dto.RegisterRequest;
import echo.wave.backend.dto.UserDetailsResponse;
import echo.wave.backend.models.User;
import echo.wave.backend.repositories.UserRepository;
import echo.wave.backend.security.JwtUtil;
import echo.wave.backend.services.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getRawPassword());
        User user = new User(null, request.getUsername(), hashedPassword, request.getRole(), null);
        userRepository.save(user);
    }

    @GetMapping("/user/details")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(customUserDetailsService.getUserDetails(token));
    }

}
