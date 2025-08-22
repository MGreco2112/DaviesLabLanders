package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.Controllers.LanderController;
import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.Battery_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.Beacon_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateBeaconDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateBeaconHeaderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.BeaconDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.BeaconHeadResponse;
import com.davies.lab.lander.Models.Data.ProcessedBeaconData;
import com.davies.lab.lander.Models.Headers.ProcessedBeaconHeader;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Repositories.Data.ProcessedBeaconDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedBeaconHeadRepository;
import com.davies.lab.lander.Repositories.LanderRepository;
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
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/beacon")
public class ProcessedBeaconController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedBeaconDataRepository repository;
    @Autowired
    private ProcessedBeaconHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;
    @Autowired
    private LanderController landerController;

    //Head Routes
    @GetMapping("/headers")
    public List<BeaconHeadResponse> findAllHeads() {
        List<ProcessedBeaconHeader> heads = headRepository.findAll();
        List<BeaconHeadResponse> res = new ArrayList<>();

        for (ProcessedBeaconHeader head : heads) {
            BeaconHeadResponse temp = new BeaconHeadResponse(
                    head
            );

            for (ProcessedBeaconData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<BeaconHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedBeaconHeader> head = headRepository.findById(id);
        BeaconHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new BeaconHeadResponse(
                head.get()
        );

        for (ProcessedBeaconData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<BeaconDataResponse> findAllEntries() {
        List<ProcessedBeaconData> data = repository.findAll();
        List<BeaconDataResponse> res = new ArrayList<>();

        for (ProcessedBeaconData dataPoint : data) {
            BeaconDataResponse temp = new BeaconDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<BeaconDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedBeaconData> data = repository.findById(id);
        BeaconDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new BeaconDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<BeaconDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
        List<ProcessedBeaconData> data = repository.findDataByHeadId(id);
        List<BeaconDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedBeaconData elem : data)  {
            res.add(new BeaconDataResponse(
                    elem
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //TODO: Create POST/PUT routes once parent entities are fully updated
    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("landerId") String landerID) {

        Optional<Lander> selLander = landerRepository.findById(landerID);
        List<Beacon_CSV_Request> rawData;
        ProcessedBeaconHeader savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getBeaconHead() != null) {
            savedHead = headRepository.findById(selLander.get().getBeaconHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } else {
            ProcessedBeaconHeader dummyHead = new ProcessedBeaconHeader();
            dummyHead.setLanderID(selLander.get());

            savedHead = headRepository.save(dummyHead);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))){
            rawData = processData(reader);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        if (rawData == null) {
            return new ResponseEntity<>("Unable to format Data", HttpStatus.BAD_REQUEST);
        }

        try {
            for (Beacon_CSV_Request dataElement : rawData) {
                repository.save(new ProcessedBeaconData(
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

        return new ResponseEntity<>("Uploaded", HttpStatus.CREATED);
    }

    @PostMapping("/upload_csv/header/{landerID}")
    public ResponseEntity<String> uploadProcessedHeader(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("landerID") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getBeaconHead() != null) {
            ProcessedBeaconHeader newHead =  new ProcessedBeaconHeader();
            newHead.setLanderID(selLander.get());
            ProcessedBeaconHeader savedHead = headRepository.save(newHead);
            selLander.get().setBeaconHead(savedHead);
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

            UpdateBeaconHeaderRequest updates =  new UpdateBeaconHeaderRequest(
                    /*
                    Insert captured and parsed values from valuesMap into completed constructor
                    */
            );

            updateBeaconHeader(selLander.get().getBeaconHead().getHeadID(), updates);

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
            return new ResponseEntity<>("Lander not Found", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getBeaconHead() != null) {
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

            ProcessedBeaconHeader beaconHead = new ProcessedBeaconHeader(
                    /*
                    Insert parsed values from valuesMap once the constructor is built
                    */
            );

            beaconHead.setLanderID(selLander.get());

            ProcessedBeaconHeader savedHead = headRepository.save(beaconHead);

            List<Beacon_CSV_Request> outputData = processData(reader);

            if (outputData == null) {
                return new ResponseEntity<>("Bad Data", HttpStatus.BAD_REQUEST);
            }

            for (Beacon_CSV_Request inputDataPoint : outputData) {
                ProcessedBeaconData newData = new ProcessedBeaconData(
                        inputDataPoint,
                        savedHead
                );

                repository.save(newData);
            }

            landerController.evictLandersCache();
            dashboardController.evictMyCache();

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<Beacon_CSV_Request> processData(BufferedReader reader) {
        List<Beacon_CSV_Request> dataList;

        try {
            CsvToBean<Beacon_CSV_Request> csvToBean = new CsvToBeanBuilder<Beacon_CSV_Request>(reader)
                    .withType(Beacon_CSV_Request.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            dataList = csvToBean.parse();

            return dataList;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    //create private void method clearBeaconCache using the evictCache annotation

    @PutMapping("/update/header/{id}")
    public ResponseEntity<String> updateBeaconHeader(@PathVariable("id") Long id, @RequestBody UpdateBeaconHeaderRequest updates) {
        ProcessedBeaconHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
    public ResponseEntity<String> updateBeaconDataByID(@PathVariable("id") Long id, @RequestBody UpdateBeaconDataRequest updates) {
        ProcessedBeaconData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getHeadID() != null) {
            selData.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedBeaconHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedBeaconData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
