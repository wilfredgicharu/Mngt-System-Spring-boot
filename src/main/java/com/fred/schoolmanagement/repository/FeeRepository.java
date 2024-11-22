package com.fred.schoolmanagement.repository;

import com.fred.schoolmanagement.entity.FeesPerTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<FeesPerTerm, Long> {
}
