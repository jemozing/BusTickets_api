package org.BusTickets.api.helpers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@AllArgsConstructor
public class DateCalculatorHelper {
    public List<LocalDate> calculateFlightDays(LocalDate fromDateStr, LocalDate toDateStr, String period) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        LocalDate fromDate = fromDateStr;
        LocalDate toDate = toDateStr;

        List<LocalDate> dates = new ArrayList<>();

        switch (period.toLowerCase()) {
            case "daily":
                return generateDaily(fromDate, toDate);
            case "odd":
                return generateOdd(fromDate, toDate);
            case "even":
                return generateEven(fromDate, toDate);
            default:
                if (period.contains(",")) {
                    String[] dayOfWeekParts = period.split(",");
                    for (String dayOfWeekPart : dayOfWeekParts) {
                        dayOfWeekPart = dayOfWeekPart.trim();
                        if (dayOfWeekPart.equalsIgnoreCase("daily")) {
                            return generateDaily(fromDate, toDate);
                        } else if (dayOfWeekPart.equalsIgnoreCase("odd")) {
                            return generateOdd(fromDate, toDate);
                        } else if (dayOfWeekPart.equalsIgnoreCase("even")) {
                            return generateEven(fromDate, toDate);
                        } else if (dayOfWeekPart.length() >= 3 && dayOfWeekPart.length() <= 9) {
                            DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayOfWeekPart.toUpperCase());
                            dates.addAll(generateDayOfWeek(fromDate, toDate, dayOfWeek));
                        }
                    }
                } else if (period.contains("-")) {
                    String[] rangeParts = period.split("-");
                    int start = Integer.parseInt(rangeParts[0]);
                    int end = Integer.parseInt(rangeParts[1]);
                    dates.addAll(generateDayOfMonth(fromDate, toDate, start, end));
                } else {
                    int dayOfMonth = Integer.parseInt(period);
                    dates.addAll(generateDayOfMonth(fromDate, toDate, dayOfMonth));
                }
                return dates;
        }
    }
    private List<LocalDate> generateDaily(LocalDate fromDate, LocalDate toDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = fromDate;
        while (!currentDate.isAfter(toDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }
    private List<LocalDate> generateOdd(LocalDate fromDate, LocalDate toDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = fromDate;
        while (!currentDate.isAfter(toDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(2);
        }
        return dates;
    }
    private List<LocalDate> generateEven(LocalDate fromDate, LocalDate toDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = fromDate.plusDays(1);
        while (!currentDate.isAfter(toDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(2);
        }
        return dates;
    }
    private List<LocalDate> generateDayOfWeek(LocalDate fromDate, LocalDate toDate, DayOfWeek dayOfWeek) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = fromDate;
        while (!currentDate.isAfter(toDate)) {
            if (currentDate.getDayOfWeek() == dayOfWeek) {
                dates.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }
    private List<LocalDate> generateDayOfMonth(LocalDate fromDate, LocalDate toDate, int... daysOfMonth) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = fromDate;
        while (!currentDate.isAfter(toDate)) {
            for (int dayOfMonth : daysOfMonth) {
                if (currentDate.getDayOfMonth() == dayOfMonth) {
                    dates.add(currentDate);
                    break;
                }
            }
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }
    public Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public List<Date> localDateListToDateList(List<LocalDate> localDateList) {
        List<Date> dateList = new ArrayList<>();
        for (LocalDate localDate : localDateList) {
            dateList.add(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        return dateList;
    }
}
