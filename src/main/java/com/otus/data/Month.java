package com.otus.data;

public enum Month {

    JANUARY("январ", "Jun"),
    FEBRUARY("феврал", "Feb"),
    MARCH("март", "Mar"),
    APRIL("апрел", "Apr"),
    MAY("ма", "May"),
    JUNE("июн", "Jun"),
    JULY("июл", "Jul"),
    AUGUST("август", "Aug"),
    SEPTEMBER("сентябр", "Sep"),
    OCTOBER("октябр", "Oct"),
    NOVEMBER("ноябр", "Nov"),
    DECEMBER("декабр", "Dec");

    private String monthNameInRussian;
    private String monthNameInEnglish;

    Month(String monthNameInRussian, String monthNameInEnglish) {
        this.monthNameInRussian = monthNameInRussian;
        this.monthNameInEnglish = monthNameInEnglish;
    }
}
