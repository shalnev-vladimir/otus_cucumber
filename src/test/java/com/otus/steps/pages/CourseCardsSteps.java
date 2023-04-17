package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.components.CourseCards;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CourseCardsSteps {

    @Inject
    CourseCards newCourseCards;

    @Then("I display information of them in the console")
    public void printNameAndStartDateOfCoursesStartAfterSpecifiedDate() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.AUGUST, 1);
        Date date = calendar.getTime();
        newCourseCards.printCoursesThatStartAfterOrOnGivenDate(date);
    }

    @ParameterType("\\d{2}\\.\\d{2}\\.\\d{4}")
    public LocalDate myDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
