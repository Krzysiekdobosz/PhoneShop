package DoboszBartoszuk.example.phone_store.config;

import DoboszBartoszuk.example.phone_store.model.EmailDetails;
import DoboszBartoszuk.example.phone_store.service.CustomUserDetails;
import DoboszBartoszuk.example.phone_store.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("Zalogowano");
        // Pobierz obiekt CustomUserDetails po udanym logowaniu
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUser().getEmail();

        logger.debug("Email: {}", userEmail);

        // Wyślij e-mail z powiadomieniem
        String subject = "Logowanie do twojego konta";
        String message = "Witaj, " + userDetails.getUser().getUsername() + "!\n\n" +
                "Właśnie nastąpiło zalogowanie się na twoje konto.";

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(userEmail);
        emailDetails.setSubject(subject);
        emailDetails.setMsgBody(message);

        emailService.sendEmail(emailDetails);

        // Przekierowanie użytkownika po udanym logowaniu
        response.sendRedirect("/");
    }
}
