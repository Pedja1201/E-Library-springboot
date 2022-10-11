package org.radak.library.app.controller;

import org.radak.library.app.aspect.LoggedAdministrator;
import org.radak.library.app.dto.BookDTO;
import org.radak.library.app.dto.LibraryDTO;
import org.radak.library.app.model.Book;
import org.radak.library.app.model.Library;
import org.radak.library.app.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/libraries")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @CrossOrigin
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<LibraryDTO>> getAll(Pageable pageable) {
        Page<Library> libraries = libraryService.findAll(pageable);
        return new ResponseEntity<Page<LibraryDTO>>(
                libraries.map(library -> new LibraryDTO(library.getId(), library.getName(),library.getAddress(),
                        library.getPhoneNumber(),library.getEmail(),
                        (ArrayList<BookDTO>) library.getBooks().stream()
                                .map(book -> new BookDTO(book.getId(), book.getName(),
                                        book.getAuthor(),book.getCategory(),book.getPrice(),
                                        book.getStatus(),null))
                                .collect(Collectors.toList()))),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/{libraryId}", method = RequestMethod.GET)
    public ResponseEntity<LibraryDTO> get(@PathVariable("libraryId") Long libraryId) {
        Optional<Library> library = libraryService.findOne(libraryId);
        LibraryDTO libraryDTO;
        if (library.isPresent()) {
            ArrayList<BookDTO> books = new ArrayList<BookDTO>();
            for (Book book : library.get().getBooks()) {
                books.add(new BookDTO(book.getId(), book.getName(),book.getAuthor(),
                        book.getCategory(), book.getPrice(),book.getStatus(),null));
            }
            libraryDTO = new LibraryDTO(library.get().getId(), library.get().getName(),
                    library.get().getAddress(), library.get().getPhoneNumber(),
                    library.get().getEmail(), books);

            return new ResponseEntity<LibraryDTO>(libraryDTO, HttpStatus.OK);
        }
        return new ResponseEntity<LibraryDTO>(HttpStatus.NOT_FOUND);
    }

//    @LoggedAdministrator
    @RequestMapping(path = "", method = RequestMethod.POST)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<LibraryDTO> create(@RequestBody Library library) {
        try {
            libraryService.save(library);
            ArrayList<BookDTO> books = new ArrayList<BookDTO>();
            for(Book book : library.getBooks()) {
                books.add(new BookDTO(book.getId(),book.getName(),book.getAuthor(),
                        book.getCategory(),book.getPrice(),book.getStatus(),null));
            }
            LibraryDTO libraryDTO = new LibraryDTO(library.getId(), library.getName(),
                    library.getAddress(),library.getPhoneNumber(),library.getEmail(),books);
            return new ResponseEntity<LibraryDTO>(libraryDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<LibraryDTO>(HttpStatus.BAD_REQUEST);
    }

//    @LoggedAdministrator
    @RequestMapping(path = "/{libraryId}", method = RequestMethod.PUT)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<LibraryDTO> update(@PathVariable("libraryId") Long libraryId,
                                            @RequestBody Library updatedLibrary) {
        Library library = libraryService.findOne(libraryId).orElse(null);
        if (library != null) {
            updatedLibrary.setId(libraryId);
            updatedLibrary = libraryService.save(updatedLibrary);
            ArrayList<BookDTO> books = new ArrayList<BookDTO>();
            for(Book book : updatedLibrary.getBooks()) {
                books.add(new BookDTO(book.getId(),book.getName(),book.getAuthor(),
                        book.getCategory(),book.getPrice(),book.getStatus(),null));
            }
            LibraryDTO libraryDTO = new LibraryDTO(updatedLibrary.getId(), updatedLibrary.getName(),
                    updatedLibrary.getAddress(),updatedLibrary.getPhoneNumber(),
                        updatedLibrary.getEmail(), books);
            return new ResponseEntity<LibraryDTO>(libraryDTO, HttpStatus.OK);
        }
        return new ResponseEntity<LibraryDTO>(HttpStatus.NOT_FOUND);
    }

//    @LoggedAdministrator
    @RequestMapping(path = "/{libraryId}", method = RequestMethod.DELETE)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<LibraryDTO> delete(@PathVariable("libraryId") Long libraryId) {
        if (libraryService.findOne(libraryId).isPresent()) {
            libraryService.delete(libraryId);
            return new ResponseEntity<LibraryDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<LibraryDTO>(HttpStatus.NOT_FOUND);
    }
}
