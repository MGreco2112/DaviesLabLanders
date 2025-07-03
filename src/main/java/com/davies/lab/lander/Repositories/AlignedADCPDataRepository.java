package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.AlignedADCPData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface AlignedADCPDataRepository extends JpaRepository<AlignedADCPData, Long> {
    @Query(value = "SELECT * FROM alignedadcpdata WHERE raw_data = :id", nativeQuery = true)
    Optional<AlignedADCPData> findAlignedDataByRawDataID(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM alignedadcpdata", nativeQuery = true)
    Integer getAlignedCount();
}
