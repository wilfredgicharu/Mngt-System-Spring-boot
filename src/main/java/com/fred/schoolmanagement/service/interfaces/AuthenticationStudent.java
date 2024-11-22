package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.StudentSaveDTO;

public interface AuthenticationStudent {

    String addStudent(StudentSaveDTO studentSaveDTO);

    boolean verifyEmail(String token);

}
