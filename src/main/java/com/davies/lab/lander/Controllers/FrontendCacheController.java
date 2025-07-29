package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.*;
import com.davies.lab.lander.FormattedModels.ResponseBody.Cache.*;
import com.davies.lab.lander.Models.*;
import com.davies.lab.lander.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

            for (ProcessedCTDData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new CTDCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/do/headers")
    public ResponseEntity<DOCacheResponse> getDOHeaders() {
        List<ProcessedDOHead> heads = doHeadRepository.findAll();
        List<DOHeadResponse> res = new ArrayList<>();

        for (ProcessedDOHead head : heads) {
            DOHeadResponse temp = new DOHeadResponse(head);

            for (ProcessedDOData data : head.getData()) {
                temp.createDODataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new DOCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/flntu/headers")
    public ResponseEntity<FLTNUCacheResponse> getFLNTUHeaders() {
        List<ProcessedFLNTUHead> heads = flntuHeadRepository.findAll();
        List<FLNTUHeadResponse> res = new ArrayList<>();

        for (ProcessedFLNTUHead head : heads) {
            FLNTUHeadResponse temp = new FLNTUHeadResponse(head);

            for (ProcessedFLNTUData data : head.getData()) {
                temp.createFLNTUDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new FLTNUCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/albex_ctd/headers")
    public ResponseEntity<Albex_CTDCacheResponse> getAlbexHeaders() {
        List<ProcessedAlbexCTDHeader> heads = albexHeaderRepository.findAll();
        List<AlbexCTDHeadResponse> res = new ArrayList<>();

        for (ProcessedAlbexCTDHeader head : heads) {
            AlbexCTDHeadResponse temp = new AlbexCTDHeadResponse(head);

            for (ProcessedAlbexCTDData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new Albex_CTDCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/adcp/headers")
    public ResponseEntity<ADCPCacheResponse> getADCPHeaders() {
        List<ProcessedADCPHead> heads = adcpHeadRepository.findAll();
        List<ADCPHeadResponse> res = new ArrayList<>();

        for (ProcessedADCPHead head : heads) {
            ADCPHeadResponse temp = new ADCPHeadResponse(head);

            for (ProcessedADCPData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new ADCPCacheResponse(res), HttpStatus.OK);
    }
}
