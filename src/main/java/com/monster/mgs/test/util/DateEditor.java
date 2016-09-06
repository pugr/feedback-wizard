package com.monster.mgs.test.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom date editor for string-to-date conversion and vice versa.
 *
 * Created by Jan Koren on 9/4/2016.
 */
public class DateEditor extends PropertyEditorSupport {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public void setAsText(String value) {
        try {
            setValue(new SimpleDateFormat(DATE_PATTERN).parse(value));
        } catch (ParseException e) {
            setValue(null);
        }
    }

    public String getAsText() {
        String s = "";
        if (getValue() != null) {
            s = new SimpleDateFormat(DATE_PATTERN).format((Date) getValue());
        }
        return s;
    }
}
