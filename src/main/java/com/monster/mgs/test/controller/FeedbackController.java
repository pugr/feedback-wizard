package com.monster.mgs.test.controller;

import com.monster.mgs.test.bo.FeedbackInputDataBo;
import com.monster.mgs.test.bo.FeedbackResult;
import com.monster.mgs.test.dto.BasicInformationDto;
import com.monster.mgs.test.dto.CourseBaseDto;
import com.monster.mgs.test.dto.CourseRatingDto;
import com.monster.mgs.test.dto.FeedbackDto;
import com.monster.mgs.test.service.FeedbackService;
import com.monster.mgs.test.util.FeedbackConstants;
import com.monster.mgs.test.util.FeedbackConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static com.monster.mgs.test.util.FeedbackConstants.BASIC_INFORMATION_FORM;
import static com.monster.mgs.test.util.FeedbackConstants.COURSE_RATING_FORM;
import static com.monster.mgs.test.util.FeedbackConstants.FEEDBACK_SUCCES;
import static com.monster.mgs.test.util.FeedbackConstants.URL_BASIC_INFORMATION;
import static com.monster.mgs.test.util.FeedbackConstants.URL_COURSE_RATING;
import static com.monster.mgs.test.util.FeedbackConstants.URL_SEND_FEEDBACK;
import static com.monster.mgs.test.util.FeedbackConstants.URL_SUMMARY;
import static com.monster.mgs.test.util.FeedbackConstants.URL_WELCOME;
import static com.monster.mgs.test.util.FeedbackConstants.VIEW_BASIC_INFORMATION;
import static com.monster.mgs.test.util.FeedbackConstants.VIEW_COURSE_RATING;
import static com.monster.mgs.test.util.FeedbackConstants.VIEW_SUMMARY;
import static com.monster.mgs.test.util.FeedbackConstants.VIEW_WELCOME;
import static java.util.stream.Collectors.toList;

/**
 * Created by Jan Koren on 9/4/2016.
 */
@Controller
@RequestMapping(FeedbackConstants.URL_FEEDBACK_BASE)
@SessionAttributes({BASIC_INFORMATION_FORM, COURSE_RATING_FORM, FEEDBACK_SUCCES})
public class FeedbackController {

    private static final List<Integer> RATING_LIST = Arrays.asList(1, 2, 3, 4, 5);

    @Autowired
    private FeedbackService feedbackService;

    // Invoked initially to create the "courseRatingDto" attribute
    // Once created the "courseRatingDto" attribute comes from the HTTP session (see @SessionAttributes)
    @ModelAttribute(BASIC_INFORMATION_FORM)
    public BasicInformationDto createBasicInformationForm() {
        return new BasicInformationDto();
    }

    @ModelAttribute(COURSE_RATING_FORM)
    public CourseRatingDto createCourseRatingForm() {
        return new CourseRatingDto();
    }

    @RequestMapping(path = URL_WELCOME, method = RequestMethod.GET)
    public String welcome(Model model, FeedbackResult feedbackResult) {
        final List<FeedbackDto> feedbacks = feedbackService.listFeedbacks().stream()
                .map(FeedbackConverter::toFeedbackDto)
                .collect(toList());
        model.addAttribute("feedbacks", feedbacks);

        if (feedbackResult == null) {
            feedbackResult = FeedbackResult.UNKNOWN;
        }
        model.addAttribute("feedbackResult", feedbackResult.name());

        return VIEW_WELCOME;
    }

    @RequestMapping(path = URL_BASIC_INFORMATION, method = RequestMethod.GET)
    public String showBasicInformation(@ModelAttribute(BASIC_INFORMATION_FORM) BasicInformationDto basicInformationDto, Model model) {
        model.addAttribute("courseData", getCourseData());
        return VIEW_BASIC_INFORMATION;
    }

    @RequestMapping(path = URL_BASIC_INFORMATION, method = RequestMethod.POST, params = {"continue"})
    public String addBasicInformation(@Valid @ModelAttribute(BASIC_INFORMATION_FORM) BasicInformationDto basicInformationDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showBasicInformation(basicInformationDto, model);
        }
        String trainingCourseName = feedbackService.getTrainingCourseName(basicInformationDto.getCourse().getId());
        basicInformationDto.getCourse().setName(trainingCourseName);

        return "redirect:" + VIEW_COURSE_RATING;
    }

    @RequestMapping(path = URL_BASIC_INFORMATION, method = RequestMethod.POST, params = {"cancel"})
    public String cancelBasicInformation(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:" + welcome(model, FeedbackResult.CANCEL);
    }

    @RequestMapping(value = URL_COURSE_RATING, method = RequestMethod.GET)
    public String showRateCourse(@ModelAttribute(BASIC_INFORMATION_FORM) BasicInformationDto basicInformationDto,
                                 @ModelAttribute(COURSE_RATING_FORM) CourseRatingDto courseRatingDto, Model model) {
        final Long selectedCourseId = basicInformationDto.getCourse().getId();
        final List<CourseBaseDto> sectionsByCourse = feedbackService.getTrainingSectionsByCourse(selectedCourseId).stream()
                .map(FeedbackConverter::toCourseDto)
                .collect(toList());

        model.addAttribute("sections", sectionsByCourse);
        model.addAttribute("ratingList", RATING_LIST);

        return VIEW_COURSE_RATING;
    }

    @RequestMapping(value = URL_COURSE_RATING, method = RequestMethod.POST, params = "continue")
    public String rateCourse(@Valid @ModelAttribute(COURSE_RATING_FORM) CourseRatingDto courseRatingDto, BindingResult bindingResult,
                             @ModelAttribute(BASIC_INFORMATION_FORM) BasicInformationDto basicInformationDto, Model model) {
        if (bindingResult.hasErrors()) {
            return showRateCourse(basicInformationDto, courseRatingDto, model);
        }

        String courseSectionName = feedbackService.getCourseSectionName(courseRatingDto.getCourseSection().getId());
        courseRatingDto.getCourseSection().setName(courseSectionName);

        return "redirect:" + VIEW_SUMMARY;
    }

    @RequestMapping(value = URL_COURSE_RATING, method = RequestMethod.POST, params = "back")
    public String goBackFromRateCourse(@ModelAttribute(BASIC_INFORMATION_FORM) BasicInformationDto basicInformationDto,
                                       @ModelAttribute(COURSE_RATING_FORM) CourseRatingDto courseRatingDto, Model model) {
        return "redirect:" + showBasicInformation(basicInformationDto, model);
    }

    @RequestMapping(value = URL_SUMMARY, method = RequestMethod.GET)
    public String checkFeedback(@ModelAttribute(BASIC_INFORMATION_FORM) BasicInformationDto basicInformationDto,
                                @ModelAttribute(COURSE_RATING_FORM) CourseRatingDto courseRatingDto, BindingResult result) {
        return VIEW_SUMMARY;
    }

    @RequestMapping(value = URL_SEND_FEEDBACK, method = RequestMethod.POST)
    public String postFeedback(@ModelAttribute(BASIC_INFORMATION_FORM) BasicInformationDto basicInformation, @ModelAttribute(COURSE_RATING_FORM) CourseRatingDto courseRating,
                               SessionStatus sessionStatus, Model model) {
        final Long createdFeedbackId = feedbackService.sendFeedback(FeedbackConverter.toFeedbackBo(basicInformation, courseRating));
        sessionStatus.setComplete();
        return welcome(model, FeedbackResult.OK);
    }

    private List<CourseBaseDto> getCourseData() {
        final FeedbackInputDataBo inputDataForFeedback = feedbackService.getAllTrainingCourses();
        return inputDataForFeedback.getTrainingCourses().stream()
                .map(FeedbackConverter::toCourseDto)
                .collect(toList());
    }

}
