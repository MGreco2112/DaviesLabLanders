package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.Controllers.LanderController;
import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.Battery_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateBatteryDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateBatteryHeaderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.BatteryDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.BatteryHeadResponse;
import com.davies.lab.lander.Models.Data.ProcessedBatteryData;
import com.davies.lab.lander.Models.Headers.ProcessedBatteryHeader;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Repositories.Data.ProcessedBatteryDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedBatteryHeadRepository;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/battery")
public class ProcessedBatteryController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedBatteryDataRepository repository;
    @Autowired
    private ProcessedBatteryHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;
    @Autowired
    private LanderController landerController;

    //Head Routes
    public List<BatteryHeadResponse> findAllHeads() {
        List<ProcessedBatteryHeader> heads = headRepository.findAll();
        List<BatteryHeadResponse> res = new ArrayList<>();

        for (ProcessedBatteryHeader head : heads) {
            BatteryHeadResponse temp = new BatteryHeadResponse(
                    head
            );

            for (ProcessedBatteryData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<BatteryHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedBatteryHeader> head = headRepository.findById(id);
        BatteryHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new BatteryHeadResponse(
                head.get()
        );

        for (ProcessedBatteryData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<BatteryDataResponse> findAllEntries() {
        List<ProcessedBatteryData> data = repository.findAll();
        List<BatteryDataResponse> res = new ArrayList<>();

        for (ProcessedBatteryData dataPoint : data) {
            BatteryDataResponse temp = new BatteryDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<BatteryDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedBatteryData> data = repository.findById(id);
        BatteryDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new BatteryDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<BatteryDataResponse>> findDataByHeadId(@PathVariable("id") Long headId) {
        List<ProcessedBatteryData> data = repository.findDataByHeadId(headId);
        List<BatteryDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedBatteryData elem : data) {
            res.add(
                    new BatteryDataResponse(
                            elem
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

//    TODO: create methods for progress bar uploads once entity is defined
//    TODO: Update POST/PUT Routes once Entity is better defined
    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile")MultipartFile processedFile, @PathVariable("landerId") String landerID) {

        Optional<Lander> selLander = landerRepository.findById(landerID);
        List<Battery_CSV_Request> rawData;
        ProcessedBatteryHeader savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getBatteryHead() != null) {
            savedHead = headRepository.findById(selLander.get().getBatteryHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } else {
            ProcessedBatteryHeader dummyHead = new ProcessedBatteryHeader();
            dummyHead.setLanderID(selLander.get());

            savedHead = headRepository.save(dummyHead);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))){
            //TODO create the CSVToBean setup for Battery CSV files
            rawData = processData(reader);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        if (rawData == null) {
            return new ResponseEntity<>("Unable to format Data", HttpStatus.BAD_REQUEST);
        }

        try {
            for (Battery_CSV_Request dataElement : rawData) {
                repository.save(new ProcessedBatteryData(
                        dataElement,
                        savedHead
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        //TODO: process rawData into ProcessedBatteryData
        dashboardController.evictMyCache();
        landerController.evictLandersCache();

        return new ResponseEntity<>("Uploaded", HttpStatus.CREATED);
    }

    @PostMapping("/upload_csv/header/{landerID}")
    public ResponseEntity<String> uploadProcessedHeader(@RequestParam("processedFile") MultipartFile processedHead, @PathVariable("landerID") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedHead.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getBatteryHead() == null) {
            ProcessedBatteryHeader newHead = new ProcessedBatteryHeader();
            newHead.setLanderID(selLander.get());
            ProcessedBatteryHeader savedHead = headRepository.save(newHead);
            selLander.get().setBatteryHead(savedHead);
            landerRepository.save(selLander.get());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedHead.getInputStream()))){
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

            UpdateBatteryHeaderRequest updates = new UpdateBatteryHeaderRequest(
                    /*
                    Insert captured and parsed values from valuesMap into completed constructor
                    */
            );

            updateBatteryHeader(selLander.get().getBatteryHead().getHeadID(), updates);

            dashboardController.evictMyCache();
            landerController.evictLandersCache();

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
            return new ResponseEntity<>("Lander not Found", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getBatteryHead() != null) {
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

            ProcessedBatteryHeader batteryHead = new ProcessedBatteryHeader(
                    /*
                    Insert parsed values from the valuesMap once the constructor is built
                    */
            );

            List<Battery_CSV_Request> outputData = processData(reader);

            if (outputData == null) {
                return new ResponseEntity<>("Bad Data", HttpStatus.BAD_REQUEST);
            }

            for (Battery_CSV_Request inputDataPoint : outputData) {
                ProcessedBatteryData newData = new ProcessedBatteryData(
                        inputDataPoint,
                        batteryHead
                );

                repository.save(newData);
            }

            batteryHead.setLanderID(selLander.get());

            //handle parsing csv data, attach to Header

            dashboardController.evictMyCache();
            landerController.evictLandersCache();

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<Battery_CSV_Request> processData(BufferedReader reader) {
        List<Battery_CSV_Request> dataList;

        try {
            CsvToBean<Battery_CSV_Request> csvToBean = new CsvToBeanBuilder<Battery_CSV_Request>(reader)
                    .withType(Battery_CSV_Request.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            dataList = csvToBean.parse();

            return dataList;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    //create private void method clearBatteryCache using the cacheEvict annotation

    @PutMapping("/update/header/{id}")
    public ResponseEntity<String> updateBatteryHeader(@PathVariable("id") Long id, @RequestBody UpdateBatteryHeaderRequest updates) {
        ProcessedBatteryHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //TODO: Update with new fields once they're created

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
    public ResponseEntity<String> updateCTDDataByID(@PathVariable("id") Long id, @RequestBody UpdateBatteryDataRequest updates) {
        ProcessedBatteryData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getHeadID() != null) {
            selData.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedBatteryHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save((selHead));

        try {
            headRepository.delete(selHead);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        dashboardController.evictMyCache();
        landerController.evictLandersCache();

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedBatteryData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
