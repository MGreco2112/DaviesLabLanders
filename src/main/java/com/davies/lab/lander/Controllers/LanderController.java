package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.NewLanderRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateLanderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.LanderResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.LatestLandersResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.*;
import com.davies.lab.lander.Models.Headers.*;
import com.davies.lab.lander.Repositories.*;
import com.davies.lab.lander.Repositories.Data.*;
import com.davies.lab.lander.Repositories.Header.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/landers")
@EnableCaching
public class LanderController {
    @Autowired
    private LanderRepository repository;
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
    private ProcessedAlbexCTDHeaderRepository albexCTDHeadRepository;
    @Autowired
    private ProcessedAlbexCTDDataRepository albexCTDDataRepository;
    @Autowired
    private ProcessedADCPDataRepository adcpDataRepository;
    @Autowired
    private ProcessedADCPHeadRepository adcpHeadRepository;
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

    @GetMapping("/all")
    public List<LanderResponse> findAllLanders() {
         List<Lander> landerList = repository.findAll();
         List<LanderResponse> resList = new ArrayList<>();

         for (Lander lander : landerList) {
             LanderResponse res = new LanderResponse(lander.getASDBLanderID(), lander.getLanderPlatform(), lander.getASDBROVDiveID());

             if (lander.getCTDHead() != null) {
                 res.createBasicCTDHeadResponse(lander.getCTDHead());
             }
             if (lander.getDOHead() != null) {
                 res.createBasicDOHeadResponse(lander.getDOHead());
             }
             if (lander.getFLNTUHead() != null) {
                 res.createBasicFLNTUHeadResponse(lander.getFLNTUHead());
             }
             if (lander.getAlbexHead() != null) {
                 LocalDateTime startTime = albexCTDDataRepository.findDeploymentDateByHeadID(lander.getAlbexHead().getHeadID());
                 LocalDateTime endTime = albexCTDDataRepository.findRecoveryDateByHeadID(lander.getAlbexHead().getHeadID());
                 res.createAlbexCTDHeadResponse(lander.getAlbexHead(), startTime, endTime);
             }
             if (lander.getADCPHead() != null) {
                 LocalDateTime startTime = adcpDataRepository.findDeploymentDateByHeadID(lander.getADCPHead().getHeadID());
                 LocalDateTime endTime = adcpDataRepository.findRecoveryDateByHeadID(lander.getADCPHead().getHeadID());
                 res.createADCPHeadResponse(lander.getADCPHead(), startTime, endTime);
             }

             if (lander.getBatteryHead() != null) {
//                 TODO: update with header values once parent model is updated
                res.createBatteryHeadResponse(lander.getBatteryHead(), null, null);
             }

             if (lander.getBeaconHead() != null) {
//                 TODO: update with header values once parent model is updated
                 res.createBeaconHeadResponse(lander.getBeaconHead(), null, null);
             }

             if (lander.getCameraHead() != null) {
//                 TODO: update with header values once parent model is updated
                 res.createCameraHeadResponse(lander.getCameraHead(), null, null);
             }

             if (lander.getSedimentTrapHead() != null) {
//                 TODO: update with header values once parent model is updated
                 res.createSedimentTrapHeadResponse(lander.getSedimentTrapHead(), null, null);
             }

             if (lander.getDeploymentDateAndTime() != null) {
                 res.setDeploymentDate(lander.getDeploymentDateAndTime());
             }

             if (lander.getRecoveryDateAndTime() != null) {
                 res.setRecoveryDate(lander.getRecoveryDateAndTime());
             }

             resList.add(res);
         }

         return resList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanderResponse> findLanderByID(@PathVariable String id) {
        Optional<Lander> lander = repository.findById(id);
        LanderResponse res;
        if (lander.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new LanderResponse(lander.get().getASDBLanderID(), lander.get().getLanderPlatform(), lander.get().getASDBROVDiveID());

        if (lander.get().getCTDHead() != null) {
            res.createBasicCTDHeadResponse(lander.get().getCTDHead());
        }
        if (lander.get().getDOHead() != null) {
            res.createBasicDOHeadResponse(lander.get().getDOHead());
        }
        if (lander.get().getFLNTUHead() != null) {
            res.createBasicFLNTUHeadResponse(lander.get().getFLNTUHead());
        }
        if (lander.get().getAlbexHead() != null) {
            LocalDateTime startTime = albexCTDDataRepository.findDeploymentDateByHeadID(lander.get().getAlbexHead().getHeadID());
            LocalDateTime endTime = albexCTDDataRepository.findRecoveryDateByHeadID(lander.get().getAlbexHead().getHeadID());
            res.createAlbexCTDHeadResponse(lander.get().getAlbexHead(), startTime, endTime);
        }
        if (lander.get().getADCPHead() != null) {
            LocalDateTime startTime = adcpDataRepository.findDeploymentDateByHeadID(lander.get().getADCPHead().getHeadID());
            LocalDateTime endTime = adcpDataRepository.findRecoveryDateByHeadID(lander.get().getADCPHead().getHeadID());
            res.createADCPHeadResponse(lander.get().getADCPHead(), startTime, endTime);
        }

        if (lander.get().getBatteryHead() != null) {
//                 TODO: update with header values once parent model is updated
            res.createBatteryHeadResponse(lander.get().getBatteryHead(), null, null);
        }

        if (lander.get().getBeaconHead() != null) {
//                 TODO: update with header values once parent model is updated
            res.createBeaconHeadResponse(lander.get().getBeaconHead(), null, null);
        }

        if (lander.get().getCameraHead() != null) {
//                 TODO: update with header values once parent model is updated
            res.createCameraHeadResponse(lander.get().getCameraHead(), null, null);
        }

        if (lander.get().getSedimentTrapHead() != null) {
//                 TODO: update with header values once parent model is updated
            res.createSedimentTrapHeadResponse(lander.get().getSedimentTrapHead(), null, null);
        }

        if (lander.get().getDeploymentDateAndTime() != null) {
            res.setDeploymentDate(lander.get().getDeploymentDateAndTime());
        }

        if (lander.get().getRecoveryDateAndTime() != null) {
            res.setRecoveryDate(lander.get().getRecoveryDateAndTime());
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/search/id/{landerID}")
    public Set<LanderResponse> getLandersByIDQuery(@PathVariable("landerID") String landerID) {
        Set<LanderResponse> res = new HashSet<>();

        Set<Lander> landers = repository.selectLandersBySimilarID(landerID);

        for (Lander lander : landers) {
            res.add(
                    new LanderResponse(
                            lander.getASDBLanderID(),
                            lander.getLanderPlatform(),
                            lander.getASDBROVDiveID()
                    )
            );
        }

        return res;
    }

    @GetMapping("/search/date/{inputDate}")
    public Set<LanderResponse> getLandersByDateQuery(@PathVariable("inputDate") String inputDate) {
        Set<Lander> landerSet = repository.selectLandersByDateRange(inputDate);
        Set<LanderResponse> res = new HashSet<>();

        for (Lander selLander : landerSet) {
            res.add(
                    new LanderResponse(
                            selLander.getASDBLanderID(),
                            selLander.getLanderPlatform(),
                            selLander.getASDBROVDiveID()
                    )
            );
        }

        return res;
    }

    @GetMapping("/latest_uploads")
    @Cacheable("latest-landers")
    public ResponseEntity<LatestLandersResponse> getLatestLanders() {
        List<Lander> landers = repository.getLatestThreeLanders();
        List<LanderResponse> res = new ArrayList<>();

        for (Lander lander : landers) {
            LanderResponse temp = new LanderResponse(lander);
            temp.setDeploymentDate(lander.getDeploymentDateAndTime());
            temp.setRecoveryDate(lander.getRecoveryDateAndTime());

            res.add(temp);
        }

        return new ResponseEntity<>(new LatestLandersResponse(res), HttpStatus.OK);
    }

    @CacheEvict(value = "latest-landers", allEntries = true)
    @Scheduled(fixedRate = 86_400_000) //24 hours to dump cache
    public void evictMyCache() {
        System.out.println("Cleared Cache");
    }

    @PostMapping("/new_lander")
    public ResponseEntity<String> postNewLander(@RequestBody NewLanderRequest newLander) {
        Lander lander = new Lander(
                newLander.getASDBLanderID(),
                newLander.getLanderPlatform(),
                newLander.getASDBROVDiveID(),
                StringFormatting.formatFrontendDateString(newLander.getDeploymentDateAndTime()),
                StringFormatting.formatFrontendDateString(newLander.getRecoveryDateAndTime())
        );

        repository.save(lander);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<String> updateLander(@PathVariable("id") String id, @RequestBody UpdateLanderRequest updates) {
       Lander selLander = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getLanderPlatform() != null) {
            selLander.setLanderPlatform(updates.getLanderPlatform());
        }
        if (updates.getASDBROBDiveID() != null) {
            selLander.setASDBROVDiveID(updates.getASDBROBDiveID());
        }
        if (updates.getDeploymentDateAndTime() != null) {
            selLander.setDeploymentDateAndTime(updates.getDeploymentDateAndTime());
        }
        if (updates.getRecoveryDateAndTime() != null) {
            selLander.setRecoveryDateAndTime(updates.getRecoveryDateAndTime());
        }
        if (updates.getCTDHead() != null) {
            selLander.setCTDHead(updates.getCTDHead());
        }
        if (updates.getDOHead() != null) {
            selLander.setDOHead(updates.getDOHead());
        }
        if (updates.getFLNTUHead() != null) {
            selLander.setFLNTUHead(updates.getFLNTUHead());
        }
        if (updates.getAlbexHead() != null) {
            selLander.setAlbexHead(updates.getAlbexHead());
        }
        if (updates.getADCPHead() != null) {
            selLander.setADCPHead(updates.getADCPHead());
        }
        if (updates.getBatteryHead() != null) {
            selLander.setBatteryHead(updates.getBatteryHead());
        }
        if (updates.getBeaconHead() != null) {
            selLander.setBeaconHead(updates.getBeaconHead());
        }
        if (updates.getCameraHead() != null) {
            selLander.setCameraHead(updates.getCameraHead());
        }
        if (updates.getSedimentTrapHead() != null) {
            selLander.setSedimentTrapHead(updates.getSedimentTrapHead());
        }

        repository.save(selLander);

        return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteLanderByID(@PathVariable("id") String id) {
        Lander selLander = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        if (selLander.getCTDHead() != null) {
            Optional<ProcessedCTDHead> ctdHead = ctdHeadRepository.findById(selLander.getCTDHead().getHeadID());
            selLander.setCTDHead(null);
            repository.save(selLander);
            ctdDataRepository.deleteAll(ctdHead.get().getData());
            ctdHeadRepository.delete(ctdHead.get());
        }

        if (selLander.getDOHead() != null) {
            Optional<ProcessedDOHead> doHead =  doHeadRepository.findById(selLander.getDOHead().getHeadID());
            selLander.setDOHead(null);
            repository.save(selLander);
            doDataRepository.deleteAll(doHead.get().getData());
            doHeadRepository.delete(doHead.get());
        }

        if (selLander.getFLNTUHead() != null) {
            Optional<ProcessedFLNTUHead> flntuHead = flntuHeadRepository.findById(selLander.getFLNTUHead().getHeadID());
            selLander.setFLNTUHead(null);
            repository.save(selLander);
            flntuDataRepository.deleteAll(flntuHead.get().getData());
            flntuHeadRepository.delete(flntuHead.get());
        }

        if (selLander.getAlbexHead() != null) {
            Optional<ProcessedAlbexCTDHeader> albexCTDHead = albexCTDHeadRepository.findById(selLander.getAlbexHead().getHeadID());
            selLander.setAlbexHead(null);
            repository.save(selLander);
            albexCTDDataRepository.deleteAll(albexCTDHead.get().getData());
            albexCTDHeadRepository.delete(albexCTDHead.get());
        }

        if (selLander.getADCPHead() != null) {
            Optional<ProcessedADCPHead> adcpHead = adcpHeadRepository.findById(selLander.getADCPHead().getHeadID());
            selLander.setADCPHead(null);
            repository.save(selLander);
            adcpDataRepository.deleteAll(adcpHead.get().getData());
            adcpHeadRepository.delete(adcpHead.get());
        }

        if (selLander.getBatteryHead() != null) {
            Optional<ProcessedBatteryHeader> batteryHead = batteryHeadRepository.findById(selLander.getBatteryHead().getHeadID());
            selLander.setBatteryHead(null);
            repository.save(selLander);
            batteryDataRepository.deleteAll(batteryHead.get().getData());
            batteryHeadRepository.delete(batteryHead.get());
        }

        if (selLander.getBeaconHead() != null) {
            Optional<ProcessedBeaconHeader> beaconHead = beaconHeadRepository.findById(selLander.getBeaconHead().getHeadID());
            selLander.setBeaconHead(null);
            repository.save(selLander);
            beaconDataRepository.deleteAll(beaconHead.get().getData());
            beaconHeadRepository.save(beaconHead.get());
        }

        if (selLander.getCameraHead() != null) {
            Optional<ProcessedCameraHeader> cameraHead = cameraHeadRepository.findById(selLander.getCameraHead().getHeadID());
            selLander.setCameraHead(null);
            repository.save(selLander);
            cameraDataRepository.deleteAll(cameraHead.get().getData());
            cameraHeadRepository.delete(cameraHead.get());
        }

        if (selLander.getSedimentTrapHead() != null) {
            Optional<ProcessedSedimentTrapHeader> sedimentTrapHead = sedimentTrapHeadRepository.findById(selLander.getSedimentTrapHead().getHeadID());
            selLander.setSedimentTrapHead(null);
            repository.save(selLander);
            sedimentTrapDataRepository.deleteAll(sedimentTrapHead.get().getData());
            sedimentTrapHeadRepository.delete(sedimentTrapHead.get());
        }

        repository.delete(selLander);

        return new ResponseEntity<>("Deleted Lander", HttpStatus.OK);
    }
}
