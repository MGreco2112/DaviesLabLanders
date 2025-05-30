package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.*;
import com.davies.lab.lander.Models.*;
import com.davies.lab.lander.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        try {
            ProcessedCTDHead ctdHead = ctdHeadRepository.getCTDHeadsByLanderId(selLander.getASDBLanderID());

            CTDHeadResponseExternal newCtdHead = new CTDHeadResponseExternal(ctdHead);

            Set<CTDDataResponseExternal> ctdDataSet = CTDDataResponseExternal.createBulkResponses(ctdDataRepository.findDataByHeadId(ctdHead.getHeadID()));

            newCtdHead.setData(ctdDataSet);

            selLander.setCtdHead(newCtdHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        //get, create, and place DOData
        try {
            ProcessedDOHead doHead = doHeadRepository.getDOHeadsByLanderID(selLander.getASDBLanderID());

            DOHeadResponseExternal newDoHead = new DOHeadResponseExternal(doHead);

            Set<DODataResponseExternal> doDataSet = DODataResponseExternal.createDataResponses(doDataRepository.findDoDataByHeadId(doHead.getHeadID()));

            newDoHead.setData(doDataSet);

            selLander.setDoHead(newDoHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        //get, create and place FLNTUData
        try {
            ProcessedFLNTUHead flntuHead = flntuHeadRepository.getFLNTUHeadsByLanderID(selLander.getASDBLanderID());

            FLNTUHeadResponseExternal newFlntuHead = new FLNTUHeadResponseExternal(flntuHead);

            Set<FLNTUDataResponseExternal> flntuDataSet = FLNTUDataResponseExternal.createDataResponse(flntuDataRepository.findDataFromHeadId(flntuHead.getHeadID()));

            newFlntuHead.setData(flntuDataSet);

            selLander.setFlntuHead(newFlntuHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        //get, create and place ALBEXData
        try {
            ProcessedAlbexCTDHeader albexHead = albexHeaderRepository.getAlbexHeadsByLanderId(selLander.getASDBLanderID());

            ALBEXCTDHeadResponseExternal newAlbexHead = new ALBEXCTDHeadResponseExternal(albexHead);

            Set<ALBEXCTDDataResponseExternal> albexDataSet = ALBEXCTDDataResponseExternal.createBulkResponses(albexDataRepository.findDataByHeadId(albexHead.getHeadID()));

            newAlbexHead.setData(albexDataSet);

            selLander.setAlbexHead(newAlbexHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        try {
            ProcessedADCPHead adcpHead = adcpHeadRepository.getADCPHeadByLanderId(selLander.getASDBLanderID());

            ADCPHeadResponseExternal newADCPHead = new ADCPHeadResponseExternal(adcpHead);

            Set<ADCPDataResponseExternal> adcpDataSet = ADCPDataResponseExternal.createBulkResponses(adcpDataRepository.findDataByHeadId(adcpHead.getHeadID()));

            newADCPHead.setData(adcpDataSet);

            selLander.setAdcpHead(newADCPHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return new ResponseEntity<>(selLander, HttpStatus.OK);
    }

    //GET Lander CTD
    @GetMapping("/lander/id/{id}/ctd")
    public ResponseEntity<CTDHeadResponseExternal> getCTDByLanderID(@PathVariable("id") String landerID) {
        ProcessedCTDHead selHead = ctdHeadRepository.getCTDHeadsByLanderId(landerID);

        if (selHead == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CTDHeadResponseExternal newHead = new CTDHeadResponseExternal(selHead);
        Set<CTDDataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedCTDData selData : selHead.getData()) {
            newDataSet.add(
                    new CTDDataResponseExternal(selData)
            );
        }

        newHead.setData(newDataSet);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    //GET Lander DO
    @GetMapping("/lander/id/{id}/do")
    public ResponseEntity<DOHeadResponseExternal> getDOByLanderID(@PathVariable("id") String landerID) {
        ProcessedDOHead selHead = doHeadRepository.getDOHeadsByLanderID(landerID);

        if (selHead == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        DOHeadResponseExternal newHead = new DOHeadResponseExternal(selHead);
        Set<DODataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedDOData selData : selHead.getData()) {
            newDataSet.add(
                    new DODataResponseExternal(selData)
            );
        }

        newHead.setData(newDataSet);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    //GET Lander FLNTU
    @GetMapping("/lander/id/{id}/flntu")
    public ResponseEntity<FLNTUHeadResponseExternal> getFLNTUByLanderID(@PathVariable("id") String landerID) {
        ProcessedFLNTUHead selHead = flntuHeadRepository.getFLNTUHeadsByLanderID(landerID);

        if (selHead == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        FLNTUHeadResponseExternal newHead = new FLNTUHeadResponseExternal(selHead);
        Set<FLNTUDataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedFLNTUData selData : selHead.getData()) {
            newDataSet.add(
                    new FLNTUDataResponseExternal(selData)
            );
        }

        newHead.setData(newDataSet);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/albex_ctd")
    public ResponseEntity<ALBEXCTDHeadResponseExternal> getAlbexByLanderId(@PathVariable("id") String id) {
        ProcessedAlbexCTDHeader selHead = albexHeaderRepository.getAlbexHeadsByLanderId(id);

        if (selHead == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        ALBEXCTDHeadResponseExternal newHead = new ALBEXCTDHeadResponseExternal(selHead);
        Set<ALBEXCTDDataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedAlbexCTDData data : selHead.getData()) {
            newDataSet.add(new ALBEXCTDDataResponseExternal(data));
        }

        newHead.setData(newDataSet);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/adcp")
    public ResponseEntity<ADCPHeadResponseExternal> getAdcpByLanderId(@PathVariable("id") String id) {
        ProcessedADCPHead selHead = adcpHeadRepository.getADCPHeadByLanderId(id);

        if (selHead == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        ADCPHeadResponseExternal newHead = new ADCPHeadResponseExternal(selHead);
        Set<ADCPDataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedADCPData data : selHead.getData()) {
            newDataSet.add(new ADCPDataResponseExternal(data));
        }

        newHead.setData(newDataSet);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }
}
