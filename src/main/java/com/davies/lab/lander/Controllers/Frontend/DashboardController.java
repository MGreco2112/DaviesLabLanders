package com.davies.lab.lander.Controllers.Frontend;

import com.davies.lab.lander.FormattedModels.ResponseBody.Dashboard.CompletedDashboard;
import com.davies.lab.lander.FormattedModels.ResponseBody.Dashboard.DashboardResponse;
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
        dataPointCount += adcpDataRepository.getCountOfData() + albexDataRepository.getCountOfData() + ctdDataRepository.findCountOfData() + doDataRepository.findCountOfAllData() + flntuDataRepository.findCountOfAllData();

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
                totals += adcpHeadRepository.getADCPHeadByLanderId(lander.getASDBLanderID()).get().getData().size();
            }
            if (lander.getAlbexHead() != null) {
                totals += albexCTDHeadRepository.getAlbexHeadsByLanderId(lander.getASDBLanderID()).get().getData().size();
            }
            if (lander.getCTDHead() != null) {
                totals += ctdHeadRepository.getCTDHeadsByLanderId(lander.getASDBLanderID()).get().getData().size();
            }
            if (lander.getDOHead() != null) {
                totals += doHeadRepository.getDOHeadsByLanderID(lander.getASDBLanderID()).get().getData().size();
            }
            if (lander.getFLNTUHead() != null) {
                totals += flntuHeadRepository.getFLNTUHeadsByLanderID(lander.getASDBLanderID()).get().getData().size();
            }

            pointsPerYear.put(landerDates.get(lander).getYear(), totals);
        }

        return pointsPerYear;
    }
}
