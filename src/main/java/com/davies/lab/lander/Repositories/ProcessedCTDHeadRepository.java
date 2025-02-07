package com.davies.lab.lander.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedCTDHeadRepository extends JpaRepository<ProcessedCTDHeadRepository, Integer> {
    //TODO: Add SQL Queries
}
