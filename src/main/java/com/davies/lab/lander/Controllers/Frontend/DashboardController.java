package com.davies.lab.lander.Controllers.Frontend;

import com.davies.lab.lander.FormattedModels.ResponseBody.Dashboard.CompletedDashboard;
import com.davies.lab.lander.FormattedModels.ResponseBody.Dashboard.DashboardResponse;
import com.davies.lab.lander.Models.Data.ProcessedADCPData;
import com.davies.lab.lander.Models.Headers.*;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Repositories.*;
import com.davies.lab.lander.Repositories.Data.*;
import com.davies.lab.lander.Repositories.Data.Aligned.AlignedADCPDataRepository;
import com.davies.lab.lander.Repositories.Data.Aligned.AlignedCTDDataRepository;
import com.davies.lab.lander.Repositories.Header.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
@EnableCaching
@EnableScheduling
public class DashboardController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedCTDDataRepository ctdDataRepository;
    @Autowired
    private ProcessedCTDHeadRepository ctdHeadRepository;
    @Autowired
    private ProcessedDODataRepository doDataRepository;
    @Autowired
    private ProcessedDOHeadRepository doHeadRepository;
    @Autowired
    private ProcessedFLNTUDataRepository flntuDataRepository;
    @Autowired
    private ProcessedFLNTUHeadRepository flntuHeadRepository;
    @Autowired
    private ProcessedAlbexCTDDataRepository albexDataRepository;
    @Autowired
    private ProcessedAlbexCTDHeaderRepository albexCTDHeadRepository;
    @Autowired
    private ProcessedADCPDataRepository adcpDataRepository;
    @Autowired
    private ProcessedADCPHeadRepository adcpHeadRepository;
    @Autowired
    private AlignedADCPDataRepository alignedADCPRepository;
    @Autowired
    private AlignedCTDDataRepository alignedCTDRepository;
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

    @GetMapping("/populate")
    @Cacheable(value = "dashboard")
    public ResponseEntity<CompletedDashboard> getDashboardInformation() {
        DashboardResponse dash = populateDashboard();
        Map<Integer, Integer> pointsPerYear = returnDateDataCount();

        return new ResponseEntity<>(new CompletedDashboard(dash, pointsPerYear), HttpStatus.OK);
    }

    @CacheEvict(value = "dashboard", allEntries = true)
    @Scheduled(fixedRate = 86_400_000) //24 hours to dump cache
    public void evictMyCache() {
        System.out.println("Cache Cleared");
    }

    private DashboardResponse populateDashboard() {
        Integer landerCount = landerRepository.getLanderCount();
        int alignedDataPointCount = 0;
        int dataPointCount = 0;

        alignedDataPointCount += alignedCTDRepository.getAlignedCTDCount() + alignedADCPRepository.getAlignedCount();
        dataPointCount += adcpDataRepository.getCountOfData() + albexDataRepository.getCountOfData()
                + ctdDataRepository.findCountOfData() + doDataRepository.findCountOfAllData() + flntuDataRepository.findCountOfAllData()
                + batteryDataRepository.findCountOfData() + beaconDataRepository.findCountOfData() + cameraDataRepository.findCountOfData()
                + sedimentTrapDataRepository.findCountOfData();

        return new DashboardResponse(landerCount, dataPointCount, alignedDataPointCount);
    }

    private Map<Integer, Integer> returnDateDataCount() {
        List<Lander> landers = landerRepository.findAll();
        Map<Lander, LocalDate> landerDates = new HashMap<>();
        Map<Integer, Integer> pointsPerYear = new HashMap<>();

        for (Lander lander : landers) {
            landerDates.put(lander, LocalDate.of(lander.getDeploymentDateAndTime().toLocalDate().getYear(), 1, 1));
        }

        for (Lander lander : landerDates.keySet()) {
            Integer totals = 0;

            if (pointsPerYear.containsKey(lander.getDeploymentDateAndTime().toLocalDate().getYear())) {
                totals = pointsPerYear.get(lander.getDeploymentDateAndTime().toLocalDate().getYear());
            }

            if (lander.getADCPHead() != null) {
                Optional<ProcessedADCPHead> adcpHead = adcpHeadRepository.getADCPHeadByLanderId(lander.getASDBLanderID());

                if (adcpHead.isPresent()) {
                    totals += adcpHead.get().getData().size();
                }
            }
            if (lander.getAlbexHead() != null) {
                Optional<ProcessedAlbexCTDHeader> albexHead = albexCTDHeadRepository.getAlbexHeadsByLanderId(lander.getASDBLanderID());

                if (albexHead.isPresent()) {
                    totals += albexHead.get().getData().size();
                }
            }
            if (lander.getCTDHead() != null) {
                Optional<ProcessedCTDHead> ctdHead = ctdHeadRepository.getCTDHeadsByLanderId(lander.getASDBLanderID());

                if (ctdHead.isPresent()) {
                    totals += ctdHead.get().getData().size();
                }
            }
            if (lander.getDOHead() != null) {
                Optional<ProcessedDOHead> doHead = doHeadRepository.getDOHeadsByLanderID(lander.getASDBLanderID());

                if (doHead.isPresent()) {
                    totals += doHead.get().getData().size();
                }
            }
            if (lander.getFLNTUHead() != null) {
                Optional<ProcessedFLNTUHead> flntuHead = flntuHeadRepository.getFLNTUHeadsByLanderID(lander.getASDBLanderID());

                if (flntuHead.isPresent()) {
                    totals += flntuHead.get().getData().size();
                }
            }
            if (lander.getBatteryHead() != null) {
                Optional<ProcessedBatteryHeader> batteryHead = batteryHeadRepository.getBatteryHeadByLanderId(lander.getASDBLanderID());

                if (batteryHead.isPresent()) {
                    totals += batteryHead.get().getData().size();
                }
            }
            if (lander.getBeaconHead() != null) {
                Optional<ProcessedBeaconHeader> beaconHead = beaconHeadRepository.getBeaconHeadByLanderId(lander.getASDBLanderID());

                if (beaconHead.isPresent()) {
                    totals += beaconHead.get().getData().size();
                }
            }
            if (lander.getCameraHead() != null) {
                Optional<ProcessedCameraHeader> cameraHead = cameraHeadRepository.getCameraHeadByLanderId(lander.getASDBLanderID());

                if (cameraHead.isPresent()) {
                    totals += cameraHead.get().getData().size();
                }
            }
            if (lander.getSedimentTrapHead() != null) {
                Optional<ProcessedSedimentTrapHeader> sedTrapHead = sedimentTrapHeadRepository.getSedimentTrapHeadByLanderId(lander.getASDBLanderID());

                if (sedTrapHead.isPresent()) {
                    totals += sedTrapHead.get().getData().size();
                }
            }

            pointsPerYear.put(landerDates.get(lander).getYear(), totals);
        }

        return pointsPerYear;
    }
}
