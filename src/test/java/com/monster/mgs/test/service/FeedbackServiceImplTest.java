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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link com.monster.mgs.test.service.FeedbackServiceImpl}
 * <p>
 * Created by Jan Koren on 9/6/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceImplTest {

    @Mock
    private VisitorRepository visitorRepository;
    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private TrainingCourseRepository trainingCourseRepository;
    @Mock
    private TrainingCourseSectionRepository trainingCourseSectionRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @Test(expected = IllegalStateException.class)
    public void getAllTrainingCoursesNull() {
        when(trainingCourseRepository.findAll()).thenReturn(null);
        feedbackService.getAllTrainingCourses();

        fail("Expected an exception to be thrown due to missing courses.");
    }

    @Test(expected = IllegalStateException.class)
    public void getAllTrainingCoursesNoCourses() {
        when(trainingCourseRepository.findAll()).thenReturn(emptyList());
        feedbackService.getAllTrainingCourses();

        fail("Expected an exception to be thrown due to missing courses.");
    }

    @Test
    public void getAllTrainingCourses() {
        when(trainingCourseRepository.findAll()).thenReturn(asList(
                createTestTrainingCourse(1L),
                createTestTrainingCourse(2L),
                createTestTrainingCourse(3L)
        ));
        final FeedbackInputDataBo trainingCourses = feedbackService.getAllTrainingCourses();

        assertNotNull(trainingCourses);
        assertNotNull(trainingCourses.getTrainingCourses());
        assertTrue(trainingCourses.getTrainingCourses().size() == 3);
    }

    @Test(expected = IllegalStateException.class)
    public void getTrainingSectionsByCourseNull() {
        when(trainingCourseSectionRepository.findAllForCourse(anyLong())).thenReturn(null);
        feedbackService.getTrainingSectionsByCourse(1L);

        fail("Expected an exception to be thrown due to missing course sections");
    }

    @Test(expected = IllegalStateException.class)
    public void getTrainingSectionsByCourseNoSections() {
        when(trainingCourseSectionRepository.findAllForCourse(anyLong())).thenReturn(emptySet());
        feedbackService.getTrainingSectionsByCourse(1L);

        fail("Expected an exception to be thrown due to missing course sections");
    }

    @Test
    public void getTrainingSectionsByCourse() {
        when(trainingCourseSectionRepository.findAllForCourse(anyLong())).thenReturn(new HashSet<>(asList(
                createTestTrainingCourseSection(1L),
                createTestTrainingCourseSection(2L),
                createTestTrainingCourseSection(3L)
        )));
        final Set<TrainingCourseSection> courseSections = feedbackService.getTrainingSectionsByCourse(1L);

        assertNotNull(courseSections);
        assertTrue(courseSections.size() == 3);
    }

    @Test(expected = IllegalStateException.class)
    public void getTrainingCourseNameNull() {
        when(trainingCourseRepository.findById(anyLong())).thenReturn(null);
        feedbackService.getTrainingCourseName(1L);

        fail("Expected an exception to be thrown due to a missing training course");
    }

    @Test(expected = IllegalStateException.class)
    public void getTrainingCourseNameMissingName() {
        final TrainingCourse trainingCourse = createTestTrainingCourse(1L);
        trainingCourse.setName(null);
        when(trainingCourseRepository.findById(anyLong())).thenReturn(trainingCourse);
        feedbackService.getTrainingCourseName(1L);

        fail("Expected an exception to be thrown due to a missing training course name");
    }

    @Test
    public void getTrainingCourseName() {
        when(trainingCourseRepository.findById(anyLong())).thenReturn(createTestTrainingCourse(1L));
        final String trainingCourseName = feedbackService.getTrainingCourseName(1L);

        assertEquals("1", trainingCourseName);
    }


    @Test(expected = IllegalStateException.class)
    public void getCourseSectionNameNull() {
        when(trainingCourseSectionRepository.findOne(anyLong())).thenReturn(null);
        feedbackService.getCourseSectionName(1L);

        fail("Expected an exception to be thrown due to a missing course section");
    }

    @Test(expected = IllegalStateException.class)
    public void getCourseSectionNameMissingName() {
        final TrainingCourseSection trainingCourseSection = createTestTrainingCourseSection(1L);
        trainingCourseSection.setName(null);
        when(trainingCourseSectionRepository.findOne(anyLong())).thenReturn(trainingCourseSection);
        feedbackService.getCourseSectionName(1L);

        fail("Expected an exception to be thrown due to a missing course section name");
    }

    @Test
    public void getCourseSectionName() {
        when(trainingCourseSectionRepository.findOne(anyLong())).thenReturn(createTestTrainingCourseSection(1L));
        final String courseSectionName = feedbackService.getCourseSectionName(1L);

        assertEquals("1", courseSectionName);
    }

    @Test
    public void sendFeedbackUpdate() {
        final FeedbackBo feedbackBo = new FeedbackBo("first", "last", "email", new CourseBo(0L, "course"),
                                                     new CourseSectionBo(0L, "courseSection"), new Date(), 1, "comment");
        when(visitorRepository.findByEmailAddress(Mockito.anyString())).thenReturn(createTestVisitor(1L));
        when(trainingCourseRepository.findOne(anyLong())).thenReturn(createTestTrainingCourse(1L));
        when(trainingCourseSectionRepository.findOne(anyLong())).thenReturn(createTestTrainingCourseSection(1L));
        when(feedbackRepository.save(any(TrainingCourseFeedback.class))).thenReturn(createTestFeedback(10L));

        final Long feedbackId = feedbackService.sendFeedback(feedbackBo);

        verify(visitorRepository, never()).save(any(Visitor.class));
        verify(feedbackRepository, times(1)).save(any(TrainingCourseFeedback.class));
        assertEquals(10, feedbackId.longValue());
    }

    @Test
    public void sendFeedbackCreate() {
        final FeedbackBo feedbackBo = new FeedbackBo("first", "last", "email", new CourseBo(0L, "course"),
                                                     new CourseSectionBo(0L, "courseSection"), new Date(), 1, "comment");
        when(visitorRepository.findByEmailAddress(Mockito.anyString())).thenReturn(null);
        when(trainingCourseRepository.findOne(anyLong())).thenReturn(createTestTrainingCourse(1L));
        when(trainingCourseSectionRepository.findOne(anyLong())).thenReturn(createTestTrainingCourseSection(1L));
        when(feedbackRepository.save(any(TrainingCourseFeedback.class))).thenReturn(createTestFeedback(10L));

        final Long feedbackId = feedbackService.sendFeedback(feedbackBo);

        verify(visitorRepository, times(1)).save(any(Visitor.class));
        verify(feedbackRepository, times(1)).save(any(TrainingCourseFeedback.class));
        assertEquals(10, feedbackId.longValue());
    }

    @Test
    public void listFeedbacksNull() {
        when(feedbackRepository.findAll()).thenReturn(null);
        final List<FeedbackBo> feedbacks = feedbackService.listFeedbacks();
        assertNotNull(feedbacks);
        assertTrue(feedbacks.isEmpty());
    }

    @Test
    public void listFeedbacksEmpty() {
        when(feedbackRepository.findAll()).thenReturn(emptyList());
        final List<FeedbackBo> feedbacks = feedbackService.listFeedbacks();
        assertNotNull(feedbacks);
        assertTrue(feedbacks.isEmpty());
    }

    @Test
    public void listFeedbacks() {
        when(feedbackRepository.findAll()).thenReturn(asList(
                createTestFeedback(1L), createTestFeedback(2L), createTestFeedback(3L)
        ));
        final List<FeedbackBo> feedbacks = feedbackService.listFeedbacks();
        assertNotNull(feedbacks);
        assertFalse(feedbacks.isEmpty());
        assertEquals(3, feedbacks.size());
    }

    private TrainingCourseFeedback createTestFeedback(Long id) {
        final TrainingCourseFeedback feedback = new TrainingCourseFeedback();
        feedback.setId(id);
        feedback.setDate(new Date());
        feedback.setTrainingCourse(createTestTrainingCourse(id));
        feedback.setFavouriteTrainingCourseSection(createTestTrainingCourseSection(id));
        feedback.setVisitor(createTestVisitor(id));

        return feedback;
    }

    private TrainingCourse createTestTrainingCourse(Long id) {
        TrainingCourse trainingCourse = new TrainingCourse();
        trainingCourse.setId(id);
        trainingCourse.setName(id.toString());
        return trainingCourse;
    }

    private TrainingCourseSection createTestTrainingCourseSection(Long id) {
        TrainingCourseSection trainingCourseSection = new TrainingCourseSection();
        trainingCourseSection.setId(id);
        trainingCourseSection.setName(id.toString());
        return trainingCourseSection;
    }

    private Visitor createTestVisitor(Long id) {
        final Visitor visitor = new Visitor();
        visitor.setId(id);
        visitor.setFirstName("first" + id);
        visitor.setLastName("last" + id);
        visitor.setEmailAddress("email" + id);

        return visitor;
    }
}
