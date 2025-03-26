package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.DO_CSV_Request;
import com.davies.lab.lander.FormattedModels.ResponseBody.DODataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.DOHeadResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/do")
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
                    head.getHeadID(),
                    head.getSondeName(),
                    head.getSondeNo(),
                    head.getSensorType(),
                    head.getChannel(),
                    head.getDelayTime(),
                    head.getPreHeat(),
                    head.getMeasModel(),
                    head.getBurstTime(),
                    head.getBurstCnt(),
                    head.getIntervalData(),
                    head.getSampleCnt(),
                    head.getStartTime(),
                    head.getEndTime(),
                    head.getDepAdiRho(),
                    head.getCoefDate(),
                    head.getCh1(),
                    head.getCh2(),
                    head.getCh3(),
                    head.getBuzzerEN(),
                    head.getBuzzerInterval(),
                    head.getCOMMENT(),
                    head.getSensorType2(),
                    head.getBuzzerNumber(),
                    head.getDepM(),
                    head.getSetSal(),
                    head.getFilmNo(),
                    head.getLanderID().getASDBLanderID()
            );

            for (ProcessedDOData data : head.getData()) {
                temp.createDODataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/headers/sanitized/{id}")
    public ResponseEntity<DOHeadResponse> findHeadWithoutData(@PathVariable Integer id) {
        Optional<ProcessedDOHead> head = headRepository.findById(id);
        DOHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new DOHeadResponse(
                head.get().getHeadID(),
                head.get().getSondeName(),
                head.get().getSondeNo(),
                head.get().getSensorType(),
                head.get().getChannel(),
                head.get().getDelayTime(),
                head.get().getPreHeat(),
                head.get().getMeasModel(),
                head.get().getBurstTime(),
                head.get().getBurstCnt(),
                head.get().getIntervalData(),
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getDepAdiRho(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getCOMMENT(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getDepM(),
                head.get().getSetSal(),
                head.get().getFilmNo(),
                head.get().getLanderID().getASDBLanderID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<DOHeadResponse> findHeadById(@PathVariable Integer id) {
        Optional<ProcessedDOHead> head = headRepository.findById(id);
        DOHeadResponse res;

        if (head.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new DOHeadResponse(
                head.get().getHeadID(),
                head.get().getSondeName(),
                head.get().getSondeNo(),
                head.get().getSensorType(),
                head.get().getChannel(),
                head.get().getDelayTime(),
                head.get().getPreHeat(),
                head.get().getMeasModel(),
                head.get().getBurstTime(),
                head.get().getBurstCnt(),
                head.get().getIntervalData(),
                head.get().getSampleCnt(),
                head.get().getStartTime(),
                head.get().getEndTime(),
                head.get().getDepAdiRho(),
                head.get().getCoefDate(),
                head.get().getCh1(),
                head.get().getCh2(),
                head.get().getCh3(),
                head.get().getBuzzerEN(),
                head.get().getBuzzerInterval(),
                head.get().getCOMMENT(),
                head.get().getSensorType2(),
                head.get().getBuzzerNumber(),
                head.get().getDepM(),
                head.get().getSetSal(),
                head.get().getFilmNo(),
                head.get().getLanderID().getASDBLanderID()
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
                    selData.getID(),
                    selData.getDate(),
                    selData.getTempDegC(),
                    selData.getDO(),
                    selData.getWeissDoMgL(),
                    selData.getBattV(),
                    selData.getGGDOMgL(),
                    selData.getBKDOMgL(),
                    selData.getHeadID().getHeadID()
            );

            res.add(temp);
        }

        return res;
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<DODataResponse> findDataById(@PathVariable Integer id) {
        Optional<ProcessedDOData> data = repository.findById(id);
        DODataResponse res;

        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new DODataResponse(
                data.get().getID(),
                data.get().getDate(),
                data.get().getTempDegC(),
                data.get().getDO(),
                data.get().getWeissDoMgL(),
                data.get().getBattV(),
                data.get().getGGDOMgL(),
                data.get().getBKDOMgL(),
                data.get().getHeadID().getHeadID()
        );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/headId/{id}")
    public ResponseEntity<List<DODataResponse>> findDataByHeadId(@PathVariable Integer id) {
        List<ProcessedDOData> data = repository.findDoDataByHeadId(id);
        List<DODataResponse> res = new ArrayList<>();

        if (data.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (ProcessedDOData elem : data) {
            res.add(new DODataResponse(
                    elem.getID(),
                    elem.getDate(),
                    elem.getTempDegC(),
                    elem.getDO(),
                    elem.getWeissDoMgL(),
                    elem.getBattV(),
                    elem.getGGDOMgL(),
                    elem.getBKDOMgL(),
                    elem.getHeadID().getHeadID()
            ));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/upload_csv/test")
    public ResponseEntity<List<DO_CSV_Request>> uploadProcessedCSV(@RequestParam("processedFile") MultipartFile processedFile) {
        List<DO_CSV_Request> dataList;

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(processedFile.getInputStream()))) {
            CsvToBean<DO_CSV_Request> csvToBean = new CsvToBeanBuilder<DO_CSV_Request>(reader)
                    .withType(DO_CSV_Request.class)
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
    public ResponseEntity<DOHeadResponse> uploadProcessedHeader(@RequestParam("processedHead") MultipartFile processedHead) {

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

            DOHeadResponse testResponse = new DOHeadResponse(
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
                    StringFormatting.formatDateString(valuesMap.get(keyNames.get(11))),
                    StringFormatting.formatDateString(valuesMap.get(keyNames.get(12))),
                    Double.parseDouble(valuesMap.get(keyNames.get(13))),
                    StringFormatting.formatCoefDateString(valuesMap.get(keyNames.get(14))),
                    Double.parseDouble(valuesMap.get(keyNames.get(15)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(16)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(17)).split(",")[0]),
                    Integer.parseInt(valuesMap.get(keyNames.get(18))),
                    Integer.parseInt(valuesMap.get(keyNames.get(19))),
                    valuesMap.get(keyNames.get(20)),
                    valuesMap.get(keyNames.get(21)),
                    Integer.parseInt(valuesMap.get(keyNames.get(22))),
                    Integer.parseInt(valuesMap.get(keyNames.get(23))),
                    Integer.parseInt(valuesMap.get(keyNames.get(24))),
                    valuesMap.get(keyNames.get(25)),
                    null
            );

            return new ResponseEntity<>(testResponse, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload_csv/combined/{lander_id}")
    public ResponseEntity<String> processCompleteCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("lander_id") String landerId) {
        Lander lander = landerRepository.findById(landerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


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

            ProcessedDOHead doHead = new ProcessedDOHead(
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
                    StringFormatting.formatDateString(valuesMap.get(keyNames.get(11))),
                    StringFormatting.formatDateString(valuesMap.get(keyNames.get(12))),
                    Double.parseDouble(valuesMap.get(keyNames.get(13))),
                    StringFormatting.formatCoefDateString(valuesMap.get(keyNames.get(14))),
                    Double.parseDouble(valuesMap.get(keyNames.get(15)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(16)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(17)).split(",")[0]),
                    Integer.parseInt(valuesMap.get(keyNames.get(18))),
                    Integer.parseInt(valuesMap.get(keyNames.get(19))),
                    valuesMap.get(keyNames.get(20)),
                    valuesMap.get(keyNames.get(21)),
                    Integer.parseInt(valuesMap.get(keyNames.get(22))),
                    Integer.parseInt(valuesMap.get(keyNames.get(23))),
                    Integer.parseInt(valuesMap.get(keyNames.get(24))),
                    valuesMap.get(keyNames.get(25)),
                    lander
            );

            ProcessedDOHead newHead = headRepository.save(doHead);

            List<DO_CSV_Request> outputData = processData(reader);

            if (outputData == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }



            for (DO_CSV_Request inputDataPoint : outputData) {
                ProcessedDOData newData = new ProcessedDOData(
                        StringFormatting.formatDateString(inputDataPoint.getDate()),
                        inputDataPoint.getTempDegC(),
                        inputDataPoint.getDo(),
                        inputDataPoint.getWeissDoMgL(),
                        inputDataPoint.getBattV(),
                        inputDataPoint.getGgDoMgL(),
                        inputDataPoint.getBkDoMgL(),
                        newHead
                );

                System.out.println(newData.getDate());
                repository.save(newData);
            }

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
}