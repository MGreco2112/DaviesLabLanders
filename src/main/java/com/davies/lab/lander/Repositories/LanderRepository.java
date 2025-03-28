package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.Lander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LanderRepository extends JpaRepository<Lander, String> {
    //TODO: Add SQL Methods
    @Query(value = "SELECT * FROM lander WHERE deployment_date_and_time BETWEEN :inputDate AND recovery_date_and_time", nativeQuery = true)
    Set<Lander> selectLandersByDateRange(@Param("inputDate") String inputDate);
}
