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
                cartItem.setQuantity(cartItem.getQuantity() + newCartItem.getQuantity());
                return;
            }
        }
        this.cartItems.add(newCartItem);
    }

    public void removeCartItem(int bookId) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getBook().getId() == bookId) {
                this.cartItems.remove(cartItem);
                break;
            }
        }
    }

}
