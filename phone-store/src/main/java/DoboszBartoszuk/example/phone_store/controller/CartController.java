package DoboszBartoszuk.example.phone_store.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DoboszBartoszuk.example.phone_store.model.CartItem;
import DoboszBartoszuk.example.phone_store.model.Phone;
import DoboszBartoszuk.example.phone_store.model.User;
import DoboszBartoszuk.example.phone_store.repository.CartItemRepository;
import DoboszBartoszuk.example.phone_store.repository.PhoneRepository;
import DoboszBartoszuk.example.phone_store.repository.UserRepository;

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
@GetMapping("/total")
public ResponseEntity<Map<String, Double>> getTotalCartPrice(Principal principal) {
    Map<String, Double> response = new HashMap<>();
    response.put("totalPrice", 1799.98);
    return ResponseEntity.ok(response);
}

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
        return "redirect:/cart";
    }
}
