package org.milan.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Examples of {@link LocalDate}
 *
 * @author Milan Rathod
 */
public class UseLocalDate {

    LocalDate getLocalDateUsingFactoryOfMethod(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    LocalDate getLocalDateUsingParseMethod(String representation) {
        return LocalDate.parse(representation);
    }

    LocalDate getLocalDateFromClock() {
        return LocalDate.now();
    }

    LocalDate getNextDay(LocalDate localDate) {
        return localDate.plusDays(1);
    }

    LocalDate getPreviousDay(LocalDate localDate) {
        return localDate.minus(1, ChronoUnit.DAYS);
    }

    DayOfWeek getDayOfWeek(LocalDate localDate) {
        return localDate.getDayOfWeek();
    }

    LocalDate getFirstDayOfMonth() {
        return LocalDate.now()
                .with(TemporalAdjusters.firstDayOfMonth());
    }

    boolean isLeapYear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    LocalDateTime getStartOfDay(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    LocalDateTime getStartOfDayOfLocalDate(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
    }

    LocalDateTime getStartOfDayAtMinTime(LocalDate localDate) {
        return localDate.atTime(LocalTime.MIN);
    }

    LocalDateTime getStartOfDayAtMidnightTime(LocalDate localDate) {
        return localDate.atTime(LocalTime.MIDNIGHT);
    }

    LocalDateTime getEndOfDay(LocalDate localDate) {
        return localDate.atTime(LocalTime.MAX);
    }

    LocalDateTime getEndOfDayFromLocalTime(LocalDate localDate) {
        return LocalTime.MAX.atDate(localDate);
    }
}
