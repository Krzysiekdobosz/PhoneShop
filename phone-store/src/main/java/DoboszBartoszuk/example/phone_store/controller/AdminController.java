package DoboszBartoszuk.example.phone_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import DoboszBartoszuk.example.phone_store.model.Phone;
import DoboszBartoszuk.example.phone_store.repository.PhoneRepository;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {

    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping("/phones")
    public String managePhones(Model model) {
        model.addAttribute("phones", phoneRepository.findAll());
        return "admin/phones";
    }

    @GetMapping("/phones/add")
    public String showAddPhoneForm(Model model) {
        model.addAttribute("phone", new Phone());
        return "admin/add-phone";
    }

    @PostMapping("/phones/add")
    public String addPhone(@ModelAttribute Phone phone) {
        phoneRepository.save(phone);
        return "redirect:/admin/phones";
    }

    // Edycja telefonu
    @GetMapping("/phones/edit/{id}")
    public String showEditPhoneForm(@PathVariable Long id, Model model) {
        Phone phone = phoneRepository.findById(id).orElse(null);
        if (phone != null) {
            model.addAttribute("phone", phone);
            return "admin/edit-phone";
        }
        return "redirect:/admin/phones";
    }

    @PostMapping("/phones/edit")
    public String editPhone(@ModelAttribute Phone phone) {
        phoneRepository.save(phone);
        return "redirect:/admin/phones";
    }

    // Usuwanie telefonu
    @PostMapping("/phones/delete/{id}")
    public String deletePhone(@PathVariable Long id) {
        phoneRepository.deleteById(id);
        return "redirect:/admin/phones";
    }
}
