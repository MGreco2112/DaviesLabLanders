package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.AlbexCTD_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.HeaderDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateAlbexCTDDataRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.AlbexCTDDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.AlbexCTDHeadResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.DataProgressResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.TotalDataResponse;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedAlbexCTDData;
import com.davies.lab.lander.Models.ProcessedAlbexCTDHeader;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.davies.lab.lander.Repositories.ProcessedAlbexCTDDataRepository;
import com.davies.lab.lander.Repositories.ProcessedAlbexCTDHeaderRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/albex_ctd")
public class ProcessedAlbexCTDController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedAlbexCTDDataRepository repository;
    @Autowired
    private ProcessedAlbexCTDHeaderRepository headerRepository;

    @GetMapping("/headers")
    public List<AlbexCTDHeadResponse> findAllHeads() {
        List<ProcessedAlbexCTDHeader> heads = headerRepository.findAll();
        List<AlbexCTDHeadResponse> res = new ArrayList<>();

        for (ProcessedAlbexCTDHeader head : heads) {
            AlbexCTDHeadResponse temp = new AlbexCTDHeadResponse(
                    head.getHeadID(),
                    head.getLanderID().getASDBLanderID()
            );

            for (ProcessedAlbexCTDData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<AlbexCTDHeadResponse> findHeadWithoutDataById(@PathVariable("id") Long id) {
        Optional<ProcessedAlbexCTDHeader> head = headerRepository.findById(id);
        AlbexCTDHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new AlbexCTDHeadResponse(head.get().getHeadID(), head.get().getLanderID().getASDBLanderID());

        res.setDataPointCount(head.get().getData().size());

        res.setStartTime(repository.findDeploymentDateByHeadID(id));
        res.setEndTime(repository.findRecoveryDateByHeadID(id));

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<AlbexCTDHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedAlbexCTDHeader> head = headerRepository.findById(id);
        AlbexCTDHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new AlbexCTDHeadResponse(head.get().getHeadID(), head.get().getLanderID().getASDBLanderID());

        for (ProcessedAlbexCTDData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data")
    public List<AlbexCTDDataResponse> findAllEntries() {
        List<ProcessedAlbexCTDData> data = repository.findAll();
        List<AlbexCTDDataResponse> res = new ArrayList<>();

        for (ProcessedAlbexCTDData dataPoint : data) {
            AlbexCTDDataResponse temp = new AlbexCTDDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/header/{id}/aligned/true")
    public ResponseEntity<List<AlbexCTDDataResponse>> findAlignedDataByHeader(@PathVariable("id") Long id) {
        List<ProcessedAlbexCTDData> data = repository.findDataByHeadAndAlignedStatus(id,true);
        List<AlbexCTDDataResponse> res = new ArrayList<>();

        for (ProcessedAlbexCTDData dataPoint : data) {
            AlbexCTDDataResponse temp = new AlbexCTDDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/header/{id}/aligned/false")
    public ResponseEntity<List<AlbexCTDDataResponse>> findUnalignedDataByHeader(@PathVariable("id") Long id) {
        List<ProcessedAlbexCTDData> data = repository.findDataByHeadAndAlignedStatus(id,false);
        List<AlbexCTDDataResponse> res = new ArrayList<>();

        for (ProcessedAlbexCTDData dataPoint : data) {
            AlbexCTDDataResponse temp = new AlbexCTDDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<AlbexCTDDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedAlbexCTDData> dataPoint = repository.findById(id);
        AlbexCTDDataResponse res;

        if (dataPoint.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new AlbexCTDDataResponse(
                dataPoint.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<AlbexCTDDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
        List<ProcessedAlbexCTDData> data = repository.findDataByHeadId(id);
        List<AlbexCTDDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedAlbexCTDData dataPoint : data) {
            res.add(new AlbexCTDDataResponse(
                    dataPoint
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<List<AlbexCTDDataResponse>> getDataByRange(@PathVariable("id") Long id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<AlbexCTDDataResponse> res = new ArrayList<>();

        List<ProcessedAlbexCTDData> data = repository.findDataByHeadAndDateRange(id, startDate, endDate);

        for (ProcessedAlbexCTDData dataPoint : data) {
            res.add(new AlbexCTDDataResponse(
                    dataPoint
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/count/{landerID}")
    public ResponseEntity<DataProgressResponse> getDataCountFromHeadID(@PathVariable("landerID") String landerID) {
        Lander selLander = landerRepository.findById(landerID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (selLander.getAlbexHead() != null) {
            ProcessedAlbexCTDHeader selHead = headerRepository.findById(selLander.getAlbexHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            Integer dataCount = repository.findCountByHeadID(selHead.getHeadID());

            return new ResponseEntity<>(new DataProgressResponse(dataCount), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DataProgressResponse(0.00), HttpStatus.OK);
    }

    @PostMapping("/data/count/headless")
    public ResponseEntity<TotalDataResponse> getHeaderlessPercentage(@RequestBody HeaderDataRequest request) {
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
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile")MultipartFile processedFile, @PathVariable("landerId") String LanderID) {

        Optional<Lander> selLander = landerRepository.findById(LanderID);
        List<AlbexCTD_CSV_Request> rawData;
        ProcessedAlbexCTDHeader savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getAlbexHead() != null) {
            savedHead = headerRepository.findById(selLander.get().getAlbexHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            ProcessedAlbexCTDHeader dummyHead = new ProcessedAlbexCTDHeader();
            dummyHead.setLanderID(selLander.get());

            savedHead = headerRepository.save(dummyHead);
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
            for (AlbexCTD_CSV_Request dataElement : rawData) {
                repository.save( new ProcessedAlbexCTDData(
                        dataElement,
                        savedHead
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Posted!", HttpStatus.OK);
    }

    private List<AlbexCTD_CSV_Request> processData(BufferedReader reader) {
        List<AlbexCTD_CSV_Request> dataList;

        try {
            CsvToBean<AlbexCTD_CSV_Request> csvToBean = new CsvToBeanBuilder<AlbexCTD_CSV_Request>(reader)
                    .withType(AlbexCTD_CSV_Request.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            dataList = csvToBean.parse();

            return dataList;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @PutMapping("/update/data/{id}")
    public ResponseEntity<String> updateAlbexCTDDataByID(@PathVariable("id") Long id, @RequestBody UpdateAlbexCTDDataRequest updates) {
        ProcessedAlbexCTDData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getDate() != null) {
            selData.setDate(updates.getDate());
        }
        if (updates.getSalinity() != null) {
            selData.setSalinity(updates.getSalinity());
        }
        if (updates.getTemperature() != null) {
            selData.setTemperature(updates.getTemperature());
        }
        if (updates.getOxygen_ml_l() != null) {
            selData.setOxygen_ml_l(updates.getOxygen_ml_l());
        }
        if (updates.getOxygenSat_percent() != null) {
            selData.setOxygenSat_percent(updates.getOxygenSat_percent());
        }
        if (updates.getTurbidity_ntu() != null) {
            selData.setTurbidity_ntu(updates.getTurbidity_ntu());
        }
        if (updates.getChla_ug_ml() != null) {
            selData.setChla_ug_ml(updates.getChla_ug_ml());
        }
        if (updates.getPressure_db() != null) {
            selData.setPressure_db(updates.getPressure_db());
        }
        if (updates.getFlag() != null) {
            selData.setFlag(updates.getFlag());
        }
        if (updates.getAligned() != null) {
            selData.setAligned(updates.getAligned());
        }
        if (updates.getHeadID() != null) {
            selData.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedAlbexCTDHeader selHead = headerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headerRepository.save(selHead);

        headerRepository.delete(selHead);

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedAlbexCTDData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(selData);

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
