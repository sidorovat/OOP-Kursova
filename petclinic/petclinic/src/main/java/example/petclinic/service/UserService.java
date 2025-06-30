package example.petclinic.service;

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
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  public void register(RegisterRequest request) {
    if (userRepository.findByUsername(request.getUsername()) != null) {
      throw new IllegalArgumentException("Username already exists");
    }
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(request.getRole());
    userRepository.save(user);
  }

  public String login(LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return jwtTokenProvider.generateToken(request.getUsername(), getUserRole(request.getUsername()));
  }

  public String getUserRole(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new IllegalArgumentException("User not found");
    }
    return user.getRole();
  }
}