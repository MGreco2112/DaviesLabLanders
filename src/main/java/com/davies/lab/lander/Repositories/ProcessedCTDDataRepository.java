package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedCTDData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedCTDDataRepository extends JpaRepository<Integer, ProcessedCTDData> {
    //TODO Add custom SQL Query routes as needed by Frontend
}
