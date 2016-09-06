package com.monster.mgs.test.bo;

import java.util.Date;

/**
 * Created by Jan Koren on 9/4/2016.
 */
public class FeedbackBo {

    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final CourseBo course;
    private final CourseSectionBo courseSection;
    private final Date courseDate;
    private final int rating;
    private final String comment;

    public FeedbackBo(String firstName, String lastName, String emailAddress, CourseBo course,
                      CourseSectionBo courseSection, Date courseDate, int rating, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.course = course;
        this.courseSection = courseSection;
        this.courseDate = courseDate;
        this.rating = rating;
        this.comment = comment;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public CourseBo getCourse() {
        return course;
    }

    public CourseSectionBo getCourseSection() {
        return courseSection;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "FeedbackBo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", course=" + course +
                ", courseSection=" + courseSection +
                ", courseDate=" + courseDate +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
