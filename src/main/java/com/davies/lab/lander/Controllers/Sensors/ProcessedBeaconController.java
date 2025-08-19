package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.BeaconDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.BeaconHeadResponse;
import com.davies.lab.lander.Models.Data.ProcessedBeaconData;
import com.davies.lab.lander.Models.Headers.ProcessedBeaconHeader;
import com.davies.lab.lander.Repositories.Data.ProcessedBeaconDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedBeaconHeadRepository;
import com.davies.lab.lander.Repositories.LanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/beacon")
public class ProcessedBeaconController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedBeaconDataRepository repository;
    @Autowired
    private ProcessedBeaconHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;

    //Head Routes
    @GetMapping("/headers")
    public List<BeaconHeadResponse> findAllHeads() {
        List<ProcessedBeaconHeader> heads = headRepository.findAll();
        List<BeaconHeadResponse> res = new ArrayList<>();

        for (ProcessedBeaconHeader head : heads) {
            BeaconHeadResponse temp = new BeaconHeadResponse(
                    head
            );

            for (ProcessedBeaconData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<BeaconHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedBeaconHeader> head = headRepository.findById(id);
        BeaconHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new BeaconHeadResponse(
                head.get()
        );

        for (ProcessedBeaconData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<BeaconDataResponse> findAllEntries() {
        List<ProcessedBeaconData> data = repository.findAll();
        List<BeaconDataResponse> res = new ArrayList<>();

        for (ProcessedBeaconData dataPoint : data) {
            BeaconDataResponse temp = new BeaconDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<BeaconDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedBeaconData> data = repository.findById(id);
        BeaconDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new BeaconDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<BeaconDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
        List<ProcessedBeaconData> data = repository.findDataByHeadId(id);
        List<BeaconDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedBeaconData elem : data)  {
            res.add(new BeaconDataResponse(
                    elem
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //TODO: Create POST/PUT routes once parent entities are fully updated

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedBeaconHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        try {
            headRepository.delete(selHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedBeaconData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        try {
            repository.delete(selData);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
