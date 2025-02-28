package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.CTDDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.CTDHeadResponse;
import com.davies.lab.lander.Models.ProcessedCTDData;
import com.davies.lab.lander.Models.ProcessedCTDHead;
import com.davies.lab.lander.Repositories.ProcessedCTDDataRepository;
import com.davies.lab.lander.Repositories.ProcessedCTDHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<CTDHeadResponse> findAllHeads() {
        List<ProcessedCTDHead> heads = headRepository.findAll();
        List<CTDHeadResponse> res = new ArrayList<>();

        for (ProcessedCTDHead head : heads) {
            CTDHeadResponse temp = new CTDHeadResponse(
                    head.getHeadID(),
                    head.getSondeName(),
                    head.getSondeNo(),
                    head.getSensorType(),
                    head.getChannel(),
                    head.getDelayTime(),
                    head.getPreHeat(),
                    head.getMeasMode(),
                    head.getBurstTime(),
                    head.getBurstCnt(),
                    head.getIntervalData(),
                    head.getSampleCnt(),
                    head.getStartTime(),
                    head.getEndTime(),
                    head.getDepAdiRho(),
                    head.getECA(),
                    head.getECB(),
                    head.getECDeg(),
                    head.getECCoef(),
                    head.getCoefDate(),
                    head.getCh1(),
                    head.getCh2(),
                    head.getCh3(),
                    head.getCh4(),
                    head.getBuzzerEN(),
                    head.getBuzzerInterval(),
                    head.getCOMMENT(),
                    head.getSensorType2(),
                    head.getBuzzerNumber(),
                    head.getDepM(),
                    head.getCondDepB(),
                    head.getLanderID().getASDBLanderID()
                    );

            for (ProcessedCTDData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<CTDHeadResponse> findHeadWithoutDataById(@PathVariable Integer id) {
        Optional<ProcessedCTDHead> head = headRepository.findById(id);
        CTDHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CTDHeadResponse(
                head.get().getHeadID(),
                head.get().getSondeName(),
                head.get().getSondeNo(),
                head.get().getSensorType(),
                head.get().getChannel(),
                head.get().getDelayTime(),
                head.get().getPreHeat(),
                head.get().getMeasMode(),
                head.get().getBurstTime(),
                head.get().getBurstCnt(),
                head.get().getIntervalData(),
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getDepAdiRho(),
                head.get().getECA(),
                head.get().getECB(),
                head.get().getECDeg(),
                head.get().getECCoef(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getCh4(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getCOMMENT(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getDepM(),
                head.get().getCondDepB(),
                head.get().getLanderID().getASDBLanderID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<CTDHeadResponse> findHeadById(@PathVariable Integer id) {
        Optional<ProcessedCTDHead> head = headRepository.findById(id);
        CTDHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CTDHeadResponse(
                head.get().getHeadID(),
                head.get().getSondeName(),
                head.get().getSondeNo(),
                head.get().getSensorType(),
                head.get().getChannel(),
                head.get().getDelayTime(),
                head.get().getPreHeat(),
                head.get().getMeasMode(),
                head.get().getBurstTime(),
                head.get().getBurstCnt(),
                head.get().getIntervalData(),
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getDepAdiRho(),
                head.get().getECA(),
                head.get().getECB(),
                head.get().getECDeg(),
                head.get().getECCoef(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getCh4(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getCOMMENT(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getDepM(),
                head.get().getCondDepB(),
                head.get().getLanderID().getASDBLanderID()
        );

        for (ProcessedCTDData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<CTDDataResponse> findAllEntries() {
        List<ProcessedCTDData> data = repository.findAll();
        List<CTDDataResponse> res = new ArrayList<>();

        for (ProcessedCTDData dataPoint : data) {
            CTDDataResponse temp = new CTDDataResponse(
                    dataPoint.getID(),
                    dataPoint.getDate(),
                    dataPoint.getTempDegC(),
                    dataPoint.getSal(),
                    dataPoint.getCondMsCm(),
                    dataPoint.getEc25UsCm(),
                    dataPoint.getBattV(),
                    dataPoint.getHeadID().getHeadID()
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<CTDDataResponse> findDataById(@PathVariable Integer id) {
        Optional<ProcessedCTDData> data = repository.findById(id);
        CTDDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CTDDataResponse(
                data.get().getID(),
                data.get().getDate(),
                data.get().getTempDegC(),
                data.get().getSal(),
                data.get().getCondMsCm(),
                data.get().getEc25UsCm(),
                data.get().getBattV(),
                data.get().getHeadID().getHeadID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<CTDDataResponse>> findDataByHeadId(@PathVariable Integer id) {
        List<ProcessedCTDData> data = repository.findDataByHeadId(id);
        List<CTDDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedCTDData elem : data) {
            res.add(new CTDDataResponse(
                    elem.getID(),
                    elem.getDate(),
                    elem.getTempDegC(),
                    elem.getSal(),
                    elem.getCondMsCm(),
                    elem.getEc25UsCm(),
                    elem.getBattV(),
                    elem.getHeadID().getHeadID()
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
