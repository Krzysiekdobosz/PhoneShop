package DoboszBartoszuk.example.phone_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DoboszBartoszuk.example.phone_store.model.Phone;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
}
