package com.fred.schoolmanagement.repository;

import com.fred.schoolmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByIdNumber(long idNumber);

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByVerificationToken(String token);

}
