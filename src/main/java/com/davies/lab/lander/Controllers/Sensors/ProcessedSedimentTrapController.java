package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.SedimentTrapDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.SedimentTrapHeadResponse;
import com.davies.lab.lander.Models.Data.ProcessedSedimentTrapData;
import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;
import com.davies.lab.lander.Repositories.Data.ProcessedSedimentTrapDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedSedimentTrapHeadRepository;
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
@RequestMapping("/api/processed/sediment_trap")
public class ProcessedSedimentTrapController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedSedimentTrapDataRepository repository;
    @Autowired
    private ProcessedSedimentTrapHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;

    //HeadRoutes
    @GetMapping("/headers")
    public List<SedimentTrapHeadResponse> findAllHeads() {
        List<ProcessedSedimentTrapHeader> heads = headRepository.findAll();
        List<SedimentTrapHeadResponse> res = new ArrayList<>();

        for (ProcessedSedimentTrapHeader head : heads) {
            SedimentTrapHeadResponse temp =  new SedimentTrapHeadResponse(
                    head
            );

            for (ProcessedSedimentTrapData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<SedimentTrapHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedSedimentTrapHeader> head = headRepository.findById(id);
        SedimentTrapHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new SedimentTrapHeadResponse(
                head.get()
        );

        for (ProcessedSedimentTrapData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data")
    public List<SedimentTrapDataResponse> findAllEntries() {
        List<ProcessedSedimentTrapData> data = repository.findAll();
        List<SedimentTrapDataResponse> res = new ArrayList<>();

        for (ProcessedSedimentTrapData dataPoint : data) {
            res.add(
                    new SedimentTrapDataResponse(
                            dataPoint
                    )
            );
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<SedimentTrapDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedSedimentTrapData> data = repository.findById(id);
        SedimentTrapDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new SedimentTrapDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //TODO: Create POST/PUT routes one entity details are identified

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderById(@PathVariable("id") Long id) {
        ProcessedSedimentTrapHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        headRepository.delete(selHead);

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataById(@PathVariable("id") Long id) {
        ProcessedSedimentTrapData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(selData);

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
