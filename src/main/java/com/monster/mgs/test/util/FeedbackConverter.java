package com.monster.mgs.test.util;

import com.monster.mgs.test.bo.CourseBo;
import com.monster.mgs.test.bo.CourseSectionBo;
import com.monster.mgs.test.bo.FeedbackBo;
import com.monster.mgs.test.dto.BasicInformationDto;
import com.monster.mgs.test.dto.CourseDto;
import com.monster.mgs.test.dto.CourseRatingDto;
import com.monster.mgs.test.dto.CourseSectionDto;
import com.monster.mgs.test.dto.FeedbackDto;
import com.monster.mgs.test.model.TrainingCourse;
import com.monster.mgs.test.model.TrainingCourseSection;

/**
 * Set of utility methods for conversion between various transfer and domain objects.
 *
 * Created by Jan Koren on 9/5/2016.
 */
public class FeedbackConverter {

    public static CourseDto toCourseDto(TrainingCourseSection trainingCourseSection) {
        final CourseDto courseBaseDto = new CourseDto();
        courseBaseDto.setId(trainingCourseSection.getId());
        courseBaseDto.setName(trainingCourseSection.getName());
        return courseBaseDto;
    }

    public static final CourseDto toCourseDto(TrainingCourse trainingCourse) {
        final CourseDto courseBaseDto = new CourseDto();
        courseBaseDto.setId(trainingCourse.getId());
        courseBaseDto.setName(trainingCourse.getName());
        return courseBaseDto;
    }

    public static FeedbackBo toFeedbackBo(BasicInformationDto basicInformation, CourseRatingDto courseRating) {
        return new FeedbackBo(basicInformation.getFirstName(), basicInformation.getLastName(),
                              basicInformation.getEmailAddress(), toCourseBo(basicInformation.getCourse()),
                              toCourseSectionBo(courseRating.getCourseSection()), basicInformation.getFeedbackDate(),
                              courseRating.getRating(), courseRating.getComment());
    }

    public static FeedbackDto toFeedbackDto(FeedbackBo feedbackBo) {
        final FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setFirstName(feedbackBo.getFirstName());
        feedbackDto.setLastName(feedbackBo.getLastName());
        feedbackDto.setEmailAddress(feedbackBo.getEmailAddress());
        feedbackDto.setCourse(feedbackBo.getCourse().getName());
        feedbackDto.setCourseSection(feedbackBo.getCourseSection().getName());
        feedbackDto.setCourseDate(feedbackBo.getCourseDate());
        feedbackDto.setRating(feedbackBo.getRating());
        feedbackDto.setComment(feedbackBo.getComment());

        return feedbackDto;
    }

    private static CourseBo toCourseBo(CourseDto courseDto) {
        return new CourseBo(courseDto.getId(), courseDto.getName());
    }

    private static CourseSectionBo toCourseSectionBo(CourseSectionDto courseSectionDto) {
        return new CourseSectionBo(courseSectionDto.getId(), courseSectionDto.getName());
    }

}
