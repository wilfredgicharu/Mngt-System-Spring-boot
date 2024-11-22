package com.fred.schoolmanagement.dto;

import com.fred.schoolmanagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {

    private long enrollmentId;

    private Student student;

    //private Batch batch;

    private String joinDate;

    private int fee;

}
