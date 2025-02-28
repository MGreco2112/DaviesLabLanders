package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedFLNTUData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedFLNTUDataRepository extends JpaRepository<ProcessedFLNTUData, Integer> {
    @Query(value = "SELECT * FROM processedflntudata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedFLNTUData> findDataFromHeadId(@Param("id") Integer id);
}
