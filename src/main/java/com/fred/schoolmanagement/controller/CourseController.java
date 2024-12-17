package com.fred.schoolmanagement.controller;


import com.fred.schoolmanagement.dto.CourseDTO;
import com.fred.schoolmanagement.dto.CourseSaveDTO;
import com.fred.schoolmanagement.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/batches")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(path = "/add-courses")
    public String saveCourse(@RequestBody CourseSaveDTO courseSaveDTO){
        String courseName = courseService.addCourse(courseSaveDTO);
        return "Course created successfully. \n Course Name:  " + courseName;
    }

    @GetMapping(path = "/view-all-Courses")
    public List<CourseDTO> getAllCourses(){
        List<CourseDTO> allCourses = courseService.getAllCourses();
        return allCourses;
    }

    @DeleteMapping(path = "/delete-course/{id}")
    public String deleteCourse(@PathVariable("id") long id){
        boolean deleteCourse = courseService.deleteCourse(id);
        if (deleteCourse){
            return "Course deleted successfully";
        } else {
            return "Course ID not found";
        }
    }

}
