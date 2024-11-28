package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.CourseDTO;
import com.fred.schoolmanagement.dto.CourseSaveDTO;
import com.fred.schoolmanagement.dto.CourseUpdateDTO;
import com.fred.schoolmanagement.entity.Course;

import java.util.List;

public interface CourseService {

    String addCourse(CourseSaveDTO courseSaveDTO);

    String updateCourse(long id, CourseUpdateDTO courseUpdateDTO);

    List<CourseDTO> getAllCourses();

    boolean deleteCourse(long id);

}
