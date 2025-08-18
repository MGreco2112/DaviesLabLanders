package com.davies.lab.lander.Repositories.Data;

import com.davies.lab.lander.Models.Data.ProcessedAlbexCTDData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProcessedAlbexCTDDataRepository extends JpaRepository<ProcessedAlbexCTDData, Long> {
    @Query(value = "SELECT * FROM processed_albexctddata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedAlbexCTDData> findDataByHeadId(@Param("id") Long id);

    @Query(value = "SELECT * FROM processed_albexctddata WHERE head_id = :id AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ProcessedAlbexCTDData> findDataByHeadAndDateRange(@Param("id") Long id, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT Date FROM processed_albexctddata WHERE head_id = :id LIMIT 1", nativeQuery = true)
    LocalDateTime findDeploymentDateByHeadID(@Param("id") Long id);

    @Query(value = "SELECT Date FROM processed_albexctddata WHERE head_id = :id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    LocalDateTime findRecoveryDateByHeadID(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM processed_albexctddata WHERE head_id = :id", nativeQuery = true)
    Integer findCountByHeadID(@Param("id") Long id);

    @Query(value = "SELECT * FROM processed_albexctddata WHERE head_id = :id AND is_aligned = :value", nativeQuery = true)
    List<ProcessedAlbexCTDData> findDataByHeadAndAlignedStatus(@Param("id") Long id, @Param("value") boolean value);

    @Query(value = "SELECT COUNT(*) FROM processed_albexctddata", nativeQuery = true)
    Integer getCountOfData();

    @Query(value = "SELECT COUNT(*) FROM processed_albexctddata WHERE date BETWEEN :date AND :endDate", nativeQuery = true)
    Integer getDateCount(@Param("date") LocalDate date, @Param("endDate") LocalDate endDate);
}
