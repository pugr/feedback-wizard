package com.monster.mgs.test.util;

import com.monster.mgs.test.bo.FeedbackResult;
import com.monster.mgs.test.controller.FeedbackController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles all kinds of exception that may be thrown globally from all controllers.
 *
 * Created by Jan Koren on 9/4/2016.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private FeedbackController feedbackController;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String restartFlow(HttpServletRequest request, Model model, Exception e) {
        logger.error("Request {} threw an exception: {}", request.getRequestURL(), e);
        return feedbackController.welcome(model, FeedbackResult.ERROR);
    }
}
