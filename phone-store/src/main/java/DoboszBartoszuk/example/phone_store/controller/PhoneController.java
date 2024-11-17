package DoboszBartoszuk.example.phone_store.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;
import DoboszBartoszuk.example.phone_store.model.Phone;
import DoboszBartoszuk.example.phone_store.repository.PhoneRepository;
import DoboszBartoszuk.example.phone_store.service.SerializationService;

@Controller
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private SerializationService serializationService;

    @GetMapping
    public String listPhones(Model model) {
        List<Phone> phones = phoneRepository.findAll();
        model.addAttribute("phones", phones);
        return "phones";
    }

    @GetMapping("/search")
    public String searchPhones(@RequestParam String query, Model model) {
        List<Phone> phones = phoneRepository.findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(query, query);
        model.addAttribute("phones", phones);
        return "phones";
    }
    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> getPhoneAsJson(@PathVariable Long id) {
    Optional<PhoneDTO> phoneDTO = phoneRepository.findById(id).map(this::convertToDTO);
    if (phoneDTO.isPresent()) {
        try {
            String json = serializationService.serializeToJson(phoneDTO.get());
            return ResponseEntity.ok(json);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd serializacji do JSON.");
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
public ResponseEntity<String> getPhoneAsXml(@PathVariable Long id) {
    Optional<PhoneDTO> phoneDTO = phoneRepository.findById(id).map(this::convertToDTO);
    if (phoneDTO.isPresent()) {
        try {
            String xml = serializationService.serializeToXml(phoneDTO.get());
            return ResponseEntity.ok(xml);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd serializacji do XML.");
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}

@PostMapping(value = "/import/json", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> importPhoneFromJson(@RequestBody PhoneDTO phoneDTO) {
    Phone phone = convertToEntity(phoneDTO);
    phoneRepository.save(phone);
    return ResponseEntity.ok("Telefon zaimportowany pomyślnie.");
}

@PostMapping(value = "/import/xml", consumes = MediaType.APPLICATION_XML_VALUE)
public ResponseEntity<String> importPhoneFromXml(@RequestBody PhoneDTO phoneDTO) {
    Phone phone = convertToEntity(phoneDTO);
    phoneRepository.save(phone);
    return ResponseEntity.ok("Telefon zaimportowany pomyślnie.");
}

private PhoneDTO convertToDTO(Phone phone) {
    PhoneDTO dto = new PhoneDTO();
    dto.setId(phone.getId());
    dto.setModel(phone.getModel());
    dto.setManufacturer(phone.getBrand());
    return dto;
}
private Phone convertToEntity(PhoneDTO dto) {
    Phone phone = new Phone();
    phone.setId(dto.getId());
    phone.setBrand(dto.getManufacturer());
    phone.setModel(dto.getModel());
    phone.setPrice(dto.getPrice());
    return phone;
}


}
