package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.TeacherDTO;
import com.fred.schoolmanagement.dto.TeacherUpdateDTO;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> getAllTeachers();

    String updateTeacher(long id, TeacherUpdateDTO teacherUpdateDTO);

    boolean deleteTeacher(long id);

}
