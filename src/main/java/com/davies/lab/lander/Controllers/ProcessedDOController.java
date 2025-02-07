package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.Models.ProcessedDOData;
import com.davies.lab.lander.Repositories.ProcessedDODataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/do")
public class ProcessedDOController {
    @Autowired
    private ProcessedDODataRepository repository;

    @GetMapping
    public List<ProcessedDOData> findAllEntries() {
        return repository.findAll();
    }
}
