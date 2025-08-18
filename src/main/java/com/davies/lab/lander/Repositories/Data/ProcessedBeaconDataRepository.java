package com.davies.lab.lander.Repositories.Data;

import com.davies.lab.lander.Models.Data.ProcessedBeaconData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedBeaconDataRepository extends JpaRepository<ProcessedBeaconData, Long> {
    @Query(value = "SELECT * FROM processedbeacondata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedBeaconData> findDataByHeadId(@Param("id") Long id);
}
