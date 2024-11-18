package DoboszBartoszuk.example.phone_store.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import DoboszBartoszuk.example.phone_store.model.EmailDetails;

@Service
@Profile("!test")
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Async
    public CompletableFuture<String> sendEmail(EmailDetails details) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("Phone Store <" + sender + ">");
            simpleMailMessage.setTo(details.getRecipient());
            simpleMailMessage.setText(details.getMsgBody());
            simpleMailMessage.setSubject(details.getSubject());

            javaMailSender.send(simpleMailMessage);
            return CompletableFuture.completedFuture("Mail sent successfully");
        } catch (Exception e) {
            return CompletableFuture.completedFuture("Error while sending mail: " + e.getMessage());
        }
    }
}
