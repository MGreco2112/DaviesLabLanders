package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedDOHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedDOHeadRepository extends JpaRepository<ProcessedDOHead, Integer> {
    //TODO: Add SQL Methods
}
