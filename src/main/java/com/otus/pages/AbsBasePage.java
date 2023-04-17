package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.Path;
import com.otus.pageobject.AbsPageObject;
import com.otus.support.GuiceScoped;

public abstract class AbsBasePage<T> extends AbsPageObject<T> {

    private String baseUrl = System.getProperty("base.url", "https://otus.ru");

    @Inject
    public AbsBasePage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    private String getPagePath() {
        Class<? extends AbsBasePage> clazz = this.getClass();
        if(clazz.isAnnotationPresent(Path.class)) {
            Path path = clazz.getAnnotation(Path.class);
            return path.value();
        }

        return "";
    }

    public T open() {
        driver.get(baseUrl + getPagePath());

        return (T) this;
    }

}