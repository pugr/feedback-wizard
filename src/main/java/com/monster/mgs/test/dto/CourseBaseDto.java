package com.monster.mgs.test.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jan Koren on 9/5/2016.
 */
public abstract class CourseBaseDto {

    @NotNull
    private Long id;
    @NotEmpty
    @Size(max = 200)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
