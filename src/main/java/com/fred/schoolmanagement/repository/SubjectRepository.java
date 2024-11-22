package com.fred.schoolmanagement.repository;

import com.fred.schoolmanagement.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubjectCode(long subjectCode);

    Optional<Subject> findBySubjectName(String Name);

    Subject findBySubjectId(long subjectId);

}
