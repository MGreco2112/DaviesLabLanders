package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.Models.ProcessedDOData;
import com.davies.lab.lander.Models.ProcessedDOHead;
import com.davies.lab.lander.Repositories.ProcessedDODataRepository;
import com.davies.lab.lander.Repositories.ProcessedDOHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/do")
public class ProcessedDOController {
    @Autowired
    private ProcessedDODataRepository repository;
    @Autowired
    private ProcessedDOHeadRepository headRepository;

    //Head Routes
    @GetMapping("/headers")
    public List<ProcessedDOHead> findAllHeads() { return headRepository.findAll(); }

    @GetMapping("/headers/{id}")
    public ResponseEntity<ProcessedDOHead> findHeadById(@PathVariable Integer id) {
        return new ResponseEntity<ProcessedDOHead>(headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<ProcessedDOData> findAllEntries() {
        return repository.findAll();
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<ProcessedDOData> findDataById(@PathVariable Integer id) {
        return new ResponseEntity<ProcessedDOData>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }
}
