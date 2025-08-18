package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedDOHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedDOHeadRepository extends JpaRepository<ProcessedDOHead, Long> {
    @Query(value = "SELECT * FROM processeddohead WHERE lander_id = :id", nativeQuery = true)
    Optional<ProcessedDOHead> getDOHeadsByLanderID(@Param("id") String id);
}
