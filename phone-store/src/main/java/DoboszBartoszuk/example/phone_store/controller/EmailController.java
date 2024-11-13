package DoboszBartoszuk.example.phone_store.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import DoboszBartoszuk.example.phone_store.model.EmailDetails;
import DoboszBartoszuk.example.phone_store.service.EmailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public CompletableFuture<String> sendEmail(@RequestBody EmailDetails details) {
        return emailService.sendEmail(details);
    }
}
