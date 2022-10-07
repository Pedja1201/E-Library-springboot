package org.radak.library.app.dto;


import java.util.ArrayList;
import java.util.Date;

public class CustomerDTO extends UserDTO{
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String place;
    private String address;

    private ArrayList<OrderDTO> orders = new ArrayList<OrderDTO>();
    private ArrayList<RentDTO> rents = new ArrayList<RentDTO>();

    public CustomerDTO() {super();
    }

    //Register consturcutor
    public CustomerDTO(Long id, String username, String password, String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber, String place, String address) {
        super(id, username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.place = place;
        this.address = address;
    }

    public CustomerDTO(Long id, String username, String password, String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber,
                       String place, String address, ArrayList<OrderDTO> orders, ArrayList<RentDTO> rents) {
        super(id, username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.place = place;
        this.address = address;
        this.orders = orders;
        this.rents = rents;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
