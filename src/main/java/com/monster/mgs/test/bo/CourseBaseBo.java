package com.monster.mgs.test.bo;

/**
 * Created by Jan Koren on 9/5/2016.
 */
public abstract class CourseBaseBo {

    private Long id;
    private String name;

    public CourseBaseBo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
