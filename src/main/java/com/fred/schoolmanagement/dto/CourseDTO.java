package com.fred.schoolmanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private long courseId;
    private String courseName;
    private String syllabus;
    private String duration;
}