package org.radak.library.app.service;

import org.radak.library.app.model.Rent;
import org.radak.library.app.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    public Iterable<Rent> findAll() {
        return rentRepository.findAll();
    }

    public Page<Rent> findAll(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }


    public Optional<Rent> findOne(Long id) {
        return rentRepository.findById(id);
    }

    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    public void delete(Long id) {
        rentRepository.deleteById(id);
    }

    public void delete(Rent rent) {
        rentRepository.delete(rent);
    }
}
