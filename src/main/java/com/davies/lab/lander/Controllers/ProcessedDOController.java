package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.DOHeadResponse;
import com.davies.lab.lander.Models.ProcessedDOData;
import com.davies.lab.lander.Models.ProcessedDOHead;
import com.davies.lab.lander.Repositories.ProcessedDODataRepository;
import com.davies.lab.lander.Repositories.ProcessedDOHeadRepository;
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
@RequestMapping("/api/processed/do")
public class ProcessedDOController {
    @Autowired
    private ProcessedDODataRepository repository;
    @Autowired
    private ProcessedDOHeadRepository headRepository;

    //Head Routes
    @GetMapping("/headers")
    public List<DOHeadResponse> findAllHeads() {
        List<ProcessedDOHead> heads = headRepository.findAll();
        List<DOHeadResponse> res = new ArrayList<>();

        for (ProcessedDOHead head : heads) {
            DOHeadResponse temp = new DOHeadResponse(
              head.getHeadID(),
              head.getSondeName(),
              head.getSondeNo(),
              head.getSensorType(),
              head.getChannel(),
              head.getDelayTime(),
              head.getPreHeat(),
              head.getMeasModel(),
              head.getBurstTime(),
              head.getBurstCnt(),
              head.getIntervalData(),
              head.getSampleCnt(),
              head.getStartTime(),
              head.getEndTime(),
              head.getDepAdiRho(),
              head.getCoefDate(),
              head.getCh1(),
              head.getCh2(),
              head.getCh3(),
              head.getBuzzerEN(),
              head.getBuzzerInterval(),
              head.getCOMMENT(),
              head.getSensorType2(),
              head.getBuzzerNumber(),
              head.getDepM(),
              head.getSetSal(),
              head.getFilmNo(),
              head.getLanderID().getASDBLanderID()
            );

            for (ProcessedDOData data : head.getData()) {
                temp.createDODataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<DOHeadResponse> findHeadById(@PathVariable Integer id) {
        Optional<ProcessedDOHead> head = headRepository.findById(id);
        DOHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new DOHeadResponse(
                head.get().getHeadID(),
                head.get().getSondeName(),
                head.get().getSondeNo(),
                head.get().getSensorType(),
                head.get().getChannel(),
                head.get().getDelayTime(),
                head.get().getPreHeat(),
                head.get().getMeasModel(),
                head.get().getBurstTime(),
                head.get().getBurstCnt(),
                head.get().getIntervalData(),
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getDepAdiRho(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getCOMMENT(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getDepM(),
                head.get().getSetSal(),
                head.get().getFilmNo(),
                head.get().getLanderID().getASDBLanderID()
        );

        for (ProcessedDOData data : head.get().getData()) {
            res.createDODataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
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
