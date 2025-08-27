package com.davies.lab.lander.Controllers.Frontend;

import com.davies.lab.lander.FormattedModels.ResponseBody.Cache.*;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.*;
import com.davies.lab.lander.Models.Data.*;
import com.davies.lab.lander.Models.Headers.*;
import com.davies.lab.lander.Repositories.*;
import com.davies.lab.lander.Repositories.Data.*;
import com.davies.lab.lander.Repositories.Header.*;
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
    @Autowired
    private ProcessedBatteryHeadRepository batteryHeadRepository;
    @Autowired
    private ProcessedBatteryDataRepository batteryDataRepository;
    @Autowired
    private ProcessedBeaconHeadRepository beaconHeadRepository;
    @Autowired
    private ProcessedBeaconDataRepository beaconDataRepository;
    @Autowired
    private ProcessedCameraHeadRepository cameraHeadRepository;
    @Autowired
    private ProcessedCameraDataRepository cameraDataRepository;
    @Autowired
    private ProcessedSedimentTrapHeadRepository sedimentTrapHeadRepository;
    @Autowired
    private ProcessedSedimentTrapDataRepository sedimentTrapDataRepository;

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

    @GetMapping("/battery/headers")
    public ResponseEntity<BatteryCacheResponse> getBatteryHeaders() {
        List<ProcessedBatteryHeader> heads = batteryHeadRepository.findAll();
        List<BatteryHeadResponse> res = new ArrayList<>();

        for (ProcessedBatteryHeader head : heads) {
            BatteryHeadResponse temp = new BatteryHeadResponse(head);

            for (ProcessedBatteryData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new BatteryCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/battery/headers/{id}")
    public ResponseEntity<BatteryCacheResponse> getBatteryHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedBatteryHeader> selHead = batteryHeadRepository.findById(id);
        List<BatteryHeadResponse> res = new ArrayList<>();

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        BatteryHeadResponse temp = new BatteryHeadResponse(selHead.get());

        for (ProcessedBatteryData dataPoint : selHead.get().getData()) {
            temp.createDataResponse(dataPoint);
        }

        res.add(temp);

        return new ResponseEntity<>(new BatteryCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/beacon/headers")
    public ResponseEntity<BeaconCacheResponse> getBeaconHeaders() {
        List<ProcessedBeaconHeader> heads = beaconHeadRepository.findAll();
        List<BeaconHeadResponse> res = new ArrayList<>();

        for (ProcessedBeaconHeader head : heads) {
            BeaconHeadResponse temp = new BeaconHeadResponse(head);

            for (ProcessedBeaconData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new BeaconCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/beacon/headers/{id}")
    public ResponseEntity<BeaconCacheResponse> getBeaconHeaderById(@PathVariable("id") Long id) {
        Optional<ProcessedBeaconHeader> selHead = beaconHeadRepository.findById(id);
        List<BeaconHeadResponse> res = new ArrayList<>();

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        BeaconHeadResponse temp = new BeaconHeadResponse(selHead.get());

        for (ProcessedBeaconData data : selHead.get().getData()) {
            temp.createDataResponse(data);
        }

        res.add(temp);

        return new ResponseEntity<>(new BeaconCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/camera/headers")
    public ResponseEntity<CameraCacheResponse> getCameraHeaders() {
        List<ProcessedCameraHeader> heads = cameraHeadRepository.findAll();
        List<CameraHeadResponse> res = new ArrayList<>();

        for (ProcessedCameraHeader head : heads) {
            CameraHeadResponse temp = new CameraHeadResponse(head);

            for (ProcessedCameraData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new CameraCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/camera/headers/{id}")
    public ResponseEntity<CameraCacheResponse> getCameraHeaderById(@PathVariable("id") Long id) {
        Optional<ProcessedCameraHeader> selHead = cameraHeadRepository.findById(id);
        List<CameraHeadResponse> res = new ArrayList<>();

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CameraHeadResponse temp = new CameraHeadResponse(selHead.get());

        for (ProcessedCameraData data : selHead.get().getData()) {
            temp.createDataResponse(data);
        }

        res.add(temp);

        return new ResponseEntity<>(new CameraCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/sediment_trap/headers")
    public ResponseEntity<SedimentTrapCacheResponse> getSedimentTrapHeads() {
        List<ProcessedSedimentTrapHeader> heads = sedimentTrapHeadRepository.findAll();
        List<SedimentTrapHeadResponse> res = new ArrayList<>();

        for (ProcessedSedimentTrapHeader head : heads) {
            SedimentTrapHeadResponse temp = new SedimentTrapHeadResponse(head);

            for (ProcessedSedimentTrapData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return new ResponseEntity<>(new SedimentTrapCacheResponse(res), HttpStatus.OK);
    }

    @GetMapping("/sediment_trap/headers/{id}")
    public ResponseEntity<SedimentTrapCacheResponse> getSedimentTrapHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedSedimentTrapHeader> selHead = sedimentTrapHeadRepository.findById(id);
        List<SedimentTrapHeadResponse> res = new ArrayList<>();

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        SedimentTrapHeadResponse temp = new SedimentTrapHeadResponse(selHead.get());

        for (ProcessedSedimentTrapData data : selHead.get().getData()) {
            temp.createDataResponse(data);
        }

        res.add(temp);

        return new ResponseEntity<>(new SedimentTrapCacheResponse(res), HttpStatus.OK);
    }
}
