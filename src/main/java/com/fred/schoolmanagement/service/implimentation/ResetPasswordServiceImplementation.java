package com.fred.schoolmanagement.service.implimentation;


import com.fred.schoolmanagement.dto.ResetPasswordDTO;
import com.fred.schoolmanagement.dto.ResetPasswordResponseDTO;
import com.fred.schoolmanagement.entity.Student;
import com.fred.schoolmanagement.entity.Teacher;
import com.fred.schoolmanagement.repository.StudentRepository;
import com.fred.schoolmanagement.repository.TeacherRepository;
import com.fred.schoolmanagement.utils.TokenHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResetPasswordServiceImplementation {
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private TokenHasher tokenHasher;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ResetPasswordServiceImplementation(StudentRepository studentRepository, TeacherRepository teacherRepository, TokenHasher tokenHasher, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.tokenHasher = tokenHasher;
        this.passwordEncoder = passwordEncoder;
    }

    public ResetPasswordResponseDTO resetPasswordResponseDTO(ResetPasswordDTO resetPasswordDTO){
        //validate the email reset code
        Optional<Student> studentOptional = studentRepository.findByEmail(resetPasswordDTO.getEmail());
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            //validate the reset token
            boolean isValidateToken = tokenHasher.validateResetToken(student.getResetToken(), resetPasswordDTO.getResetCode());
            if (isValidateToken) {
                String hashedPassword = passwordEncoder.encode(resetPasswordDTO.getNewPassword());
                student.setPassword(hashedPassword);

                student.setResetToken(null);
                studentRepository.save(student);

                return new ResetPasswordResponseDTO(
                        "Password reset successfully",
                        student.getEmail()
                );
            }
        }

            //validate the teacher email
            Optional<Teacher> teacherOptional = teacherRepository.findByEmail(resetPasswordDTO.getEmail());
            if (teacherOptional.isPresent()){
                Teacher teacher = teacherOptional.get();

                //validate reset token
                boolean isValidToken = tokenHasher.validateResetToken(teacher.getResetToken(), resetPasswordDTO.getNewPassword());
                if (isValidToken){
                    String hashedPassword = passwordEncoder.encode(resetPasswordDTO.getNewPassword());
                    teacher.setPassword(hashedPassword);

                    teacher.setResetToken(null);
                    teacherRepository.save(teacher);

                    return new ResetPasswordResponseDTO(
                            "Password reset successfull",
                            teacher.getEmail()
                    );
                }

                return new ResetPasswordResponseDTO(
                        "Invalid reset",
                        null
                );
            }
            return new ResetPasswordResponseDTO(
                    "Email not found",
                    null
            );

        }

}
