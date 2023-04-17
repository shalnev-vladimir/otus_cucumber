package com.otus.pageobject;

import com.google.inject.Inject;
import com.otus.support.GuiceScoped;
import com.otus.waiters.BaseWaiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsPageObject<T> {

    protected WebDriver driver;
    protected BaseWaiters baseWaiters;
    protected GuiceScoped guiceScoped;

    @Inject
    public AbsPageObject(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
        this.driver = guiceScoped.driver;
        this.baseWaiters = new BaseWaiters(driver);

        PageFactory.initElements(guiceScoped.driver, this);
    }

}