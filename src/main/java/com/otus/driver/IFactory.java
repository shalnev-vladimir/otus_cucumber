package com.otus.driver;

import com.otus.exceptions.BrowserNotSupported;
import org.openqa.selenium.WebDriver;

public interface IFactory {

    WebDriver newDriver() throws BrowserNotSupported;

}