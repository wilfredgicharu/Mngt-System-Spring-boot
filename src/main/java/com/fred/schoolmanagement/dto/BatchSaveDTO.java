package com.fred.schoolmanagement.dto;


import com.fred.schoolmanagement.entity.Course;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchSaveDTO {

    private String batchName;

    private long courseId;

    private String startDate;

}
