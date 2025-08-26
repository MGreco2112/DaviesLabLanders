package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedBatteryHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedBatteryHeadRepository extends JpaRepository<ProcessedBatteryHeader, Long> {
    @Query(value = "SELECT * FROM processed_battery_header WHERE lander_id = :id", nativeQuery = true)
    Optional<ProcessedBatteryHeader> getBatteryHeadByLanderId(@Param("id") String landerID);
}
