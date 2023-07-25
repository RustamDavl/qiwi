package com.rustdv.exchangerate.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UrlBuilder {

    private static String url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
    private static String dummyDate = "%s/%s/%d";

    private UrlBuilder() {

    }
    public static String buildUrlByDate(String date) {
        return url.concat(format(date));
    }

    private static String format(String date) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var localDate = LocalDate.parse(date, formatter);


        StringBuilder dayOfMonth = new StringBuilder();
        int day = localDate.getDayOfMonth();
        if (day < 10) {
            dayOfMonth.append("0")
                    .append(localDate.getDayOfMonth());
        } else {
            dayOfMonth.append(localDate.getDayOfMonth());
        }

        StringBuilder monthNum = new StringBuilder();
        int month = localDate.getMonth().getValue();
        if (month < 10) {
            monthNum.append("0")
                    .append(localDate.getMonth().getValue());
        } else {
            monthNum.append(localDate.getMonth().getValue());
        }


        return String.format(dummyDate, dayOfMonth, monthNum, localDate.getYear());
    }
}
