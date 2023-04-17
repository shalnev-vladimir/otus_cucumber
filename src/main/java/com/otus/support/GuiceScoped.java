package com.otus.support;

import com.otus.driver.WebDriverFactory;
import com.otus.exceptions.BrowserNotSupported;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
    public WebDriver driver = new WebDriverFactory().newDriver();
}
