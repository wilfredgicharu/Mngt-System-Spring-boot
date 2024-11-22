package com.fred.schoolmanagement.repository;

import com.fred.schoolmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
