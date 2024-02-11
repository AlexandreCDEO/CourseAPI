package com.rocketseat.courseapi.controller;

import com.rocketseat.courseapi.DTO.CreateCourseDTO;
import com.rocketseat.courseapi.DTO.UpdateCourseDTO;
import com.rocketseat.courseapi.enums.Status;
import com.rocketseat.courseapi.infra.ResultDTO;
import com.rocketseat.courseapi.model.Course;
import com.rocketseat.courseapi.repository.CourseRepository;
import com.rocketseat.courseapi.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService service;

    @Autowired
    private CourseRepository repository;

    @GetMapping
    public ResponseEntity<ResultDTO<List<Course>>> GetAll(){
        var courses = this.repository.findAll();
        return ResponseEntity.ok(new ResultDTO<List<Course>>(courses));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ResultDTO<List<Course>>> GetByNameOrCategory(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
            if(name == null && category != null){
                var courses  = this.repository.findByCategoryContainingIgnoreCase(category);
                return ResponseEntity.ok(new ResultDTO<List<Course>>(courses));
            }

            if(name != null && category == null){
                var courses = this.repository.findByNameContainingIgnoreCase(name);
                return ResponseEntity.ok(new ResultDTO<List<Course>>(courses));
            }

            var courses = this.repository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name, category);
            return ResponseEntity.ok(new ResultDTO<List<Course>>(courses));
    }

    @PostMapping
    public ResponseEntity<ResultDTO<CreateCourseDTO>> Create(@RequestBody @Valid CreateCourseDTO courseDTO){
        var course = Course.builder()
                .name(courseDTO.getName())
                .category(courseDTO.getCategory())
                .active(Status.ACTIVE)
                .build();

        var result = this.service.CreateCourse(course);
        return ResponseEntity.status(201).body(new ResultDTO<CreateCourseDTO>(courseDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO<UpdateCourseDTO>> Update(@PathVariable UUID id, @RequestBody @Valid UpdateCourseDTO courseDTO){
        var course = Course.builder()
                .name(courseDTO.getName())
                .category(courseDTO.getCategory())
                .build();

        this.service.UpdateCourse(id, course);
        return ResponseEntity.ok(new ResultDTO<UpdateCourseDTO>(courseDTO));

    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> ModifyCourseStatus(@PathVariable UUID id) {

        this.service.ModifyCourseStatus(id);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable UUID id) {

        this.service.DeleteCourse(id);
        return ResponseEntity.noContent().build();

    }
}
