package com.otus.data;

public enum BrowserData {
    CHROME("chrome"),
    OPERA("opera"),
    FIREFOX("firefox");

    private final String name;

    BrowserData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}