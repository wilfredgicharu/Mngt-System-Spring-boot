package com.fred.schoolmanagement.repository;

import com.fred.schoolmanagement.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
