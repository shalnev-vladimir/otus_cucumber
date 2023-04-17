package com.otus.components;

import static java.lang.System.*;

import com.google.inject.Inject;
import com.otus.support.GuiceScoped;
import com.otus.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class CourseCards extends AbsComponent<CourseCards> {

    @Inject
    DateUtils dateUtils;

    String allDatesOnAllCardsOnTheMainPage =
            "[class='lessons'] .lessons__new-item-start, [class='lessons'] "
                    +
                    ".lessons__new-item-bottom > .lessons__new-item-time";
    String coursesTitles = ".lessons__new-item-title";

    String courseName;
    Date courseStartDate;

    @Inject
    public CourseCards(GuiceScoped guiceScoped, String courseName, Date courseStartDate) {
        super(guiceScoped);
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
    }

    // Получаем список дат начала курсов в формате String
    public List<String> getDatesList() {
        List<WebElement> webEl = getWebElementsList(By.cssSelector(allDatesOnAllCardsOnTheMainPage));
        List<String> datesInStringFormat = new ArrayList<>();
        for (WebElement el : webEl) {
            datesInStringFormat.add(el.getText());
        }
        return datesInStringFormat;
    }

    // Получаем список названий курсов в формате String
    public List<String> getCoursesTitlesList() {
        List<WebElement> webEl = getWebElementsList(By.cssSelector(coursesTitles));
        List<String> titlesInStringFormat = new ArrayList<>();
        for (WebElement el : webEl) {
            titlesInStringFormat.add(el.getText());
        }
        return titlesInStringFormat;
    }

    // Вытаскиваем дату из шаблона '29 марта 12 месяцев'
    public String getDateFromStringThatStartsWithNumber(String str) {
        String[] ss = str.split(" ");
        ArrayList<String> wordsArray = new ArrayList<>(Arrays.asList(ss));
        int currentYear = LocalDate.now().getYear();
        return String.format("%s-%s-%d", wordsArray.get(0), convertMonthToEnglishName(wordsArray.get(1)), currentYear); // convertMonthToEnglishName(wordsArray.get(1))
    }

    // Вытаскиваем дату мз шаблона 'В сентябре 2025 года 9 месяцев'
    public String getDateFromStringThatStartsFromCapitalB(String str) {
        String[] ss = str.split(" ");
        ArrayList<String> wordsArray = new ArrayList<>(Arrays.asList(ss));
        return String.format("%s-%s-%s", 1, convertMonthToEnglishName(wordsArray.get(1)), wordsArray.get(2));
    }

    // Вытаскиваем дату из шаблона 'С 30 марта'
    public String getDateFromStringThatStartsFromCapitalC(String str) {
        String[] ss = str.split(" ");
        ArrayList<String> wordsArray = new ArrayList<>(Arrays.asList(ss));
        int currentYear = LocalDate.now().getYear();
        return String.format("%s-%s-%d", wordsArray.get(1), convertMonthToEnglishName(wordsArray.get(2)), currentYear);
    }

    // Получаем самый ранний и самый поздний курс по дате начала
    public void printCoursesThatStartAfterOrOnGivenDate(Date courseStartDate) throws ParseException {
        List<CourseCards> coursesThatStartAfterOrOnGivenDate = new ArrayList<>();
        List<String> names = getCoursesTitlesList();
        List<String> dates = getDatesList();

        for (int i = 0; i < dates.size(); i++) {
            if (!dates.get(i).equalsIgnoreCase("О дате старта будет объявлено позже")) {
                // устанавливаем имя курса в объект
                setCourseName(names.get(i));
                if (dates.get(i).trim().startsWith("С ")) {
                    // устанавливаем дату в объект
                    String startCourseDate = getDateFromStringThatStartsFromCapitalC(dates.get(i));
                    setCourseStartDate(dateUtils.convertStringToDate(startCourseDate));
                } else if (dates.get(i).trim().startsWith("В ")) {
                    // устанавливаем дату в объект
                    String startCourseDate = getDateFromStringThatStartsFromCapitalB(dates.get(i));
                    setCourseStartDate(dateUtils.convertStringToDate(startCourseDate));
                } else {
                    // устанавливаем дату в объект
                    String startCourseDate = getDateFromStringThatStartsWithNumber(dates.get(i));
                    setCourseStartDate(dateUtils.convertStringToDate(startCourseDate));
                }
            }
            CourseCards newCourseCards = new CourseCards(guiceScoped, getCourseName(), getCourseStartDate());
            if (newCourseCards.getCourseStartDate().after(courseStartDate) || newCourseCards.getCourseStartDate().equals(courseStartDate)) {
                coursesThatStartAfterOrOnGivenDate.add(newCourseCards);
            }
        }
        for (CourseCards courseCard : coursesThatStartAfterOrOnGivenDate) {
            out.println("Course name is " + courseCard.getCourseName());
            out.println("Course start date is " + courseCard.getCourseStartDate());
        }
    }

    // Переводим русские названия месяцев в английские
    public String convertMonthToEnglishName(String month) {
        String courseStartMonth;
        switch (month.trim().toLowerCase().replaceAll("[^а-я]", "")) {
            case "января":
            case "январе":
                courseStartMonth = "Jan";
                break;
            case "февраля":
            case "феврале":
                courseStartMonth = "Feb";
                break;
            case "марта":
            case "марте":
                courseStartMonth = "Mar";
                break;
            case "апреля":
            case "апреле":
                courseStartMonth = "Apr";
                break;
            case "мая":
            case "мае":
                courseStartMonth = "May";
                break;
            case "июня":
            case "июне":
                courseStartMonth = "Jun";
                break;
            case "июля":
            case "июле":
                courseStartMonth = "Jul";
                break;
            case "августа":
            case "августе":
                courseStartMonth = "Aug";
                break;
            case "сентября":
            case "сентябре":
                courseStartMonth = "Sep";
                break;
            case "октября":
            case "октябре":
                courseStartMonth = "Oct";
                break;
            case "ноября":
            case "ноябре":
                courseStartMonth = "Nov";
                break;
            case "декабря":
            case "декабре":
                courseStartMonth = "Dec";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + month);
        }
        return courseStartMonth;
    }

    public final List<WebElement> getWebElementsList(final By locator) {
        return guiceScoped.driver.findElements(locator);
    }
}
