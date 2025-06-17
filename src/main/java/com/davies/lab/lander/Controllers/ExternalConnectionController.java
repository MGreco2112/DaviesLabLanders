package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.*;
import com.davies.lab.lander.Models.*;
import com.davies.lab.lander.Repositories.*;
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
}
