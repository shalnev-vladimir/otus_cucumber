package com.otus.components;

import com.google.inject.Inject;
import com.otus.pageobject.AbsPageObject;
import com.otus.support.GuiceScoped;
import org.openqa.selenium.WebDriver;

public abstract class AbsComponent<T> extends AbsPageObject<T> {

    @Inject
    public AbsComponent(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }


}