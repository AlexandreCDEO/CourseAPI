package com.rocketseat.courseapi.service;

import com.rocketseat.courseapi.enums.Status;
import com.rocketseat.courseapi.exception.CourseNotFoundException;
import com.rocketseat.courseapi.model.Course;
import com.rocketseat.courseapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public Course CreateCourse(Course course) {
        return repository.save(course);
    }

    public Course UpdateCourse(UUID id, Course course) {
        var courseFound = FindCourseById(id);

        if (courseFound == null) {
            throw new CourseNotFoundException();
        }

        if (course.getName() != null && !course.getName().isEmpty()) courseFound.setName(course.getName());
        if (course.getCategory() != null && !course.getCategory().isEmpty()) courseFound.setCategory(course.getCategory());

        return repository.save(courseFound);
    }

    public void DeleteCourse(UUID id) {
        var course = FindCourseById(id);

        if (course == null) {
            throw new CourseNotFoundException();
        }

        repository.delete(course);
    }

    public Course ModifyCourseStatus(UUID id) {
        var course = FindCourseById(id);

        if (course == null) {
            throw new CourseNotFoundException();
        }

        if(course.getActive() == Status.ACTIVE){
            course.setActive(Status.INACTIVE);
        }else {
            course.setActive(Status.ACTIVE);
        }

        return repository.save(course);
    }

    private Course FindCourseById(UUID id) {
        return repository.findById(id).orElseThrow(CourseNotFoundException::new);
    }
}
