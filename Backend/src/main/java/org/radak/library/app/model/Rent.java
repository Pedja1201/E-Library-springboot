package org.radak.library.app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double quantity;
    @Column(nullable = false)
    private String payment;
    @Column(nullable = false)
    private String currency;
    @Temporal(TemporalType.DATE)
    private Date rentalOrder;
    @Temporal(TemporalType.DATE)
    private Date dateOrder;

    @ManyToOne()
    private Customer customer;
    @ManyToOne()
    private Book book;

    public Rent() {super();
    }

    public Rent(Long id, double quantity, String payment, String currency, Date rentalOrder, Date dateOrder, Customer customer, Book book) {
        this.id = id;
        this.quantity = quantity;
        this.payment = payment;
        this.currency = currency;
        this.rentalOrder = rentalOrder;
        this.dateOrder = dateOrder;
        this.customer = customer;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getRentalOrder() {
        return rentalOrder;
    }

    public void setRentalOrder(Date rentalOrder) {
        this.rentalOrder = rentalOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
