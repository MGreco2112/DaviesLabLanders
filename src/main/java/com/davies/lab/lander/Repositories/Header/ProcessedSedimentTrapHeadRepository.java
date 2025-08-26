package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedSedimentTrapHeadRepository extends JpaRepository<ProcessedSedimentTrapHeader, Long> {
    @Query(value = "SELECT * FROM processed_sediment_trap_header WHERE lander_id = :id", nativeQuery = true)
    Optional<ProcessedSedimentTrapHeader> getSedimentTrapHeadByLanderId(@Param("id") String landerID);
}
