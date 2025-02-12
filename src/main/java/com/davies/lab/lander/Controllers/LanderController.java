package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.ResponseBody.LanderResponse;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedCTDHead;
import com.davies.lab.lander.Models.ProcessedDOHead;
import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import com.davies.lab.lander.Repositories.LanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/landers")
public class LanderController {
    @Autowired
    private LanderRepository repository;

    @GetMapping("/all")
    public List<LanderResponse> findAllLanders() {
         List<Lander> landerList = repository.findAll();
         List<LanderResponse> resList = new ArrayList<>();

         for (Lander lander : landerList) {
             LanderResponse res = new LanderResponse(lander.getASDBLanderID(), lander.getLanderPlatform(), lander.getASDBROVDiveID());

             for (ProcessedCTDHead head : lander.getCTDHeads()) {
                 res.createCTDHeadResponse(head);
             }

             for (ProcessedDOHead head : lander.getDOHeads()) {
                 res.createDOHeadResponse(head);
             }

             for (ProcessedFLNTUHead head : lander.getFLNTUHeads()) {
                 res.createFLNTUHeadResponse(head);
             }

             resList.add(res);
         }

         return resList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanderResponse> findLanderByID(@PathVariable String id) {
        Optional<Lander> lander = repository.findById(id);
        LanderResponse res;
        if (lander.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        res = new LanderResponse(lander.get().getASDBLanderID(), lander.get().getLanderPlatform(), lander.get().getASDBROVDiveID());

        for (ProcessedCTDHead head : lander.get().getCTDHeads()) {
            res.createCTDHeadResponse(head);
        }

        for (ProcessedDOHead head : lander.get().getDOHeads()) {
            res.createDOHeadResponse(head);
        }

        for (ProcessedFLNTUHead head : lander.get().getFLNTUHeads()) {
            res.createFLNTUHeadResponse(head);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
