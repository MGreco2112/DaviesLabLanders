package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.ADCP_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.HeaderDataRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.ADCPDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.ADCPHeadResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.DataProgressResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.TotalDataResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedADCPData;
import com.davies.lab.lander.Models.ProcessedADCPHead;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.davies.lab.lander.Repositories.ProcessedADCPDataRepository;
import com.davies.lab.lander.Repositories.ProcessedADCPHeadRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.tags.NestedPathTag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/adcp")
public class ProcessedADCPController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedADCPHeadRepository headRepository;
    @Autowired
    private ProcessedADCPDataRepository repository;

    @GetMapping("/headers")
    public List<ADCPHeadResponse> findAllHeads() {
        List<ProcessedADCPHead> heads = headRepository.findAll();
        List<ADCPHeadResponse> res = new ArrayList<>();

        for (ProcessedADCPHead head : heads) {
            ADCPHeadResponse temp = new ADCPHeadResponse(
                    head.getHeadID(),
                    head.getLanderID().getASDBLanderID()
            );

            for (ProcessedADCPData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<ADCPHeadResponse> findHeadWithoutDataById(@PathVariable("id") Long id) {
        Optional<ProcessedADCPHead> head = headRepository.findById(id);
        ADCPHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new ADCPHeadResponse(head.get().getHeadID(), head.get().getLanderID().getASDBLanderID());

        res.setDataPointCount(head.get().getData().size());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<ADCPHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedADCPHead> head = headRepository.findById(id);
        ADCPHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new ADCPHeadResponse(head.get().getHeadID(), head.get().getLanderID().getASDBLanderID());

        for (ProcessedADCPData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data")
    public List<ADCPDataResponse> findAllEntries() {
        List<ProcessedADCPData> data = repository.findAll();
        List<ADCPDataResponse> res = new ArrayList<>();

        for (ProcessedADCPData dataPoint : data) {
            ADCPDataResponse temp = new ADCPDataResponse(
                    dataPoint.getID(),
                    dataPoint.getDate(),
                    dataPoint.getBattery(),
                    dataPoint.getHeading(),
                    dataPoint.getPitch(),
                    dataPoint.getRoll(),
                    dataPoint.getPressure(),
                    dataPoint.getTemperature(),
                    dataPoint.getAnalogIn1(),
                    dataPoint.getAnalogIn2(),
                    dataPoint.getSpeed1_1_0m(),
                    dataPoint.getSpeed2_1_5m(),
                    dataPoint.getSpeed3_2_0m(),
                    dataPoint.getSpeed4_2_5m(),
                    dataPoint.getSpeed5_3_0m(),
                    dataPoint.getSpeed6_3_5m(),
                    dataPoint.getSpeed7_4_0m(),
                    dataPoint.getSpeed8_4_5m(),
                    dataPoint.getSpeed9_5_0m(),
                    dataPoint.getSpeed10_5_5m(),
                    dataPoint.getSpeed11_6_0m(),
                    dataPoint.getSpeed12_6_5m(),
                    dataPoint.getSpeed13_7_0m(),
                    dataPoint.getSpeed14_7_5m(),
                    dataPoint.getSpeed15_8_0m(),
                    dataPoint.getSpeed16_8_5m(),
                    dataPoint.getSpeed17_9_0m(),
                    dataPoint.getSpeed18_9_5m(),
                    dataPoint.getSpeed19_10_0m(),
                    dataPoint.getSpeed20_10_5m(),
                    dataPoint.getDir1_1_0m(),
                    dataPoint.getDir2_1_5m(),
                    dataPoint.getDir3_2_0m(),
                    dataPoint.getDir4_2_5m(),
                    dataPoint.getDir5_3_0m(),
                    dataPoint.getDir6_3_5m(),
                    dataPoint.getDir7_4_0m(),
                    dataPoint.getDir8_4_5m(),
                    dataPoint.getDir9_5_0m(),
                    dataPoint.getDir10_5_5m(),
                    dataPoint.getDir11_6_0m(),
                    dataPoint.getDir12_6_5m(),
                    dataPoint.getDir13_7_0m(),
                    dataPoint.getDir14_7_5m(),
                    dataPoint.getDir15_8_0m(),
                    dataPoint.getDir16_8_5m(),
                    dataPoint.getDir17_9_0m(),
                    dataPoint.getDir18_9_5m(),
                    dataPoint.getDir19_10_0m(),
                    dataPoint.getDir20_10_5m(),
                    dataPoint.getHeadID().getHeadID()
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<ADCPDataResponse> findDataById(@PathVariable("id") Long id) {
        ProcessedADCPData dataPoint = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ADCPDataResponse res = new ADCPDataResponse(
                dataPoint.getID(),
                dataPoint.getDate(),
                dataPoint.getBattery(),
                dataPoint.getHeading(),
                dataPoint.getPitch(),
                dataPoint.getRoll(),
                dataPoint.getPressure(),
                dataPoint.getTemperature(),
                dataPoint.getAnalogIn1(),
                dataPoint.getAnalogIn2(),
                dataPoint.getSpeed1_1_0m(),
                dataPoint.getSpeed2_1_5m(),
                dataPoint.getSpeed3_2_0m(),
                dataPoint.getSpeed4_2_5m(),
                dataPoint.getSpeed5_3_0m(),
                dataPoint.getSpeed6_3_5m(),
                dataPoint.getSpeed7_4_0m(),
                dataPoint.getSpeed8_4_5m(),
                dataPoint.getSpeed9_5_0m(),
                dataPoint.getSpeed10_5_5m(),
                dataPoint.getSpeed11_6_0m(),
                dataPoint.getSpeed12_6_5m(),
                dataPoint.getSpeed13_7_0m(),
                dataPoint.getSpeed14_7_5m(),
                dataPoint.getSpeed15_8_0m(),
                dataPoint.getSpeed16_8_5m(),
                dataPoint.getSpeed17_9_0m(),
                dataPoint.getSpeed18_9_5m(),
                dataPoint.getSpeed19_10_0m(),
                dataPoint.getSpeed20_10_5m(),
                dataPoint.getDir1_1_0m(),
                dataPoint.getDir2_1_5m(),
                dataPoint.getDir3_2_0m(),
                dataPoint.getDir4_2_5m(),
                dataPoint.getDir5_3_0m(),
                dataPoint.getDir6_3_5m(),
                dataPoint.getDir7_4_0m(),
                dataPoint.getDir8_4_5m(),
                dataPoint.getDir9_5_0m(),
                dataPoint.getDir10_5_5m(),
                dataPoint.getDir11_6_0m(),
                dataPoint.getDir12_6_5m(),
                dataPoint.getDir13_7_0m(),
                dataPoint.getDir14_7_5m(),
                dataPoint.getDir15_8_0m(),
                dataPoint.getDir16_8_5m(),
                dataPoint.getDir17_9_0m(),
                dataPoint.getDir18_9_5m(),
                dataPoint.getDir19_10_0m(),
                dataPoint.getDir20_10_5m(),
                dataPoint.getHeadID().getHeadID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<ADCPDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
        List<ProcessedADCPData> data = repository.findDataByHeadId(id);
        List<ADCPDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedADCPData dataPoint : data) {
            res.add(
                    new ADCPDataResponse(
                        dataPoint.getID(),
                        dataPoint.getDate(),
                        dataPoint.getBattery(),
                        dataPoint.getHeading(),
                        dataPoint.getPitch(),
                        dataPoint.getRoll(),
                        dataPoint.getPressure(),
                        dataPoint.getTemperature(),
                        dataPoint.getAnalogIn1(),
                        dataPoint.getAnalogIn2(),
                        dataPoint.getSpeed1_1_0m(),
                        dataPoint.getSpeed2_1_5m(),
                        dataPoint.getSpeed3_2_0m(),
                        dataPoint.getSpeed4_2_5m(),
                        dataPoint.getSpeed5_3_0m(),
                        dataPoint.getSpeed6_3_5m(),
                        dataPoint.getSpeed7_4_0m(),
                        dataPoint.getSpeed8_4_5m(),
                        dataPoint.getSpeed9_5_0m(),
                        dataPoint.getSpeed10_5_5m(),
                        dataPoint.getSpeed11_6_0m(),
                        dataPoint.getSpeed12_6_5m(),
                        dataPoint.getSpeed13_7_0m(),
                        dataPoint.getSpeed14_7_5m(),
                        dataPoint.getSpeed15_8_0m(),
                        dataPoint.getSpeed16_8_5m(),
                        dataPoint.getSpeed17_9_0m(),
                        dataPoint.getSpeed18_9_5m(),
                        dataPoint.getSpeed19_10_0m(),
                        dataPoint.getSpeed20_10_5m(),
                        dataPoint.getDir1_1_0m(),
                        dataPoint.getDir2_1_5m(),
                        dataPoint.getDir3_2_0m(),
                        dataPoint.getDir4_2_5m(),
                        dataPoint.getDir5_3_0m(),
                        dataPoint.getDir6_3_5m(),
                        dataPoint.getDir7_4_0m(),
                        dataPoint.getDir8_4_5m(),
                        dataPoint.getDir9_5_0m(),
                        dataPoint.getDir10_5_5m(),
                        dataPoint.getDir11_6_0m(),
                        dataPoint.getDir12_6_5m(),
                        dataPoint.getDir13_7_0m(),
                        dataPoint.getDir14_7_5m(),
                        dataPoint.getDir15_8_0m(),
                        dataPoint.getDir16_8_5m(),
                        dataPoint.getDir17_9_0m(),
                        dataPoint.getDir18_9_5m(),
                        dataPoint.getDir19_10_0m(),
                        dataPoint.getDir20_10_5m(),
                        dataPoint.getHeadID().getHeadID()
                    )
            );
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<List<ADCPDataResponse>> getDataByRange(@PathVariable("id") Long id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<ADCPDataResponse> res = new ArrayList<>();

        List<ProcessedADCPData> data = repository.findDataByHeadAndDateRange(id, startDate, endDate);

        for (ProcessedADCPData dataPoint : data) {
            res.add(
                    new ADCPDataResponse(
                            dataPoint.getID(),
                            dataPoint.getDate(),
                            dataPoint.getBattery(),
                            dataPoint.getHeading(),
                            dataPoint.getPitch(),
                            dataPoint.getRoll(),
                            dataPoint.getPressure(),
                            dataPoint.getTemperature(),
                            dataPoint.getAnalogIn1(),
                            dataPoint.getAnalogIn2(),
                            dataPoint.getSpeed1_1_0m(),
                            dataPoint.getSpeed2_1_5m(),
                            dataPoint.getSpeed3_2_0m(),
                            dataPoint.getSpeed4_2_5m(),
                            dataPoint.getSpeed5_3_0m(),
                            dataPoint.getSpeed6_3_5m(),
                            dataPoint.getSpeed7_4_0m(),
                            dataPoint.getSpeed8_4_5m(),
                            dataPoint.getSpeed9_5_0m(),
                            dataPoint.getSpeed10_5_5m(),
                            dataPoint.getSpeed11_6_0m(),
                            dataPoint.getSpeed12_6_5m(),
                            dataPoint.getSpeed13_7_0m(),
                            dataPoint.getSpeed14_7_5m(),
                            dataPoint.getSpeed15_8_0m(),
                            dataPoint.getSpeed16_8_5m(),
                            dataPoint.getSpeed17_9_0m(),
                            dataPoint.getSpeed18_9_5m(),
                            dataPoint.getSpeed19_10_0m(),
                            dataPoint.getSpeed20_10_5m(),
                            dataPoint.getDir1_1_0m(),
                            dataPoint.getDir2_1_5m(),
                            dataPoint.getDir3_2_0m(),
                            dataPoint.getDir4_2_5m(),
                            dataPoint.getDir5_3_0m(),
                            dataPoint.getDir6_3_5m(),
                            dataPoint.getDir7_4_0m(),
                            dataPoint.getDir8_4_5m(),
                            dataPoint.getDir9_5_0m(),
                            dataPoint.getDir10_5_5m(),
                            dataPoint.getDir11_6_0m(),
                            dataPoint.getDir12_6_5m(),
                            dataPoint.getDir13_7_0m(),
                            dataPoint.getDir14_7_5m(),
                            dataPoint.getDir15_8_0m(),
                            dataPoint.getDir16_8_5m(),
                            dataPoint.getDir17_9_0m(),
                            dataPoint.getDir18_9_5m(),
                            dataPoint.getDir19_10_0m(),
                            dataPoint.getDir20_10_5m(),
                            dataPoint.getHeadID().getHeadID()
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/count/{landerID}")
    public ResponseEntity<DataProgressResponse> getDataCountFromHeadID(@PathVariable("landerID") String landerID) {
        Lander selLander = landerRepository.findById(landerID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (selLander.getADCPHead() != null) {
            ProcessedADCPHead selHead = headRepository.findById(selLander.getADCPHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            Integer dataCount = repository.findCountByHeadID(selHead.getHeadID());

            return new ResponseEntity<>(new DataProgressResponse(dataCount), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DataProgressResponse(0.00), HttpStatus.OK);
    }

    @PostMapping("/data/count/headless")
    public ResponseEntity<TotalDataResponse> getHeaderlessResponse(@RequestBody HeaderDataRequest request) {
        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();
        int burstCount = request.getBurstCnt();
        int burstTime = request.getBurstTime();

        double hoursBetween = ChronoUnit.HOURS.between(startTime, endTime);

        hoursBetween *= (60.0 / burstTime);

        hoursBetween *= burstCount;

        return new ResponseEntity<>(new TotalDataResponse((int) hoursBetween), HttpStatus.OK);
    }

    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile")MultipartFile processedFile, @PathVariable("landerId") String landerId) {

        Optional<Lander> selLander = landerRepository.findById(landerId);
        List<ADCP_CSV_Request> rawData;
        ProcessedADCPHead savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getADCPHead() != null) {
            savedHead = headRepository.findById(selLander.get().getADCPHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            ProcessedADCPHead dummyHead = new ProcessedADCPHead();
            dummyHead.setLanderID(selLander.get());

            savedHead = headRepository.save(dummyHead);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
            rawData = processData(reader);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        if (rawData == null) {
            return new ResponseEntity<>("Unable to format Data", HttpStatus.BAD_REQUEST);
        }

        try {
            for (ADCP_CSV_Request dataPoint : rawData) {
                repository.save(
                        new ProcessedADCPData(
                                StringFormatting.formatDataDateString(dataPoint.getDate()),
                                dataPoint.getBattery(),
                                dataPoint.getHeading(),
                                dataPoint.getPitch(),
                                dataPoint.getRoll(),
                                dataPoint.getPressure(),
                                dataPoint.getTemperature(),
                                dataPoint.getAnalogIn1(),
                                dataPoint.getAnalogIn2(),
                                dataPoint.getSpeed1_1_0m(),
                                dataPoint.getSpeed2_1_5m(),
                                dataPoint.getSpeed3_2_0m(),
                                dataPoint.getSpeed4_2_5m(),
                                dataPoint.getSpeed5_3_0m(),
                                dataPoint.getSpeed6_3_5m(),
                                dataPoint.getSpeed7_4_0m(),
                                dataPoint.getSpeed8_4_5m(),
                                dataPoint.getSpeed9_5_0m(),
                                dataPoint.getSpeed10_5_5m(),
                                dataPoint.getSpeed11_6_0m(),
                                dataPoint.getSpeed12_6_5m(),
                                dataPoint.getSpeed13_7_0m(),
                                dataPoint.getSpeed14_7_5m(),
                                dataPoint.getSpeed15_8_0m(),
                                dataPoint.getSpeed16_8_5m(),
                                dataPoint.getSpeed17_9_0m(),
                                dataPoint.getSpeed18_9_5m(),
                                dataPoint.getSpeed19_10_0m(),
                                dataPoint.getSpeed20_10_5m(),
                                dataPoint.getDir1_1_0m(),
                                dataPoint.getDir2_1_5m(),
                                dataPoint.getDir3_2_0m(),
                                dataPoint.getDir4_2_5m(),
                                dataPoint.getDir5_3_0m(),
                                dataPoint.getDir6_3_5m(),
                                dataPoint.getDir7_4_0m(),
                                dataPoint.getDir8_4_5m(),
                                dataPoint.getDir9_5_0m(),
                                dataPoint.getDir10_5_5m(),
                                dataPoint.getDir11_6_0m(),
                                dataPoint.getDir12_6_5m(),
                                dataPoint.getDir13_7_0m(),
                                dataPoint.getDir14_7_5m(),
                                dataPoint.getDir15_8_0m(),
                                dataPoint.getDir16_8_5m(),
                                dataPoint.getDir17_9_0m(),
                                dataPoint.getDir18_9_5m(),
                                dataPoint.getDir19_10_0m(),
                                dataPoint.getDir20_10_5m(),
                                savedHead
                        )
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Posted!", HttpStatus.OK);
    }

    private List<ADCP_CSV_Request> processData(BufferedReader reader) {
        List<ADCP_CSV_Request> dataList;

        try {
            CsvToBean<ADCP_CSV_Request> csvToBean = new CsvToBeanBuilder<ADCP_CSV_Request>(reader)
                    .withType(ADCP_CSV_Request.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            dataList = csvToBean.parse();
            return dataList;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedADCPHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        headRepository.delete(selHead);

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedADCPData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(selData);

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
