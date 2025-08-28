package com.davies.lab.lander.Controllers.Frontend;

import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.*;
import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Data.*;
import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Head.*;
import com.davies.lab.lander.Models.*;
import com.davies.lab.lander.Models.Data.*;
import com.davies.lab.lander.Models.Headers.*;
import com.davies.lab.lander.Repositories.*;
import com.davies.lab.lander.Repositories.Data.*;
import com.davies.lab.lander.Repositories.Header.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/developers")
public class ExternalConnectionController {
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

    @GetMapping("/online")
    public ResponseEntity<APIOnlineResponse> checkOnlineStatus() {
        APIOnlineResponse res = new APIOnlineResponse(200);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //GET Lander without sensors
    @GetMapping("/basic_lander/id/{id}")
    public ResponseEntity<BasicLanderResponseExternal> getBasicLander(@PathVariable("id") String id) {
        Optional<Lander> lander = landerRepository.findById(id);

        if (lander.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        BasicLanderResponseExternal newRes = new BasicLanderResponseExternal(lander.get());

        return new ResponseEntity<>(newRes, HttpStatus.OK);
    }

    //GET Full Lander
    @GetMapping("/full_lander/id/{id}")
    public ResponseEntity<LanderResponseExternal> getFullLander(@PathVariable("id") String id) {
        //get and create Lander
        Lander lander = landerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        LanderResponseExternal selLander = new LanderResponseExternal(lander);

        //get, create, and place CTDData
        Optional<ProcessedCTDHead> ctdHead = ctdHeadRepository.getCTDHeadsByLanderId(selLander.getASDBLanderID());

        if (ctdHead.isPresent()) {
            CTDHeadResponseExternal newCtdHead = new CTDHeadResponseExternal(ctdHead.get());

            List<CTDDataResponseExternal> ctdDataList = CTDDataResponseExternal.createBulkResponses(ctdDataRepository.findDataByHeadAndAlingedStatus(ctdHead.get().getHeadID(), true));

            newCtdHead.setData(ctdDataList);

            selLander.setCtdHead(newCtdHead);
        }


        //get, create, and place DOData
        Optional<ProcessedDOHead> doHead = doHeadRepository.getDOHeadsByLanderID(selLander.getASDBLanderID());

        if (doHead.isPresent()) {
            DOHeadResponseExternal newDoHead = new DOHeadResponseExternal(doHead.get());

            List<DODataResponseExternal> doDataList = DODataResponseExternal.createDataResponses(doDataRepository.findDoDataByHeadId(doHead.get().getHeadID()));

            newDoHead.setData(doDataList);

            selLander.setDoHead(newDoHead);
        }

        //get, create and place FLNTUData
        Optional<ProcessedFLNTUHead> flntuHead = flntuHeadRepository.getFLNTUHeadsByLanderID(selLander.getASDBLanderID());

        if (flntuHead.isPresent()) {
            FLNTUHeadResponseExternal newFlntuHead = new FLNTUHeadResponseExternal(flntuHead.get());

            List<FLNTUDataResponseExternal> flntuDataList = FLNTUDataResponseExternal.createDataResponse(flntuDataRepository.findDataFromHeadId(flntuHead.get().getHeadID()));

            newFlntuHead.setData(flntuDataList);

            selLander.setFlntuHead(newFlntuHead);
        }


        //get, create and place ALBEXData
        Optional<ProcessedAlbexCTDHeader> albexHead = albexHeaderRepository.getAlbexHeadsByLanderId(selLander.getASDBLanderID());

        if (albexHead.isPresent()) {
            ALBEXCTDHeadResponseExternal newAlbexHead = new ALBEXCTDHeadResponseExternal(albexHead.get());

            List<ALBEXCTDDataResponseExternal> albexDataList = ALBEXCTDDataResponseExternal.createBulkResponses(albexDataRepository.findDataByHeadId(albexHead.get().getHeadID()));

            newAlbexHead.setData(albexDataList);

            selLander.setAlbexHead(newAlbexHead);
        }


        Optional<ProcessedADCPHead> adcpHead = adcpHeadRepository.getADCPHeadByLanderId(selLander.getASDBLanderID());

        if (adcpHead.isPresent()) {
            ADCPHeadResponseExternal newADCPHead = new ADCPHeadResponseExternal(adcpHead.get());

            List<ADCPDataResponseExternal> adcpDataList = ADCPDataResponseExternal.createBulkResponses(adcpDataRepository.findDataByHeadAndAlignedStatus(adcpHead.get().getHeadID(), true));

            newADCPHead.setData(adcpDataList);

            selLander.setAdcpHead(newADCPHead);
        }

        Optional<ProcessedBatteryHeader> batteryHead = batteryHeadRepository.getBatteryHeadByLanderId(selLander.getASDBLanderID());

        if (batteryHead.isPresent()) {
            BatteryHeadResponseExternal newBatteryHead = new BatteryHeadResponseExternal(batteryHead.get());

            List<BatteryDataResponseExternal> batteryDataList = BatteryDataResponseExternal.createDataResponse(batteryDataRepository.findDataByHeadId(batteryHead.get().getHeadID()));

            newBatteryHead.setData(batteryDataList);

            selLander.setBatteryHead(newBatteryHead);
        }

//        TODO: update the method with SedimentTrap heads
        Optional<ProcessedBeaconHeader> beaconHead = beaconHeadRepository.getBeaconHeadByLanderId(selLander.getASDBLanderID());

        if (beaconHead.isPresent()) {
            BeaconHeadResponseExternal newBeaconHead = new BeaconHeadResponseExternal(beaconHead.get());

            List<BeaconDataResponseExternal> beaconList = BeaconDataResponseExternal.createDataResponse(beaconDataRepository.findDataByHeadId(beaconHead.get().getHeadID()));

            newBeaconHead.setData(beaconList);

            selLander.setBeaconHead(newBeaconHead);
        }

        Optional<ProcessedCameraHeader> cameraHead = cameraHeadRepository.getCameraHeadByLanderId(selLander.getASDBLanderID());

        if (cameraHead.isPresent()) {
            CameraHeadResponseExternal newCameraHead = new CameraHeadResponseExternal(cameraHead.get());

            List<CameraDataResponseExternal> cameraList = CameraDataResponseExternal.createDataResponse(cameraDataRepository.findDataByHeadId(cameraHead.get().getHeadID()));

            newCameraHead.setData(cameraList);

            selLander.setCameraHead(newCameraHead);
        }

        Optional<ProcessedSedimentTrapHeader> sedimentTrapHead = sedimentTrapHeadRepository.getSedimentTrapHeadByLanderId(selLander.getASDBLanderID());

        if (sedimentTrapHead.isPresent()) {
            SedimentTrapHeadResponseExternal newSedimentTrapHead = new SedimentTrapHeadResponseExternal(sedimentTrapHead.get());

            List<SedimentTrapDataResponseExternal> sedimentTrapList = SedimentTrapDataResponseExternal.createDataResponse(sedimentTrapDataRepository.findDataByHeadId(sedimentTrapHead.get().getHeadID()));

            newSedimentTrapHead.setData(sedimentTrapList);

            selLander.setSedimentTrapHead(newSedimentTrapHead);
        }

        return new ResponseEntity<>(selLander, HttpStatus.OK);
    }

    //GET Lander CTD
    @GetMapping("/lander/id/{id}/ctd")
    public ResponseEntity<CTDHeadResponseExternal> getCTDByLanderID(@PathVariable("id") String landerID) {
        Optional<ProcessedCTDHead> selHead = ctdHeadRepository.getCTDHeadsByLanderId(landerID);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CTDHeadResponseExternal newHead = new CTDHeadResponseExternal(selHead.get());
        List<CTDDataResponseExternal> newDataList = new ArrayList<>();

        for (ProcessedCTDData selData : ctdDataRepository.findDataByHeadAndAlingedStatus(selHead.get().getHeadID(), true)) {
            newDataList.add(
                    new CTDDataResponseExternal(selData)
            );
        }

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    //GET Lander DO
    @GetMapping("/lander/id/{id}/do")
    public ResponseEntity<DOHeadResponseExternal> getDOByLanderID(@PathVariable("id") String landerID) {
        Optional<ProcessedDOHead> selHead = doHeadRepository.getDOHeadsByLanderID(landerID);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        DOHeadResponseExternal newHead = new DOHeadResponseExternal(selHead.get());
        List<DODataResponseExternal> newDataList = new ArrayList<>();

        for (ProcessedDOData selData : selHead.get().getData()) {
            newDataList.add(
                    new DODataResponseExternal(selData)
            );
        }

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    //GET Lander FLNTU
    @GetMapping("/lander/id/{id}/flntu")
    public ResponseEntity<FLNTUHeadResponseExternal> getFLNTUByLanderID(@PathVariable("id") String landerID) {
        Optional<ProcessedFLNTUHead> selHead = flntuHeadRepository.getFLNTUHeadsByLanderID(landerID);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        FLNTUHeadResponseExternal newHead = new FLNTUHeadResponseExternal(selHead.get());
        List<FLNTUDataResponseExternal> newDataList = new ArrayList<>();

        for (ProcessedFLNTUData selData : selHead.get().getData()) {
            newDataList.add(
                    new FLNTUDataResponseExternal(selData)
            );
        }

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/albex_ctd")
    public ResponseEntity<ALBEXCTDHeadResponseExternal> getAlbexByLanderId(@PathVariable("id") String id) {
        Optional<ProcessedAlbexCTDHeader> selHead = albexHeaderRepository.getAlbexHeadsByLanderId(id);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        ALBEXCTDHeadResponseExternal newHead = new ALBEXCTDHeadResponseExternal(selHead.get());
        List<ALBEXCTDDataResponseExternal> newDataList = new ArrayList<>();

        for (ProcessedAlbexCTDData data : selHead.get().getData()) {
            newDataList.add(new ALBEXCTDDataResponseExternal(data));
        }

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/adcp")
    public ResponseEntity<ADCPHeadResponseExternal> getAdcpByLanderId(@PathVariable("id") String id) {
        Optional<ProcessedADCPHead> selHead = adcpHeadRepository.getADCPHeadByLanderId(id);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        ADCPHeadResponseExternal newHead = new ADCPHeadResponseExternal(selHead.get());
        List<ADCPDataResponseExternal> newDataList = new ArrayList<>();

        for (ProcessedADCPData data : adcpDataRepository.findDataByHeadAndAlignedStatus(selHead.get().getHeadID(), true)) {
            newDataList.add(new ADCPDataResponseExternal(data));
        }

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/battery")
    public ResponseEntity<BatteryHeadResponseExternal> getBatteryByLanderId(@PathVariable("id") String id) {
        Optional<ProcessedBatteryHeader> selHead = batteryHeadRepository.getBatteryHeadByLanderId(id);

        if (selHead.isEmpty()) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        BatteryHeadResponseExternal newHead = new BatteryHeadResponseExternal(selHead.get());
        List<BatteryDataResponseExternal> newDataList = new ArrayList<>();

        for (ProcessedBatteryData data : batteryDataRepository.findDataByHeadId(selHead.get().getHeadID())) {
            newDataList.add(new BatteryDataResponseExternal(data));
        }

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/beacon")
    public ResponseEntity<BeaconHeadResponseExternal> getBeaconByLanderId(@PathVariable("id") String id) {
        Optional<ProcessedBeaconHeader> selHead = beaconHeadRepository.getBeaconHeadByLanderId(id);

        if (selHead.isEmpty()) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        BeaconHeadResponseExternal newHead = new BeaconHeadResponseExternal(selHead.get());
        List<BeaconDataResponseExternal> newDataList = BeaconDataResponseExternal.createDataResponse(selHead.get().getData());

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/camera")
    public ResponseEntity<CameraHeadResponseExternal> getCameraByLanderId(@PathVariable("id") String id) {
        Optional<ProcessedCameraHeader> selHead = cameraHeadRepository.getCameraHeadByLanderId(id);

        if (selHead.isEmpty()) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CameraHeadResponseExternal newHead = new CameraHeadResponseExternal(selHead.get());
        List<CameraDataResponseExternal> newDataList = CameraDataResponseExternal.createDataResponse(selHead.get().getData());

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/sediment_trap")
    public ResponseEntity<SedimentTrapHeadResponseExternal> getSedimentTrapByLanderId(@PathVariable("id") String id) {
        Optional<ProcessedSedimentTrapHeader> selHead = sedimentTrapHeadRepository.getSedimentTrapHeadByLanderId(id);

        if (selHead.isEmpty()) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        SedimentTrapHeadResponseExternal newHead = new SedimentTrapHeadResponseExternal(selHead.get());
        List<SedimentTrapDataResponseExternal> newDataList = SedimentTrapDataResponseExternal.createDataResponse(selHead.get().getData());

        newHead.setData(newDataList);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }
}
