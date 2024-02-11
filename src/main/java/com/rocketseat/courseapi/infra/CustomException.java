package com.rocketseat.courseapi.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CustomException {
    private HttpStatus status;
    private String message;
}
