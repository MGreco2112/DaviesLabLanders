package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedFLNTUHeadRepository extends JpaRepository<ProcessedFLNTUHead, Integer> {
    //TODO: Add SQL Methods
}
