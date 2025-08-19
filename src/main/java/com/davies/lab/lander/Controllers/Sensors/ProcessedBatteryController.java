package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.BatteryDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.BatteryHeadResponse;
import com.davies.lab.lander.Models.Data.ProcessedBatteryData;
import com.davies.lab.lander.Models.Headers.ProcessedBatteryHeader;
import com.davies.lab.lander.Repositories.Data.ProcessedBatteryDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedBatteryHeadRepository;
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
@RequestMapping("/api/processed/battery")
public class ProcessedBatteryController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedBatteryDataRepository repository;
    @Autowired
    private ProcessedBatteryHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;

    //Head Routes
    public List<BatteryHeadResponse> findAllHeads() {
        List<ProcessedBatteryHeader> heads = headRepository.findAll();
        List<BatteryHeadResponse> res = new ArrayList<>();

        for (ProcessedBatteryHeader head : heads) {
            BatteryHeadResponse temp = new BatteryHeadResponse(
                    head
            );

            for (ProcessedBatteryData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<BatteryHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedBatteryHeader> head = headRepository.findById(id);
        BatteryHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new BatteryHeadResponse(
                head.get()
        );

        for (ProcessedBatteryData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<BatteryDataResponse> findAllEntries() {
        List<ProcessedBatteryData> data = repository.findAll();
        List<BatteryDataResponse> res = new ArrayList<>();

        for (ProcessedBatteryData dataPoint : data) {
            BatteryDataResponse temp = new BatteryDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<BatteryDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedBatteryData> data = repository.findById(id);
        BatteryDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new BatteryDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<BatteryDataResponse>> findDataByHeadId(@PathVariable("id") Long headId) {
        List<ProcessedBatteryData> data = repository.findDataByHeadId(headId);
        List<BatteryDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedBatteryData elem : data) {
            res.add(
                    new BatteryDataResponse(
                            elem
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

//    TODO: Update with POST/PUT Routes once Entity is better defined

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedBatteryHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save((selHead));

        headRepository.delete(selHead);

        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedBatteryData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(selData);

        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
