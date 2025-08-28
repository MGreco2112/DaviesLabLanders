package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.Controllers.LanderController;
import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.SedimentTrap_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.HeaderDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.Data.UpdateSedimentTrapDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.Head.UpdateSedimentTrapHeaderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.DataProgressResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.SedimentTrapDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.TotalDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.SedimentTrapHeadResponse;
import com.davies.lab.lander.Models.Data.ProcessedSedimentTrapData;
import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Repositories.Data.ProcessedSedimentTrapDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedSedimentTrapHeadRepository;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@RequestMapping("/api/processed/sediment_trap")
public class ProcessedSedimentTrapController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedSedimentTrapDataRepository repository;
    @Autowired
    private ProcessedSedimentTrapHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;
    @Autowired
    private LanderController landerController;

    //HeadRoutes
    @GetMapping("/headers")
    public List<SedimentTrapHeadResponse> findAllHeads() {
        List<ProcessedSedimentTrapHeader> heads = headRepository.findAll();
        List<SedimentTrapHeadResponse> res = new ArrayList<>();

        for (ProcessedSedimentTrapHeader head : heads) {
            SedimentTrapHeadResponse temp =  new SedimentTrapHeadResponse(
                    head
            );

            for (ProcessedSedimentTrapData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<SedimentTrapHeadResponse> findHeadWithoutDataById(@PathVariable("id") Long id) {
        Optional<ProcessedSedimentTrapHeader> head = headRepository.findById(id);
        SedimentTrapHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        res = new SedimentTrapHeadResponse(head.get());

        res.setDataPointCount(head.get().getData().size());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<SedimentTrapHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedSedimentTrapHeader> head = headRepository.findById(id);
        SedimentTrapHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new SedimentTrapHeadResponse(
                head.get()
        );

        for (ProcessedSedimentTrapData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data")
    public List<SedimentTrapDataResponse> findAllEntries() {
        List<ProcessedSedimentTrapData> data = repository.findAll();
        List<SedimentTrapDataResponse> res = new ArrayList<>();

        for (ProcessedSedimentTrapData dataPoint : data) {
            res.add(
                    new SedimentTrapDataResponse(
                            dataPoint
                    )
            );
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<SedimentTrapDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedSedimentTrapData> data = repository.findById(id);
        SedimentTrapDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new SedimentTrapDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/count/{landerID}")
    public ResponseEntity<DataProgressResponse> getDataCountFromHeadID(@PathVariable("landerID") String landerID) {
        Lander selLander = landerRepository.findById(landerID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (selLander.getSedimentTrapHead() != null) {
            ProcessedSedimentTrapHeader selHead = headRepository.findById(selLander.getSedimentTrapHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            Integer dataCount = repository.findCountOfDataByHead(selHead.getHeadID());

            //TODO: update with conditional with startATime, endTime, burstCnt, burstTime
            //double hoursBetween = calculateDataSize(selHead);
            //return new ResponseEntity<>(new DataProgressResponse((dataCount / hoursBetween)), httpStatus.OK);

            return new ResponseEntity<>(new DataProgressResponse(dataCount), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DataProgressResponse(0.00), HttpStatus.OK);
    }

    @Cacheable(value = "SedimentCount")
    private double CalculateDataSize(ProcessedSedimentTrapHeader selHead) {
        LocalDateTime startTime; //= selHead.getStartTime();
        LocalDateTime endTime; //= selHead.getEndTime();
        int burstCount = 0; //= selHead.getBurstCnt();
        int burstTime = 0; //= selHead.getBurstTime();

        double hoursBetween = 0.00; //= ChronoUnit.HOURS.between(startTime, endTime);

        hoursBetween *= (60.0 / burstTime);

        hoursBetween *= burstCount;

        return hoursBetween;
    }

    @PostMapping("/data/count/headless")
    @Cacheable(value = "SedimentCount-Headless")
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

    //TODO: Create POST/PUT routes one entity details are identified
    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile")MultipartFile processedFile, @PathVariable("landerId") String landerID) {

        Optional<Lander> selLander = landerRepository.findById(landerID);
        List<SedimentTrap_CSV_Request> rawData;
        ProcessedSedimentTrapHeader savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getSedimentTrapHead() != null) {
            savedHead = headRepository.findById(selLander.get().getSedimentTrapHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } else {
            ProcessedSedimentTrapHeader dummyHead = new ProcessedSedimentTrapHeader();
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
            for (SedimentTrap_CSV_Request dataElement : rawData) {
                repository.save(new ProcessedSedimentTrapData(
                        dataElement,
                         savedHead
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        landerController.evictLandersCache();
        dashboardController.evictMyCache();
        evictSedimentCache();

        return new ResponseEntity<>("Uploaded", HttpStatus.CREATED);
    }

    @PostMapping("/upload_csv/header/{landerID}")
    public ResponseEntity<String> uploadedProcessedHeader(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("landerID") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getSedimentTrapHead() == null) {
            ProcessedSedimentTrapHeader newHead = new ProcessedSedimentTrapHeader();
            newHead.setLanderID(selLander.get());
            ProcessedSedimentTrapHeader savedHead = headRepository.save(newHead);
            selLander.get().setSedimentTrapHead(savedHead);
            landerRepository.save(selLander.get());
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

            UpdateSedimentTrapHeaderRequest updates = new UpdateSedimentTrapHeaderRequest(
                    /*
                    Insert captured and parsed values from valuesMap into completed constructor
                    */
            );

            updateSedimentTrapHeader(selLander.get().getSedimentTrapHead().getHeadID(), updates);

            landerController.evictLandersCache();
            dashboardController.evictMyCache();

            return new ResponseEntity<>("Posted", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload_csv/combined/{lander_id}")
    public ResponseEntity<String> processCompleteCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("lander_id") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Lander not found", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getSedimentTrapHead() != null) {
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

            ProcessedSedimentTrapHeader sedimentTrapHead = new ProcessedSedimentTrapHeader(
                    /*
                    Insert parsed values from the valuesMap once the constructor is built
                    */
            );

            sedimentTrapHead.setLanderID(selLander.get());

            ProcessedSedimentTrapHeader savedHead = headRepository.save(sedimentTrapHead);

            List<SedimentTrap_CSV_Request> outputData = processData(reader);

            if (outputData == null) {
                return new ResponseEntity<>("Bad Data", HttpStatus.BAD_REQUEST);
            }

            for (SedimentTrap_CSV_Request inputDataPoint : outputData) {
                ProcessedSedimentTrapData newData = new ProcessedSedimentTrapData(
                        inputDataPoint,
                        savedHead
                );

                repository.save(newData);
            }

            landerController.evictLandersCache();
            dashboardController.evictMyCache();
            evictSedimentCache();

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<SedimentTrap_CSV_Request> processData(BufferedReader reader) {
        List<SedimentTrap_CSV_Request> dataList;

        try {
            CsvToBean<SedimentTrap_CSV_Request> csvToBean = new CsvToBeanBuilder<SedimentTrap_CSV_Request>(reader)
                    .withType(SedimentTrap_CSV_Request.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            dataList = csvToBean.parse();

            return dataList;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @CacheEvict(value = {"SedimentCount", "SedimentCount-Headless"}, allEntries = true)
    public void evictSedimentCache() {

    }

    @PutMapping("/update/header/{id}")
    public ResponseEntity<String> updateSedimentTrapHeader(@PathVariable("id") Long id, @RequestBody UpdateSedimentTrapHeaderRequest updates) {
        ProcessedSedimentTrapHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
    public ResponseEntity<String> updateSedimentTrapDataById(@PathVariable("id") Long id, @RequestBody UpdateSedimentTrapDataRequest updates) {
        ProcessedSedimentTrapData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getHeadID() != null) {
            selData.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderById(@PathVariable("id") Long id) {
        ProcessedSedimentTrapHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        try {
            headRepository.delete(selHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        landerController.evictLandersCache();
        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataById(@PathVariable("id") Long id) {
        ProcessedSedimentTrapData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        try {
            repository.delete(selData);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        landerController.evictLandersCache();
        dashboardController.evictMyCache();

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
