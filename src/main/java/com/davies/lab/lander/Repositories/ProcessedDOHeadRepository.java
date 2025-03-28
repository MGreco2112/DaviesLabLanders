package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedDOHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedDOHeadRepository extends JpaRepository<ProcessedDOHead, Integer> {
    //TODO: Add SQL Methods

}
