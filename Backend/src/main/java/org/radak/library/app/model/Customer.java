package org.radak.library.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends User{
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true)
    private String phoneNumber;
    @Column(nullable = true)
    private String place;
    @Column(nullable = true)
    private String address;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<Order>();
    @OneToMany(mappedBy = "customer")
    private Set<Rent> rents = new HashSet<Rent>();

    public Customer() {
        super();
    }

    public Customer(Long id, String username, String password, String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber, String place, String address) {
        super(id, username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.place = place;
        this.address = address;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Rent> getRents() {
        return rents;
    }

    public void setRents(Set<Rent> rents) {
        this.rents = rents;
    }
}
