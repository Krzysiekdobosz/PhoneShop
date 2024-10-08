package DoboszBartoszuk.example.phone_store.repository;

import DoboszBartoszuk.example.phone_store.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
}
