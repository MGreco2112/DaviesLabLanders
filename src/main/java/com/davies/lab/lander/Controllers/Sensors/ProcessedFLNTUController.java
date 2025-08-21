package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.Controllers.LanderController;
import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.FLNTU_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.HeaderDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateFLNTUDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateFLNTUHeaderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.DataProgressResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.FLNTUDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.FLNTUHeadResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.TotalDataResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.Data.ProcessedFLNTUData;
import com.davies.lab.lander.Models.Headers.ProcessedFLNTUHead;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.davies.lab.lander.Repositories.Data.ProcessedFLNTUDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedFLNTUHeadRepository;
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
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/flntu")
@EnableCaching
public class ProcessedFLNTUController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedFLNTUDataRepository repository;
    @Autowired
    private ProcessedFLNTUHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;
    @Autowired
    private LanderController landerController;

    //Header Routes
    @GetMapping("/headers")
    public List<FLNTUHeadResponse> findAllHeads() {
        List<ProcessedFLNTUHead> heads = headRepository.findAll();
        List<FLNTUHeadResponse> res = new ArrayList<>();

        for (ProcessedFLNTUHead selHead : heads) {
            FLNTUHeadResponse temp = new FLNTUHeadResponse(
                    selHead
            );

            for (ProcessedFLNTUData selData : selHead.getData()) {
                temp.createFLNTUDataResponse(selData);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<FLNTUHeadResponse> findHeadWithoutDataById(@PathVariable("id") Long id) {
        Optional<ProcessedFLNTUHead> head = headRepository.findById(id);
        FLNTUHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new FLNTUHeadResponse(
                head.get()
        );

        res.setDataPointCount(head.get().getData().size());

        if (res.getStartTime() == null && res.getDataPointCount() > 0) {
            ProcessedFLNTUData firstData = repository.findFirstDataPointInHead(head.get().getHeadID());
            if (firstData != null) {
                res.setStartTime(firstData.getDate());
            }
        }

        if (res.getEndTime() == null && res.getDataPointCount() > 0) {
            ProcessedFLNTUData lastData = repository.findLastDataPointInHead(head.get().getHeadID());
            if (lastData != null) {
                res.setEndTime(lastData.getDate());
            }
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<FLNTUHeadResponse> findHeadByID(@PathVariable("id") Long id) {
        Optional<ProcessedFLNTUHead> head = headRepository.findById(id);
        FLNTUHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new FLNTUHeadResponse(
                head.get()
        );

        for (ProcessedFLNTUData data : head.get().getData()) {
            res.createFLNTUDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<FLNTUDataResponse> findAllEntries() {
        List<ProcessedFLNTUData> data = repository.findAll();
        List<FLNTUDataResponse> res = new ArrayList<>();

        for (ProcessedFLNTUData selData : data) {
            FLNTUDataResponse temp = new FLNTUDataResponse(
                    selData
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<FLNTUDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedFLNTUData> data = repository.findById(id);
        FLNTUDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new FLNTUDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/header/{id}/aligned/true")
    public ResponseEntity<List<FLNTUDataResponse>> findAlignedDataByHeader(@PathVariable("id") Long id) {
        List<ProcessedFLNTUData> data = repository.findDataByHeadAndAlignedStatus(id, true);
        List<FLNTUDataResponse> res = new ArrayList<>();

        for (ProcessedFLNTUData selData : data) {
            FLNTUDataResponse temp = new FLNTUDataResponse(
                    selData
            );

            res.add(temp);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/header/{id}/aligned/false")
    public ResponseEntity<List<FLNTUDataResponse>> findUnalignedDataByHeader(@PathVariable("id") Long id) {
        List<ProcessedFLNTUData> data = repository.findDataByHeadAndAlignedStatus(id, false);
        List<FLNTUDataResponse> res = new ArrayList<>();

        for (ProcessedFLNTUData selData : data) {
            FLNTUDataResponse temp = new FLNTUDataResponse(
                    selData
            );

            res.add(temp);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<FLNTUDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
        List<ProcessedFLNTUData> data = repository.findDataFromHeadId(id);
        List<FLNTUDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedFLNTUData elem : data) {
            res.add(new FLNTUDataResponse(
                    elem
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<List<FLNTUDataResponse>> getDataByRange(@PathVariable("id") Long id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<FLNTUDataResponse> res = new ArrayList<>();

        List<ProcessedFLNTUData> data = repository.findDataByHeadAndDateRange(id, startDate, endDate);

        for (ProcessedFLNTUData selData : data) {
            res.add(
                    new FLNTUDataResponse(
                            selData
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/count/{landerID}")
    public ResponseEntity<DataProgressResponse> getDataCountFromHeadID(@PathVariable("landerID") String landerID) {
        Lander selLander = landerRepository.findById(landerID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (selLander.getFLNTUHead() != null) {

            ProcessedFLNTUHead selHead = headRepository.findById(selLander.getFLNTUHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            Integer dataCount = repository.findCountByHeadID(selHead.getHeadID());

            if (selHead.getStartTime() != null && selHead.getEndTime() != null && selHead.getBurstCnt() != null && selHead.getBurstTime() != null) {
                double hoursBetween = calculateDataSize(selHead);

                return new ResponseEntity<>(new DataProgressResponse( (dataCount / hoursBetween) ), HttpStatus.OK);
            }

            return new ResponseEntity<>(new DataProgressResponse(dataCount), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DataProgressResponse(0.00), HttpStatus.OK);
    }

    @Cacheable(value = "FLNTUCount")
    private double calculateDataSize(ProcessedFLNTUHead selHead) {
        LocalDateTime startTime = selHead.getStartTime();
        LocalDateTime endTime = selHead.getEndTime();
        int burstCount = selHead.getBurstCnt();
        int burstTime = selHead.getBurstTime();

        double hoursBetween = ChronoUnit.HOURS.between(startTime, endTime);

        hoursBetween *= (60.0 / burstTime);

        hoursBetween *= burstCount;

        return hoursBetween;
    }

    @PostMapping("/data/count/headless")
    @Cacheable(value = "FLNTUCount-Headless")
    public ResponseEntity<TotalDataResponse> getHeaderlessPercentage(@RequestBody HeaderDataRequest request) {
        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();
        int burstCount = request.getBurstCnt();
        int burstTime = request.getBurstTime();

        double hoursBetween = ChronoUnit.HOURS.between(startTime, endTime);

        hoursBetween *= (60.0 / burstTime);

        hoursBetween *= burstCount;

        return new ResponseEntity<>(new TotalDataResponse((int) hoursBetween ), HttpStatus.OK);
    }

    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("landerId") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);
        List<FLNTU_CSV_Request> rawData = null;
        Optional<ProcessedFLNTUHead> optionalHead;
        ProcessedFLNTUHead savedHead;

        if (selLander.isEmpty()) {

            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {

            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getFLNTUHead() != null) {
            optionalHead = headRepository.findById(selLander.get().getFLNTUHead().getHeadID());

            if (optionalHead.isEmpty()) {

                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            savedHead = optionalHead.get();
        } else {
            ProcessedFLNTUHead dummyHead = new ProcessedFLNTUHead();
            dummyHead.setLanderID(selLander.get());

            savedHead = headRepository.save(dummyHead);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
            rawData = processData(reader);
        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
        }

        if (rawData == null) {

            return new ResponseEntity<>("Unable to format Data", HttpStatus.BAD_REQUEST);
        }

        try {
            for (FLNTU_CSV_Request dataElement : rawData) {
                repository.save(new ProcessedFLNTUData(
                        dataElement,
                        savedHead
                ));
            }
        } catch (Exception e) {
            clearFLNTUCache();

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        clearFLNTUCache();
        dashboardController.evictMyCache();
        landerController.evictLandersCache();

        return new ResponseEntity<>("Posted!", HttpStatus.OK);
    }

    @PostMapping("/upload_csv/header/{landerID}")
    public ResponseEntity<String> uploadProcessedHeader(@RequestParam("processedHead") MultipartFile processedHead, @PathVariable("landerID") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);

        if (selLander.isEmpty()) {
            clearFLNTUCache();

            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedHead.isEmpty()) {
            clearFLNTUCache();

            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getFLNTUHead() == null) {
            ProcessedFLNTUHead newHead = new ProcessedFLNTUHead();
            newHead.setLanderID(selLander.get());
            ProcessedFLNTUHead savedHead = headRepository.save(newHead);
            selLander.get().setFLNTUHead(savedHead);
            landerRepository.save(selLander.get());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedHead.getInputStream()))) {
            String temp = "";
            List<String> output = new ArrayList<>();
            List<String> keyNames = new ArrayList<>();
            Map<String, String> valuesMap = new HashMap<>();

            while (!Objects.equals(temp, "[Item]")) {
                temp = reader.readLine();

                if (temp.charAt(0) != '/' && temp.charAt(0) != '[') {
                    output.add(temp);
                }
            }

            for (String datapoint : output) {
                String[] hold = datapoint.split("=");
                keyNames.add(hold[0]);

                valuesMap.put(hold[0], hold[1].stripTrailing());
            }

            UpdateFLNTUHeaderRequest updates = new UpdateFLNTUHeaderRequest(
                    valuesMap.get("SondeName"),
                    valuesMap.get("SondeNo"),
                    valuesMap.get("SensorType"),
                    Integer.parseInt(valuesMap.get("Channel")),
                    Integer.parseInt(valuesMap.get("DelayTime")),
                    Integer.parseInt(valuesMap.get("PreHeat")),
                    Integer.parseInt(valuesMap.get("MeasMode")),
                    Integer.parseInt(valuesMap.get("BurstTime")),
                    Integer.parseInt(valuesMap.get("BurstCnt")),
                    Integer.parseInt(valuesMap.get("Interval")),
                    Integer.parseInt(valuesMap.get("WiperInterval")),
                    Integer.parseInt(valuesMap.get("SampleCnt")),
                    StringFormatting.formatDateString(valuesMap.get("StartTime")),
                    StringFormatting.formatDateString(valuesMap.get("EndTime")),
                    Integer.parseInt(valuesMap.get("CHLA")),
                    Integer.parseInt(valuesMap.get("CHLB")),
                    StringFormatting.formatCoefDateString(valuesMap.get("CoefDate")),
                    Double.parseDouble(valuesMap.get("Ch1").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch2").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch3").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch4").split(",")[0]),
                    Integer.parseInt(valuesMap.get("BuzzerEN")),
                    Integer.parseInt(valuesMap.get("BuzzerInterval")),
                    valuesMap.get("COMMENT"),
                    valuesMap.get("SensorType2"),
                    Integer.parseInt(valuesMap.get("BuzzerNumber")),
                    selLander.get(),
                    selLander.get().getFLNTUHead().getData()
            );

            updateFLNTUHeader(selLander.get().getFLNTUHead().getHeadID(), updates);

            clearFLNTUCache();
            dashboardController.evictMyCache();
            landerController.evictLandersCache();

            return new ResponseEntity<>("Posted", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            clearFLNTUCache();

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload_csv/combined/{lander_id}")
    public ResponseEntity<String> processCompleteCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("lander_id") String landerId) {
        Optional<Lander> selLander = landerRepository.findById(landerId);

        if (selLander.isEmpty()) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Lander lander = selLander.get();


        if (processedFile.isEmpty()) {

            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (lander.getFLNTUHead() != null) {

            return new ResponseEntity<>("Header already present", HttpStatus.BAD_REQUEST);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
            String temp = "";
            List<String> output = new ArrayList<>();
            Map<String, String> valuesMap = new HashMap<>();

            while (!Objects.equals(temp, "[Item]")) {
                temp = reader.readLine();
                if (temp.charAt(0) != '/' && temp.charAt(0) != '[') {
                    output.add(temp);
                }
            }

            for (String datapoint : output) {
                String[] hold = datapoint.split("=");

                valuesMap.put(hold[0], hold[1].stripTrailing());
            }

            ProcessedFLNTUHead flntuHead = new ProcessedFLNTUHead(
                    valuesMap.get("SondeName"),
                    valuesMap.get("SondeNo"),
                    valuesMap.get("SensorType"),
                    Integer.parseInt(valuesMap.get("Channel")),
                    Integer.parseInt(valuesMap.get("DelayTime")),
                    Integer.parseInt(valuesMap.get("PreHeat")),
                    Integer.parseInt(valuesMap.get("MeasMode")),
                    Integer.parseInt(valuesMap.get("BurstTime")),
                    Integer.parseInt(valuesMap.get("BurstCnt")),
                    Integer.parseInt(valuesMap.get("Interval")),
                    Integer.parseInt(valuesMap.get("WiperInterval")),
                    Integer.parseInt(valuesMap.get("SampleCnt")),
                    StringFormatting.formatDateString(valuesMap.get("StartTime")),
                    StringFormatting.formatDateString(valuesMap.get("EndTime")),
                    Integer.parseInt(valuesMap.get("CHLA")),
                    Integer.parseInt(valuesMap.get("CHLB")),
                    StringFormatting.formatCoefDateString(valuesMap.get("CoefDate")),
                    Double.parseDouble(valuesMap.get("Ch1").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch2").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch3").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch4").split(",")[0]),
                    Integer.parseInt(valuesMap.get("BuzzerEN")),
                    Integer.parseInt(valuesMap.get("BuzzerInterval")),
                    valuesMap.get("COMMENT"),
                    valuesMap.get("SensorType2"),
                    Integer.parseInt(valuesMap.get("BuzzerNumber")),
                    lander
            );

            ProcessedFLNTUHead newHead = headRepository.save(flntuHead);

            List<FLNTU_CSV_Request> outputData = processData(reader);

            if (outputData == null) {

                return new ResponseEntity<>("Could not generate Data from File", HttpStatus.BAD_REQUEST);
            }

            for (FLNTU_CSV_Request inputDataPoint : outputData) {
                ProcessedFLNTUData newData = new ProcessedFLNTUData (
                        inputDataPoint,
                        newHead
                );

                repository.save(newData);

            }

            clearFLNTUCache();

            dashboardController.evictMyCache();
            landerController.evictLandersCache();

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<FLNTU_CSV_Request> processData(BufferedReader reader) {
        List<FLNTU_CSV_Request> dataList;

        try {
            CsvToBean<FLNTU_CSV_Request> csvToBean = new CsvToBeanBuilder<FLNTU_CSV_Request>(reader)
                    .withType(FLNTU_CSV_Request.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            dataList = csvToBean.parse();

            return dataList;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @CacheEvict(value = {"FLNTUCount", "FLNTUCount-Headless"}, allEntries = true)
    private void clearFLNTUCache() {

    }

    @PutMapping("/update/header/{id}")
    public ResponseEntity<String> updateFLNTUHeader(@PathVariable("id") Long id, @RequestBody UpdateFLNTUHeaderRequest updates) {
        ProcessedFLNTUHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getSondeName() != null) {
            selHead.setSondeName(updates.getSondeName());
        }
        if (updates.getSondeNo() != null) {
            selHead.setSondeNo(updates.getSondeNo());
        }
        if (updates.getSensorType() != null) {
            selHead.setSensorType(updates.getSensorType());
        }
        if (updates.getChannel() != null) {
            selHead.setChannel(updates.getChannel());
        }
        if (updates.getDelayTime() != null) {
            selHead.setDelayTime(updates.getDelayTime());
        }
        if (updates.getPreHeat() != null) {
            selHead.setPreHeat(updates.getPreHeat());
        }
        if (updates.getMeasMode() != null) {
            selHead.setMeasMode(updates.getMeasMode());
        }
        if (updates.getBurstTime() != null) {
            selHead.setBurstTime(updates.getBurstTime());
        }
        if (updates.getBurstCnt() != null) {
            selHead.setBurstCnt(updates.getBurstCnt());
        }
        if (updates.getIntervalData() != null) {
            selHead.setIntervalData(updates.getIntervalData());
        }
        if (updates.getWiperInterval() != null) {
            selHead.setWiperInterval(updates.getWiperInterval());
        }
        if (updates.getSampleCnt() != null) {
            selHead.setSampleCnt(updates.getSampleCnt());
        }
        if (updates.getStartTime() != null) {
            selHead.setStartTime(updates.getStartTime());
        }
        if (updates.getEndTime() != null) {
            selHead.setEndTime(updates.getEndTime());
        }
        if (updates.getCHLA() != null) {
            selHead.setCHLA(updates.getCHLA());
        }
        if (updates.getCHLB() != null) {
            selHead.setCHLB(updates.getCHLB());
        }
        if (updates.getCoefDate() != null) {
            selHead.setCoefDate(updates.getCoefDate());
        }
        if (updates.getCh1() != null) {
            selHead.setCh1(updates.getCh1());
        }
        if (updates.getCh2() != null) {
            selHead.setCh2(updates.getCh2());
        }
        if (updates.getCh3() != null) {
            selHead.setCh3(updates.getCh3());
        }
        if (updates.getCh4() != null) {
            selHead.setCh4(updates.getCh4());
        }
        if (updates.getBuzzerEN() != null) {
            selHead.setBuzzerEN(updates.getBuzzerEN());
        }
        if (updates.getBuzzerInterval() != null) {
            selHead.setBuzzerInterval(updates.getBuzzerInterval());
        }
        if (updates.getComment() != null) {
            selHead.setComment(updates.getComment());
        }
        if (updates.getSensorType2() != null) {
            selHead.setSensorType2(updates.getSensorType2());
        }
        if (updates.getBuzzerNumber() != null) {
            selHead.setBuzzerNumber(updates.getBuzzerNumber());
        }
        if (updates.getLanderID() != null) {
            selHead.setLanderID(updates.getLanderID());
        }
        if (updates.getData() != null) {
            selHead.setData(updates.getData());
        }

        headRepository.save(selHead);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @PutMapping("/update/data/{id}")
    public ResponseEntity<String> updateFLNTUDataByID(@PathVariable("id") Long id, @RequestBody UpdateFLNTUDataRequest updates) {
        ProcessedFLNTUData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getDate() != null) {
            selData.setDate(updates.getDate());
        }
        if (updates.getTempDegC() != null) {
            selData.setTempDegC(updates.getTempDegC());
        }
        if (updates.getChlFluPPB() != null) {
            selData.setChlFluPPB(updates.getChlFluPPB());
        }
        if (updates.getChlAUgL() != null) {
            selData.setChlAUgL(updates.getChlAUgL());
        }
        if (updates.getTurbMFTU() != null) {
            selData.setTurbMFTU(updates.getTurbMFTU());
        }
        if (updates.getBattV() != null) {
            selData.setBattV(updates.getBattV());
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
        ProcessedFLNTUHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        try {
            headRepository.delete(selHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedFLNTUData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        try {
            repository.delete(selData);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
