package org.radak.library.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.security.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
