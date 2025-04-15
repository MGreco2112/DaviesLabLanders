package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedFLNTUData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedFLNTUDataRepository extends JpaRepository<ProcessedFLNTUData, Long> {
    @Query(value = "SELECT * FROM processedflntudata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedFLNTUData> findDataFromHeadId(@Param("id") Long id);

    @Query(value = "SELECT * FROM processedflntudata WHERE head_id = :id AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ProcessedFLNTUData> findDataByHeadAndDateRange(@Param("id") Long id, @Param("startDate") String startDate, @Param("endDate") String endDate);
}