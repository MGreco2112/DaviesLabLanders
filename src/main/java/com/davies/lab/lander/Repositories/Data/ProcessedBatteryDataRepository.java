package com.davies.lab.lander.Repositories.Data;

import com.davies.lab.lander.Models.Data.ProcessedBatteryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedBatteryDataRepository extends JpaRepository<ProcessedBatteryData, Long> {
    @Query(value = "SELECT * FROM processed_battery_data WHERE head_id = :id", nativeQuery = true)
    List<ProcessedBatteryData> findDataByHeadId(@Param("id") Long headId);

    @Query(value = "SELECT COUNT(*) FROM processed_battery_data", nativeQuery = true)
    Integer findCountOfData();
}
