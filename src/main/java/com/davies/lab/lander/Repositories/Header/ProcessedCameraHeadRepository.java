package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedCameraHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedCameraHeadRepository extends JpaRepository<ProcessedCameraHeader, Long> {
    @Query(value = "SELECT * FROM processedcamerahead WHERE lander_id = :id", nativeQuery = true)
    Optional<ProcessedCameraHeader> getCameraHeadByLanderId(@Param("id") String landerID);
}
