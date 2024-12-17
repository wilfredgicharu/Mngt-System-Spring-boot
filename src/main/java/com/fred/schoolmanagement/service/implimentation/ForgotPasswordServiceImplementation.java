package com.fred.schoolmanagement.service.implimentation;


import com.fred.schoolmanagement.dto.ForgotPasswordDTO;
import com.fred.schoolmanagement.dto.ForgotPasswordResponseDTO;
import com.fred.schoolmanagement.entity.Student;
import com.fred.schoolmanagement.entity.Teacher;
import com.fred.schoolmanagement.repository.StudentRepository;
import com.fred.schoolmanagement.repository.TeacherRepository;
import com.fred.schoolmanagement.utils.EmailsManagement;
import com.fred.schoolmanagement.utils.NumberGenerator;
import com.fred.schoolmanagement.utils.TokenHasher;
import org.aspectj.apache.bcel.generic.FieldGenOrMethodGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgotPasswordServiceImplementation {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private NumberGenerator numberGenerator;
    private TokenHasher tokenHasher;
    private EmailsManagement emailsManagement;

    @Autowired
    public ForgotPasswordServiceImplementation(StudentRepository studentRepository, TeacherRepository teacherRepository, NumberGenerator numberGenerator, TokenHasher tokenHasher, EmailsManagement emailsManagement) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.numberGenerator = numberGenerator;
        this.tokenHasher = tokenHasher;
        this.emailsManagement = emailsManagement;
    }

    public ForgotPasswordResponseDTO forgotPasswordResponseDTO(ForgotPasswordDTO forgotPasswordDTO){
        //for student
        Optional<Student> studentOptional = studentRepository.findByEmail(forgotPasswordDTO.getEmail());
        if (studentOptional.isPresent()){
            Student student = studentOptional.get();

            String token = numberGenerator.generateRandomNumber();
            String hashedToken = tokenHasher.hashToken(token);
            student.setResetToken(hashedToken);
            studentRepository.save(student);

            String emailBody = "Your password reset token is: "+ token;
            emailsManagement.sendEmails(student.getEmail(), "Password Reset Token ", emailBody);

            return new ForgotPasswordResponseDTO(
                    "Token sent successfull",
                    student.getEmail(),
                    token
            );
        }

        //for teacher
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(forgotPasswordDTO.getEmail());
        if (teacherOptional.isPresent()){
            Teacher teacher = teacherOptional.get();

            String token = numberGenerator.generateRandomNumber();
            String hashedToken = tokenHasher.hashToken(token);
            teacher.setResetToken(hashedToken);
            teacherRepository.save(teacher);

            //prepring email details
            String emailBody = "Your password reset token is" + token;
            emailsManagement.sendEmails(teacher.getEmail(), "Password Reset token", emailBody);

            return new ForgotPasswordResponseDTO(
                    "Token sent successfully",
                    teacher.getEmail(),
                    token
            );

        }
        return new ForgotPasswordResponseDTO(
                "Token sent successful",
                null,
                null
        );

    }
}
