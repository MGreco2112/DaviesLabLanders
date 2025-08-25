package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedBeaconHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedBeaconHeadRepository extends JpaRepository<ProcessedBeaconHeader, Long> {
    @Query(value = "SELECT * FROM processedbeaconhead WHERE lander_id = :id", nativeQuery = true)
    Optional<ProcessedBeaconHeader> getBeaconHeadByLanderId(@Param("id") String landerID);
}
