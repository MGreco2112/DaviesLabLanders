package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedDOData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedDODataRepository extends JpaRepository<ProcessedDOData, Long> {
    @Query(value = "SELECT * FROM processeddodata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedDOData> findDoDataByHeadId(@Param("id") Long id);

    @Query(value = "SELECT * FROM processeddodata WHERE head_id = :id AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ProcessedDOData> findDataByHeadAndDateRange(@Param("id") Long id, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
