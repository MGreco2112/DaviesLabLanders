package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.Models.ProcessedFLNTUData;
import com.davies.lab.lander.Repositories.ProcessedFLNTUDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/flntu")
public class ProcessedFLNTUController {
    @Autowired
    private ProcessedFLNTUDataRepository repository;

    @GetMapping
    public List<ProcessedFLNTUData> findAllEntries() {
        return repository.findAll();
    }
}
