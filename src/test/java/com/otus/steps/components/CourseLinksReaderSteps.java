package com.otus.steps.components;

import com.google.inject.Inject;
import com.otus.components.CourseLinksReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CourseLinksReaderSteps {

    @Inject
    CourseLinksReader courseLinksReader;

    @Given("Unique courses links")
    public Set<String> getUniqueCourseLinksAndCheckSetIsNotEmpty() throws IOException {
        Set<String> uniqueCourseLinks = courseLinksReader.getUniqueCourseLinks();
        Assertions.assertFalse(uniqueCourseLinks.isEmpty());
        return uniqueCourseLinks;
    }

    @When("We get the name and cost of each Otus course")
    public HashMap<String, Integer> getCourseNameAndItsPrice() throws IOException {
        Map<String, Integer> courseNamesAndPrices =
                courseLinksReader.getNamesAndCostsOfCourses(getUniqueCourseLinksAndCheckSetIsNotEmpty());
        Assertions.assertFalse(courseNamesAndPrices.isEmpty());
        return (HashMap<String, Integer>) courseNamesAndPrices;//courseLinksReader.getNamesAndCostsOfCourses(getUniqueCourseLinksAndCheckSetIsNotEmpty());
    }

    @Then("We print the most expansive and the cheapest Otus course")
    public void printMostExpansiveAndCheapestOtusCourse() throws IOException {
        Set<String> uniqueCourseLinks = courseLinksReader.getUniqueCourseLinks();
        Assertions.assertFalse(uniqueCourseLinks.isEmpty());

        Map<String, Integer> courseNamesAndPrices =
                courseLinksReader.getNamesAndCostsOfCourses(uniqueCourseLinks);
        Assertions.assertFalse(courseNamesAndPrices.isEmpty());

        courseLinksReader.printMostExpansiveAndCheapestCourse(courseNamesAndPrices);
    }
}
