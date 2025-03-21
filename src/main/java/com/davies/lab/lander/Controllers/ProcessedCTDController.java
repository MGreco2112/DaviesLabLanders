package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.CTD_CSV_Request;
import com.davies.lab.lander.FormattedModels.ResponseBody.CTDDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.CTDHeadResponse;
import com.davies.lab.lander.Models.ProcessedCTDData;
import com.davies.lab.lander.Models.ProcessedCTDHead;
import com.davies.lab.lander.Repositories.ProcessedCTDDataRepository;
import com.davies.lab.lander.Repositories.ProcessedCTDHeadRepository;
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
@RequestMapping("/api/processed/ctd")
public class ProcessedCTDController {
    @Autowired
    private ProcessedCTDDataRepository repository;
    @Autowired
    private ProcessedCTDHeadRepository headRepository;

    //Head Routes
    @GetMapping("/headers")
    public List<CTDHeadResponse> findAllHeads() {
        List<ProcessedCTDHead> heads = headRepository.findAll();
        List<CTDHeadResponse> res = new ArrayList<>();

        for (ProcessedCTDHead head : heads) {
            CTDHeadResponse temp = new CTDHeadResponse(
                    head.getHeadID(),
                    head.getSondeName(),
                    head.getSondeNo(),
                    head.getSensorType(),
                    head.getChannel(),
                    head.getDelayTime(),
                    head.getPreHeat(),
                    head.getMeasMode(),
                    head.getBurstTime(),
                    head.getBurstCnt(),
                    head.getIntervalData(),
                    head.getSampleCnt(),
                    head.getStartTime(),
                    head.getEndTime(),
                    head.getDepAdiRho(),
                    head.getECA(),
                    head.getECB(),
                    head.getECDeg(),
                    head.getECCoef(),
                    head.getCoefDate(),
                    head.getCh1(),
                    head.getCh2(),
                    head.getCh3(),
                    head.getCh4(),
                    head.getBuzzerEN(),
                    head.getBuzzerInterval(),
                    head.getCOMMENT(),
                    head.getSensorType2(),
                    head.getBuzzerNumber(),
                    head.getDepM(),
                    head.getCondDepB(),
                    head.getLanderID().getASDBLanderID()
                    );

            for (ProcessedCTDData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<CTDHeadResponse> findHeadWithoutDataById(@PathVariable Integer id) {
        Optional<ProcessedCTDHead> head = headRepository.findById(id);
        CTDHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CTDHeadResponse(
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
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getDepAdiRho(),
                head.get().getECA(),
                head.get().getECB(),
                head.get().getECDeg(),
                head.get().getECCoef(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getCh4(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getCOMMENT(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getDepM(),
                head.get().getCondDepB(),
                head.get().getLanderID().getASDBLanderID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<CTDHeadResponse> findHeadById(@PathVariable Integer id) {
        Optional<ProcessedCTDHead> head = headRepository.findById(id);
        CTDHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CTDHeadResponse(
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
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getDepAdiRho(),
                head.get().getECA(),
                head.get().getECB(),
                head.get().getECDeg(),
                head.get().getECCoef(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getCh4(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getCOMMENT(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getDepM(),
                head.get().getCondDepB(),
                head.get().getLanderID().getASDBLanderID()
        );

        for (ProcessedCTDData data : head.get().getData()) {
            res.createDataResponse(data);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //Data Routes
    @GetMapping("/data")
    public List<CTDDataResponse> findAllEntries() {
        List<ProcessedCTDData> data = repository.findAll();
        List<CTDDataResponse> res = new ArrayList<>();

        for (ProcessedCTDData dataPoint : data) {
            CTDDataResponse temp = new CTDDataResponse(
                    dataPoint.getID(),
                    dataPoint.getDate(),
                    dataPoint.getTempDegC(),
                    dataPoint.getSal(),
                    dataPoint.getCondMsCm(),
                    dataPoint.getEc25UsCm(),
                    dataPoint.getBattV(),
                    dataPoint.getHeadID().getHeadID()
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<CTDDataResponse> findDataById(@PathVariable Integer id) {
        Optional<ProcessedCTDData> data = repository.findById(id);
        CTDDataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new CTDDataResponse(
                data.get().getID(),
                data.get().getDate(),
                data.get().getTempDegC(),
                data.get().getSal(),
                data.get().getCondMsCm(),
                data.get().getEc25UsCm(),
                data.get().getBattV(),
                data.get().getHeadID().getHeadID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<CTDDataResponse>> findDataByHeadId(@PathVariable Integer id) {
        List<ProcessedCTDData> data = repository.findDataByHeadId(id);
        List<CTDDataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedCTDData elem : data) {
            res.add(new CTDDataResponse(
                    elem.getID(),
                    elem.getDate(),
                    elem.getTempDegC(),
                    elem.getSal(),
                    elem.getCondMsCm(),
                    elem.getEc25UsCm(),
                    elem.getBattV(),
                    elem.getHeadID().getHeadID()
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/upload_csv/test")
    //TODO: Modify route to save each row to DB
    public ResponseEntity<List<CTD_CSV_Request>> uploadProcessedCSV(@RequestParam("processedFile") MultipartFile processedFile) {
        List<CTD_CSV_Request> dataList;

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
            CsvToBean<CTD_CSV_Request> csvToBean = new CsvToBeanBuilder<CTD_CSV_Request>(reader)
                    .withType(CTD_CSV_Request.class)
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
    //TODO: Connect this method to main upload method, save header and datapoints to DB
    public ResponseEntity<CTDHeadResponse> uploadProcessedHeader(@RequestParam("processedHead") MultipartFile processedHead) {

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

            CTDHeadResponse testResponse = new CTDHeadResponse (
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
                    valuesMap.get(keyNames.get(11)),
                    valuesMap.get(keyNames.get(12)),
                    Double.parseDouble(valuesMap.get(keyNames.get(13))),
                    Integer.parseInt(valuesMap.get(keyNames.get(14))),
                    Integer.parseInt(valuesMap.get(keyNames.get(15))),
                    Integer.parseInt(valuesMap.get(keyNames.get(16))),
                    Double.parseDouble(valuesMap.get(keyNames.get(17))),
                    valuesMap.get(keyNames.get(18)),
                    Double.parseDouble(valuesMap.get(keyNames.get(19)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(20)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(21)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(22)).split(",")[0]),
                    Integer.parseInt(valuesMap.get(keyNames.get(23))),
                    Integer.parseInt(valuesMap.get(keyNames.get(24))),
                    valuesMap.get(keyNames.get(25)),
                    valuesMap.get(keyNames.get(26)),
                    Integer.parseInt(valuesMap.get(keyNames.get(27))),
                    Integer.parseInt(valuesMap.get(keyNames.get(28))),
                    Integer.parseInt(valuesMap.get(keyNames.get(29))),
                    null
            );

            return new ResponseEntity<>(testResponse, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload_csv/combined/test")
    public ResponseEntity<List<CTD_CSV_Request>> processCompleteCSV(@RequestParam("processedFile") MultipartFile processedFile) {

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

            CTDHeadResponse testResponse = new CTDHeadResponse (
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
                    valuesMap.get(keyNames.get(11)),
                    valuesMap.get(keyNames.get(12)),
                    Double.parseDouble(valuesMap.get(keyNames.get(13))),
                    Integer.parseInt(valuesMap.get(keyNames.get(14))),
                    Integer.parseInt(valuesMap.get(keyNames.get(15))),
                    Integer.parseInt(valuesMap.get(keyNames.get(16))),
                    Double.parseDouble(valuesMap.get(keyNames.get(17))),
                    valuesMap.get(keyNames.get(18)),
                    Double.parseDouble(valuesMap.get(keyNames.get(19)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(20)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(21)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(22)).split(",")[0]),
                    Integer.parseInt(valuesMap.get(keyNames.get(23))),
                    Integer.parseInt(valuesMap.get(keyNames.get(24))),
                    valuesMap.get(keyNames.get(25)),
                    valuesMap.get(keyNames.get(26)),
                    Integer.parseInt(valuesMap.get(keyNames.get(27))),
                    Integer.parseInt(valuesMap.get(keyNames.get(28))),
                    Integer.parseInt(valuesMap.get(keyNames.get(29))),
                    null
            );


            List<CTD_CSV_Request> outputData = processData(reader, processedFile);

            return new ResponseEntity<>(outputData, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    private List<CTD_CSV_Request> processData(BufferedReader reader, MultipartFile processedFile) {
        List<CTD_CSV_Request> dataList;

        try {
            CsvToBean<CTD_CSV_Request> csvToBean = new CsvToBeanBuilder<CTD_CSV_Request>(reader)
                    .withType(CTD_CSV_Request.class)
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
