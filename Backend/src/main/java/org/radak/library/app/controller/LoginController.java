package org.radak.library.app.controller;

import org.radak.library.app.dto.AdminDTO;
import org.radak.library.app.dto.CustomerDTO;
import org.radak.library.app.dto.TokenDTO;
import org.radak.library.app.dto.UserDTO;
import org.radak.library.app.model.Admin;
import org.radak.library.app.model.Customer;
import org.radak.library.app.model.UserPermission;
import org.radak.library.app.service.AdminService;
import org.radak.library.app.service.CustomerService;
import org.radak.library.app.service.PermissionService;
import org.radak.library.app.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;

@Controller
@CrossOrigin(origins = "http://localhost:4200") //Napravi proxy na frontu
@RequestMapping("/api")
public class LoginController { //TODO:RAspodeliti uloge prilikom register: ROLE_ADMIN...

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AdminService adminService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO user) {
        try {
            // Kreiranje tokena za login, token sadrzi korisnicko ime i lozinku.
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword());
            // Autentifikacija korisnika na osnovu korisnickog imena i lozinke.
            Authentication authentication = authenticationManager.authenticate(token);
            // Dodavanje uspesne autentifikacije u security context.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Ucitavanje podatka o korisniku i kreiranje jwt-a.
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = tokenUtils.generateToken(userDetails);
            TokenDTO jwtDTO = new TokenDTO(jwt);

            return new ResponseEntity<TokenDTO>(jwtDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TokenDTO>(HttpStatus.UNAUTHORIZED);
        }
    }


    @RequestMapping(path = "/registerAdmin", method = RequestMethod.POST)
    public ResponseEntity<AdminDTO> registerAdmin(@RequestBody AdminDTO admin) {
        // Novi korisnik se registruje kreiranjem instance korisnika
        // cija je lozinka enkodovana.
        Admin newAdmin = new Admin(null, admin.getUsername(),
                passwordEncoder.encode(admin.getPassword()), admin.getFirstName(), admin.getLastName(),
                admin.getEmail(), admin.getUcin());
        newAdmin = adminService.save(newAdmin);
        // Dodavanje prava pristupa.
        newAdmin.setUserPermissions(new HashSet<UserPermission>());
        newAdmin.getUserPermissions()                                //Trazimo id=1 zato sto je Admin Administrator (ROLE_ADMIN)
                .add(new UserPermission(null, newAdmin, permissionService.findOne(1l).get()));
        adminService.save(newAdmin);

        return new ResponseEntity<AdminDTO>(
                new AdminDTO(newAdmin.getId(), newAdmin.getUsername(), null,
                        newAdmin.getFirstName(), newAdmin.getLastName(),
                        newAdmin.getEmail(), newAdmin.getUcin() ), HttpStatus.OK);
    }

    @RequestMapping(path = "/registerCustomer", method = RequestMethod.POST)
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody CustomerDTO customer) {
        // Novi korisnik se registruje kreiranjem instance korisnika
        // cija je lozinka enkodovana.
        Customer newCustomer = new Customer(null, customer.getUsername(),
                passwordEncoder.encode(customer.getPassword()), customer.getFirstName(), customer.getLastName(),
                customer.getDateOfBirth(), customer.getEmail(), customer.getPhoneNumber(),customer.getPlace(),
                customer.getAddress());
        newCustomer = customerService.save(newCustomer);
        // Dodavanje prava pristupa.
        newCustomer.setUserPermissions(new HashSet<UserPermission>());
        newCustomer.getUserPermissions()                                //Trazimo id=2 zato sto je customer  (ROLE_CUSTOMER)
                .add(new UserPermission(null, newCustomer, permissionService.findOne(2l).get()));
        customerService.save(newCustomer);

        return new ResponseEntity<CustomerDTO>(
                new CustomerDTO(newCustomer.getId(), newCustomer.getUsername(), null,
                        newCustomer.getFirstName(), newCustomer.getLastName(),newCustomer.getDateOfBirth(),
                        newCustomer.getEmail(),newCustomer.getPhoneNumber(),
                        newCustomer.getPlace(), newCustomer.getAddress()), HttpStatus.OK);
    }
}
