package DoboszBartoszuk.example.phone_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class PhoneStoreApplication {

	public static void main(String[] args) {
		// Ładowanie zmiennych z pliku .env
        Dotenv dotenv = Dotenv.load();
        // Ustawienie zmiennych jako właściwości systemowe
        System.setProperty("spring.mail.username", dotenv.get("SPRING_MAIL_USERNAME"));
        System.setProperty("spring.mail.password", dotenv.get("SPRING_MAIL_PASSWORD"));

		SpringApplication.run(PhoneStoreApplication.class, args);
	}

}
