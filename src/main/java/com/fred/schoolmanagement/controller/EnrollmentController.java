package com.fred.schoolmanagement.controller;


import com.fred.schoolmanagement.dto.EnrollmentDTO;
import com.fred.schoolmanagement.dto.EnrollmentSaveDTO;
import com.fred.schoolmanagement.dto.EnrollmentUpdateDTO;
import com.fred.schoolmanagement.service.interfaces.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/enrollments")
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping(path = "/add-enrollments")
    public String saveEnrollment(@RequestBody EnrollmentSaveDTO enrollmentSaveDTO){
        String results = enrollmentService.addEnrollment(enrollmentSaveDTO);
        return results;
    }

    @GetMapping(path = "/view-all-enrollments")
    public List<EnrollmentDTO> getAllEnrollments(){
        List<EnrollmentDTO> results = enrollmentService.getAllEnrollments();
        return results;
    }

    @PutMapping(path = "/update-enrollment/{id}")
    public String updateEnrollment(@PathVariable("id")long id, EnrollmentUpdateDTO enrollmentUpdateDTO){
        String results = enrollmentService.updateEnrollment(id, enrollmentUpdateDTO);
        return results;
    }

    @DeleteMapping(path = "/delete-enrollment/{id}")
    public String deleteEnrollment(@PathVariable("id")long id){
        boolean deleteEnrollment = enrollmentService.deleteEnrollment(id);
        if (deleteEnrollment){
            return "Enrollment deleted successful";
        } else {
            return "Course ID not Found";
        }
    }

}
