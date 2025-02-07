package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedFLNTUData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedFLNTUDataRepository extends JpaRepository<ProcessedFLNTUData, Integer> {
    //TODO: Add SQL Query methods as needed
}
