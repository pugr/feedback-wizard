package com.monster.mgs.test.service;

import com.monster.mgs.test.bo.FeedbackBo;
import com.monster.mgs.test.bo.FeedbackInputDataBo;
import com.monster.mgs.test.bo.FeedbackResult;
import com.monster.mgs.test.model.TrainingCourseFeedback;
import com.monster.mgs.test.model.TrainingCourseSection;
import com.monster.mgs.test.model.Visitor;

import java.util.List;
import java.util.Set;

/**
 * Manages logic related to feedback data.
 *
 * Created by Jan Koren on 9/4/2016.
 */
public interface FeedbackService {

    /**
     * Lists all available training courses.
     * @return The business object holding the retrieved training courses.
     */
    FeedbackInputDataBo getAllTrainingCourses();

    /**
     * Lists all training sections bound to a course specified by its id.
     * @param courseId The course id.
     * @return The set of course section for the given training course.
     */
    Set<TrainingCourseSection> getTrainingSectionsByCourse(Long courseId);

    /**
     * Finds the course name for the given training course id.
     * @param trainingCourseId The training course id.
     * @return The course name.
     */
    String getTrainingCourseName(Long trainingCourseId);

    /**
     * Finds the course section name for the given course section id.
     * @param courseSectionId The course section id.
     * @return The course section name.
     */
    String getCourseSectionName(Long courseSectionId);

    /**
     * Saves the created feedback.
     * @param feedbackBo The business object representing the feedback to be saved.
     * @return The id of the created feedback.
     */
    Long sendFeedback(FeedbackBo feedbackBo);

    /**
     * Lists all created course feedbacks.
     * @return The available course feedbacks.
     */
    List<FeedbackBo> listFeedbacks();
}
