package com.fred.schoolmanagement.service.implimentation;

import com.fred.schoolmanagement.dto.EnrollmentDTO;
import com.fred.schoolmanagement.dto.EnrollmentSaveDTO;
import com.fred.schoolmanagement.dto.EnrollmentUpdateDTO;
import com.fred.schoolmanagement.entity.Batch;
import com.fred.schoolmanagement.entity.Enrollment;
import com.fred.schoolmanagement.entity.Student;
import com.fred.schoolmanagement.repository.BatchRepository;
import com.fred.schoolmanagement.repository.CourseRepository;
import com.fred.schoolmanagement.repository.EnrollmentRepository;
import com.fred.schoolmanagement.repository.StudentRepository;
import com.fred.schoolmanagement.service.interfaces.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EnrollmentServiceImplementation implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private BatchRepository batchRepository;

    public EnrollmentServiceImplementation(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository, BatchRepository batchRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public String addEnrollment(EnrollmentSaveDTO enrollmentSaveDTO) {

        try {

            Optional<Student> studentOptional = studentRepository.findById(enrollmentSaveDTO.getEnrollmentId());
            if (!studentOptional.isPresent()){
                return "Student not found";
            }

            Optional<Batch> batchOptional = batchRepository.findById(enrollmentSaveDTO.getBatchId());
            if (!batchOptional.isPresent()){
                return "Batch Not Found";
            }

            Student student = studentOptional.get();
            Batch batch = batchOptional.get();

            Enrollment enrollment = new Enrollment(
                    student,
                    batch,
                    enrollmentSaveDTO.getJoinDate(),
                    enrollmentSaveDTO.getFee()
            );

            enrollmentRepository.save(enrollment);
            System.out.println("\nMessage: Enrollment Saved Successfully");
            return "Enrollment created successfully. Enrollment Join Date: " + enrollment.getJoinDate();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();

        for (Enrollment enrollment: enrollments){
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO(
                    enrollment.getEnrollmentId(),
                    enrollment.getStudent(),
                    enrollment.getJoinDate(),
                    enrollment.getFee()
            );

            enrollmentDTOList.add(enrollmentDTO);
        }

        return enrollmentDTOList;
    }

    @Override
    public String updateEnrollment(long id, EnrollmentUpdateDTO enrollmentUpdateDTO) {
        try {
            //retrieve enrollment by ts ID
            Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(id);
            if (!enrollmentOptional.isPresent()){
                return "Enrollment Not Found";
            }

            Enrollment enrollment = enrollmentOptional.get();

            //retrieve student and batch by their iDS
            Optional<Student> studentOptional = studentRepository.findById(enrollmentUpdateDTO.getStudent().getStudentId());
            if (!studentOptional.isPresent()){
                return "Student not fOUND";
            }

            Optional<Batch> batchOptional = batchRepository.findById(enrollmentUpdateDTO.getBatch().getBatchId());
            if (!batchOptional.isPresent()){
                return "Batch Not Found";
            }
            Student student = studentOptional.get();
            Batch batch = batchOptional.get();

            enrollment.setStudent(student);
            enrollment.setBatch(batch);
            enrollment.setJoinDate(enrollmentUpdateDTO.getJoinDate());
            enrollment.setFee(enrollmentUpdateDTO.getFee());

            enrollmentRepository.save(enrollment);

            return "Enrollment updated successful. Enrollment ID: " + enrollment.getEnrollmentId();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean deleteEnrollment(long id) {
        if (enrollmentRepository.existsById(id)){
            enrollmentRepository.deleteById(id);
            System.out.println("Enrollment ID not Found");
            return true;
        } else {
            System.out.println("Enrollment ID not found");
            return false;
        }
    }
}
