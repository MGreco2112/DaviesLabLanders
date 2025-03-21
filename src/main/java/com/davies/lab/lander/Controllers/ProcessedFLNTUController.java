package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.FLNTU_CSV_Request;
import com.davies.lab.lander.FormattedModels.ResponseBody.FLNTUDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.FLNTUHeadResponse;
import com.davies.lab.lander.Models.ProcessedFLNTUData;
import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import com.davies.lab.lander.Repositories.ProcessedFLNTUDataRepository;
import com.davies.lab.lander.Repositories.ProcessedFLNTUHeadRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/flntu")
public class ProcessedFLNTUController {
    @Autowired
    private ProcessedFLNTUDataRepository repository;
    @Autowired
    private ProcessedFLNTUHeadRepository headRepository;

    //Header Routes
    @GetMapping("/headers")
    public List<FLNTUHeadResponse> findAllHeads() {
        List<ProcessedFLNTUHead> heads = headRepository.findAll();
        List<FLNTUHeadResponse> res = new ArrayList<>();

        for (ProcessedFLNTUHead selHead : heads) {
            FLNTUHeadResponse temp = new FLNTUHeadResponse(
                    selHead.getHeadID(),
                    selHead.getSondeName(),
                    selHead.getSondeNo(),
                    selHead.getSensorType(),
                    selHead.getChannel(),
                    selHead.getDelayTime(),
                    selHead.getPreHeat(),
                    selHead.getMeasMode(),
                    selHead.getBurstTime(),
                    selHead.getBurstCnt(),
                    selHead.getIntervalData(),
                    selHead.getWiperInterval(),
                    selHead.getSampleCnt(),
                    selHead.getStartTime(),
                    selHead.getEndTime(),
                    selHead.getCHLA(),
                    selHead.getCHLB(),
                    selHead.getCoefDate(),
                    selHead.getCh1(),
                    selHead.getCh2(),
                    selHead.getCh3(),
                    selHead.getCh4(),
                    selHead.getBuzzerEN(),
                    selHead.getBuzzerInterval(),
                    selHead.getComment(),
                    selHead.getSensorType2(),
                    selHead.getBuzzerNumber(),
                    selHead.getLanderID().getASDBLanderID()
            );

            for (ProcessedFLNTUData selData : selHead.getData()) {
                temp.createFLNTUDataResponse(selData);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<FLNTUHeadResponse> findHeadWithoutDataById(@PathVariable Integer id) {
        Optional<ProcessedFLNTUHead> head = headRepository.findById(id);
        FLNTUHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new FLNTUHeadResponse(
                head.get().getHeadID(),
                head.get().getSondeName(),
                head.get().getSondeNo(),
                head.get().getSensorType(),
                head.get().getChannel(),
                head.get().getDelayTime(),
                head.get().getPreHeat(),
                head.get().getMeasMode(),
                head.get().getBurstTime(),
                head.get().getBurstCnt(),
                head.get().getIntervalData(),
                head.get().getWiperInterval(),
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getCHLA(),
                head.get().getCHLB(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getCh4(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getComment(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getLanderID().getASDBLanderID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<FLNTUHeadResponse> findHeadByID(@PathVariable Integer id) {
        Optional<ProcessedFLNTUHead> head = headRepository.findById(id);
        FLNTUHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new FLNTUHeadResponse(
                head.get().getHeadID(),
                head.get().getSondeName(),
                head.get().getSondeNo(),
                head.get().getSensorType(),
                head.get().getChannel(),
                head.get().getDelayTime(),
                head.get().getPreHeat(),
                head.get().getMeasMode(),
                head.get().getBurstTime(),
                head.get().getBurstCnt(),
                head.get().getIntervalData(),
                head.get().getWiperInterval(),
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getCHLA(),
                head.get().getCHLB(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getCh4(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getComment(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getLanderID().getASDBLanderID()
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
                    selData.getID(),
                    selData.getDate(),
                    selData.getTempDegC(),
                    selData.getChlFluPPB(),
                    selData.getChlAUgL(),
                    selData.getTurbMFTU(),
                    selData.getBattV(),
                    selData.getHeadID().getHeadID()
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<FLNTUDataResponse> findDataById(@PathVariable Integer id) {
        Optional<ProcessedFLNTUData> data = repository.findById(id);
        FLNTUDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new FLNTUDataResponse(
                data.get().getID(),
                data.get().getDate(),
                data.get().getTempDegC(),
                data.get().getChlFluPPB(),
                data.get().getChlAUgL(),
                data.get().getTurbMFTU(),
                data.get().getBattV(),
                data.get().getHeadID().getHeadID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<FLNTUDataResponse>> findDataByHeadId(@PathVariable Integer id) {
        List<ProcessedFLNTUData> data = repository.findDataFromHeadId(id);
        List<FLNTUDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedFLNTUData elem : data) {
            res.add(new FLNTUDataResponse(
                    elem.getID(),
                    elem.getDate(),
                    elem.getTempDegC(),
                    elem.getChlFluPPB(),
                    elem.getChlAUgL(),
                    elem.getTurbMFTU(),
                    elem.getBattV(),
                    elem.getHeadID().getHeadID()
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/upload_csv/test")
    public ResponseEntity<List<FLNTU_CSV_Request>> uploadProcessedCSV(@RequestParam("processedFile") MultipartFile processedFile) {
        List<FLNTU_CSV_Request> dataList;

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
            CsvToBean<FLNTU_CSV_Request> csvToBean = new CsvToBeanBuilder<FLNTU_CSV_Request>(reader)
                    .withType(FLNTU_CSV_Request.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            dataList = csvToBean.parse();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }

    @PostMapping("/upload_csv/header/test")
    public ResponseEntity<FLNTUHeadResponse> uploadProcessedHeader(@RequestParam("processedHead") MultipartFile processedHead) {

        if (processedHead.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

            FLNTUHeadResponse testResponse = new FLNTUHeadResponse(
                    null,
                    valuesMap.get(keyNames.get(0)),
                    valuesMap.get(keyNames.get(1)),
                    valuesMap.get(keyNames.get(2)),
                    Integer.parseInt(valuesMap.get(keyNames.get(3))),
                    Integer.parseInt(valuesMap.get(keyNames.get(4))),
                    Integer.parseInt(valuesMap.get(keyNames.get(5))),
                    Integer.parseInt(valuesMap.get(keyNames.get(6))),
                    Integer.parseInt(valuesMap.get(keyNames.get(7))),
                    Integer.parseInt(valuesMap.get(keyNames.get(8))),
                    Integer.parseInt(valuesMap.get(keyNames.get(9))),
                    Integer.parseInt(valuesMap.get(keyNames.get(10))),
                    Integer.parseInt(valuesMap.get(keyNames.get(11))),
                    valuesMap.get(keyNames.get(12)),
                    valuesMap.get(keyNames.get(13)),
                    Integer.parseInt(valuesMap.get(keyNames.get(14))),
                    Integer.parseInt(valuesMap.get(keyNames.get(15))),
                    valuesMap.get(keyNames.get(16)),
                    Double.parseDouble(valuesMap.get(keyNames.get(17)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(18)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(19)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(20)).split(",")[0]),
                    Integer.parseInt(valuesMap.get(keyNames.get(21))),
                    Integer.parseInt(valuesMap.get(keyNames.get(22))),
                    valuesMap.get(keyNames.get(23)),
                    valuesMap.get(keyNames.get(24)),
                    Integer.parseInt(valuesMap.get(keyNames.get(25))),
                    null
            );

            return new ResponseEntity<>(testResponse, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload_csv/combined/test")
    public ResponseEntity<List<FLNTU_CSV_Request>> processCompleteCSV(@RequestParam("processedFile") MultipartFile processedFile) {

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
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

            FLNTUHeadResponse testResponse = new FLNTUHeadResponse(
                    null,
                    valuesMap.get(keyNames.get(0)),
                    valuesMap.get(keyNames.get(1)),
                    valuesMap.get(keyNames.get(2)),
                    Integer.parseInt(valuesMap.get(keyNames.get(3))),
                    Integer.parseInt(valuesMap.get(keyNames.get(4))),
                    Integer.parseInt(valuesMap.get(keyNames.get(5))),
                    Integer.parseInt(valuesMap.get(keyNames.get(6))),
                    Integer.parseInt(valuesMap.get(keyNames.get(7))),
                    Integer.parseInt(valuesMap.get(keyNames.get(8))),
                    Integer.parseInt(valuesMap.get(keyNames.get(9))),
                    Integer.parseInt(valuesMap.get(keyNames.get(10))),
                    Integer.parseInt(valuesMap.get(keyNames.get(11))),
                    valuesMap.get(keyNames.get(12)),
                    valuesMap.get(keyNames.get(13)),
                    Integer.parseInt(valuesMap.get(keyNames.get(14))),
                    Integer.parseInt(valuesMap.get(keyNames.get(15))),
                    valuesMap.get(keyNames.get(16)),
                    Double.parseDouble(valuesMap.get(keyNames.get(17)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(18)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(19)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(20)).split(",")[0]),
                    Integer.parseInt(valuesMap.get(keyNames.get(21))),
                    Integer.parseInt(valuesMap.get(keyNames.get(22))),
                    valuesMap.get(keyNames.get(23)),
                    valuesMap.get(keyNames.get(24)),
                    Integer.parseInt(valuesMap.get(keyNames.get(25))),
                    null
            );

            List<FLNTU_CSV_Request> outputData = processData(reader);

            return new ResponseEntity<>(outputData, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
}
