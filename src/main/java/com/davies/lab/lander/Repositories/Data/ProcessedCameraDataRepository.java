package com.davies.lab.lander.Repositories.Data;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedCameraDataRepository extends JpaRepository<ProcessedCameraData, Long> {
    @Query(value = "SELECT * FROM processedcameradata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedCameraData> findDataByHeadId(@Param("id") Long id);
}
