package DoboszBartoszuk.example.phone_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import DoboszBartoszuk.example.phone_store.model.Phone;
import DoboszBartoszuk.example.phone_store.repository.PhoneRepository;

import java.util.List;

@Controller
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

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
}
