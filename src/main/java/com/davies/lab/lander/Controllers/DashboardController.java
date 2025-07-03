package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.Dashboard.DashboardResponse;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedCTDDataRepository ctdDataRepository;
    @Autowired
    private ProcessedDODataRepository doDataRepository;
    @Autowired
    private ProcessedFLNTUDataRepository flntuDataRepository;
    @Autowired
    private ProcessedAlbexCTDDataRepository albexDataRepository;
    @Autowired
    private ProcessedADCPDataRepository adcpDataRepository;
    @Autowired
    private AlignedADCPDataRepository alignedADCPRepository;
    @Autowired
    private AlignedCTDDataRepository alignedCTDRepository;

    @GetMapping("/populate")
    public ResponseEntity<DashboardResponse> populateDashboard() {
        Integer landerCount = landerRepository.getLanderCount();
        Integer alignedDataPointCount = 0;
        Integer dataPointCount = 0;

        alignedDataPointCount += alignedCTDRepository.getAlignedCTDCount() + alignedADCPRepository.getAlignedCount();
        dataPointCount += adcpDataRepository.getCountOfData() + albexDataRepository.getCountOfData() + ctdDataRepository.findCountOfData() + doDataRepository.findCountOfAllData() + flntuDataRepository.findCountOfAllData();

        return new ResponseEntity<>(new DashboardResponse(landerCount, dataPointCount, alignedDataPointCount), HttpStatus.OK);
    }

    @GetMapping("/dates")
    public ResponseEntity<Object> returnDateDataCount() {
        List<Lander> landers = landerRepository.findAll();
        Set<LocalDate> landerDates = new HashSet<>();
        Map<Integer, Integer> pointsPerYear = new HashMap<>();

        for (Lander lander : landers) {
            landerDates.add(LocalDate.of(lander.getDeploymentDateAndTime().toLocalDate().getYear(), 1, 1));
        }

        for (LocalDate date : landerDates) {
            LocalDate endDate = LocalDate.of(date.getYear(), 12, 31);
            Integer totalPerDate =
                    adcpDataRepository.getDateCount(date, endDate) +
                    albexDataRepository.getDateCount(date, endDate) +
                    ctdDataRepository.getDateCount(date, endDate) +
                    doDataRepository.getDateCount(date, endDate) +
                    flntuDataRepository.getDateCount(date, endDate);

            pointsPerYear.put(date.getYear(), totalPerDate);
        }



        return new ResponseEntity<>(pointsPerYear, HttpStatus.OK);
    }
}
