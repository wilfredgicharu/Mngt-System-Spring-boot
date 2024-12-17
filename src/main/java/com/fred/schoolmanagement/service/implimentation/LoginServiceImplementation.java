package com.fred.schoolmanagement.service.implimentation;


import com.fred.schoolmanagement.dto.LoginDTO;
import com.fred.schoolmanagement.dto.LoginResponseDTO;
import com.fred.schoolmanagement.entity.Student;
import com.fred.schoolmanagement.entity.Teacher;
import com.fred.schoolmanagement.repository.StudentRepository;
import com.fred.schoolmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImplementation {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceImplementation(StudentRepository studentRepository, TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO authenticateUser(LoginDTO loginDTO){
        Optional<Student> studentOptional = studentRepository.findByEmail(loginDTO.getEmail());
        if (studentOptional.isPresent()){
            Student student = studentOptional.get();
            if (passwordEncoder.matches(loginDTO.getPassword(),  student.getPassword()) && !student.isDeleted()){
                return new LoginResponseDTO(
                        "Student Login successful",
                        student.getName(),
                        student.getEmail(),
                        "student-access-token"
                );

            }
            if (student.isDeleted()){
                return new LoginResponseDTO(
                        "Account with the below credentials is already deleted",
                        student.getName(),
                        student.getEmail(),
                        ""
                );
            }
        }

        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(loginDTO.getEmail());
        if (teacherOptional.isPresent()){
            Teacher teacher = teacherOptional.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), teacher.getPassword()) && !teacher.isDeleted()){
                return new LoginResponseDTO(
                        "Teacher Login successful",
                        teacher.getName(),
                        teacher.getEmail(),
                        "Teacher-access-token"
                );
            }
            if (teacher.isDeleted()){
                return new LoginResponseDTO(
                        "Account with that credential is deleted",
                        teacher.getName(),
                        teacher.getEmail(),
                        ""
                );
            }
        }
        return new LoginResponseDTO(
                "Invalid credentials or user not found",
                null,
                null,
                null
        );

    }

}
