package com.example.amazinbookstore.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Book> books;

    private String firstName;

    private String lastName;

    private String address;

    private double subtotal;

    private double shippingFee;

    private double tax;

    private double total;

    public Purchase() {
        this.books = new ArrayList<>();
    }

    public Purchase(Cart cart, String firstName, String lastName, String address) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setSubtotal(cart.getSubtotal());
        this.setShippingFee(cart.getShippingFee());
        this.setTax(cart.getTax());
        this.setTotal(cart.getTotalPrice());
        this.books = new ArrayList<>();
        for (CartItem cartItem: cart.getCartItems()){
            this.addBook(cartItem.getBook());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book newBook) {
        for (Book book : this.books) {
            if (book.getId() == newBook.getId()) {
                return;
            }
        }
        this.books.add(newBook);
    }

    public List<Long> getBookIds() {
        List<Long> bookIds = new ArrayList<>();
        for (Book book : this.books) {
            bookIds.add(book.getId());
        }
        return bookIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}