package com.davies.lab.lander.Controllers;

import com.davies.lab.lander.FormattedModels.RequestBody.NewLanderRequest;
import com.davies.lab.lander.FormattedModels.ResponseBody.LanderResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;
import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedCTDHead;
import com.davies.lab.lander.Models.ProcessedDOHead;
import com.davies.lab.lander.Models.ProcessedFLNTUHead;
import com.davies.lab.lander.Repositories.LanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/search/id/{landerID}")
    public Set<LanderResponse> getLandersByIDQuery(@PathVariable("landerID") String landerID) {
        Set<LanderResponse> res = new HashSet<>();

        Set<Lander> landers = repository.selectLandersBySimilarID(landerID);

        for (Lander lander : landers) {
            res.add(
                    new LanderResponse(
                            lander.getASDBLanderID(),
                            lander.getLanderPlatform(),
                            lander.getASDBROVDiveID()
                    )
            );
        }

        return res;
    }

    @GetMapping("/search/date/{inputDate}")
    public Set<LanderResponse> getLandersByDateQuery(@PathVariable("inputDate") String inputDate) {
        Set<Lander> landerSet = repository.selectLandersByDateRange(inputDate);
        Set<LanderResponse> res = new HashSet<>();

        for (Lander selLander : landerSet) {
            res.add(
                    new LanderResponse(
                            selLander.getASDBLanderID(),
                            selLander.getLanderPlatform(),
                            selLander.getASDBROVDiveID()
                    )
            );
        }

        return res;
    }

    @PostMapping("/new_lander")
    public ResponseEntity<String> postNewLander(@RequestBody NewLanderRequest newLander) {
        Lander lander = new Lander(
                newLander.getASDBLanderID(),
                newLander.getLanderPlatform(),
                newLander.getASDBROVDiveID(),
                StringFormatting.formatFrontendDateString(newLander.getDeploymentDateAndTime()),
                StringFormatting.formatFrontendDateString(newLander.getRecoveryDateAndTime())
        );

        repository.save(lander);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
