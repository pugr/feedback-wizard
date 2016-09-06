package com.monster.mgs.test.service;

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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Implementation of {@link com.monster.mgs.test.service.FeedbackService}
 *
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
        final List<TrainingCourse> trainingCourses = StreamSupport
                .stream(trainingCourseRepository.findAll().spliterator(), false)
                .collect(toList());
        return new FeedbackInputDataBo(trainingCourses);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<TrainingCourseSection> getTrainingSectionsByCourse(Long courseId) {
        return trainingCourseSectionRepository.findAllForCourse(courseId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTrainingCourseName(Long trainingCourseId) {
        return Optional.of(trainingCourseRepository.findById(trainingCourseId))
                .map(TrainingCourse::getName)
                .orElseThrow(() -> new IllegalStateException("Missing training course name for id " + trainingCourseId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCourseSectionName(Long courseSectionId) {
        return Optional.of(trainingCourseSectionRepository.findOne(courseSectionId))
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
        return StreamSupport.stream(feedbackRepository.findAll().spliterator(), false)
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
        return  visitor;
    }
}
