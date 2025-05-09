package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.FLNTU_CSV_Request;
import com.davies.lab.lander.FormattedModels.RequestBody.HeaderDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateFLNTUDataRequest;
import com.davies.lab.lander.FormattedModels.RequestBody.UpdateFLNTUHeaderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.DataProgressResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.FLNTUDataResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.FLNTUHeadResponse;
import com.davies.lab.lander.FormattedModels.ResponseBody.TotalDataResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedFLNTUData;
import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.davies.lab.lander.Repositories.ProcessedFLNTUDataRepository;
import com.davies.lab.lander.Repositories.ProcessedFLNTUHeadRepository;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/flntu")
public class ProcessedFLNTUController {
    @Autowired
    private LanderRepository landerRepository;
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
    public ResponseEntity<FLNTUHeadResponse> findHeadWithoutDataById(@PathVariable("id") Long id) {
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

        res.setDataPointCount(head.get().getData().size());

        if (res.getStartTime() == null && res.getDataPointCount() > 0) {
            ProcessedFLNTUData firstData = repository.findFirstDataPointInHead(head.get().getHeadID());
            if (firstData != null) {
                res.setStartTime(firstData.getDate());
            }
        }

        if (res.getEndTime() == null && res.getDataPointCount() > 0) {
            ProcessedFLNTUData lastData = repository.findLastDataPointInHead(head.get().getHeadID());
            if (lastData != null) {
                res.setEndTime(lastData.getDate());
            }
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/headers/{id}")
    public ResponseEntity<FLNTUHeadResponse> findHeadByID(@PathVariable("id") Long id) {
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
    public ResponseEntity<FLNTUDataResponse> findDataById(@PathVariable("id") Long id) {
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
    public ResponseEntity<List<FLNTUDataResponse>> findDataByHeadId(@PathVariable("id") Long id) {
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

    @GetMapping("/data/headId/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<List<FLNTUDataResponse>> getDataByRange(@PathVariable("id") Long id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<FLNTUDataResponse> res = new ArrayList<>();

        List<ProcessedFLNTUData> data = repository.findDataByHeadAndDateRange(id, startDate, endDate);

        for (ProcessedFLNTUData selData : data) {
            res.add(
                    new FLNTUDataResponse(
                            selData.getID(),
                            selData.getDate(),
                            selData.getTempDegC(),
                            selData.getChlFluPPB(),
                            selData.getChlAUgL(),
                            selData.getTurbMFTU(),
                            selData.getBattV(),
                            selData.getHeadID().getHeadID()
                    )
            );
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/data/count/{landerID}")
    public ResponseEntity<DataProgressResponse> getDataCountFromHeadID(@PathVariable("landerID") String landerID) {
        Lander selLander = landerRepository.findById(landerID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (selLander.getFLNTUHead() != null) {

            ProcessedFLNTUHead selHead = headRepository.findById(selLander.getFLNTUHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
        List<FLNTU_CSV_Request> rawData = null;
        ProcessedFLNTUHead savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getFLNTUHead() != null) {
            savedHead = headRepository.findById(selLander.get().getFLNTUHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            ProcessedFLNTUHead dummyHead = new ProcessedFLNTUHead();
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
            for (FLNTU_CSV_Request dataElement : rawData) {
                repository.save(new ProcessedFLNTUData(
                        StringFormatting.formatDataDateString(dataElement.getDate()),
                        dataElement.getTempDegC(),
                        dataElement.getChlFluPpb(),
                        dataElement.getChlAUgL(),
                        dataElement.getTurbMFtu(),
                        dataElement.getBattV(),
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

        if (selLander.get().getFLNTUHead() == null) {
            ProcessedFLNTUHead newHead = new ProcessedFLNTUHead();
            newHead.setLanderID(selLander.get());
            ProcessedFLNTUHead savedHead = headRepository.save(newHead);
            selLander.get().setFLNTUHead(savedHead);
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

            UpdateFLNTUHeaderRequest updates = new UpdateFLNTUHeaderRequest(
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
                    StringFormatting.formatDateString(valuesMap.get(keyNames.get(12))),
                    StringFormatting.formatDateString(valuesMap.get(keyNames.get(13))),
                    Integer.parseInt(valuesMap.get(keyNames.get(14))),
                    Integer.parseInt(valuesMap.get(keyNames.get(15))),
                    StringFormatting.formatCoefDateString(valuesMap.get(keyNames.get(16))),
                    Double.parseDouble(valuesMap.get(keyNames.get(17)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(18)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(19)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(20)).split(",")[0]),
                    Integer.parseInt(valuesMap.get(keyNames.get(21))),
                    Integer.parseInt(valuesMap.get(keyNames.get(22))),
                    valuesMap.get(keyNames.get(23)),
                    valuesMap.get(keyNames.get(24)),
                    Integer.parseInt(valuesMap.get(keyNames.get(25))),
                    selLander.get(),
                    selLander.get().getFLNTUHead().getData()
            );

            updateFLNTUHeader(selLander.get().getFLNTUHead().getHeadID(), updates);

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

        if (lander.getFLNTUHead() != null) {
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

            ProcessedFLNTUHead flntuHead = new ProcessedFLNTUHead(
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
                    StringFormatting.formatDateString(valuesMap.get(keyNames.get(12))),
                    StringFormatting.formatDateString(valuesMap.get(keyNames.get(13))),
                    Integer.parseInt(valuesMap.get(keyNames.get(14))),
                    Integer.parseInt(valuesMap.get(keyNames.get(15))),
                    StringFormatting.formatCoefDateString(valuesMap.get(keyNames.get(16))),
                    Double.parseDouble(valuesMap.get(keyNames.get(17)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(18)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(19)).split(",")[0]),
                    Double.parseDouble(valuesMap.get(keyNames.get(20)).split(",")[0]),
                    Integer.parseInt(valuesMap.get(keyNames.get(21))),
                    Integer.parseInt(valuesMap.get(keyNames.get(22))),
                    valuesMap.get(keyNames.get(23)),
                    valuesMap.get(keyNames.get(24)),
                    Integer.parseInt(valuesMap.get(keyNames.get(25))),
                    lander
            );

            ProcessedFLNTUHead newHead = headRepository.save(flntuHead);

            List<FLNTU_CSV_Request> outputData = processData(reader);

            if (outputData == null) {
                return new ResponseEntity<>("Could not generate Data from File", HttpStatus.BAD_REQUEST);
            }

            for (FLNTU_CSV_Request inputDataPoint : outputData) {
                ProcessedFLNTUData newData = new ProcessedFLNTUData (
                        StringFormatting.formatDateString(inputDataPoint.getDate()),
                        inputDataPoint.getTempDegC(),
                        inputDataPoint.getChlFluPpb(),
                        inputDataPoint.getChlAUgL(),
                        inputDataPoint.getTurbMFtu(),
                        inputDataPoint.getBattV(),
                        newHead
                );

                ProcessedFLNTUData printData = repository.save(newData);

                System.out.println(printData.getDate());
            }

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
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

    @PutMapping("/update/header/{id}")
    public ResponseEntity<String> updateFLNTUHeader(@PathVariable("id") Long id, @RequestBody UpdateFLNTUHeaderRequest updates) {
        ProcessedFLNTUHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
        if (updates.getWiperInterval() != null) {
            selHead.setWiperInterval(updates.getWiperInterval());
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
        if (updates.getCHLA() != null) {
            selHead.setCHLA(updates.getCHLA());
        }
        if (updates.getCHLB() != null) {
            selHead.setCHLB(updates.getCHLB());
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
        if (updates.getComment() != null) {
            selHead.setComment(updates.getComment());
        }
        if (updates.getSensorType2() != null) {
            selHead.setSensorType2(updates.getSensorType2());
        }
        if (updates.getBuzzerNumber() != null) {
            selHead.setBuzzerNumber(updates.getBuzzerNumber());
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
    public ResponseEntity<String> updateFLNTUDataByID(@PathVariable("id") Long id, @RequestBody UpdateFLNTUDataRequest updates) {
        ProcessedFLNTUData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getDate() != null) {
            selData.setDate(updates.getDate());
        }
        if (updates.getTempDegC() != null) {
            selData.setTempDegC(updates.getTempDegC());
        }
        if (updates.getChlFluPPB() != null) {
            selData.setChlFluPPB(updates.getChlFluPPB());
        }
        if (updates.getChlAUgL() != null) {
            selData.setChlAUgL(updates.getChlAUgL());
        }
        if (updates.getTurbMFTU() != null) {
            selData.setTurbMFTU(updates.getTurbMFTU());
        }
        if (updates.getBattV() != null) {
            selData.setBattV(updates.getBattV());
        }
        if (updates.getHeadID() != null) {
            selData.setHeadID(updates.getHeadID());
        }

        repository.save(selData);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/header/{id}")
    public ResponseEntity<String> deleteHeaderByID(@PathVariable("id") Long id) {
        ProcessedFLNTUHead selHead = headRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteAll(selHead.getData());

        selHead.setData(null);

        selHead.setLanderID(null);

        headRepository.save(selHead);

        headRepository.delete(selHead);

        return new ResponseEntity<>("Deleted Head", HttpStatus.OK);
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<String> deleteDataByID(@PathVariable("id") Long id) {
        ProcessedFLNTUData selData = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(selData);

        return new ResponseEntity<>("Deleted Data", HttpStatus.OK);
    }
}
