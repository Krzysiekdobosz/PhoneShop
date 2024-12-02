package DoboszBartoszuk.example.phone_store.service;

import java.util.concurrent.CompletableFuture;
import DoboszBartoszuk.example.phone_store.model.EmailDetails;

public interface EmailServiceInterface {
    public CompletableFuture<String> sendEmail(EmailDetails details);
}
