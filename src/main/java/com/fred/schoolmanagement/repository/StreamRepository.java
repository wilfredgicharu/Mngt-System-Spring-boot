package com.fred.schoolmanagement.repository;

import com.fred.schoolmanagement.entity.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreamRepository extends JpaRepository<Stream, Long> {

    Optional<Stream> findByStreamCode(long streamCode);

    Optional<Stream> findByStreamName(String streamName);

}
