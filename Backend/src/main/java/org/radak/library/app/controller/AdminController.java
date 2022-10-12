package org.radak.library.app.controller;

import org.radak.library.app.dto.AdminDTO;
import org.radak.library.app.model.Admin;
import org.radak.library.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Function;

@Controller
@RequestMapping(path = "api/admini")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<AdminDTO>> getAll(Pageable pageable) {
        Page<Admin> administrator = adminService.findAll(pageable);
        Page<AdminDTO> administratori = administrator.map(new Function<Admin, AdminDTO>() {
            public AdminDTO apply(Admin administrator) {
                AdminDTO administratorDTO = new AdminDTO(administrator.getId(),administrator.getUsername(), administrator.getPassword(),
                        administrator.getFirstName(), administrator.getLastName(), administrator.getEmail(), administrator.getUcin());
                // Conversion logic
                return administratorDTO;
            }
        });
        return new ResponseEntity<Page<AdminDTO>>(administratori, HttpStatus.OK);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdminDTO> get(@PathVariable("administratorId") Long administratorId) {
        Optional<Admin> administrator = adminService.findOne(administratorId);
        if (administrator.isPresent()) {
            AdminDTO administratorDTO = new AdminDTO(administrator.get().getId(),administrator.get().getUsername(),administrator.get().getPassword(),
                    administrator.get().getFirstName(),administrator.get().getLastName(),administrator.get().getEmail(),administrator.get().getUcin());
            return new ResponseEntity<AdminDTO>(administratorDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdminDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdminDTO> create(@RequestBody Admin administrator) {
        try {
            adminService.save(administrator);
            AdminDTO administratorDTO = new AdminDTO(administrator.getId(),administrator.getUsername(),administrator.getPassword(),
                    administrator.getFirstName(), administrator.getLastName(), administrator.getEmail(), administrator.getUcin());
            return new ResponseEntity<AdminDTO>(administratorDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<AdminDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.PUT)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdminDTO> update(@PathVariable("administratorId") Long administratorId,
                                                   @RequestBody Admin updatedAdministrator) {
        Admin administrator = adminService.findOne(administratorId).orElse(null);
        if (administrator != null) {
            updatedAdministrator.setId(administratorId);
            adminService.save(updatedAdministrator);
            AdminDTO administratorDTO = new AdminDTO(updatedAdministrator.getId(),updatedAdministrator.getUsername(),
                    updatedAdministrator.getPassword(), updatedAdministrator.getFirstName(), updatedAdministrator.getLastName(),
                    updatedAdministrator.getEmail(), updatedAdministrator.getUcin());
            return new ResponseEntity<AdminDTO>(administratorDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdminDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.DELETE)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Admin> delete(@PathVariable("administratorId") Long administratorId) {
        if (adminService.findOne(administratorId).isPresent()) {
            adminService.delete(administratorId);
            return new ResponseEntity<Admin>(HttpStatus.OK);
        }
        return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
    }
}
