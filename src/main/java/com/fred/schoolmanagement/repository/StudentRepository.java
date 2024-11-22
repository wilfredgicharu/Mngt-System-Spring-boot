package com.fred.schoolmanagement.repository;

import com.fred.schoolmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentIdNumber(long idNumber);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByVerificationToken(String token);
}