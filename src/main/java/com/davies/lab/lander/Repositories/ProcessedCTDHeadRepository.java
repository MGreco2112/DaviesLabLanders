package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.ProcessedCTDHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedCTDHeadRepository extends JpaRepository<ProcessedCTDHead, Integer> {

}
