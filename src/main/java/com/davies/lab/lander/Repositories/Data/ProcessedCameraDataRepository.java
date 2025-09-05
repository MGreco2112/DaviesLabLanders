package com.davies.lab.lander.Repositories.Data;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProcessedCameraDataRepository extends JpaRepository<ProcessedCameraData, Long> {
    @Query(value = "SELECT * FROM processed_camera_data WHERE head_id = :id", nativeQuery = true)
    List<ProcessedCameraData> findDataByHeadId(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM processed_camera_data", nativeQuery = true)
    Integer findCountOfData();

    @Query(value = "SELECT COUNT(*) FROM processed_camera_data WHERE head_id = :id", nativeQuery = true)
    Integer findCountOfDataByHeadID(@Param("id") Long id);

    @Query(value = "SELECT * FROM processed_camera_data WHERE head_id = :id AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ProcessedCameraData> findDataByHeadAndDateRange(Long id, LocalDateTime startDate, LocalDateTime endDate);
}
