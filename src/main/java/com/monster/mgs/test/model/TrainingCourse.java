package com.monster.mgs.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "TRAINING_COURSE")
public class TrainingCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TRAINING_COURSE_ID")
    private Long id;
    private String name;

    @OneToMany
    private Set<TrainingCourseSection> trainingCourseSections;
    @OneToMany
    private Set<TrainingCourseFeedback> trainingCourseFeedbacks;

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

    public Set<TrainingCourseSection> getTrainingCourseSections() {
        return trainingCourseSections;
    }

    public Set<TrainingCourseFeedback> getTrainingCourseFeedbacks() {
        return trainingCourseFeedbacks;
    }

}
