package org.radak.library.app.repository;

import org.radak.library.app.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    ///Metoda koja dobovalja Korisnika iz baze podataka.
    Optional<User> findByUsername(String username);
}

