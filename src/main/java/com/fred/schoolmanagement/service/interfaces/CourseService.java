package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.CourseSaveDTO;
import com.fred.schoolmanagement.dto.CourseUpdateDTO;

public interface CourseService {

    String addCourse(CourseSaveDTO courseSaveDTO);

    String update(long id, CourseUpdateDTO courseUpdateDTO);

    boolean deleteCourse(long id);

}
