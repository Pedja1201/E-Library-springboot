package org.radak.library.app.controller;

import org.radak.library.app.aspect.Logged;
import org.radak.library.app.dto.BookDTO;
import org.radak.library.app.dto.LibraryDTO;
import org.radak.library.app.model.Book;
import org.radak.library.app.service.BookService;
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
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<BookDTO>> getAll(Pageable pageable) {
        Page<Book> book = bookService.findAll(pageable);
        Page<BookDTO> books = book.map(new Function<Book, BookDTO>() {
            public BookDTO apply(Book book) {
                BookDTO bookDTO = new BookDTO(book.getId(), book.getName(),
                        book.getAuthor(),book.getCategory(),book.getPrice(),book.getStatus(),
                        new LibraryDTO(book.getLibrary().getId(), book.getLibrary().getName(),
                                book.getLibrary().getAddress(),book.getLibrary().getPhone_number(),book.getLibrary().getEmail(),null)
                );
                // Conversion logic

                return bookDTO;
            }
        });
        return new ResponseEntity<Page<BookDTO>>(books, HttpStatus.OK);
    }

    @RequestMapping(path = "/{bookId}", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BookDTO> get(@PathVariable("bookId") Long bookId) {
        Optional<Book> book = bookService.findOne(bookId);
        if (book.isPresent()) {
            BookDTO bookDTO = new BookDTO(book.get().getId(),book.get().getName(),
                    book.get().getAuthor(),book.get().getCategory(),book.get().getPrice(),book.get().getStatus(),
                    new LibraryDTO(book.get().getLibrary().getId(),book.get().getLibrary().getName(),
                            book.get().getLibrary().getAddress(), book.get().getLibrary().getPhone_number(),book.get().getLibrary().getEmail(),null));

            return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
        }
        return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BookDTO> create(@RequestBody Book book) {
        try {
            bookService.save(book);
            LibraryDTO libraryDTO =  new LibraryDTO(book.getLibrary().getId(), book.getLibrary().getName(), book.getLibrary().getAddress(),
                    book.getLibrary().getPhone_number(), book.getLibrary().getEmail(),  null);;

            BookDTO bookDTO = new BookDTO(book.getId(), book.getName(),book.getAuthor(),
                    book.getCategory(),book.getPrice(),book.getStatus(),libraryDTO);

            return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<BookDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{bookId}", method = RequestMethod.PUT)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BookDTO> update(@PathVariable("bookId") Long bookId,
                                                @RequestBody Book updatedBook) {
        Book book = bookService.findOne(bookId).orElse(null);
        if (book != null) {
            updatedBook.setId(bookId);
            bookService.save(updatedBook);
            LibraryDTO libraryDTO =  new LibraryDTO(updatedBook.getLibrary().getId(), updatedBook.getLibrary().getName(), updatedBook.getLibrary().getAddress(),
                    updatedBook.getLibrary().getPhone_number(), updatedBook.getLibrary().getEmail(), null);

            BookDTO bookDTO = new BookDTO(updatedBook.getId(), updatedBook.getName(),updatedBook.getAuthor(),
                    updatedBook.getCategory(),updatedBook.getPrice(), updatedBook.getStatus(), libraryDTO);

            return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
        }
        return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{bookId}", method = RequestMethod.DELETE)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BookDTO> delete(@PathVariable("bookId") Long bookId) {
        if (bookService.findOne(bookId).isPresent()) {
            bookService.delete(bookId);
            bookService.delete(bookId);
            return new ResponseEntity<BookDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
    }
}
