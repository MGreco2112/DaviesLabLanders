package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedAlbexCTDHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedAlbexCTDHeaderRepository extends JpaRepository<ProcessedAlbexCTDHeader, Long> {
    @Query(value = "SELECT * FROM processed_albexctdheader WHERE lander_id = :id", nativeQuery = true)
    Optional<ProcessedAlbexCTDHeader> getAlbexHeadsByLanderId(@Param("id") String id);
}
