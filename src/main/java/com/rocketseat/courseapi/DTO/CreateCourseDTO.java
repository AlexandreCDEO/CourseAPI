package com.rocketseat.courseapi.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateCourseDTO {
    @Length(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @Length(min = 3, max = 100, message = "A categoria deve ter entre 3 e 100 caracteres")
    private String category;
}
