package com.monster.mgs.test.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Jan Koren on 9/4/2016.
 */
public class BasicInformationDto {

    @Size(min=2, max=50)
    private String firstName;

    @Size(min=2, max=50)
    private String lastName;

    @NotEmpty
    @Email
    @Size(max = 200)
    private String emailAddress;

    @NotNull
    private CourseDto course;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Past
    private Date feedbackDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
