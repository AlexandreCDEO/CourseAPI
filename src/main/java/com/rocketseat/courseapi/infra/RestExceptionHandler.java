package com.rocketseat.courseapi.infra;
import com.rocketseat.courseapi.exception.CourseCategoryInvalidException;
import com.rocketseat.courseapi.exception.CourseNameInvalidException;
import com.rocketseat.courseapi.exception.CourseNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    private final MessageSource messageSource;
    public RestExceptionHandler(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultDTO<MethodArgumentNotValidException>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {

        List<String> dto = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            dto.add(message);
        });

        return ResponseEntity.badRequest().body(new ResultDTO<MethodArgumentNotValidException>(dto));
    }

    @ExceptionHandler(CourseCategoryInvalidException.class)
    public ResponseEntity<ResultDTO<CourseCategoryInvalidException>> courseCategoryInvalidHandler(CourseCategoryInvalidException exception){
        return ResponseEntity.badRequest().body(new ResultDTO<CourseCategoryInvalidException>(exception.getMessage()));
    }

    @ExceptionHandler(CourseNameInvalidException.class)
    public ResponseEntity<ResultDTO<CourseNameInvalidException>> courseNameInvalidHandler(CourseNameInvalidException exception){
        return ResponseEntity.badRequest().body(new ResultDTO<CourseNameInvalidException>(exception.getMessage()));
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ResultDTO<CourseNotFoundException>> courseNotFoundHandler(CourseNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultDTO<CourseNotFoundException>(exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResultDTO<CourseNotFoundException>> courseNotFoundHandler(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultDTO<CourseNotFoundException>(exception.getMessage()));
    }
}
