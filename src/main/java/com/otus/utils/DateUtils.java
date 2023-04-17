package com.otus.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {


    // Переводим дату из формата String в формат Date
    public Date convertStringToDate(String stringDateToParse) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        return simpleDateFormat.parse(stringDateToParse);
    }

}
