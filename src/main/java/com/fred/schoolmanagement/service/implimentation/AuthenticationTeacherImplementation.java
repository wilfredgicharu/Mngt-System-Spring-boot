package com.fred.schoolmanagement.service.implimentation;

import com.fred.schoolmanagement.dto.TeacherSaveDTO;
import com.fred.schoolmanagement.entity.Role;
import com.fred.schoolmanagement.entity.Subject;
import com.fred.schoolmanagement.entity.Teacher;
import com.fred.schoolmanagement.repository.RoleRepository;
import com.fred.schoolmanagement.repository.SubjectRepository;
import com.fred.schoolmanagement.repository.TeacherRepository;
import com.fred.schoolmanagement.service.interfaces.AuthenticationTeacher;
import com.fred.schoolmanagement.utils.EmailsManagement;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationTeacherImplementation implements AuthenticationTeacher {

    private TeacherRepository teacherRepository;
    private PasswordEncoder passwordEncoder;
    private EmailsManagement emailsManagement;
    private SubjectRepository subjectRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AuthenticationTeacherImplementation(TeacherRepository teacherRepository,
                                               PasswordEncoder passwordEncoder,
                                               EmailsManagement emailsManagement,
                                               SubjectRepository subjectRepository,
                                               RoleRepository roleRepository) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailsManagement = emailsManagement;
        this.subjectRepository = subjectRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String addTeacher(TeacherSaveDTO teacherSaveDTO) {
        try {

            Optional<Teacher> teacherExists = teacherRepository.findByIdNumber(teacherSaveDTO.getIdNumber());

            if (teacherExists.isPresent()){
                return ("Teacher with id: " + teacherSaveDTO.getIdNumber() + " already exists!!");
            }
            Optional<Teacher> teacherEmailExists = teacherRepository.findByEmail(teacherSaveDTO.getEmail());
            if (teacherEmailExists.isPresent()){
                return ("Teacher with email: " + teacherSaveDTO.getEmail() + " already exists!!");
            }

            String encodedPassword = passwordEncoder.encode(teacherSaveDTO.getPassword());
            String verificationToken = RandomStringUtils.randomAlphanumeric(32);

            // fetching subjects and assigning them to teachers

            Optional<Subject> subjectExists = subjectRepository.findById(teacherSaveDTO.getSubjectIds());
            if (!subjectExists.isPresent()){
                return ("Subject with that id does not exist");
            }

            Subject subject = subjectExists.get();

            Optional<Role> roleOptionalExists = roleRepository.findRoleByCode(teacherSaveDTO.getRoleCodes());
            if (!roleOptionalExists.isPresent()){
                return "Role with that code is not found";
            }

            Role role = roleOptionalExists.get();

            Teacher teacher = new Teacher(
                    teacherSaveDTO.getName(),
                    teacherSaveDTO.getAdress(),
                    teacherSaveDTO.getEmail(),
                    teacherSaveDTO.getPhoneNumber(),
                    teacherSaveDTO.getIdNumber(),
                    subject,
                    role,
                    encodedPassword,
                    "",
                    "",
                    verificationToken,
                    false,
                    false
            );

            teacherRepository.save(teacher);

            String emailBody = "Dear " + teacherSaveDTO.getName() + ",\nWelcome to Bright Star School. We are pleased to have you aboard.\nPlease verify your email by clicking the link below:\n" +
                    "http://localhost:5555/api/v1/teachers/verify-email?token=" + verificationToken;

            emailsManagement.sendEmails(teacherSaveDTO.getEmail(), "Registration successful", emailBody);

            return teacher.getName();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    public boolean verifyEmail(String token){
        Optional<Teacher> teacherOptional = teacherRepository.findByVerificationToken(token);
        if (teacherOptional.isPresent()){
            Teacher teacher = teacherOptional.get();
            teacher.setEmailVerified(true);
            teacher.setVerificationToken(null);
            teacherRepository.save(teacher);
            return true;
        }
        return false;
     }
}
