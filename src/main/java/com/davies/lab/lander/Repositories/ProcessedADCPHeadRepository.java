package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedADCPHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedADCPHeadRepository extends JpaRepository<ProcessedADCPHead, Long> {
    @Query(value = "SELECT * FROM processedadcphead WHERE lander_id = :id", nativeQuery = true)
    ProcessedADCPHead getADCPHeadByLanderId(@Param("id") String id);
}
