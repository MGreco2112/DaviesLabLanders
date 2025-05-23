package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.AlbexCTD_CSV_Request;
import com.davies.lab.lander.FormattedModels.ResponseBody.AlbexCTDHeadResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedAlbexCTDData;
import com.davies.lab.lander.Models.ProcessedAlbexCTDHeader;
import com.davies.lab.lander.Repositories.LanderRepository;
import com.davies.lab.lander.Repositories.ProcessedAlbexCTDDataRepository;
import com.davies.lab.lander.Repositories.ProcessedAlbexCTDHeaderRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/processed/albex_ctd")
public class ProcessedAlbexCTDController {
    @Autowired
    private LanderRepository landerRepository;
    @Autowired
    private ProcessedAlbexCTDDataRepository repository;
    @Autowired
    private ProcessedAlbexCTDHeaderRepository headerRepository;

    @GetMapping("/headers")
    public List<AlbexCTDHeadResponse> findAllHeads() {
        List<ProcessedAlbexCTDHeader> heads = headerRepository.findAll();
        List<AlbexCTDHeadResponse> res = new ArrayList<>();

        for (ProcessedAlbexCTDHeader head : heads) {
            AlbexCTDHeadResponse temp = new AlbexCTDHeadResponse(
                    head.getHeadID(),
                    head.getLanderID().getASDBLanderID()
            );

            for (ProcessedAlbexCTDData data : head.getData()) {
                temp.createDataResponse(data);
            }

            res.add(temp);
        }

        return res;
    }

    @PostMapping("/upload_csv/data/{landerId}")
    public ResponseEntity<String> uploadProcessedCSV(@RequestParam("processedFile")MultipartFile processedFile, @PathVariable("landerId") String LanderID) {

        Optional<Lander> selLander = landerRepository.findById(LanderID);
        List<AlbexCTD_CSV_Request> rawData;
        ProcessedAlbexCTDHeader savedHead;

        if (selLander.isEmpty()) {
            return new ResponseEntity<>("Unable to locate Lander", HttpStatus.BAD_REQUEST);
        }

        if (processedFile.isEmpty()) {
            return new ResponseEntity<>("Missing Uploaded CSV in Request", HttpStatus.BAD_REQUEST);
        }

        if (selLander.get().getAlbexHead() != null) {
            savedHead = headerRepository.findById(selLander.get().getAlbexHead().getHeadID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            ProcessedAlbexCTDHeader dummyHead = new ProcessedAlbexCTDHeader();
            dummyHead.setLanderID(selLander.get());

            savedHead = headerRepository.save(dummyHead);
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
            for (AlbexCTD_CSV_Request dataElement : rawData) {
                repository.save( new ProcessedAlbexCTDData(
                        StringFormatting.formatDataDateString(dataElement.getDate()),
                        dataElement.getSalinity_psu(),
                        dataElement.getTemperature_c(),
                        dataElement.getOxygen_ml_l(),
                        dataElement.getOxygen_sat_percent(),
                        dataElement.getTurbidity_ntu(),
                        dataElement.getChla_ug_ml(),
                        dataElement.getPressure_db(),
                        dataElement.getFlag(),
                        savedHead
                ));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Posted!", HttpStatus.OK);
    }

    private List<AlbexCTD_CSV_Request> processData(BufferedReader reader) {
        List<AlbexCTD_CSV_Request> dataList;

        try {
            CsvToBean<AlbexCTD_CSV_Request> csvToBean = new CsvToBeanBuilder<AlbexCTD_CSV_Request>(reader)
                    .withType(AlbexCTD_CSV_Request.class)
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
