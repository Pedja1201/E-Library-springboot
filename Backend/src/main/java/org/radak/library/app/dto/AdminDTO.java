package org.radak.library.app.dto;

import javax.persistence.Column;

public class AdminDTO extends UserDTO{
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true, unique = true)
    private String ucin; //	Unique Citizens Identity Number

    public AdminDTO() {super();
    }

    public AdminDTO(Long id, String username, String password, String firstName, String lastName, String email, String ucin) {
        super(id, username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ucin = ucin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUcin() {
        return ucin;
    }

    public void setUcin(String ucin) {
        this.ucin = ucin;
    }
}
