package com.fred.schoolmanagement.controller;


import com.fred.schoolmanagement.dto.FeesPerTermDTO;
import com.fred.schoolmanagement.dto.FeesPerTermSaveDTO;
import com.fred.schoolmanagement.dto.FeesPerTermUpdateDTO;
import com.fred.schoolmanagement.entity.FeesPerTerm;
import com.fred.schoolmanagement.service.interfaces.FeesPerTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/fees-per-term")
public class FeePerTermController {
    private FeesPerTermService feesPerTermService;

    @Autowired
    public FeePerTermController(FeesPerTermService feesPerTermService) {
        this.feesPerTermService = feesPerTermService;
    }

    @PostMapping(path = "/add-term-fee")
    public ResponseEntity<String> createFeePerTerm(@RequestBody FeesPerTermSaveDTO feesPerTermSaveDTO){
        String response = feesPerTermService.addFeesPerTerm(feesPerTermSaveDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/view-all-fees-per-term")
    public List<FeesPerTermDTO> getAllFeesTerm(){
        List<FeesPerTermDTO> allFeesPerTerm = feesPerTermService.getAllFeesPerTerm();
        return allFeesPerTerm;
    }

    @PutMapping(path = "update-fee-per-term")
    public ResponseEntity<String> updateFeePerTerm(@PathVariable("id") long id, @RequestBody FeesPerTermUpdateDTO feesPerTermUpdateDTO){
        String response = feesPerTermService.updateFeesPerTerm(id, feesPerTermUpdateDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/delete-fee-per-term")
    public String deleteFeePerTerm(@PathVariable("id")long id){
        boolean deleteFeePerTerm = feesPerTermService.deleteFeesPerTerm(id);

        if (deleteFeePerTerm){
            return "Fee per Term Deleted successfully";
        } else {
            return "Fee per Term ID not Found";
        }
    }
}
