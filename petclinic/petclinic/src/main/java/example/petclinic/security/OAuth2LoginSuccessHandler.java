package example.petclinic.security;

import example.petclinic.model.User;
import example.petclinic.repository.UserRepository;
import example.petclinic.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    String username = oAuth2User.getAttribute("email");
    User user = userRepository.findByUsername(username);
    if (user == null) {
      user = new User();
      user.setUsername(username);
      user.setRole("USER");
      userRepository.save(user);
    }
    String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole());
    getRedirectStrategy().sendRedirect(request, response, "/api/auth/success?token=" + token);
  }
}