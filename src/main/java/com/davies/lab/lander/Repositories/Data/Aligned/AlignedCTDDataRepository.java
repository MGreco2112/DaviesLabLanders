package com.davies.lab.lander.Repositories.Data.Aligned;

import com.davies.lab.lander.Models.Data.Aligned.AlignedCTDData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlignedCTDDataRepository extends JpaRepository<AlignedCTDData, Long> {
    @Query(value = "SELECT * FROM alignedctddata WHERE raw_data = :id", nativeQuery = true)
    Optional<AlignedCTDData> findAlignedDataByRawDataID(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM alignedctddata", nativeQuery = true)
    Integer getAlignedCTDCount();

}
