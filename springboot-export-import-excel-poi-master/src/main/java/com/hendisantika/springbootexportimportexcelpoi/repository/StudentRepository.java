package com.hendisantika.springbootexportimportexcelpoi.repository;

import com.hendisantika.springbootexportimportexcelpoi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
