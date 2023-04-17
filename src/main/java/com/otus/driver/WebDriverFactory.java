package com.otus.driver;

import com.google.inject.Inject;
import com.otus.data.BrowserData;
import com.otus.exceptions.BrowserNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Locale;

public class WebDriverFactory implements IFactory {

    private String browserName = "";

    @Inject
    public WebDriverFactory() {
        browserName = System.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);
    }

    public WebDriver newDriver() {
        boolean isSupported = false;

        for(BrowserData browserData: BrowserData.values()) {
            if(browserName.equals(browserData.name().toLowerCase(Locale.ROOT))) {
                isSupported = true;
            }
        }

        if(!isSupported) {
            try {
                throw new BrowserNotSupported(browserName);
            } catch(BrowserNotSupported ignored) {
                newDriver().quit();
            }
        }

        switch(BrowserData.valueOf(browserName.toUpperCase(Locale.ROOT))) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                try {
                    throw new BrowserNotSupported(browserName);
                } catch (BrowserNotSupported ignored) {
                    newDriver().quit();
                }
        }

        return null;
    }

}