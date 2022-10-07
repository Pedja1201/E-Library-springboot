package org.radak.library.app.dto;


import java.util.Date;

public class OrderDTO {
    private Long id;
    private double quantity;
    private String payment;
    private String currency;
    private Date dateOrder;
    private CustomerDTO customer;
    private BookDTO book;

    public OrderDTO() {super();
    }

    public OrderDTO(Long id, double quantity, String payment, String currency, Date dateOrder, CustomerDTO customer, BookDTO book) {
        this.id = id;
        this.quantity = quantity;
        this.payment = payment;
        this.currency = currency;
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

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
