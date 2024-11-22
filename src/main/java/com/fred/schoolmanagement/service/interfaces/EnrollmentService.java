package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.EnrollmentDTO;
import com.fred.schoolmanagement.dto.EnrollmentSaveDTO;
import com.fred.schoolmanagement.dto.EnrollmentUpdateDTO;

import java.util.List;

public interface EnrollmentService {

    String addEnrollment(EnrollmentSaveDTO enrollmentSaveDTO);

    List<EnrollmentDTO> getAllEnrollments();

    String updateEnrollment(long id, EnrollmentUpdateDTO enrollmentUpdateDTO);

    boolean deleteEnrollment(long id);

}
