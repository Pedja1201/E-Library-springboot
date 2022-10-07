package org.radak.library.app.service;

import org.radak.library.app.model.Library;
import org.radak.library.app.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    public Iterable<Library> findAll() {
        return libraryRepository.findAll();
    }

    public Page<Library> findAll(Pageable pageable) {
        return libraryRepository.findAll(pageable);
    }


    public Optional<Library> findOne(Long id) {
        return libraryRepository.findById(id);
    }

    public Library save(Library library) {
        return libraryRepository.save(library);
    }

    public void delete(Long id) {
        libraryRepository.deleteById(id);
    }

    public void delete(Library library) {
        libraryRepository.delete(library);
    }
}
