package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.Models.ProcessedCTDData;
import com.davies.lab.lander.Repositories.ProcessedCTDDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProcessedCTDData> findById(@PathVariable Integer id) {
        return new ResponseEntity(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }
}
