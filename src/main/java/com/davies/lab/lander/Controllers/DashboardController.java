package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.Dashboard.DashboardResponse;
import com.davies.lab.lander.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
