package com.example.amazinbookstore.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem newCartItem) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getBook().getId() == newCartItem.getBook().getId()) {
                incrementCartItemQuantity(cartItem.getId());
                return;
            }
        }
        if (newCartItem.getQuantity() <= newCartItem.getBook().getQuantity()) {
            this.cartItems.add(newCartItem);
        }
    }

    public boolean incrementCartItemQuantity(Long cartItemId) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getId() == cartItemId) {
                int maxQuantity = cartItem.getBook().getQuantity();
                if (cartItem.getQuantity() + 1 > maxQuantity) {
                    return false;
                }
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return true;
            }
        }
        return false;
    }

    public boolean decrementCartItemQuantity(Long cartItemId) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getId() == cartItemId) {
                if (cartItem.getQuantity() == 1) {
                    this.cartItems.remove(cartItem);
                }else {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                }
                return true;
            }
        }
        return false;
    }

    public double getSubtotal(){
        double price = 0;
        for (CartItem cartItem : this.cartItems) {
            price += cartItem.getBook().getPrice() * cartItem.getQuantity();
        }
        return price;
    }

    public double getShippingFee(){
        double subtotal = getSubtotal();
        if (subtotal > 0 && subtotal < 100) return 14.99;
        return 0;
    }

    public double getTax(){
        double totalPrice = getSubtotal() + getShippingFee();
        return totalPrice * 0.13;
    }

    public double getTotalPrice(){
        return getSubtotal() + getShippingFee() + getTax();
    }

}
