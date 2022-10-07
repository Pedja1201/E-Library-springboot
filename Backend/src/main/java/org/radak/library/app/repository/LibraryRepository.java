package org.radak.library.app.repository;

import org.radak.library.app.model.Library;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends PagingAndSortingRepository<Library, Long> {
}
