package org.radak.library.app.controller;

import org.radak.library.app.aspect.Logged;
import org.radak.library.app.aspect.LoggedAdministrator;
import org.radak.library.app.dto.BookDTO;
import org.radak.library.app.dto.CustomerDTO;
import org.radak.library.app.dto.RentDTO;
import org.radak.library.app.model.Rent;
import org.radak.library.app.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Function;

@Controller
@RequestMapping(path = "/api/rents")
public class RentController {
    @Autowired
    private RentService rentService;

    @LoggedAdministrator
    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<RentDTO>> getAll(Pageable pageable) {
        Page<Rent> rent = rentService.findAll(pageable);
        Page<RentDTO> rents = rent.map(new Function<Rent, RentDTO>() {
            public RentDTO apply(Rent rent) {
                RentDTO rentDTO = new RentDTO(rent.getId(), rent.getQuantity(),
                        rent.getPayment(),rent.getCurrency(),rent.getRentalOrder(),rent.getDateOrder(),
                        new CustomerDTO(rent.getCustomer().getId(), rent.getCustomer().getUsername(),null,
                                rent.getCustomer().getFirstName(),rent.getCustomer().getLastName(),
                                rent.getCustomer().getDateOfBirth(),rent.getCustomer().getEmail(),
                                rent.getCustomer().getPhoneNumber(),rent.getCustomer().getPlace(),rent.getCustomer().getAddress()),
                        new BookDTO(rent.getBook().getId(), rent.getBook().getName(),
                                rent.getBook().getAuthor(),rent.getBook().getCategory(),rent.getBook().getPrice(),
                                rent.getBook().getStatus(),null));
                // Conversion logic

                return rentDTO;
            }
        });
        return new ResponseEntity<Page<RentDTO>>(rents, HttpStatus.OK);
    }

    @RequestMapping(path = "/{rentId}", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<RentDTO> get(@PathVariable("rentId") Long rentId) {
        Optional<Rent> rent = rentService.findOne(rentId);
        if (rent.isPresent()) {
            RentDTO rentDTO = new RentDTO(rent.get().getId(),rent.get().getQuantity(),rent.get().getPayment(),
                    rent.get().getCurrency(),rent.get().getRentalOrder(),rent.get().getDateOrder(),
                    new CustomerDTO(rent.get().getCustomer().getId(),rent.get().getCustomer().getUsername(),null,
                            rent.get().getCustomer().getFirstName(), rent.get().getCustomer().getLastName(),
                            rent.get().getCustomer().getDateOfBirth(),rent.get().getCustomer().getEmail(),
                            rent.get().getCustomer().getPhoneNumber(),rent.get().getCustomer().getPlace(),
                            rent.get().getCustomer().getAddress()),
                    new BookDTO(rent.get().getBook().getId(), rent.get().getBook().getName(),
                            rent.get().getBook().getAuthor(),rent.get().getBook().getCategory(),
                            rent.get().getBook().getPrice(), rent.get().getBook().getStatus(),null));
            return new ResponseEntity<RentDTO>(rentDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RentDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<RentDTO> create(@RequestBody Rent rent) {
        try {
            rentService.save(rent);
            CustomerDTO customerDTO =  new CustomerDTO(rent.getCustomer().getId(), rent.getCustomer().getUsername(),null,
                    rent.getCustomer().getFirstName(), rent.getCustomer().getLastName(),  rent.getCustomer().getDateOfBirth(),
                    rent.getCustomer().getEmail(),rent.getCustomer().getPhoneNumber(),rent.getCustomer().getPlace(),
                    rent.getCustomer().getAddress());
            BookDTO bookDTO =  new BookDTO(rent.getBook().getId(), rent.getBook().getName(),
                    rent.getBook().getAuthor(),rent.getBook().getCategory(),rent.getBook().getPrice(),
                    rent.getBook().getStatus(),null);

            RentDTO rentDTO = new RentDTO(rent.getId(), rent.getQuantity(),rent.getPayment(),
                    rent.getCurrency(),rent.getRentalOrder(),rent.getDateOrder(),customerDTO, bookDTO);

            return new ResponseEntity<RentDTO>(rentDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<RentDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{rentId}", method = RequestMethod.PUT)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<RentDTO> update(@PathVariable("rentId") Long rentId,
                                                @RequestBody Rent updatedRent) {
        Rent rent = rentService.findOne(rentId).orElse(null);
        if (rent != null) {
            updatedRent.setId(rentId);
            rentService.save(updatedRent);
            CustomerDTO customerDTO =  new CustomerDTO(updatedRent.getCustomer().getId(), updatedRent.getCustomer().getUsername(),null,
                    updatedRent.getCustomer().getFirstName(), updatedRent.getCustomer().getLastName(), updatedRent.getCustomer().getDateOfBirth(),
                    updatedRent.getCustomer().getEmail(), updatedRent.getCustomer().getPhoneNumber(), updatedRent.getCustomer().getPlace(),
                    updatedRent.getCustomer().getAddress());
            BookDTO bookDTO =  new BookDTO(updatedRent.getBook().getId(), updatedRent.getBook().getName(),
                    updatedRent.getBook().getAuthor(),updatedRent.getBook().getCategory(),updatedRent.getBook().getPrice(),
                    updatedRent.getBook().getStatus(),null);

            RentDTO rentDTO = new RentDTO(updatedRent.getId(), updatedRent.getQuantity(),updatedRent.getPayment(),
                    updatedRent.getCurrency(),updatedRent.getRentalOrder(), updatedRent.getDateOrder(),customerDTO, bookDTO);

            return new ResponseEntity<RentDTO>(rentDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RentDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{rentId}", method = RequestMethod.DELETE)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<RentDTO> delete(@PathVariable("rentId") Long rentId) {
        if (rentService.findOne(rentId).isPresent()) {
            rentService.delete(rentId);
            return new ResponseEntity<RentDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<RentDTO>(HttpStatus.NOT_FOUND);
    }
}
