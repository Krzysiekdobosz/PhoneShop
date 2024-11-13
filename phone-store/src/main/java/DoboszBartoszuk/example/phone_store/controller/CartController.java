package DoboszBartoszuk.example.phone_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import DoboszBartoszuk.example.phone_store.model.*;
import DoboszBartoszuk.example.phone_store.repository.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String viewCart(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long phoneId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Phone phone = phoneRepository.findById(phoneId).orElse(null);
        if (phone != null) {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setPhone(phone);
            cartItem.setQuantity(1);
            cartItemRepository.save(cartItem);
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
        return "redirect:/cart";
    }
}
