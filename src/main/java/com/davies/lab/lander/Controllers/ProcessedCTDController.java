package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.Models.ProcessedCTDData;
import com.davies.lab.lander.Repositories.ProcessedCTDDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/ctd")
public class ProcessedCTDController {
    @Autowired
    private ProcessedCTDDataRepository repository;

    @GetMapping
    public List<ProcessedCTDData> findAllEntries() {
        return repository.findAll();
    }
}
