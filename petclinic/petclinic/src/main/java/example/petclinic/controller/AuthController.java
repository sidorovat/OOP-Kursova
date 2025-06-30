package example.petclinic.controller;

import example.petclinic.dto.auth.AuthResponse;
import example.petclinic.dto.auth.LoginRequest;
import example.petclinic.dto.auth.RegisterRequest;
import example.petclinic.security.JwtTokenProvider;
import example.petclinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService userService;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
    userService.register(request);
    String token = jwtTokenProvider.generateToken(request.getUsername(), request.getRole());
    return ResponseEntity.ok(new AuthResponse(token, request.getRole()));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    String token = userService.login(request);
    String role = userService.getUserRole(request.getUsername());
    return ResponseEntity.ok(new AuthResponse(token, role));
  }
}