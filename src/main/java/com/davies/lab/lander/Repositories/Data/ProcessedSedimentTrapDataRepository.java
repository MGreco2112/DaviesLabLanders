package com.davies.lab.lander.Repositories.Data;

import com.davies.lab.lander.Models.Data.ProcessedSedimentTrapData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedSedimentTrapDataRepository extends JpaRepository<ProcessedSedimentTrapData, Long> {
    @Query(value = "SELECT * FROM processedsedimenttrapdata WHERE head_id = :id", nativeQuery = true)
    List<ProcessedSedimentTrapData> findDataByHeadId(@Param("id") Long headId);
}
