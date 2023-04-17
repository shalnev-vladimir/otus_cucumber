package com.otus.components;

import static java.lang.Integer.parseInt;
import static java.lang.System.*;
import static java.util.Comparator.comparingInt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.NotFoundException;

import java.io.IOException;
import java.util.*;

/**
 * Здесь мы собрали все ссылки.
 * Потом прошли по каждой ссылке и вытащили название и стоимость курса.
 * Дальше получили самый дорогой курс.
 * Потом самый дешёвый.
 */
public class CourseLinksReader {

    // получаем все уникальные ссылки
    public Set<String> getUniqueCourseLinks() throws IOException {
        Set<String> uniqueURL = new HashSet<>();

        org.jsoup.nodes.Document document = Jsoup.connect("https://otus.ru/lessons/calendar/2023").get();

        Elements links = document.select("a[href]");
        for (Element link : links) {
            if (link.attr("href").contains("lessons/") && !link.attr("href").contains("calendar/")) {
                uniqueURL.add("https://otus.ru" + link.attr("href"));
            }
        }
        return uniqueURL;
    }

    // получаем мапу из имен и цен курсов
    public Map<String, Integer> getNamesAndCostsOfCourses(Set<String> coursesUrls) throws IOException {
        Map<String, Integer> nameAndCostOfCourse = new HashMap<>();

        for (String url : coursesUrls) {
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
            if (doc.selectFirst(".course-bottom-bar-meta__value > nobr") != null) {
                nameAndCostOfCourse
                        .put(Objects.requireNonNull(doc.selectFirst(".course-header2__title")).text(),
                                parseInt(Objects.requireNonNull(doc.selectFirst(".course-bottom-bar-meta__value > nobr")).text()
                                        .replaceAll("\\D+", "")));
            }
        }
        return nameAndCostOfCourse;
    }

    // печатаем самый дорогой и самый дешевый курс
    public void printMostExpansiveAndCheapestCourse(Map<String, Integer> namesAndCostsOfCourses) {
        Optional<Map.Entry<String, Integer>> mostExpansiveCourse =
                namesAndCostsOfCourses
                        .entrySet()
                        .stream()
                        .filter(x -> x.getValue() > 0)
                        .max(comparingInt(Map.Entry::getValue));

        Optional<Map.Entry<String, Integer>> cheapestCourse =
                namesAndCostsOfCourses
                        .entrySet()
                        .stream()
                        .filter(x -> x.getValue() > 0)
                        .min(comparingInt(Map.Entry::getValue));

        if (mostExpansiveCourse.isEmpty() || cheapestCourse.isEmpty()) {
            throw new NotFoundException();
        } else {
            print("The most expansive course is: " + mostExpansiveCourse.get());
            print("The cheapest course is: " + cheapestCourse.get());
        }
    }

    static void print(Object print) {
        out.println(print);
    }
}