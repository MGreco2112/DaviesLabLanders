package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.Models.ProcessedFLNTUData;
import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import com.davies.lab.lander.Repositories.ProcessedFLNTUDataRepository;
import com.davies.lab.lander.Repositories.ProcessedFLNTUHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/flntu")
public class ProcessedFLNTUController {
    @Autowired
    private ProcessedFLNTUDataRepository repository;
    @Autowired
    private ProcessedFLNTUHeadRepository headRepository;

    //Header Routes
    @GetMapping("/headers")
    public List<ProcessedFLNTUHead> findAllHeads() { return headRepository.findAll(); }

    @GetMapping("/headers/{id}")
    public ResponseEntity<ProcessedFLNTUHead> findHeadByID(@PathVariable Integer id) {
        return new ResponseEntity<ProcessedFLNTUHead>(headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<ProcessedFLNTUData> findAllEntries() {
        return repository.findAll();
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<ProcessedFLNTUData> findDataById(@PathVariable Integer id) {
        return new ResponseEntity<ProcessedFLNTUData>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }


}
