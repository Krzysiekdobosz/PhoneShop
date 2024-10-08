package DoboszBartoszuk.example.phone_store.repository;

import DoboszBartoszuk.example.phone_store.model.CartItem;
import DoboszBartoszuk.example.phone_store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}
