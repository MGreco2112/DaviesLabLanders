package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedAlbexCTDHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedAlbexCTDHeaderRepository extends JpaRepository<ProcessedAlbexCTDHeader, Long> {

}
