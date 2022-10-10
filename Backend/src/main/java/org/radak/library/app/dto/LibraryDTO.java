package org.radak.library.app.dto;


import java.util.ArrayList;

public class LibraryDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    private ArrayList<BookDTO> books = new ArrayList<BookDTO>();

    public LibraryDTO() {super();
    }

    public LibraryDTO(Long id, String name, String address, String phoneNumber, String email, ArrayList<BookDTO> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.books = books;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookDTO> books) {
        this.books = books;
    }
}
