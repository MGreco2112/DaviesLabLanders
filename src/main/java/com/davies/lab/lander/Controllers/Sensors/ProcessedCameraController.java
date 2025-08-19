package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.CameraDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.CameraHeadResponse;
import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import com.davies.lab.lander.Models.Headers.ProcessedCameraHeader;
import com.davies.lab.lander.Repositories.Data.ProcessedCameraDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedCameraHeadRepository;
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
@RequestMapping("/api/processed/camera")
public class ProcessedCameraController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedCameraDataRepository repository;
    @Autowired
    private ProcessedCameraHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;

    //Head Routes
    @GetMapping("/headers")
    public List<CameraHeadResponse> findAllHeads() {
        List<ProcessedCameraHeader> heads = headRepository.findAll();
        List<CameraHeadResponse> res = new ArrayList<>();

        for (ProcessedCameraHeader head : heads) {
            CameraHeadResponse temp = new CameraHeadResponse(
                    head
            );

            for (ProcessedCameraData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<CameraHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedCameraHeader> head = headRepository.findById(id);
        CameraHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CameraHeadResponse(
                head.get()
        );

        for (ProcessedCameraData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<CameraDataResponse> findAllEntries() {
        List<ProcessedCameraData> data = repository.findAll();
        List<CameraDataResponse> res = new ArrayList<>();

        for (ProcessedCameraData dataPoint : data) {
            CameraDataResponse temp = new CameraDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<CameraDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedCameraData> data = repository.findById(id);
        CameraDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CameraDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<CameraDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
        List<ProcessedCameraData> data = repository.findDataByHeadId(id);
        List<CameraDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedCameraData elem : data) {
            res.add(
                    new CameraDataResponse(
                            elem
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //TODO: Add POST/PUT mappings after model is defined

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderById(@PathVariable("id") Long id) {
        ProcessedCameraHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        headRepository.delete(selHead);

        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedCameraData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(selData);

        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
