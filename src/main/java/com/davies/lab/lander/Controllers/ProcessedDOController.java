package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.DO_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.HeaderDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateDODataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateDOHeaderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.DODataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.DOHeadResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.DataProgressResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.TotalDataResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedDOData;
import com.davies.lab.lander.Models.ProcessedDOHead;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.davies.lab.lander.Repositories.ProcessedDODataRepository;
import com.davies.lab.lander.Repositories.ProcessedDOHeadRepository;
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

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/do")
@EnableCaching
public class ProcessedDOController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedDODataRepository repository;
    @Autowired
    private ProcessedDOHeadRepository headRepository;

    //Head Routes
    @GetMapping("/headers")
    public List<DOHeadResponse> findAllHeads() {
        List<ProcessedDOHead> heads = headRepository.findAll();
        List<DOHeadResponse> res = new ArrayList<>();

        for (ProcessedDOHead head : heads) {
            DOHeadResponse temp = new DOHeadResponse(
                    head
            );

            for (ProcessedDOData data : head.getData()) {
                temp.createDODataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<DOHeadResponse> findHeadWithoutData(@PathVariable("id") Long id) {
        Optional<ProcessedDOHead> head = headRepository.findById(id);
        DOHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new DOHeadResponse(
                head.get()
        );

        res.setDataPointCount(head.get().getData().size());

        if (res.getStartTime() == null && res.getDataPointCount() > 0) {
            ProcessedDOData firstData = repository.findFirstDataPointInHead(head.get().getHeadID());
            if (firstData != null) {
                res.setStartTime(firstData.getDate());
            }
        }

        if (res.getEndTime() == null && res.getDataPointCount() > 0) {
            ProcessedDOData lastData = repository.findLastDataPointInHead(head.get().getHeadID());
            if (lastData != null) {
                res.setEndTime(lastData.getDate());
            }
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<DOHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedDOHead> head = headRepository.findById(id);
        DOHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new DOHeadResponse(
                head.get()
        );

        for (ProcessedDOData data : head.get().getData()) {
            res.createDODataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<DODataResponse> findAllEntries() {
        List<ProcessedDOData> data = repository.findAll();
        List<DODataResponse> res = new ArrayList<>();

        for (ProcessedDOData selData : data) {
            DODataResponse temp = new DODataResponse(
                    selData
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/header/{id}/aligned/true")
    public ResponseEntity<List<DODataResponse>> findAlignedDataByHeader(@PathVariable("id") Long id) {
        List<ProcessedDOData> data = repository.findDataByHeadAndAlignedStatus(id, true);
        List<DODataResponse> res = new ArrayList<>();

        for (ProcessedDOData selData : data) {
            DODataResponse temp = new DODataResponse(
                    selData
            );

            res.add(temp);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/header/{id}/aligned/false")
    public ResponseEntity<List<DODataResponse>> findUnalignedDataByHeader(@PathVariable("id") Long id) {
        List<ProcessedDOData> data = repository.findDataByHeadAndAlignedStatus(id, false);
        List<DODataResponse> res = new ArrayList<>();

        for (ProcessedDOData selData : data) {
            DODataResponse temp = new DODataResponse(
                    selData
            );

            res.add(temp);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<DODataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedDOData> data = repository.findById(id);
        DODataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new DODataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<DODataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
        List<ProcessedDOData> data = repository.findDoDataByHeadId(id);
        List<DODataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedDOData elem : data) {
            res.add(new DODataResponse(
                    elem
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<List<DODataResponse>> getDataByRange(@PathVariable("id") Long id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<DODataResponse> res = new ArrayList<>();

        List<ProcessedDOData> data = repository.findDataByHeadAndDateRange(id, startDate, endDate);

        for (ProcessedDOData selData: data) {
            res.add(
                    new DODataResponse(
                            selData
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/count/{landerID}")
    @Cacheable(value = "DOCount")
    public ResponseEntity<DataProgressResponse> getDataCountFromHeadID(@PathVariable("landerID") String landerID) {
        Lander selLander = landerRepository.findById(landerID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (selLander.getDOHead() != null) {
            ProcessedDOHead selHead = headRepository.findById(selLander.getDOHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            Integer dataCount = repository.findCountByHeadID(selHead.getHeadID());

            if (selHead.getStartTime() != null && selHead.getEndTime() != null && selHead.getBurstCnt() != null && selHead.getBurstTime() != null) {
                LocalDateTime startTime = selHead.getStartTime();
                LocalDateTime endTime = selHead.getEndTime();
                int burstCount = selHead.getBurstCnt();
                int burstTime = selHead.getBurstTime();

                double hoursBetween = ChronoUnit.HOURS.between(startTime, endTime);

                hoursBetween *= (60.0 / burstTime);

                hoursBetween *= burstCount;

                return new ResponseEntity<>(new DataProgressResponse( (dataCount / hoursBetween) ), HttpStatus.OK);
            }

            return new ResponseEntity<>(new DataProgressResponse(dataCount), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DataProgressResponse(0.00), HttpStatus.OK);
    }

    @PostMapping("/data/count/headless")
    @Cacheable("DOCount-Headless")
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
        List<DO_CSV_Request> rawData = null;
        Optional<ProcessedDOHead> optionalHead;
        ProcessedDOHead savedHead;

        if (selLander.isEmpty()) {
            clearDOCache();

            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            clearDOCache();

            return new ResponseEntity<>("Missing Uploadded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getDOHead() != null) {
            optionalHead = headRepository.findById(selLander.get().getDOHead().getHeadID());

            if (optionalHead.isEmpty()) {
                clearDOCache();

                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            savedHead = optionalHead.get();
        } else {
            ProcessedDOHead dummyHead = new ProcessedDOHead();
            dummyHead.setLanderID(selLander.get());

            savedHead = headRepository.save(dummyHead);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
            rawData = processData(reader);
        } catch (Exception e) {
            clearDOCache();

            System.out.println(e.getLocalizedMessage());
        }

        if (rawData == null) {
            clearDOCache();

            return new ResponseEntity<>("Unable to format Data", HttpStatus.BAD_REQUEST);
        }

        try {
            for (DO_CSV_Request dataElement : rawData) {
                repository.save(new ProcessedDOData(
                        dataElement,
                        savedHead
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            clearDOCache();

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        clearDOCache();

        return new ResponseEntity<>("Posted!", HttpStatus.OK);
    }

    @PostMapping("/upload_csv/header/{landerID}")
    public ResponseEntity<String> uploadProcessedHeader(@RequestParam("processedHead") MultipartFile processedHead, @PathVariable("landerID") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);

        if (selLander.isEmpty()) {
            clearDOCache();

            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedHead.isEmpty()) {
            clearDOCache();

            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getDOHead() == null) {
            ProcessedDOHead newHead = new ProcessedDOHead();
            newHead.setLanderID(selLander.get());
            ProcessedDOHead savedHead = headRepository.save(newHead);
            selLander.get().setDOHead(savedHead);
            landerRepository.save(selLander.get());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedHead.getInputStream()))) {
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

            UpdateDOHeaderRequest updates = new UpdateDOHeaderRequest(
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
                    Integer.parseInt(valuesMap.get("SampleCnt")),
                    StringFormatting.formatDateString(valuesMap.get("StartTime")),
                    StringFormatting.formatDateString(valuesMap.get("EndTime")),
                    Double.parseDouble(valuesMap.get("DepAdjRho")),
                    StringFormatting.formatCoefDateString(valuesMap.get("CoefDate")),
                    Double.parseDouble(valuesMap.get("Ch1").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch2").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch3").split(",")[0]),
                    Integer.parseInt(valuesMap.get("BuzzerEN")),
                    Integer.parseInt(valuesMap.get("BuzzerInterval")),
                    valuesMap.get("COMMENT"),
                    valuesMap.get("SensorType2"),
                    Integer.parseInt(valuesMap.get("BuzzerNumber")),
                    Integer.parseInt(valuesMap.get("DepM")),
                    Integer.parseInt(valuesMap.get("SetSal")),
                    valuesMap.get("FilmNo"),
                    selLander.get(),
                    selLander.get().getDOHead().getData()
            );

            updateDOHeader(selLander.get().getDOHead().getHeadID(), updates);

            clearDOCache();

            return new ResponseEntity<>("Posted", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            clearDOCache();

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload_csv/combined/{lander_id}")
    public ResponseEntity<String> processCompleteCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("lander_id") String landerId) {
        Optional<Lander> selLander = landerRepository.findById(landerId);
        Lander lander;

        if (selLander.isEmpty()) {
            clearDOCache();

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        lander = selLander.get();


        if (processedFile.isEmpty()) {
            clearDOCache();

            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (lander.getDOHead() != null) {
            clearDOCache();

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

            ProcessedDOHead doHead = new ProcessedDOHead(
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
                    Integer.parseInt(valuesMap.get("SampleCnt")),
                    StringFormatting.formatDateString(valuesMap.get("StartTime")),
                    StringFormatting.formatDateString(valuesMap.get("EndTime")),
                    Double.parseDouble(valuesMap.get("DepAdjRho")),
                    StringFormatting.formatCoefDateString(valuesMap.get("CoefDate")),
                    Double.parseDouble(valuesMap.get("Ch1").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch2").split(",")[0]),
                    Double.parseDouble(valuesMap.get("Ch3").split(",")[0]),
                    Integer.parseInt(valuesMap.get("BuzzerEN")),
                    Integer.parseInt(valuesMap.get("BuzzerInterval")),
                    valuesMap.get("COMMENT"),
                    valuesMap.get("SensorType2"),
                    Integer.parseInt(valuesMap.get("BuzzerNumber")),
                    Integer.parseInt(valuesMap.get("DepM")),
                    Integer.parseInt(valuesMap.get("SetSal")),
                    valuesMap.get("FilmNo"),
                    lander
            );

            ProcessedDOHead newHead = headRepository.save(doHead);

            List<DO_CSV_Request> outputData = processData(reader);

            if (outputData == null) {
                clearDOCache();

                return new ResponseEntity<>("Could not generate Data from File", HttpStatus.BAD_REQUEST);
            }

            for (DO_CSV_Request inputDataPoint : outputData) {
                ProcessedDOData newData = new ProcessedDOData(
                        inputDataPoint,
                        newHead
                );

                repository.save(newData);
            }

            clearDOCache();

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            clearDOCache();

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<DO_CSV_Request> processData(BufferedReader reader) {
        List<DO_CSV_Request> dataList;

        try {
            CsvToBean<DO_CSV_Request> csvToBean = new CsvToBeanBuilder<DO_CSV_Request>(reader)
                    .withType(DO_CSV_Request.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            dataList = csvToBean.parse();

            return dataList;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @CacheEvict(value = {"DOCache", "DOCache-Headless"}, allEntries = true)
    private void clearDOCache() {

    }

    @PutMapping("/update/header/{id}")
    public ResponseEntity<String> updateDOHeader(@PathVariable("id") Long id, @RequestBody UpdateDOHeaderRequest updates) {
        ProcessedDOHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
        if (updates.getMeasModel() != null) {
            selHead.setMeasModel(updates.getMeasModel());
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
        if (updates.getSampleCnt() != null) {
            selHead.setSampleCnt(updates.getSampleCnt());
        }
        if (updates.getStartTime() != null) {
            selHead.setStartTime(updates.getStartTime());
        }
        if (updates.getEndTime() != null) {
            selHead.setEndTime(updates.getEndTime());
        }
        if (updates.getDepAdiRho() != null) {
            selHead.setDepAdiRho(updates.getDepAdiRho());
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
        if (updates.getBuzzerEN() != null) {
            selHead.setBuzzerEN(updates.getBuzzerEN());
        }
        if (updates.getBuzzerInterval() != null) {
            selHead.setBuzzerInterval(updates.getBuzzerInterval());
        }
        if (updates.getCOMMENT() != null) {
            selHead.setCOMMENT(updates.getCOMMENT());
        }
        if (updates.getSensorType2() != null) {
            selHead.setSensorType2(updates.getSensorType2());
        }
        if (updates.getBuzzerNumber() != null) {
            selHead.setBuzzerNumber(updates.getBuzzerNumber());
        }
        if (updates.getDepM() != null) {
            selHead.setDepM(updates.getDepM());
        }
        if (updates.getSetSal() != null) {
            selHead.setSetSal(updates.getSetSal());
        }
        if (updates.getFilmNo() != null) {
            selHead.setFilmNo(updates.getFilmNo());
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
    public ResponseEntity<String> updateDODataByID(@PathVariable("id") Long id, @RequestBody UpdateDODataRequest updates) {
        ProcessedDOData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getDate() != null) {
            selData.setDate(updates.getDate());
        }
        if (updates.getTempDegC() != null) {
            selData.setTempDegC(updates.getTempDegC());
        }
        if (updates.getDO() != null) {
            selData.setDO(updates.getDO());
        }
        if (updates.getWeissDoMgL() != null) {
            selData.setWeissDoMgL(updates.getWeissDoMgL());
        }
        if (updates.getBattV() != null) {
            selData.setBattV(updates.getBattV());
        }
        if (updates.getGGDOMgL() != null) {
            selData.setGGDOMgL(updates.getGGDOMgL());
        }
        if (updates.getBKDOMgL() != null) {
            selData.setBKDOMgL(updates.getBKDOMgL());
        }
        if (updates.getAligned() != null) {
            selData.setAligned(updates.getAligned());
        }
        if (updates.getHeadID() != null) {
            selData.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated Data", HttpStatus.OK);
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedDOHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        headRepository.delete(selHead);

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedDOData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(selData);

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}