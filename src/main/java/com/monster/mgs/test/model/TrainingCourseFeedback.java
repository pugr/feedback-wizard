package com.monster.mgs.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "TRAINING_COURSE_FEEDBACK")
public class TrainingCourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TRAINING_COURSE_FEEDBACK_ID")
    private Long id;
    @Column(name = "TRAINING_COURSE_DATE")
    private Date date;
    @Column(name = "COMMENT")
    private String comment;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "VISITOR_ID")
    private Visitor visitor;
    @ManyToOne
    @JoinColumn(name = "FAVORITE_SECTION_ID")
    private TrainingCourseSection favouriteTrainingCourseSection;
    @ManyToOne
    @JoinColumn(name = "TRAINING_COURSE_ID")
    private TrainingCourse trainingCourse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public TrainingCourseSection getFavouriteTrainingCourseSection() {
        return favouriteTrainingCourseSection;
    }

    public void setFavouriteTrainingCourseSection(TrainingCourseSection favouriteTrainingCourseSection) {
        this.favouriteTrainingCourseSection = favouriteTrainingCourseSection;
    }

    public TrainingCourse getTrainingCourse() {
        return trainingCourse;
    }

    public void setTrainingCourse(TrainingCourse trainingCourse) {
        this.trainingCourse = trainingCourse;
    }

}
