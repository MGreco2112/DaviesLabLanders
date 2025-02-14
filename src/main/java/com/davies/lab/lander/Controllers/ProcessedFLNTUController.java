package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.FLNTUDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.FLNTUHeadResponse;
import com.davies.lab.lander.Models.ProcessedFLNTUData;
import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import com.davies.lab.lander.Repositories.ProcessedFLNTUDataRepository;
import com.davies.lab.lander.Repositories.ProcessedFLNTUHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<FLNTUHeadResponse> findAllHeads() {
        List<ProcessedFLNTUHead> heads = headRepository.findAll();
        List<FLNTUHeadResponse> res = new ArrayList<>();

        for (ProcessedFLNTUHead selHead : heads) {
            FLNTUHeadResponse temp = new FLNTUHeadResponse(
                    selHead.getHeadID(),
                    selHead.getSondeName(),
                    selHead.getSondeNo(),
                    selHead.getSensorType(),
                    selHead.getChannel(),
                    selHead.getDelayTime(),
                    selHead.getPreHeat(),
                    selHead.getMeasMode(),
                    selHead.getBurstTime(),
                    selHead.getBurstCnt(),
                    selHead.getIntervalData(),
                    selHead.getWiperInterval(),
                    selHead.getSampleCnt(),
                    selHead.getStartTime(),
                    selHead.getEndTime(),
                    selHead.getCHLA(),
                    selHead.getCHLB(),
                    selHead.getCoefDate(),
                    selHead.getCh1(),
                    selHead.getCh2(),
                    selHead.getCh3(),
                    selHead.getCh4(),
                    selHead.getBuzzerEN(),
                    selHead.getBuzzerInterval(),
                    selHead.getComment(),
                    selHead.getSensorType2(),
                    selHead.getBuzzerNumber(),
                    selHead.getLanderID().getASDBLanderID()
            );

            for (ProcessedFLNTUData selData : selHead.getData()) {
                temp.createFLNTUDataResponse(selData);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<FLNTUHeadResponse> findHeadByID(@PathVariable Integer id) {
        Optional<ProcessedFLNTUHead> head = headRepository.findById(id);
        FLNTUHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new FLNTUHeadResponse(
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
                head.get().getWiperInterval(),
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getCHLA(),
                head.get().getCHLB(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getCh4(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getComment(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getLanderID().getASDBLanderID()
        );

        for (ProcessedFLNTUData data : head.get().getData()) {
            res.createFLNTUDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<FLNTUDataResponse> findAllEntries() {
        List<ProcessedFLNTUData> data = repository.findAll();
        List<FLNTUDataResponse> res = new ArrayList<>();

        for (ProcessedFLNTUData selData : data) {
            FLNTUDataResponse temp = new FLNTUDataResponse(
                    selData.getID(),
                    selData.getDate(),
                    selData.getTempDegC(),
                    selData.getChlFluPPB(),
                    selData.getChlAUgL(),
                    selData.getTurbMFTU(),
                    selData.getBattV(),
                    selData.getHeadID().getHeadID()
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<FLNTUDataResponse> findDataById(@PathVariable Integer id) {
        Optional<ProcessedFLNTUData> data = repository.findById(id);
        FLNTUDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new FLNTUDataResponse(
                data.get().getID(),
                data.get().getDate(),
                data.get().getTempDegC(),
                data.get().getChlFluPPB(),
                data.get().getChlAUgL(),
                data.get().getTurbMFTU(),
                data.get().getBattV(),
                data.get().getHeadID().getHeadID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
