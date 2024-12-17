package com.fred.schoolmanagement.service.implimentation;


import com.fred.schoolmanagement.dto.TeacherDTO;
import com.fred.schoolmanagement.dto.TeacherUpdateDTO;
import com.fred.schoolmanagement.entity.Role;
import com.fred.schoolmanagement.entity.Subject;
import com.fred.schoolmanagement.entity.Teacher;
import com.fred.schoolmanagement.repository.RoleRepository;
import com.fred.schoolmanagement.repository.SubjectRepository;
import com.fred.schoolmanagement.repository.TeacherRepository;
import com.fred.schoolmanagement.service.interfaces.TeacherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImplementation implements TeacherService {

    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    private RoleRepository roleRepository;


    public TeacherServiceImplementation(TeacherRepository teacherRepository, SubjectRepository subjectRepository, RoleRepository roleRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> getTeacher = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOList = new ArrayList<>();

        for (Teacher teacher: getTeacher){

            TeacherDTO teacherDTO = new TeacherDTO(
                    teacher.getTeacherId(),
                    teacher.getName(),
                    teacher.getAdress(),
                    teacher.getPhoneNumber(),
                    teacher.getEmail(),
                    teacher.getIdNumber(),
                    teacher.getSubjects(),
                    teacher.getRoles(),
                    teacher.getAccessToken(),
                    teacher.getResetToken(),
                    teacher.isEmailVerified(),
                    teacher.isDeleted()
            );
            teacherDTOList.add(teacherDTO);

        }
        return teacherDTOList;

     }

    @Override
    public String updateTeacher(long id, TeacherUpdateDTO teacherUpdateDTO) {
        if (teacherRepository.existsById(id)){
            Teacher teacher = teacherRepository.getById(id);

            if (teacherUpdateDTO.getTeacherName() != null){
                teacher.setName(teacherUpdateDTO.getTeacherName());
            }
            if (teacherUpdateDTO.getAdress() != null){
                teacher.setAdress(teacherUpdateDTO.getAdress());
            }
            if (teacherUpdateDTO.getPhoneNumber() != 0){
                teacher.setPhoneNumber(teacherUpdateDTO.getPhoneNumber());
            }
            if (teacherUpdateDTO.getEmail() != null){
                teacher.setEmail(teacherUpdateDTO.getEmail());
            }
            if (teacherUpdateDTO.getIdNumber() != 0){
                teacher.setIdNumber(teacherUpdateDTO.getIdNumber());
            }
            if (teacherUpdateDTO.getSubjectIds() != 0){
                Subject subject = subjectRepository.findBySubjectId(teacherUpdateDTO.getSubjectIds());
                teacher.setSubjects(subject);
            }
            if (teacherUpdateDTO.getRoleCodes() != 0){
                Role role = roleRepository.getReferenceById(teacherUpdateDTO.getSubjectIds());
                teacher.setRoles(role);
            }
            teacherRepository.save(teacher);
            return "Teacher details updated successful";
        } else {
            return "Teacher with that ID not found";
        }
    }

    @Override
    public boolean deleteTeacher(long id) {
        if (teacherRepository.existsById(id)){
            Teacher teacher = teacherRepository.getById(id);
            teacher.setDeleted(true);

            teacherRepository.save(teacher);
            return true;
        } else {
            return false;
        }
    }
}
