package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.*;
import com.davies.lab.lander.FormattedModels.ResponseBody.Cache.*;
import com.davies.lab.lander.Models.*;
import com.davies.lab.lander.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/cache")
public class FrontendCacheController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedCTDHeadRepository ctdHeadRepository;
    @Autowired
    private ProcessedCTDDataRepository ctdDataRepository;
    @Autowired
    private ProcessedDOHeadRepository doHeadRepository;
    @Autowired
    private ProcessedDODataRepository doDataRepository;
    @Autowired
    private ProcessedFLNTUHeadRepository flntuHeadRepository;
    @Autowired
    private ProcessedFLNTUDataRepository flntuDataRepository;
    @Autowired
    private ProcessedAlbexCTDHeaderRepository albexHeaderRepository;
    @Autowired
    private ProcessedAlbexCTDDataRepository albexDataRepository;
    @Autowired
    private ProcessedADCPHeadRepository adcpHeadRepository;
    @Autowired
    private ProcessedADCPDataRepository adcpDataRepository;

    @GetMapping("/ctd/headers")
    public ResponseEntity<CTDCacheResponse> getCTDHeaders() {
        List<ProcessedCTDHead> heads = ctdHeadRepository.findAll();
        List<CTDHeadResponse> res = new ArrayList<>();

        for (ProcessedCTDHead head : heads) {
            CTDHeadResponse temp = new CTDHeadResponse(head);

            temp.setDataPointCount(head.getData().size());

            for (ProcessedCTDData data : head.getData()) {
                temp.createFullDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new CTDCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/ctd/headers/{id}")
    public ResponseEntity<CTDCacheResponse> getCTDHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedCTDHead> head = ctdHeadRepository.findById(id);
        List<ProcessedCTDData> data = ctdDataRepository.findDataByHeadId(id);
        List<CTDHeadResponse> res = new ArrayList<>();

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CTDHeadResponse headResponse = new CTDHeadResponse(head.get());

        for (ProcessedCTDData dataPoint : data) {
            headResponse.createFullDataResponse(dataPoint);
        }

        res.add(
                headResponse
        );

        return new ResponseEntity<>(new CTDCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/do/headers")
    public ResponseEntity<DOCacheResponse> getDOHeaders() {
        List<ProcessedDOHead> heads = doHeadRepository.findAll();
        List<DOHeadResponse> res = new ArrayList<>();

        for (ProcessedDOHead head : heads) {
            DOHeadResponse temp = new DOHeadResponse(head);

            temp.setDataPointCount(head.getData().size());

            for (ProcessedDOData data : head.getData()) {
                temp.createFullDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new DOCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/do/headers/{id}")
    public ResponseEntity<DOCacheResponse> getDOHeaderById(@PathVariable("id") Long id) {
        Optional<ProcessedDOHead> head = doHeadRepository.findById(id);
        List<ProcessedDOData> data = doDataRepository.findDoDataByHeadId(id);
        List<DOHeadResponse> res = new ArrayList<>();

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        DOHeadResponse headResponse = new DOHeadResponse(head.get());

        for (ProcessedDOData dataPoint : data) {
            headResponse.createFullDataResponse(dataPoint);
        }

        res.add(
                headResponse
        );

        return new ResponseEntity<>(new DOCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/flntu/headers")
    public ResponseEntity<FLTNUCacheResponse> getFLNTUHeaders() {
        List<ProcessedFLNTUHead> heads = flntuHeadRepository.findAll();
        List<FLNTUHeadResponse> res = new ArrayList<>();

        for (ProcessedFLNTUHead head : heads) {
            FLNTUHeadResponse temp = new FLNTUHeadResponse(head);

            temp.setDataPointCount(head.getData().size());

            for (ProcessedFLNTUData data : head.getData()) {
                temp.createFullDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new FLTNUCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/flntu/headers/{id}")
    public ResponseEntity<FLTNUCacheResponse> getFLNTUHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedFLNTUHead> head = flntuHeadRepository.findById(id);
        List<ProcessedFLNTUData> data = flntuDataRepository.findDataFromHeadId(id);
        List<FLNTUHeadResponse> res = new ArrayList<>();

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        FLNTUHeadResponse headResponse = new FLNTUHeadResponse(head.get());

        for (ProcessedFLNTUData dataPoint : data) {
            headResponse.createFullDataResponse(dataPoint);
        }

        res.add(
                headResponse
        );

        return new ResponseEntity<>(new FLTNUCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/albex_ctd/headers")
    public ResponseEntity<Albex_CTDCacheResponse> getAlbexHeaders() {
        List<ProcessedAlbexCTDHeader> heads = albexHeaderRepository.findAll();
        List<AlbexCTDHeadResponse> res = new ArrayList<>();

        for (ProcessedAlbexCTDHeader head : heads) {
            AlbexCTDHeadResponse temp = new AlbexCTDHeadResponse(head);

            temp.setDataPointCount(head.getData().size());

            for (ProcessedAlbexCTDData data : head.getData()) {
                temp.createFullDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new Albex_CTDCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/albex_ctd/headeres/{id}")
    public ResponseEntity<Albex_CTDCacheResponse> getAlbexHeaderById(@PathVariable("id") Long id) {
        Optional<ProcessedAlbexCTDHeader> head = albexHeaderRepository.findById(id);
        List<ProcessedAlbexCTDData> data = albexDataRepository.findDataByHeadId(id);
        List<AlbexCTDHeadResponse> res = new ArrayList<>();

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        AlbexCTDHeadResponse headResponse = new AlbexCTDHeadResponse(head.get());

        for (ProcessedAlbexCTDData dataPoint : data) {
            headResponse.createFullDataResponse(dataPoint);
        }

        res.add(
                headResponse
        );

        return new ResponseEntity<>(new Albex_CTDCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/adcp/headers")
    public ResponseEntity<ADCPCacheResponse> getADCPHeaders() {
        List<ProcessedADCPHead> heads = adcpHeadRepository.findAll();
        List<ADCPHeadResponse> res = new ArrayList<>();

        for (ProcessedADCPHead head : heads) {
            ADCPHeadResponse temp = new ADCPHeadResponse(head);

            temp.setDataPointCount(head.getData().size());

            for (ProcessedADCPData data : head.getData()) {
                temp.createFullDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new ADCPCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/adcp/headers/{id}")
    public ResponseEntity<ADCPCacheResponse> getADCPHeaderById(@PathVariable("id") Long id) {
        Optional<ProcessedADCPHead> head = adcpHeadRepository.findById(id);
        List<ProcessedADCPData> data = adcpDataRepository.findDataByHeadId(id);
        List<ADCPHeadResponse> res = new ArrayList<>();

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        ADCPHeadResponse headResponse = new ADCPHeadResponse(head.get());

        for (ProcessedADCPData dataPoint : data) {
            headResponse.createFullDataResponse(dataPoint);
        }

        res.add(
                headResponse
        );

        return new ResponseEntity<>(new ADCPCacheResponse(res), HttpStatus.OK);
    }
}
