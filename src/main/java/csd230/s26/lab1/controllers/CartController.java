package csd230.s26.lab1.controllers;

import csd230.s26.lab1.entities.CartEntity;
import csd230.s26.lab1.entities.ProductEntity;
import csd230.s26.lab1.entities.UserEntity;
import csd230.s26.lab1.repositories.CartRepository;
import csd230.s26.lab1.repositories.ProductRepository;
import csd230.s26.lab1.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartController(CartRepository cartRepository,
                          ProductRepository productRepository,
                          UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    private CartEntity getCartForCurrentUser(Principal principal) {
        String username = principal.getName();
        UserEntity user = userRepository.findByUsername(username);
        CartEntity cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new CartEntity();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    // 1. VIEW CART
    @GetMapping
    public String viewCart(Model model, Principal principal) {
        model.addAttribute("cart", getCartForCurrentUser(principal));
        return "cartDetails";
    }

    // 2. ADD TO CART
    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);
        ProductEntity product = productRepository.findById(productId).orElse(null);
        if (cart != null && product != null) {
            cart.addProduct(product);
            cartRepository.save(cart);
        }
        return "redirect:/books";
    }

    // 3. REMOVE FROM CART
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId, Principal principal) {
        CartEntity cart = getCartForCurrentUser(principal);
        ProductEntity product = productRepository.findById(productId).orElse(null);
        if (cart != null && product != null) {
            cart.getProducts().remove(product);
            cartRepository.save(cart);
        }
        return "redirect:/cart";
    }
}