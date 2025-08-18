package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedBatteryHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedBatteryHeadRepository extends JpaRepository<ProcessedBatteryHeader, Long> {

}
