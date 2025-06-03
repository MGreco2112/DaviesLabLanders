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
        Optional<ProcessedCTDHead> ctdHead = ctdHeadRepository.getCTDHeadsByLanderId(selLander.getASDBLanderID());

        if (ctdHead.isPresent()) {
            CTDHeadResponseExternal newCtdHead = new CTDHeadResponseExternal(ctdHead.get());

            Set<CTDDataResponseExternal> ctdDataSet = CTDDataResponseExternal.createBulkResponses(ctdDataRepository.findDataByHeadId(ctdHead.get().getHeadID()));

            newCtdHead.setData(ctdDataSet);

            selLander.setCtdHead(newCtdHead);
        }


        //get, create, and place DOData
        Optional<ProcessedDOHead> doHead = doHeadRepository.getDOHeadsByLanderID(selLander.getASDBLanderID());

        if (doHead.isPresent()) {
            DOHeadResponseExternal newDoHead = new DOHeadResponseExternal(doHead.get());

            Set<DODataResponseExternal> doDataSet = DODataResponseExternal.createDataResponses(doDataRepository.findDoDataByHeadId(doHead.get().getHeadID()));

            newDoHead.setData(doDataSet);

            selLander.setDoHead(newDoHead);
        }

        //get, create and place FLNTUData
        Optional<ProcessedFLNTUHead> flntuHead = flntuHeadRepository.getFLNTUHeadsByLanderID(selLander.getASDBLanderID());

        if (flntuHead.isPresent()) {
            FLNTUHeadResponseExternal newFlntuHead = new FLNTUHeadResponseExternal(flntuHead.get());

            Set<FLNTUDataResponseExternal> flntuDataSet = FLNTUDataResponseExternal.createDataResponse(flntuDataRepository.findDataFromHeadId(flntuHead.get().getHeadID()));

            newFlntuHead.setData(flntuDataSet);

            selLander.setFlntuHead(newFlntuHead);
        }


        //get, create and place ALBEXData
        Optional<ProcessedAlbexCTDHeader> albexHead = albexHeaderRepository.getAlbexHeadsByLanderId(selLander.getASDBLanderID());

        if (albexHead.isPresent()) {
            ALBEXCTDHeadResponseExternal newAlbexHead = new ALBEXCTDHeadResponseExternal(albexHead.get());

            Set<ALBEXCTDDataResponseExternal> albexDataSet = ALBEXCTDDataResponseExternal.createBulkResponses(albexDataRepository.findDataByHeadId(albexHead.get().getHeadID()));

            newAlbexHead.setData(albexDataSet);

            selLander.setAlbexHead(newAlbexHead);
        }


        Optional<ProcessedADCPHead> adcpHead = adcpHeadRepository.getADCPHeadByLanderId(selLander.getASDBLanderID());

        if (adcpHead.isPresent()) {
            ADCPHeadResponseExternal newADCPHead = new ADCPHeadResponseExternal(adcpHead.get());

            Set<ADCPDataResponseExternal> adcpDataSet = ADCPDataResponseExternal.createBulkResponses(adcpDataRepository.findDataByHeadId(adcpHead.get().getHeadID()));

            newADCPHead.setData(adcpDataSet);

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
        Set<CTDDataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedCTDData selData : selHead.get().getData()) {
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
        Optional<ProcessedDOHead> selHead = doHeadRepository.getDOHeadsByLanderID(landerID);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        DOHeadResponseExternal newHead = new DOHeadResponseExternal(selHead.get());
        Set<DODataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedDOData selData : selHead.get().getData()) {
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
        Optional<ProcessedFLNTUHead> selHead = flntuHeadRepository.getFLNTUHeadsByLanderID(landerID);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        FLNTUHeadResponseExternal newHead = new FLNTUHeadResponseExternal(selHead.get());
        Set<FLNTUDataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedFLNTUData selData : selHead.get().getData()) {
            newDataSet.add(
                    new FLNTUDataResponseExternal(selData)
            );
        }

        newHead.setData(newDataSet);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/albex_ctd")
    public ResponseEntity<ALBEXCTDHeadResponseExternal> getAlbexByLanderId(@PathVariable("id") String id) {
        Optional<ProcessedAlbexCTDHeader> selHead = albexHeaderRepository.getAlbexHeadsByLanderId(id);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        ALBEXCTDHeadResponseExternal newHead = new ALBEXCTDHeadResponseExternal(selHead.get());
        Set<ALBEXCTDDataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedAlbexCTDData data : selHead.get().getData()) {
            newDataSet.add(new ALBEXCTDDataResponseExternal(data));
        }

        newHead.setData(newDataSet);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }

    @GetMapping("/lander/id/{id}/adcp")
    public ResponseEntity<ADCPHeadResponseExternal> getAdcpByLanderId(@PathVariable("id") String id) {
        Optional<ProcessedADCPHead> selHead = adcpHeadRepository.getADCPHeadByLanderId(id);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        ADCPHeadResponseExternal newHead = new ADCPHeadResponseExternal(selHead.get());
        Set<ADCPDataResponseExternal> newDataSet = new HashSet<>();

        for (ProcessedADCPData data : selHead.get().getData()) {
            newDataSet.add(new ADCPDataResponseExternal(data));
        }

        newHead.setData(newDataSet);

        return new ResponseEntity<>(newHead, HttpStatus.OK);
    }
}
