package DoboszBartoszuk.example.phone_store.controller;

import DoboszBartoszuk.example.phone_store.entities.AuthRequest;
import DoboszBartoszuk.example.phone_store.entities.AuthResponse;
import DoboszBartoszuk.example.phone_store.model.EmailDetails;
import DoboszBartoszuk.example.phone_store.model.User;
import DoboszBartoszuk.example.phone_store.repository.UserRepository;
import DoboszBartoszuk.example.phone_store.security.JwtTokenProvider;
import DoboszBartoszuk.example.phone_store.service.CustomUserDetails;
import DoboszBartoszuk.example.phone_store.service.CustomUserDetailsService;
import DoboszBartoszuk.example.phone_store.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        System.out.println(
                "Login attempt: Username = " + authRequest.getUsername() + ", Password = " + authRequest.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
                    .loadUserByUsername(authRequest.getUsername());

            String token = jwtTokenProvider.createToken(userDetails.getUsername(),
                    userDetails.getAuthorities().stream()
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("User has no roles"))
                            .getAuthority());

            sendLoginNotificationEmail(userDetails);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            System.out.println("Zła nazwa lub hasło");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Zła nazwa lub hasło");
        } catch (DisabledException e) {
            System.out.println("Konto jest zablokowane");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Konto jest zablokowane");
        } catch (Exception e) {
            System.out.println("Błąd autoryzacji: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Błąd autoryzacji");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Nazwa użytkownika jest zajęta");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return ResponseEntity.ok("Użytkownik zarejestrowany pomyślnie");
    }

    private void sendLoginNotificationEmail(CustomUserDetails userDetails) {
        String userEmail = userDetails.getUser().getEmail();
        String subject = "Logowanie do twojego konta";
        String message = "Witaj, " + userDetails.getUser().getUsername() + "!\n\n" +
                "Właśnie nastąpiło zalogowanie się na twoje konto.";

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(userEmail);
        emailDetails.setSubject(subject);
        emailDetails.setMsgBody(message);

        emailService.sendEmail(emailDetails);
    }
}
