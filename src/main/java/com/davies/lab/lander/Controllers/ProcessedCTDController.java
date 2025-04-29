package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.CTD_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateCTDDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateCTDHeaderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.CTDDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.CTDHeadResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedCTDData;
import com.davies.lab.lander.Models.ProcessedCTDHead;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.davies.lab.lander.Repositories.ProcessedCTDDataRepository;
import com.davies.lab.lander.Repositories.ProcessedCTDHeadRepository;
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
@RequestMapping("/api/processed/ctd")
public class ProcessedCTDController {
    @Autowired
    private LanderRepository landerRepository;
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
    public ResponseEntity<CTDHeadResponse> findHeadWithoutDataById(@PathVariable Long id) {
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

        res.setDataPointCount(head.get().getData().size());

        if (res.getStartTime() == null && res.getDataPointCount() > 0) {
            ProcessedCTDData firstData = repository.findFirstDataPointInHead(head.get().getHeadID());
            if (firstData != null) {
                res.setStartTime(firstData.getDate());
            }
        }

        if (res.getEndTime() == null && res.getDataPointCount() > 0) {
            ProcessedCTDData lastData = repository.findLastDataPointInHead(head.get().getHeadID());
            if (lastData != null) {
                res.setEndTime(lastData.getDate());
            }
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<CTDHeadResponse> findHeadById(@PathVariable Long id) {
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
    public ResponseEntity<CTDDataResponse> findDataById(@PathVariable("id") Long id) {
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
    public ResponseEntity<List<CTDDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
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

    @GetMapping("/data/headId/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<List<CTDDataResponse>> getDataByRange(@PathVariable("id") Long headId, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<CTDDataResponse> res = new ArrayList<>();

        List<ProcessedCTDData> data = repository.findDataByHeadAndDateRange(headId, startDate, endDate);

        for (ProcessedCTDData selData : data) {
            res.add(
                    new CTDDataResponse(
                            selData.getID(),
                            selData.getDate(),
                            selData.getTempDegC(),
                            selData.getSal(),
                            selData.getCondMsCm(),
                            selData.getEc25UsCm(),
                            selData.getBattV(),
                            selData.getHeadID().getHeadID()
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/count/{headID}")
    public ResponseEntity<Integer> getDataCountFromHeadID(@PathVariable("headID") Long headID) {
        Optional<Integer> dataCount = repository.findCountByHeadID(headID);

        return dataCount.map(integer -> new ResponseEntity<>(integer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("landerId") String landerID) {
       long startTime = System.nanoTime();

       Optional<Lander> selLander = landerRepository.findById(landerID);
       List<CTD_CSV_Request> rawData;
       ProcessedCTDHead savedHead;

       if (selLander.isEmpty()) {
           return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
       }

       if (processedFile.isEmpty()) {
           return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
       }

       if (selLander.get().getCTDHead() != null) {
            savedHead = headRepository.findById(selLander.get().getCTDHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
       } else {
           ProcessedCTDHead dummyHead = new ProcessedCTDHead();
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
           for (CTD_CSV_Request dataElement : rawData) {
               repository.save(new ProcessedCTDData(
                       StringFormatting.formatDataDateString(dataElement.getDate()),
                       dataElement.getTempDegC(),
                       dataElement.getSal(),
                       dataElement.getCondMsCm(),
                       dataElement.geteC25uScM(),
                       dataElement.getBattV(),
                       savedHead
               ));
           }
       } catch (Exception e) {
           return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
       }

       long endTime = System.nanoTime();
       long executionTime = endTime - startTime;

       System.out.println("Execution Time Seconds: " + (executionTime / 1_000_000) / 1_000);
       return new ResponseEntity<>("Posted!", HttpStatus.OK);
    }

    @PostMapping("/upload_csv/header/{landerID}")
    public ResponseEntity<String> uploadProcessedHeader(@RequestParam("processedHead") MultipartFile processedHead, @PathVariable("landerID") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedHead.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getCTDHead() == null) {
            ProcessedCTDHead newHead = new ProcessedCTDHead();
            newHead.setLanderID(selLander.get());
            ProcessedCTDHead savedHead = headRepository.save(newHead);
            selLander.get().setCTDHead(savedHead);
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

            UpdateCTDHeaderRequest updates = new UpdateCTDHeaderRequest (
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
                    Integer.parseInt(valuesMap.get(keyNames.get(14))),
                    Integer.parseInt(valuesMap.get(keyNames.get(15))),
                    Integer.parseInt(valuesMap.get(keyNames.get(16))),
                    Double.parseDouble(valuesMap.get(keyNames.get(17))),
                    StringFormatting.formatCoefDateString(valuesMap.get(keyNames.get(18))),
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
                    selLander.get(),
                    selLander.get().getCTDHead().getData()
            );

            updateCTDHeader(selLander.get().getCTDHead().getHeadID(), updates);

            return new ResponseEntity<>("Posted", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload_csv/combined/{lander_id}")
    public ResponseEntity<String> processCompleteCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("lander_id") String landerId) {
        Lander lander = landerRepository.findById(landerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (lander.getCTDHead() != null) {
            return new ResponseEntity<>("Header already present", HttpStatus.BAD_REQUEST);
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

            ProcessedCTDHead ctdHead = new ProcessedCTDHead (
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
                    Integer.parseInt(valuesMap.get(keyNames.get(14))),
                    Integer.parseInt(valuesMap.get(keyNames.get(15))),
                    Integer.parseInt(valuesMap.get(keyNames.get(16))),
                    Double.parseDouble(valuesMap.get(keyNames.get(17))),
                    StringFormatting.formatCoefDateString(valuesMap.get(keyNames.get(18))),
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
                    lander
            );

            ProcessedCTDHead newHead = headRepository.save(ctdHead);

            List<CTD_CSV_Request> outputData = processData(reader);

            if (outputData == null) {
                return new ResponseEntity<>("Bad Data", HttpStatus.BAD_REQUEST);
            }

            for (CTD_CSV_Request inputDataPoint : outputData) {
                ProcessedCTDData newData = new ProcessedCTDData(
                        StringFormatting.formatDateString(inputDataPoint.getDate()),
                        inputDataPoint.getTempDegC(),
                        inputDataPoint.getSal(),
                        inputDataPoint.getCondMsCm(),
                        inputDataPoint.geteC25uScM(),
                        inputDataPoint.getBattV(),
                        newHead
                );

                repository.save(newData);
            }

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    private List<CTD_CSV_Request> processData(BufferedReader reader) {
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

    @PutMapping("/update/header/{id}")
    public ResponseEntity<String> updateCTDHeader(@PathVariable("id") Long id, @RequestBody UpdateCTDHeaderRequest updates) {
        ProcessedCTDHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
        if (updates.getECA() != null) {
            selHead.setECA(updates.getECA());
        }
        if (updates.getECB() != null) {
            selHead.setECB(updates.getECB());
        }
        if (updates.getECDeg() != null) {
            selHead.setECDeg(updates.getECDeg());
        }
        if (updates.getECCoef() != null) {
            selHead.setECCoef(updates.getECCoef());
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
        if (updates.getCondDepB() != null) {
            selHead.setCondDepB(updates.getCondDepB());
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
    public ResponseEntity<String> updateCTDDataByID(@PathVariable("id") Long id, @RequestBody UpdateCTDDataRequest updates) {
        ProcessedCTDData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getDate() != null) {
            selData.setDate(updates.getDate());
        }
        if (updates.getTempDegC() != null) {
            selData.setTempDegC(updates.getTempDegC());
        }
        if (updates.getSal() != null) {
            selData.setSal(updates.getSal());
        }
        if (updates.getCondMsCm() != null) {
            selData.setCondMsCm(updates.getCondMsCm());
        }
        if (updates.getEc25UsCm() != null) {
            selData.setEc25UsCm(updates.getEc25UsCm());
        }
        if (updates.getBattV() != null) {
            selData.setBattV(updates.getBattV());
        }
        if (updates.getHeadID() != null) {
            updates.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedCTDHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        headRepository.delete(selHead);

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedCTDData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(selData);

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
