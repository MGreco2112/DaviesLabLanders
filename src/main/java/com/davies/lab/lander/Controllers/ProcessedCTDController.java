package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.Models.ProcessedCTDData;
import com.davies.lab.lander.Models.ProcessedCTDHead;
import com.davies.lab.lander.Repositories.ProcessedCTDDataRepository;
import com.davies.lab.lander.Repositories.ProcessedCTDHeadRepository;
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
    @Autowired
    private ProcessedCTDHeadRepository headRepository;

    //Head Routes
    @GetMapping("/headers")
    public List<ProcessedCTDHead> findAllHeads() { return headRepository.findAll(); }

    @GetMapping("/headers/{id}")
    public ResponseEntity<ProcessedCTDHead> findHeadById(@PathVariable Integer id) {
        return new ResponseEntity<ProcessedCTDHead>(headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<ProcessedCTDData> findAllEntries() {
        return repository.findAll();
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<ProcessedCTDData> findDataById(@PathVariable Integer id) {
        return new ResponseEntity<ProcessedCTDData>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }
}
