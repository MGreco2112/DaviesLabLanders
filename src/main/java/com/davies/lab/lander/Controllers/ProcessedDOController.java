package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.DO_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateDODataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateDOHeaderRequest;
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
    public ResponseEntity<DOHeadResponse> findHeadWithoutData(@PathVariable("id") Long id) {
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
    public ResponseEntity<DODataResponse> findDataById(@PathVariable("id") Long id) {
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
    public ResponseEntity<List<DODataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
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

    @GetMapping("/data/headId/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<List<DODataResponse>> getDataByRange(@PathVariable("id") Long id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<DODataResponse> res = new ArrayList<>();

        List<ProcessedDOData> data = repository.findDataByHeadAndDateRange(id, startDate, endDate);

        for (ProcessedDOData selData: data) {
            res.add(
                    new DODataResponse(
                            selData.getID(),
                            selData.getDate(),
                            selData.getTempDegC(),
                            selData.getDO(),
                            selData.getWeissDoMgL(),
                            selData.getBattV(),
                            selData.getGGDOMgL(),
                            selData.getBKDOMgL(),
                            selData.getHeadID().getHeadID()
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile") MultipartFile processedFile, @PathVariable("landerId") String landerID) {
        Optional<Lander> selLander = landerRepository.findById(landerID);
        List<DO_CSV_Request> rawData = null;
        ProcessedDOHead savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploadded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getDOHead() != null) {
            savedHead = headRepository.findById(selLander.get().getDOHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            ProcessedDOHead dummyHead = new ProcessedDOHead();
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
            for (DO_CSV_Request dataElement : rawData) {
                repository.save(new ProcessedDOData(
                        StringFormatting.formatDataDateString(dataElement.getDate()),
                        dataElement.getTempDegC(),
                        dataElement.getDo(),
                        dataElement.getWeissDoMgL(),
                        dataElement.getBattV(),
                        dataElement.getGgDoMgL(),
                        dataElement.getBkDoMgL(),
                        savedHead
                ));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }


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

            UpdateDOHeaderRequest updates = new UpdateDOHeaderRequest(
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
                    selLander.get(),
                    selLander.get().getDOHead().getData()
            );

            updateDOHeader(selLander.get().getDOHead().getHeadID(), updates);

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

        if (lander.getDOHead() != null) {
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
                return new ResponseEntity<>("Could not generate Data from File", HttpStatus.BAD_REQUEST);
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

                repository.save(newData);
            }

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
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