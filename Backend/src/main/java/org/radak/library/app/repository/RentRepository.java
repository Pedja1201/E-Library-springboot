package org.radak.library.app.repository;

import org.radak.library.app.model.Rent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends PagingAndSortingRepository<Rent, Long> {
}
