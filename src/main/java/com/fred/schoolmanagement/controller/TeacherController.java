package com.fred.schoolmanagement.controller;


import com.fred.schoolmanagement.dto.TeacherDTO;
import com.fred.schoolmanagement.dto.TeacherSaveDTO;
import com.fred.schoolmanagement.dto.TeacherUpdateDTO;
import com.fred.schoolmanagement.service.implimentation.AuthenticationTeacherImplementation;
import com.fred.schoolmanagement.service.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/teachers")
public class TeacherController {
    private TeacherService teacherService;
    private AuthenticationTeacherImplementation authenticationTeacherImplementation;
    @Autowired
    public TeacherController(TeacherService teacherService, AuthenticationTeacherImplementation authenticationTeacherImplementation) {
        this.teacherService = teacherService;
        this.authenticationTeacherImplementation = authenticationTeacherImplementation;
    }

    @PostMapping(path = "/add-teacher")
    public String saveTeacher(@RequestBody TeacherSaveDTO teacherSaveDTO){
        String teacherName = authenticationTeacherImplementation.addTeacher(teacherSaveDTO);
        return "Name" + teacherName;
    }

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token){
        boolean isVerified = authenticationTeacherImplementation.verifyEmail(token);
        if (isVerified){
            return "Email Verified Successfully";
        } else {
            return "Invalid or Expired verification Token";
        }
    }

    @GetMapping(path = "/view-all-teachers")
    public List<TeacherDTO> getAllTeachers(){
        List<TeacherDTO> allTeachers = teacherService.getAllTeachers();
        return allTeachers;
    }

    @PutMapping(path = "/update-teacher/{id}")
    public String updateTeacher(@PathVariable("id") long id, @RequestBody TeacherUpdateDTO teacherUpdateDTO){
        String updatedTeacher = teacherService.updateTeacher(id, teacherUpdateDTO);
        return updatedTeacher;
    }

    @DeleteMapping(path = "/delete-teacher/{id}")
    public String deleteTeacher(@PathVariable("id") long id){
        boolean deleteTeacher = teacherService.deleteTeacher(id);
        if (deleteTeacher){
            return "Teacher deleted successfully";
        } else {
            return "Teacher ID not Found";
        }
    }


}
