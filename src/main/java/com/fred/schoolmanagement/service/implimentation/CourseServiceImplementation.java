package com.fred.schoolmanagement.service.implimentation;

import com.fred.schoolmanagement.dto.CourseDTO;
import com.fred.schoolmanagement.dto.CourseSaveDTO;
import com.fred.schoolmanagement.dto.CourseUpdateDTO;
import com.fred.schoolmanagement.entity.Course;
import com.fred.schoolmanagement.repository.CourseRepository;
import com.fred.schoolmanagement.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImplementation(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public String addCourse(CourseSaveDTO courseSaveDTO) {
        try {
            Course course = new Course(
                    courseSaveDTO.getCourseName(),
                    courseSaveDTO.getSyllabus(),
                    courseSaveDTO.getDuration()
            );

            courseRepository.save(course);

            System.out.println("Course added successfully");

            return course.getCourseName();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String updateCourse(long id, CourseUpdateDTO courseUpdateDTO) {
        if (courseRepository.existsById(id)){
            Course course = courseRepository.getById(id);
            course.setCourseName(courseUpdateDTO.getCourseName());
            course.setDuration(courseUpdateDTO.getDuration());
            course.setSyllabus(courseUpdateDTO.getSyllabus());

            courseRepository.save(course);

            System.out.println("\nCourse details updated Successfully");
            return "Course details updated Successfully";
        } else {
            System.out.println(" \n Course with that ID not found");
            return "\n Course with that ID not found";
        }
    }

    @Override
    public List<CourseDTO> getAllCourses() {
       List<Course> getCourse = courseRepository.findAll();
       List<CourseDTO> courseDTOList = new ArrayList<>();

       for (Course course : getCourse){
           CourseDTO courseDTO = new CourseDTO(
                   course.getCourseId(),
                   course.getCourseName(),
                   course.getDuration(),
                   course.getSyllabus()
           );

           courseDTOList.add(courseDTO);

       }
       return courseDTOList;
    }

    @Override
    public boolean deleteCourse(long id) {
        if (courseRepository.existsById(id)){
            courseRepository.deleteById(id);

            System.out.println("\nCourse deleted successfully");

            return true;
        } else {
            System.out.println("\nCourse ID not found");
            return false;
        }
    }
}
