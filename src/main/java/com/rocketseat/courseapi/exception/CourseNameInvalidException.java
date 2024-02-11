package com.rocketseat.courseapi.exception;

public class CourseNameInvalidException extends RuntimeException{
    public CourseNameInvalidException() {
        super("The name must have between 3 and 100 characters.");
    }
}
