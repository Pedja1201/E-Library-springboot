package org.radak.library.app.repository;

import org.radak.library.app.model.UserPermission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends CrudRepository<UserPermission,Long> {
}
