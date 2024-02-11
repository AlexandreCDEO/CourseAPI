package com.rocketseat.courseapi.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(){
        super("Course not found");
    }
}
