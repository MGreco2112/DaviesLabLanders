package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedCTDData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT * FROM processedctddata WHERE head_id = :id AND is_aligned = :value", nativeQuery = true)
    List<ProcessedCTDData> findDataByHeadAndAlingedStatus(@Param("id") Long id, @Param("value") boolean value);

    @Query(value = "SELECT * FROM processedctddata WHERE head_id = :id AND is_aligned = 1 AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ProcessedCTDData> findDataByHeadAndAlignedStatusInRange(@Param("id") Long id, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT COUNT(*) FROM processedctddata WHERE head_id = :id", nativeQuery = true)
    Integer findCountByHeadID(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM processedctddata WHERE head_id = :id AND is_aligned = true", nativeQuery = true)
    Integer findCountOfAlignedByHeadID(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM processedctddata", nativeQuery = true)
    Integer findCountOfData();
}
