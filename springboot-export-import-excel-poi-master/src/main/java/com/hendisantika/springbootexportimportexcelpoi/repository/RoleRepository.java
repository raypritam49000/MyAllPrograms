package com.hendisantika.springbootexportimportexcelpoi.repository;

import com.hendisantika.springbootexportimportexcelpoi.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
