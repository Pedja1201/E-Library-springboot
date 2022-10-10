package org.radak.library.app.controller;

import org.radak.library.app.dto.CustomerDTO;
import org.radak.library.app.model.Customer;
import org.radak.library.app.service.CustomerService;
import org.radak.library.app.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;

@Controller
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PdfService pdfService;

    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<CustomerDTO>> getAll(Pageable pageable) {
        Page<Customer> customer = customerService.findAll(pageable);
        Page<CustomerDTO> customers = customer.map(new Function<Customer, CustomerDTO>() {
            public CustomerDTO apply(Customer customer) {
                CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getUsername(),
                        customer.getPassword(), customer.getFirstName(), customer.getLastName(),  customer.getDateOfBirth(),  customer.getEmail(),
                        customer.getPhoneNumber(),customer.getPlace(), customer.getAddress());
                // Conversion logic
                return customerDTO;
            }
        });
        return new ResponseEntity<Page<CustomerDTO>>(customers, HttpStatus.OK);
    }

    @RequestMapping(path = "/{customerId}", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<CustomerDTO> get(@PathVariable("customerId") Long customerId) {
        Optional<Customer> customer = customerService.findOne(customerId);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = new CustomerDTO(customer.get().getId(),
                    customer.get().getUsername(), customer.get().getPassword(), customer.get().getFirstName(),
                    customer.get().getLastName(), customer.get().getDateOfBirth(),
                    customer.get().getEmail(), customer.get().getPhoneNumber(), customer.get().getPlace(), customer.get().getAddress());
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
        }
        return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<CustomerDTO> create(@RequestBody Customer customer) {
        try {
            customerService.save(customer);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(),
                    customer.getUsername(), customer.getPassword(), customer.getFirstName(),
                    customer.getLastName(), customer.getDateOfBirth(), customer.getEmail(), customer.getPhoneNumber(),
                    customer.getPlace(),customer.getAddress());
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<CustomerDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{customerId}", method = RequestMethod.PUT)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<CustomerDTO> update(@PathVariable("customerId") Long customerId,
                                           @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.findOne(customerId).orElse(null);
        if (customer != null) {
            updatedCustomer.setId(customerId);
            customerService.save(updatedCustomer);  //DONE:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            CustomerDTO customerDTO = new CustomerDTO(updatedCustomer.getId(),
                    updatedCustomer.getUsername(), updatedCustomer.getPassword(),updatedCustomer.getFirstName(),
                    updatedCustomer.getLastName(), updatedCustomer.getDateOfBirth(), updatedCustomer.getEmail(),
                    updatedCustomer.getPhoneNumber(),updatedCustomer.getPlace(),updatedCustomer.getAddress());
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
        }
        return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{customerId}", method = RequestMethod.DELETE)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Customer> delete(@PathVariable("customerId") Long customerId) {
        if (customerService.findOne(customerId).isPresent()) {
            customerService.delete(customerId);
            return new ResponseEntity<Customer>(HttpStatus.OK);
        }
        return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }

    //Metoda za preuzimanje PDF dokumenta - Potrebno (PdfService, pom.xml, resources)
    @RequestMapping(path = "/export", method = RequestMethod.GET)
    public void downloadPdf(HttpServletResponse response){
        try{
            Path file = Paths.get(pdfService.generateCustomersPdf().getAbsolutePath());
            if (Files.exists(file)){
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename"+ file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
