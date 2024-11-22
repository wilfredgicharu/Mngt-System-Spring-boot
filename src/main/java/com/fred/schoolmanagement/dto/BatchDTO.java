package com.fred.schoolmanagement.dto;


import com.fred.schoolmanagement.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {

    private long batchId;

    private String batchName;

    private Course course;

    private String startDate;

}
