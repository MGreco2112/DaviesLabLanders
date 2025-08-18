package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedADCPHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedADCPHeadRepository extends JpaRepository<ProcessedADCPHead, Long> {
    @Query(value = "SELECT * FROM processedadcphead WHERE lander_id = :id", nativeQuery = true)
    Optional<ProcessedADCPHead> getADCPHeadByLanderId(@Param("id") String id);
}
