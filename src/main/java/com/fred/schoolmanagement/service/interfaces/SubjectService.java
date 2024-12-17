package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.SubjectDTO;
import com.fred.schoolmanagement.dto.SubjectSaveDTO;
import com.fred.schoolmanagement.dto.SubjectUpdateDTO;

import java.util.List;

public interface SubjectService {

    String addSubject(SubjectSaveDTO subjectSaveDTO);

    List<SubjectDTO> getAllSubjects();

    String updateSubject(long id, SubjectUpdateDTO subjectUpdateDTO);

    boolean deleteSubject(long id);

}
