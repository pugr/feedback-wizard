package com.monster.mgs.test.bo;

import com.monster.mgs.test.model.TrainingCourse;

import java.util.Collection;

/**
 * Created by Jan Koren on 9/4/2016.
 */
public class FeedbackInputDataBo {

    private Collection<TrainingCourse> trainingCourses;

    public FeedbackInputDataBo(Collection<TrainingCourse> trainingCourses) {
        this.trainingCourses = trainingCourses;
    }

    public Collection<TrainingCourse> getTrainingCourses() {
        return trainingCourses;
    }

    public void setTrainingCourses(Collection<TrainingCourse> trainingCourses) {
        this.trainingCourses = trainingCourses;
    }
}
