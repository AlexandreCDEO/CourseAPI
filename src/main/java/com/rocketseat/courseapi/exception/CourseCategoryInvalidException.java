package com.rocketseat.courseapi.exception;

public class CourseCategoryInvalidException extends RuntimeException{
    public CourseCategoryInvalidException() {
        super("The category must have between 3 and 100 characters.");
    }
}
