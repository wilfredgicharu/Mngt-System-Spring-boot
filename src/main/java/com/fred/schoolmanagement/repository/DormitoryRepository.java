package com.fred.schoolmanagement.repository;

import com.fred.schoolmanagement.entity.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DormitoryRepository extends JpaRepository<Dormitory, Long> {

    Optional<Dormitory> findByDormCode(long dormCode);

    Optional<Dormitory> findByDormName(String dormName);

}
