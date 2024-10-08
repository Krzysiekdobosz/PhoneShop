package DoboszBartoszuk.example.phone_store.repository;

import DoboszBartoszuk.example.phone_store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
