package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedCTDHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedCTDHeadRepository extends JpaRepository<ProcessedCTDHead, Integer> {
    //TODO: Add SQL Queries
}
