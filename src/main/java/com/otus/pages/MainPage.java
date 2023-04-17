package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.Path;
import com.otus.support.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    @Inject
    public MainPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @FindBy(css = ".lessons__new-item-title")
    private List<WebElement> courseTitles;

    public void clickCourseTitleByName(String courseName) {
        Actions actions = new Actions(guiceScoped.driver);
        for (WebElement element : courseTitles) {
            if (element.getText().trim().equals(courseName)) {
                actions.moveToElement(element).build().perform();
                element.click();
                break;
            }
        }
    }

    public void searchingCourseCardByNameAndClickOnIt(String courseName) {
        WebElement courseCard = getWebElement(By.xpath("//div[contains(text(), '" + courseName + "')]"));
        Actions actions = new Actions(guiceScoped.driver);
        actions.moveToElement(courseCard).build().perform();
        actions.click(courseCard).build().perform();
    }

    public void checkCoursesAreDisplayedOnTheMainPage() {
        assert !this.courseTitles.isEmpty(): "Course cards aren't displayed on the Main Page";
    }

    public final WebElement getWebElement(final By locator) {
        return guiceScoped.driver.findElement(locator);
    }
}