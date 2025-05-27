package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedADCPData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedADCPDataRepository extends JpaRepository<ProcessedADCPData, Long> {
    @Query(value = "SELECT * FROM processedadcpdata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedADCPData> findDataByHeadId(@Param("id") Long id);

    @Query(value = "SELECT * FROM processedadcpdata WHERE head_id = :id AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ProcessedADCPData> findDataByHeadAndDateRange(@Param("id") Long id, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT COUNT(*) FROM processedadcpdata WHERE head_id = :id", nativeQuery = true)
    Integer findCountByHeadID(@Param("id") Long id);
}
