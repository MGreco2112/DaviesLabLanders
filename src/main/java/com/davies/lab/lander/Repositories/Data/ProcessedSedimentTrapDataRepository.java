package com.davies.lab.lander.Repositories.Data;

import com.davies.lab.lander.Models.Data.ProcessedSedimentTrapData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedSedimentTrapDataRepository extends JpaRepository<ProcessedSedimentTrapData, Long> {

}
