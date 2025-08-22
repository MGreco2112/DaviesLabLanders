package com.davies.lab.lander.Controllers.Sensors;

import com.davies.lab.lander.Controllers.Frontend.DashboardController;
import com.davies.lab.lander.Controllers.LanderController;
import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.Camera_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateCameraDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.Updates.UpdateCameraHeaderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.Data.CameraDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.Head.CameraHeadResponse;
import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import com.davies.lab.lander.Models.Headers.ProcessedCameraHeader;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Repositories.Data.ProcessedCameraDataRepository;
import com.davies.lab.lander.Repositories.Header.ProcessedCameraHeadRepository;
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
@RequestMapping("/api/processed/camera")
public class ProcessedCameraController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedCameraDataRepository repository;
    @Autowired
    private ProcessedCameraHeadRepository headRepository;
    @Autowired
    private DashboardController dashboardController;
    @Autowired
    private LanderController landerController;

    //Head Routes
    @GetMapping("/headers")
    public List<CameraHeadResponse> findAllHeads() {
        List<ProcessedCameraHeader> heads = headRepository.findAll();
        List<CameraHeadResponse> res = new ArrayList<>();

        for (ProcessedCameraHeader head : heads) {
            CameraHeadResponse temp = new CameraHeadResponse(
                    head
            );

            for (ProcessedCameraData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<CameraHeadResponse> findHeadById(@PathVariable("id") Long id) {
        Optional<ProcessedCameraHeader> head = headRepository.findById(id);
        CameraHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CameraHeadResponse(
                head.get()
        );

        for (ProcessedCameraData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<CameraDataResponse> findAllEntries() {
        List<ProcessedCameraData> data = repository.findAll();
        List<CameraDataResponse> res = new ArrayList<>();

        for (ProcessedCameraData dataPoint : data) {
            CameraDataResponse temp = new CameraDataResponse(
                    dataPoint
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<CameraDataResponse> findDataById(@PathVariable("id") Long id) {
        Optional<ProcessedCameraData> data = repository.findById(id);
        CameraDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CameraDataResponse(
                data.get()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<CameraDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
        List<ProcessedCameraData> data = repository.findDataByHeadId(id);
        List<CameraDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedCameraData elem : data) {
            res.add(
                    new CameraDataResponse(
                            elem
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //TODO: Add POST/PUT mappings after model is defined
    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile")MultipartFile processedFile, @PathVariable("landerId") String landerID) {

        Optional<Lander> selLander = landerRepository.findById(landerID);
        List<Camera_CSV_Request> rawData;
        ProcessedCameraHeader savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getCameraHead() != null) {
            savedHead = headRepository.findById(selLander.get().getCameraHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            ProcessedCameraHeader dummyHead = new ProcessedCameraHeader();
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
            for (Camera_CSV_Request dataElement : rawData) {
                repository.save(new ProcessedCameraData(
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

        if (selLander.get().getCameraHead() == null) {
            ProcessedCameraHeader newHead = new ProcessedCameraHeader();
            newHead.setLanderID(selLander.get());
            ProcessedCameraHeader savedHead = headRepository.save(newHead);
            selLander.get().setCameraHead(savedHead);
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

            UpdateCameraHeaderRequest updates = new UpdateCameraHeaderRequest(
                    /*
                    Insert captured and parsed values from valuesMap into completed constructor
                    */
            );

            updateCameraHeader(selLander.get().getCameraHead().getHeadID(), updates);

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

        if (selLander.get().getCameraHead() != null) {
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

            ProcessedCameraHeader cameraHead = new ProcessedCameraHeader(
                    /*
                    Insert parsed values from the valuesMap once the constructor is built
                    */
            );

            cameraHead.setLanderID(selLander.get());

            List<Camera_CSV_Request> outputData = processData(reader);

            if (outputData == null) {
                return new ResponseEntity<>("Bad Data", HttpStatus.BAD_REQUEST);
            }

            for (Camera_CSV_Request inputDataPoint : outputData) {
                ProcessedCameraData newData = new ProcessedCameraData(
                        inputDataPoint,
                        cameraHead
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

    private List<Camera_CSV_Request> processData(BufferedReader reader) {
        List<Camera_CSV_Request> dataList;

        try {
            CsvToBean<Camera_CSV_Request> csvToBean = new CsvToBeanBuilder<Camera_CSV_Request>(reader)
                    .withType(Camera_CSV_Request.class)
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
    public ResponseEntity<String> updateCameraHeader(@PathVariable("id") Long id, @RequestBody UpdateCameraHeaderRequest updates) {
        ProcessedCameraHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
    public ResponseEntity<String> updateCameraDataById(@PathVariable("id") Long id, @RequestBody UpdateCameraDataRequest updates) {
        ProcessedCameraData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getHeadID() != null) {
            selData.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderById(@PathVariable("id") Long id) {
        ProcessedCameraHeader selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
        ProcessedCameraData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
