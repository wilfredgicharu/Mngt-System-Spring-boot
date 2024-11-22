package com.fred.schoolmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryDTO {
    private long dormitoryId;
    private long dormCode;
    private String dormName;
}

