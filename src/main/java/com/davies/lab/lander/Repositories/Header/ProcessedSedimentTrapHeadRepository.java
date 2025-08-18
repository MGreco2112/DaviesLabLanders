package com.davies.lab.lander.Repositories.Header;

import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedSedimentTrapHeadRepository extends JpaRepository<ProcessedSedimentTrapHeader, Long> {

}
