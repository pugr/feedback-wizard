package com.monster.mgs.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

/**
 * Binds custom editors globally.
 *
 * Created by Jan Koren on 9/4/2016.
 */
@ControllerAdvice
public class GlobalBindingInitializer {

    private static final Logger logger = LoggerFactory.getLogger(GlobalBindingInitializer.class);

    /* Initialize a global InitBinder for dates instead of cloning its code in every Controller */
    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

}
