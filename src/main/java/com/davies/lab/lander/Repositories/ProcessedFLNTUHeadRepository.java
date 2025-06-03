package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessedFLNTUHeadRepository extends JpaRepository<ProcessedFLNTUHead, Long> {
    //TODO: Add SQL Methods
    @Query(value = "SELECT * FROM processedflntuhead WHERE lander_id = :id", nativeQuery = true)
    Optional<ProcessedFLNTUHead> getFLNTUHeadsByLanderID(@Param("id") String id);
}
