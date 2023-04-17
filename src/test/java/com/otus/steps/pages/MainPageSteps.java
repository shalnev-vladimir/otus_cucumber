package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MainPageSteps {

    @Inject
    private MainPage mainPage;

    @And("Course cards are displayed")
    public void checkCourseCardsAreDisplayed() {
        mainPage.checkCoursesAreDisplayedOnTheMainPage();
    }

    @Then("I click on the {string} course title")
    public void clickOnSpecifiedCourseTitle(String courseName) {
        mainPage.searchingCourseCardByNameAndClickOnIt(courseName);
    }
}
