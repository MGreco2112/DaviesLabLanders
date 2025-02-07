package com.davies.lab.lander.Repositories;

import com.davies.lab.lander.Models.Lander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanderRepository extends JpaRepository<Lander, String> {
    //TODO: Add SQL Methods
}
