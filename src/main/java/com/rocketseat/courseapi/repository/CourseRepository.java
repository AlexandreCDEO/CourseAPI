package com.rocketseat.courseapi.repository;

import com.rocketseat.courseapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByNameContainingIgnoreCase(String name);
    List<Course> findByCategoryContainingIgnoreCase(String category);
    List<Course> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String name, String category);
}
