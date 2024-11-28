package com.fred.schoolmanagement.service.implimentation;


import com.fred.schoolmanagement.dto.StudentSaveDTO;
import com.fred.schoolmanagement.entity.*;
import com.fred.schoolmanagement.repository.*;
import com.fred.schoolmanagement.service.interfaces.AuthenticationStudent;
import com.fred.schoolmanagement.utils.EmailsManagement;
import com.fred.schoolmanagement.utils.NumberGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationStudentServiceImplementation implements AuthenticationStudent {


    private StudentRepository studentRepository;
    private PasswordEncoder passwordEncoder;
    private EmailsManagement emailsManagement;
    private NumberGenerator numberGenerator;
    private AdmissionTracker admissionTracker;
    private FeesPerTermRepository feesPerTermRepository;
    private DormitoryRepository dormitoryRepository;
    private StreamRepository streamRepository;

    @Autowired
    public AuthenticationStudentServiceImplementation(StudentRepository studentRepository,
                                                      PasswordEncoder passwordEncoder,
                                                      EmailsManagement emailsManagement,
                                                      NumberGenerator numberGenerator,
                                                      AdmissionTracker admissionTracker,
                                                      FeesPerTermRepository feesPerTermRepository,
                                                      DormitoryRepository dormitoryRepository,
                                                      StreamRepository streamRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailsManagement = emailsManagement;
        this.numberGenerator = numberGenerator;
        this.admissionTracker = admissionTracker;
        this.feesPerTermRepository = feesPerTermRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.streamRepository = streamRepository;
    }


    @Override
    public String addStudent(StudentSaveDTO studentSaveDTO) {

        try {
            //check if the student exists
            Optional<Student> studentExist = studentRepository.findByStudentIdNumber(studentSaveDTO.getIdNumber());

            if (studentExist.isPresent()){
                return ("Student with id " + studentSaveDTO.getIdNumber() + "Already exists !!");
            };

            Optional<Student> studentEmailExists = studentRepository.findByEmail(studentSaveDTO.getEmail());

            if (studentExist.isPresent()){
                return ("Student with email" + studentSaveDTO.getEmail() + " Already exists !!");
            }

            String encodedPassword = passwordEncoder.encode(studentSaveDTO.getPassword());
            String verificationToken = RandomStringUtils.randomAlphanumeric(32);
            
            String admissionNumber = generateUniqueAdmissionNumber();

            //fetch fees by ID

            Optional<FeesPerTerm> currentTermOptional = feesPerTermRepository.findById(studentSaveDTO.getCurrentTermId());

            if (currentTermOptional.isEmpty()){
                return ("current term ID" + studentSaveDTO.getCurrentTermId() + "does not exist");
            }
            FeesPerTerm currentTerm = currentTermOptional.get();
            long totalFeeBilled = currentTerm.getFeeCharged();

            //fetch dorm id

            Optional<Dormitory> currentDormitoryOptional = dormitoryRepository.findById(studentSaveDTO.getDormitoryId());
            if (currentDormitoryOptional.isEmpty()){
                return ("Current Dormitory with ID " + studentSaveDTO.getDormitoryId()+ "does not exist");
            }

            Dormitory currentDormitory = currentDormitoryOptional.get();

            Optional<Stream> streamOptionalExists = streamRepository.findById(studentSaveDTO.getStreamId());
            if (!streamOptionalExists.isPresent()){
                return "stream with ID" + studentSaveDTO.getStreamId() + "does not exist !";
            }

            Stream stream = streamOptionalExists.get();

            Student student = new Student(
                    admissionNumber,
                    studentSaveDTO.getName(),
                    studentSaveDTO.getAdress(),
                    studentSaveDTO.getEmail(),
                    studentSaveDTO.getPhoneNumber(),
                    studentSaveDTO.getIdNumber(),
                    stream,
                    currentDormitory,
                    currentTerm,
                    totalFeeBilled,
                    studentSaveDTO.getTotalPaidFee(),
                    encodedPassword,
                    "",
                    "",
                    verificationToken,
                    false,
                    false
            );

            studentRepository.save(student);

            String emailBody = "Dear " + studentSaveDTO.getName() + ", Admission Number: "+ admissionNumber + ",\nWelcome to Bright Star School. We are pleased to have you aboard.\nPlease verify your email by clicking the link below:\n" +
                    "http://localhost:5555/api/v1/students/verify-email?token=" + verificationToken;

            emailsManagement.sendEmails(studentSaveDTO.getEmail(), "Registration successful", emailBody);

            return  student.getName();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    private String generateUniqueAdmissionNumber() {

        Optional<AdmissionNumber> trackerOptional = admissionTracker.findTopByOrderByAdmissionNumberIdDesc();
        String lastNumberStr = trackerOptional.map(AdmissionNumber::getRecentAdmissionNumber).orElse("BS0000");
        int lastNumber = Integer.parseInt(lastNumberStr.replace("BS", ""));
        int nextNumber = lastNumber +1;

        String nextAdmissionNumber = numberGenerator.generateSequentialNumber(nextNumber);

        AdmissionNumber tracker = trackerOptional.orElse(new AdmissionNumber());

        tracker.setRecentAdmissionNumber(nextAdmissionNumber);
        admissionTracker.save(tracker);

        return nextAdmissionNumber;
    }

    @Override
    public boolean verifyEmail(String token) {
        Optional<Student> studentOptional = studentRepository.findByVerificationToken(token);
        if (studentOptional.isPresent()){
            Student student = studentOptional.get();
            student.setEmailVerified(true);
            student.setVerificationToken(null);
            studentRepository.save(student);
            return true;
        }
        return false;
    }
}
