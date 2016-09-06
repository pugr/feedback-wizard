package com.monster.mgs.test.util;

/**
 * Utility class holding common constants for feedback forms.
 *
 * Created by Jan Koren on 9/4/2016.
 */
public abstract class FeedbackConstants {

    public static final String BASIC_INFORMATION_FORM = "basicInformationForm";
    public static final String COURSE_RATING_FORM = "courseRatingForm";
    public static final String FEEDBACK_SUCCES = "feedbackSuccess";

    public static final String URL_FEEDBACK_BASE = "/feedback";

    public static final String URL_BASIC_INFORMATION = "/basicInformation";
    public static final String URL_COURSE_RATING = "/courseRating";
    public static final String URL_SUMMARY = "/summary";
    public static final String URL_SEND_FEEDBACK = "/sendFeedback";
    public static final String URL_WELCOME = "/welcome";

    public static final String VIEW_BASIC_INFORMATION = "basicInformation";
    public static final String VIEW_COURSE_RATING = "courseRating";
    public static final String VIEW_WELCOME = "welcome";
    public static final String VIEW_SUMMARY = "summary";

    private FeedbackConstants() {
        // avoid instantiation
    }
}
