package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.StudentDTO;
import com.fred.schoolmanagement.dto.StudentSaveDTO;
import com.fred.schoolmanagement.dto.StudentUpdateDTO;
import com.fred.schoolmanagement.entity.Student;

import java.util.List;

public interface StudentService {

   //  String addStudent(StudentSaveDTO studentSaveDTO);

    List<StudentDTO> getAllStudents();

    String updateStudent(long id, StudentUpdateDTO studentUpdateDTO);

    boolean deleteStudent(long id);

}
