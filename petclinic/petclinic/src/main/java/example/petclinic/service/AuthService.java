package example.petclinic.service;

import example.petclinic.dto.auth.AuthResponse;
import example.petclinic.dto.auth.LoginRequest;
import example.petclinic.dto.auth.RegisterRequest;
import example.petclinic.model.User;
import example.petclinic.repository.UserRepository;
import example.petclinic.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  public AuthResponse registerUser(RegisterRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(request.getRole());
    userRepository.save(user);

    String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole());
    return new AuthResponse(token, user.getRole());
  }

  public AuthResponse authenticateUser(LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenProvider.generateToken(request.getUsername(),
        userRepository.findByUsername(request.getUsername()).getRole());
    return new AuthResponse(token, userRepository.findByUsername(request.getUsername()).getRole());
  }
}