package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.DOHeadResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.*;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedCTDHead;
import com.davies.lab.lander.Models.ProcessedDOHead;
import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import com.davies.lab.lander.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/lander/id/{id}")
    public ResponseEntity<LanderResponseExternal> testGetMapping(@PathVariable("id") String id) {
        //get and create Lander
        Lander lander = landerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        LanderResponseExternal selLander = new LanderResponseExternal(lander);

        //get, create, and place CTDData
        try {
            ProcessedCTDHead ctdHead = ctdHeadRepository.getCTDHeadsByLanderId(selLander.getASDBLanderID()).get(0);

            CTDHeadResponseExternal newCtdHead = new CTDHeadResponseExternal(ctdHead);

            Set<CTDDataResponseExternal> ctdDataSet = CTDDataResponseExternal.createBulkResponses(ctdDataRepository.findDataByHeadId(ctdHead.getHeadID()));

            newCtdHead.setData(ctdDataSet);

            selLander.addCTDHead(newCtdHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        //get, create, and place DOData
        try {
            ProcessedDOHead doHead = doHeadRepository.getDOHeadsByLanderID(selLander.getASDBLanderID()).get(0);

            DOHeadResponseExternal newDoHead = new DOHeadResponseExternal(doHead);

            Set<DODataResponseExternal> doDataSet = DODataResponseExternal.createDataResponses(doDataRepository.findDoDataByHeadId(doHead.getHeadID()));

            newDoHead.setData(doDataSet);

            selLander.addDOHead(newDoHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        //get, create and place FLNTUData
        try {
            ProcessedFLNTUHead flntuHead = flntuHeadRepository.getFLNTUHeadsByLanderID(selLander.getASDBLanderID()).get(0);

            FLNTUHeadResponseExternal newFlntuHead = new FLNTUHeadResponseExternal(flntuHead);

            Set<FLNTUDataResponseExternal> flntuDataSet = FLNTUDataResponseExternal.createDataResponse(flntuDataRepository.findDataFromHeadId(flntuHead.getHeadID()));

            newFlntuHead.setData(flntuDataSet);

            selLander.addFLNTUHead(newFlntuHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return new ResponseEntity<>(selLander, HttpStatus.OK);
    }
}
