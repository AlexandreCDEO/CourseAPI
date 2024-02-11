package com.rocketseat.courseapi.model;

import com.rocketseat.courseapi.enums.Status;
import com.rocketseat.courseapi.exception.CourseCategoryInvalidException;
import com.rocketseat.courseapi.exception.CourseNameInvalidException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @Length(min = 3, max = 100, message = "A categoria deve ter entre 3 e 100 caracteres")
    private String category;

    @Enumerated(EnumType.STRING)
    private Status active;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public void setName(String name) {
        if(name.length() < 3 || name.length() > 100)  throw new CourseNameInvalidException();
        this.name = name;
    }

    public void setCategory(String category) {
        if(category.length() < 3 || category.length() > 100)  throw new CourseCategoryInvalidException();
        this.category = category;
    }
}
