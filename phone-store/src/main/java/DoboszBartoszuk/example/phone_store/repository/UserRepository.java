package DoboszBartoszuk.example.phone_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DoboszBartoszuk.example.phone_store.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
