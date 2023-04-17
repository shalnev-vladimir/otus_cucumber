package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OpenBrowserSteps {

    @Inject
    private MainPage mainPage;

    @Given("I open browser {string}")
    public void iOpenBrowser(String browserName) {
        mainPage.open();
        System.out.println("Браузер открыт");
    }

    @When("I moved to the main page")
    public void iMovedToTheMainPage() {
        System.out.println("Я перешел на главную страницу");
    }

    @Then("Search field is visible")
    public void searchFieldIsVisible() {
        System.out.println("Строка поиска видна");
    }
}
