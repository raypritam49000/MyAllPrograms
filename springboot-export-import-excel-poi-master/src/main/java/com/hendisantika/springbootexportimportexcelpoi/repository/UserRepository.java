package com.hendisantika.springbootexportimportexcelpoi.repository;

import com.hendisantika.springbootexportimportexcelpoi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
