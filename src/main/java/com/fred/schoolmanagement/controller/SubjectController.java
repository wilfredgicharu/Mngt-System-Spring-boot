package com.fred.schoolmanagement.controller;


import com.fred.schoolmanagement.dto.SubjectDTO;
import com.fred.schoolmanagement.dto.SubjectSaveDTO;
import com.fred.schoolmanagement.dto.SubjectUpdateDTO;
import com.fred.schoolmanagement.entity.Subject;
import com.fred.schoolmanagement.service.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/subjects")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @PostMapping(path = "/add-subject")
    public ResponseEntity<String> addSubject(@RequestBody SubjectSaveDTO subjectSaveDTO){
        String response = subjectService.addSubject(subjectSaveDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/get-all-subjects")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects(){
        List<SubjectDTO> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @PutMapping(path = "/update-subject/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable("id") long id, @RequestBody SubjectUpdateDTO subjectUpdateDTO){
        String response = subjectService.updateSubject(id, subjectUpdateDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/delete-subject/{id}")
    public String deleteSubject(@PathVariable("id")long id){
        boolean deleteSubject = subjectService.deleteSubject(id);
        if (deleteSubject){
            return "Subject Deleted Successfully";
        } else {
            return "Subject ID not Found";
        }
    }

}
