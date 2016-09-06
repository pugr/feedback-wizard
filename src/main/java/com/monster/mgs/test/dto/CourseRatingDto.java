package com.monster.mgs.test.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Jan Koren on 9/5/2016.
 */
public class CourseRatingDto {

    @NotNull
    private CourseSectionDto courseSection;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;

    private String comment;

    public CourseSectionDto getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSectionDto courseSection) {
        this.courseSection = courseSection;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
