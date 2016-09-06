package com.monster.mgs.test.service;

import com.google.common.collect.Lists;
import com.monster.mgs.test.bo.CourseBo;
import com.monster.mgs.test.bo.CourseSectionBo;
import com.monster.mgs.test.bo.FeedbackBo;
import com.monster.mgs.test.bo.FeedbackInputDataBo;
import com.monster.mgs.test.model.TrainingCourse;
import com.monster.mgs.test.model.TrainingCourseFeedback;
import com.monster.mgs.test.model.TrainingCourseSection;
import com.monster.mgs.test.model.Visitor;
import com.monster.mgs.test.repository.FeedbackRepository;
import com.monster.mgs.test.repository.TrainingCourseRepository;
import com.monster.mgs.test.repository.TrainingCourseSectionRepository;
import com.monster.mgs.test.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Implementation of {@link com.monster.mgs.test.service.FeedbackService}
 * <p>
 * Created by Jan Koren on 9/4/2016.
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private VisitorRepository visitorRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private TrainingCourseRepository trainingCourseRepository;
    @Autowired
    private TrainingCourseSectionRepository trainingCourseSectionRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public FeedbackInputDataBo getAllTrainingCourses() {
        final Iterable<TrainingCourse> trainingCourses = trainingCourseRepository.findAll();
        if (trainingCourses == null || !trainingCourses.iterator().hasNext()) {
            throw new IllegalStateException("No training courses found.");
        }
        return new FeedbackInputDataBo(Lists.newArrayList(trainingCourses));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<TrainingCourseSection> getTrainingSectionsByCourse(Long courseId) {
        final Set<TrainingCourseSection> courseSections = trainingCourseSectionRepository.findAllForCourse(courseId);
        if (CollectionUtils.isEmpty(courseSections)) {
            throw new IllegalStateException(String.format("No training course sections for course ID %d found.", courseId));
        }
        return courseSections;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTrainingCourseName(Long trainingCourseId) {
        return Optional.ofNullable(trainingCourseRepository.findById(trainingCourseId))
                .map(TrainingCourse::getName)
                .orElseThrow(() -> new IllegalStateException("Missing training course name for id " + trainingCourseId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCourseSectionName(Long courseSectionId) {
        return Optional.ofNullable(trainingCourseSectionRepository.findOne(courseSectionId))
                .map(TrainingCourseSection::getName)
                .orElseThrow(() -> new IllegalStateException("Missing training course section name for id " + courseSectionId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long sendFeedback(FeedbackBo feedbackBo) {
        Visitor visitor = createOrUpdateVisitor(feedbackBo);

        TrainingCourseFeedback feedback = new TrainingCourseFeedback();

        final TrainingCourse trainingCourse = trainingCourseRepository.findOne(feedbackBo.getCourse().getId());
        feedback.setTrainingCourse(trainingCourse);
        final TrainingCourseSection favouriteTrainingCourseSection = trainingCourseSectionRepository.findOne(feedbackBo.getCourseSection().getId());
        feedback.setFavouriteTrainingCourseSection(favouriteTrainingCourseSection);

        feedback.setDate(feedbackBo.getCourseDate());
        feedback.setRating(feedbackBo.getRating());
        feedback.setComment(feedbackBo.getComment());

        feedback.setVisitor(visitor);

        final TrainingCourseFeedback savedFeedback = feedbackRepository.save(feedback);

        return savedFeedback.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeedbackBo> listFeedbacks() {
        final Iterable<TrainingCourseFeedback> allFeedbacks = feedbackRepository.findAll();
        if (allFeedbacks == null) {
            return Collections.emptyList();
        }

        return Lists.newArrayList(allFeedbacks).stream()
                .map(this::toFeedbackBo)
                .collect(toList());
    }

    private Visitor createOrUpdateVisitor(FeedbackBo feedbackBo) {
        final Visitor inputVisitor = extractVisitor(feedbackBo);
        Visitor visitor = visitorRepository.findByEmailAddress(feedbackBo.getEmailAddress());
        if (visitor == null) {
            visitor = visitorRepository.save(inputVisitor);
        } else {
            visitor.setFirstName(inputVisitor.getFirstName());
            visitor.setLastName(inputVisitor.getLastName());
        }
        return visitor;
    }

    private FeedbackBo toFeedbackBo(TrainingCourseFeedback feedback) {
        final TrainingCourse trainingCourse = feedback.getTrainingCourse();
        final CourseBo courseBo = new CourseBo(trainingCourse.getId(), trainingCourse.getName());
        final TrainingCourseSection courseSection = feedback.getFavouriteTrainingCourseSection();
        final CourseSectionBo courseSectionBo = new CourseSectionBo(courseSection.getId(), courseSection.getName());

        return new FeedbackBo(feedback.getVisitor().getFirstName(), feedback.getVisitor().getLastName(), feedback.getVisitor().getEmailAddress(),
                              courseBo, courseSectionBo, feedback.getDate(), feedback.getRating(), feedback.getComment());
    }

    private Visitor extractVisitor(FeedbackBo feedbackBo) {
        Visitor visitor = new Visitor();
        visitor.setEmailAddress(feedbackBo.getEmailAddress());
        visitor.setFirstName(feedbackBo.getFirstName());
        visitor.setLastName(feedbackBo.getLastName());
        return visitor;
    }
}
