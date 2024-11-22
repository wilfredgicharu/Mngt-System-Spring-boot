package com.fred.schoolmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSaveDTO {
    private String name;
    private String adress;
    private long phoneNumber;
    private String email;
    private long idNumber;
    private long subjectIds;
    private long roleCodes;
    private String password;
}
