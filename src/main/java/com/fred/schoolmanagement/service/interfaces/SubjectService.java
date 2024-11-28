package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.SubjectDTO;
import com.fred.schoolmanagement.dto.SubjectSaveDTO;

import java.util.List;

public interface SubjectService {

    String addSubject(SubjectSaveDTO subjectSaveDTO);

    List<SubjectDTO> getAllSubjects();

    boolean deleteSubject(long id);

}
