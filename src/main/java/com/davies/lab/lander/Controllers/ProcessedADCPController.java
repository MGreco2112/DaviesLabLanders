package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.AlignedADCPRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.BulkADCPUploadRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.ADCP_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.HeaderDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateADCPDataRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.*;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.AlignedADCPData;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedADCPData;
import com.davies.lab.lander.Models.ProcessedADCPHead;
import com.davies.lab.lander.Repositories.AlignedADCPDataRepository;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.davies.lab.lander.Repositories.ProcessedADCPDataRepository;
import com.davies.lab.lander.Repositories.ProcessedADCPHeadRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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
@RequestMapping("/api/processed/adcp")
@EnableCaching
public class ProcessedADCPController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedADCPHeadRepository headRepository;
    @Autowired
    private ProcessedADCPDataRepository repository;
    @Autowired
    private AlignedADCPDataRepository alignedRepository;

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
        res.setAlignedDataPointCount(repository.findAlignedCountByHeadID(head.get().getHeadID()));

        res.setStartTime(repository.findDeploymentDateByHeadID(id));
        res.setEndTime(repository.findRecoveryDateByHeadID(id));

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
                    dataPoint
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<ADCPDataResponse> findDataById(@PathVariable("id") Long id) {
        ProcessedADCPData dataPoint = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ADCPDataResponse res = new ADCPDataResponse(
                dataPoint
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/header/{id}/aligned/true")
    public ResponseEntity<List<AlignedADCPDataResponse>> findAlignedDataByHeader(@PathVariable("id") Long id) {
        List<ProcessedADCPData> data = repository.findDataByHeadAndAlignedStatus(id, true);
        List<AlignedADCPDataResponse> res = new ArrayList<>();

        for (ProcessedADCPData dataPoint : data) {
            res.add(new AlignedADCPDataResponse(dataPoint));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/header/{id}/aligned/false")
    public ResponseEntity<List<ADCPDataResponse>> findUnalignedDataByHeader(@PathVariable("id") Long id) {
        List<ProcessedADCPData> data = repository.findDataByHeadAndAlignedStatus(id, false);
        List<ADCPDataResponse> res = new ArrayList<>();

        for (ProcessedADCPData dataPoint : data) {
            ADCPDataResponse temp = new ADCPDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

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
                        dataPoint
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
                            dataPoint
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/aligned_data/headId/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<List<AlignedADCPDataResponse>> getAlignedDataByRange(@PathVariable("id") Long headId, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<AlignedADCPDataResponse> res = new ArrayList<>();

        List<ProcessedADCPData> data = repository.findDataByHeadAndAlignedByRange(headId, startDate, endDate);

        for (ProcessedADCPData dataPoint : data) {
            res.add(
                    new AlignedADCPDataResponse(dataPoint)
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
    @Cacheable(value = "ADCPCount-Headless")
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
        Optional<ProcessedADCPHead> optionalHead;
        ProcessedADCPHead savedHead;

        if (selLander.isEmpty()) {
            clearADCPCache();

            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            clearADCPCache();

            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getADCPHead() != null) {
            optionalHead = headRepository.findById(selLander.get().getADCPHead().getHeadID());

            if (optionalHead.isEmpty()) {
                clearADCPCache();

                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            savedHead = optionalHead.get();

        } else {
            ProcessedADCPHead dummyHead = new ProcessedADCPHead();
            dummyHead.setLanderID(selLander.get());

            savedHead = headRepository.save(dummyHead);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
            rawData = processData(reader);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            clearADCPCache();

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        if (rawData == null) {
            clearADCPCache();

            return new ResponseEntity<>("Unable to format Data", HttpStatus.BAD_REQUEST);
        }

        try {
            for (ADCP_CSV_Request dataPoint : rawData) {
                repository.save(
                        new ProcessedADCPData(
                                dataPoint,
                                savedHead
                        )
                );
            }
        } catch (Exception e) {
            clearADCPCache();

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        clearADCPCache();

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

    @CacheEvict(value = "ADCPCount-Headless", allEntries = true)
    private void clearADCPCache() {

    }

//    TODO: Create Posting Head method as well, or incorporate Head Data into below method as well

//    TODO: Update Request based on Jane's response to this. Will refactor base Entity Object anyway based on Response
    @PostMapping("/upload/bulk/{head_id}")
    public ResponseEntity<String> uploadBulkData(@PathVariable("head_id") Long id, @RequestBody BulkADCPUploadRequest request) {
        Optional<ProcessedADCPHead> selHead = headRepository.findById(id);

        if (selHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


//        TODO: Refactor this to manage adding the Header to the Obj via ID Long alone
        repository.saveAll(request.getData());

        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @PutMapping("/update/data/{id}")
    public ResponseEntity<String> updateADCPDataByID(@PathVariable("id") Long id, @RequestBody UpdateADCPDataRequest updates) {
        ProcessedADCPData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getDate() != null) {
            selData.setDate(updates.getDate());
        }
        if (updates.getBattery() != null) {
            selData.setBattery(updates.getBattery());
        }
        if (updates.getHeading() != null) {
            selData.setHeading(updates.getHeading());
        }
        if (updates.getPitch() != null) {
            selData.setPitch(updates.getPitch());
        }
        if (updates.getRoll() != null) {
            selData.setRoll(updates.getRoll());
        }
        if (updates.getPressure() != null) {
            selData.setPressure(updates.getPressure());
        }
        if (updates.getTemperature() != null) {
            selData.setTemperature(updates.getTemperature());
        }
        if (updates.getAnalogIn1() != null) {
            selData.setAnalogIn1(updates.getAnalogIn1());
        }
        if (updates.getAnalogIn2() != null) {
            selData.setAnalogIn2(updates.getAnalogIn2());
        }
        if (updates.getAligned() != null) {
            selData.setAligned(updates.getAligned());
        }
        if (updates.getAlignedData() != null) {
            Optional<AlignedADCPData> updatesData = alignedRepository.findAlignedDataByRawDataID(selData.getID());
            if (updatesData.isEmpty()) {
                AlignedADCPData newAlignedData = alignedRepository.save(updates.getAlignedData());
                newAlignedData.setRawData(selData);
                selData.setAlignedData(newAlignedData);
            } else {
                updatesData.get().setVertical_Current_Speed_cm_s(updates.getAlignedData().getVertical_Current_Speed_cm_s());
                updatesData.get().setHorizontal_Current_Speed_cm_s(updates.getAlignedData().getHorizontal_Current_Speed_cm_s());
                updatesData.get().setCurrent_Direction(updates.getAlignedData().getCurrent_Direction());
                alignedRepository.save(updatesData.get());
            }
        }
        if (updates.getHeadID() != null) {
            selData.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @PutMapping("/update/data/bulk_aligned")
    public ResponseEntity<String> updateBulkAlignedData(@RequestBody List<AlignedADCPRequest> alignedData) {
        for (AlignedADCPRequest req : alignedData) {
            UpdateADCPDataRequest newRequest = new UpdateADCPDataRequest();
            newRequest.setAligned(true);
            newRequest.setAlignedData(new AlignedADCPData(req));
            updateADCPDataByID(req.getRawID(), newRequest);
        }

        return new ResponseEntity<>("Posted!", HttpStatus.OK);
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
