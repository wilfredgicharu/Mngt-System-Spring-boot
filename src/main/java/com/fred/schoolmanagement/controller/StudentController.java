package com.fred.schoolmanagement.controller;


import com.fred.schoolmanagement.dto.StudentDTO;
import com.fred.schoolmanagement.dto.StudentSaveDTO;
import com.fred.schoolmanagement.dto.StudentUpdateDTO;
import com.fred.schoolmanagement.service.implimentation.AuthenticationStudentServiceImplementation;
import com.fred.schoolmanagement.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private StudentService studentService;
    private AuthenticationStudentServiceImplementation authenticationStudentServiceImplementation;

    @Autowired
    public StudentController(StudentService studentService,
                             AuthenticationStudentServiceImplementation authenticationStudentServiceImplementation
                             ) {
        this.studentService = studentService;
        this.authenticationStudentServiceImplementation = authenticationStudentServiceImplementation;
    }

    @PostMapping(path = "/add-student")
    public String saveStudent(@RequestBody StudentSaveDTO studentSaveDTO){
        String studentName = authenticationStudentServiceImplementation.addStudent(studentSaveDTO);
        return "Name" + studentName;
    }

    @GetMapping(path = "/verify-email")
    public String verifyEmail(@RequestParam("token") String token ){
        boolean isVerified  = authenticationStudentServiceImplementation.verifyEmail(token);
        if (isVerified){
            return "Email verified successfully";
        } else {
            return "Invalid or expired Token";
        }
    }

    @GetMapping(path = "/view-all-students")
    public List<StudentDTO> getAllStudents(){
        List<StudentDTO> AllStudents = studentService.getAllStudents();
        return AllStudents;
    }

    @PutMapping(path = "/update-student/{id}")
    public String updateStudent(@PathVariable("id")long id, StudentUpdateDTO studentUpdateDTO){
        String updateStudent = studentService.updateStudent(id, studentUpdateDTO);
        return updateStudent;
    }

    @DeleteMapping(path = "/delete-student")
    public String deleteStudent(@PathVariable("id") long id){
        boolean deleteStudent = studentService.deleteStudent(id);
        if (deleteStudent){
            return "Student deleted Successfully";
        } else {
            return "Student ID not Found";
        }
    }


}
