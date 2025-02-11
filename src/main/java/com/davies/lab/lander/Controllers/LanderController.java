package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Repositories.LanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/landers")
public class LanderController {
    @Autowired
    private LanderRepository repository;

    @GetMapping("/all")
    public List<Lander> findAllLanders() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Lander> findLanderByID(@PathVariable String id) {
        return new ResponseEntity<Lander>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }
}
