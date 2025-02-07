package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedDOData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedDODataRepository extends JpaRepository<ProcessedDOData, Integer> {
    //TODO: Add custom SQL Query routes as needed
}
