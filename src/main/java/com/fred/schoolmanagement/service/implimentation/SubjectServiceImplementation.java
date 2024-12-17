package com.fred.schoolmanagement.service.implimentation;


import com.fasterxml.jackson.annotation.OptBoolean;
import com.fred.schoolmanagement.dto.SubjectDTO;
import com.fred.schoolmanagement.dto.SubjectSaveDTO;
import com.fred.schoolmanagement.dto.SubjectUpdateDTO;
import com.fred.schoolmanagement.entity.Subject;
import com.fred.schoolmanagement.repository.SubjectRepository;
import com.fred.schoolmanagement.service.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImplementation implements SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImplementation(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public String addSubject(SubjectSaveDTO subjectSaveDTO) {
        Optional<Subject> subjectCodeExists = subjectRepository.findBySubjectCode(subjectSaveDTO.getSubjectCode());
        Optional<Subject> subjectNameExists = subjectRepository.findBySubjectName(subjectSaveDTO.getSubjectName());

        if (subjectCodeExists.isPresent()){
            return "subject with that code exists";
        }
        if (subjectNameExists.isPresent()){
            return "Subject with that Name already exists";
        }
        Subject subject = new Subject(
                subjectSaveDTO.getSubjectCode(),
                subjectSaveDTO.getSubjectName()
        );

        subjectRepository.save(subject);

        return "Subject created successfully";
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> allSubjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOList = new ArrayList<>();

        for (Subject subject: allSubjects){
            SubjectDTO subjectDTO = new SubjectDTO(
                    subject.getSubjectId(),
                    subject.getSubjectCode(),
                    subject.getSubjectName()
            );
            subjectDTOList.add(subjectDTO);
        }
        return subjectDTOList;
     }

    @Override
    public String updateSubject(long id, SubjectUpdateDTO subjectUpdateDTO) {
        if (subjectRepository.existsById(id)){
            Subject subject = subjectRepository.getById(id);

            if (subjectUpdateDTO.getSubjectCode() != 0){
                subject.setSubjectCode(subjectUpdateDTO.getSubjectCode());
            }
            if (subjectUpdateDTO.getSubjectName() != null){
                subject.setSubjectName(subjectUpdateDTO.getSubjectName());
            }

            subjectRepository.save(subject);
            return "Subject Details updated successfully";

        } else {
            return "Subject with that ID not found";
        }
    }

    @Override
    public boolean deleteSubject(long id) {
        if (subjectRepository.existsById(id)){
            subjectRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
