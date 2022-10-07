package org.radak.library.app.dto;

import java.util.ArrayList;

public class BookDTO {
    private Long id;
    private String name;
    private String author;
    private String category;
    private double price;
    private String status;
    private LibraryDTO library;

    private ArrayList<OrderDTO> orders = new ArrayList<OrderDTO>();
    private ArrayList<RentDTO> rents = new ArrayList<RentDTO>();

    public BookDTO() {super();
    }

    public BookDTO(Long id, String name, String author, String category, double price, String status, LibraryDTO library) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
        this.status = status;
        this.library = library;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LibraryDTO getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDTO library) {
        this.library = library;
    }

    public ArrayList<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderDTO> orders) {
        this.orders = orders;
    }

    public ArrayList<RentDTO> getRents() {
        return rents;
    }

    public void setRents(ArrayList<RentDTO> rents) {
        this.rents = rents;
    }
}
