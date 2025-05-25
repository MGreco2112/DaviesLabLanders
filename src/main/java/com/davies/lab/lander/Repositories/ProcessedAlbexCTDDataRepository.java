package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedAlbexCTDData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcessedAlbexCTDDataRepository extends JpaRepository<ProcessedAlbexCTDData, Long> {
    @Query(value = "SELECT * FROM processedalbexctddata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedAlbexCTDData> findDataByHeadId(@Param("id") Long id);

    @Query(value = "SELECT * FROM processedalbexctddata WHERE head_id = :id AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ProcessedAlbexCTDData> findDataByHeadAndDateRange(@Param("id") Long id, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT * FROM processedalbexctddata WHERE head_id = :id LIMIT 1", nativeQuery = true)
    ProcessedAlbexCTDData findFirstDataPointInHead(@Param("id") Long id);

    @Query(value = "SELECT * FROM processedalbexctddata WHERE head_id = :id ORDER BY id DESC LIMIT 1")
    ProcessedAlbexCTDData findLastDataPointInHeader(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM processedalbexctddata WHERE head_id = :id", nativeQuery = true)
    Integer findCountByHeadID(@Param("id") Long id);
}
