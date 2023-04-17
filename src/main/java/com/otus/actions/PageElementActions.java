package com.otus.actions;

//import jdk.jfr.Description;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class PageElementActions {

    // Фильтрует список по текту
    public static List<String> filteringListByText(final List<WebElement> elements, final String text) {
        return elements
                .stream()
                .map(WebElement::getText)
                .filter(e -> e.equalsIgnoreCase(text))
                .collect(Collectors.toList());
    }
}
