package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedCTDData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedCTDDataRepository extends JpaRepository<ProcessedCTDData, Long> {
    //TODO Add custom SQL Query routes as needed by Frontend
    @Query(value = "SELECT * FROM processedctddata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedCTDData> findDataByHeadId(@Param("id") Long id);

    @Query(value = "SELECT * FROM processedctddata WHERE head_id = :id AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ProcessedCTDData> findDataByHeadAndDateRange(@Param("id") Long id, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT * FROM processedctddata WHERE head_id = :id LIMIT 1", nativeQuery = true)
    ProcessedCTDData findFirstDataPointInHead(@Param("id") Long id);

    @Query(value = "SELECT * FROM processedctddata WHERE head_id = :id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ProcessedCTDData findLastDataPointInHead(@Param("id") Long id);
}
