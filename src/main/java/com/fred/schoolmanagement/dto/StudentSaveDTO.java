package com.fred.schoolmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSaveDTO {
    private String name;
    private String adress;
    private long phoneNumber;
    private long streamId;
    private long dormitoryId;
    private long currentTermId;
    private String email;
    private long idNumber;
    private String password;
    private long totalPaidFee;
}

