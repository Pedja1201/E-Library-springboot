package org.radak.library.app.dto;


import java.util.ArrayList;

public class LibraryDTO {
    private Long id;
    private String name;
    private String address;
    private String phone_number;
    private String email;

    private ArrayList<BookDTO> books = new ArrayList<BookDTO>();

    public LibraryDTO() {super();
    }

    public LibraryDTO(Long id, String name, String address, String phone_number, String email, ArrayList<BookDTO> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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
