package com.fred.schoolmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeesPerTermDTO {
    private long Id;
    private double term;
    private LocalDate termStartDate;
    private LocalDate termEndDate;
    private long feeCharged;
}
