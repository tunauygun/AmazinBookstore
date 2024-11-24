package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.dto.CheckoutForm;
import com.example.amazinbookstore.entities.Cart;
import com.example.amazinbookstore.entities.CartItem;
import com.example.amazinbookstore.entities.Purchase;
import com.example.amazinbookstore.repositories.CartRepository;
import com.example.amazinbookstore.repositories.PurchaseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.util.Optional;

@Controller
@RequestMapping("/amazinBookstore/checkout")
public class CheckoutViewController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping
    public String viewCheckoutPage(Model model) {
        populateModal(model);
        model.addAttribute("checkoutForm", new CheckoutForm());
        return "Checkout_Page";
    }

    @PostMapping
    public String checkCheckoutFrom(@Valid CheckoutForm checkoutForm, BindingResult bindingResult, Model model) {
        populateModal(model);

        if (bindingResult.hasErrors()) {
            return "Checkout_Page";
        }

        Optional<Cart> cartOptional = cartRepository.findById(1L);
        Cart cart = cartOptional.orElseGet(Cart::new);
        Purchase purchase = new Purchase(cart, checkoutForm.getFirstName(), checkoutForm.getLastName(), checkoutForm.getAddress());
        purchaseRepository.save(purchase);

        cart.emptyCart();
        cartRepository.save(cart);

        model.addAttribute("isCheckedOut", true);
        return "Checkout_Page";
    }

    private void populateModal(Model model) {
        Optional<Cart> cartOptional = cartRepository.findById(1L);
        Cart cart = cartOptional.orElseGet(Cart::new);

        DecimalFormat df = new DecimalFormat("$0.00");
        model.addAttribute("subtotal", df.format(cart.getSubtotal()));
        model.addAttribute("shipping", df.format(cart.getShippingFee()));
        model.addAttribute("tax", df.format(cart.getTax()));
        model.addAttribute("total", df.format(cart.getTotalPrice()));
    }


}


