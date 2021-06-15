package org.milan.date;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

/**
 * @author Milan Rathod
 */
public class UseLocalDateTime {

    LocalDateTime getLocalDateTimeUsingParseMethod(String representation) {
        return LocalDateTime.parse(representation);
    }

    LocalDateTime getEndOfDayFromLocalDateTimeDirectly(LocalDateTime localDateTime) {
        return localDateTime.with(ChronoField.NANO_OF_DAY, LocalTime.MAX.toNanoOfDay());
    }

    LocalDateTime getEndOfDayFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate()
                .atTime(LocalTime.MAX);
    }

    LocalDateTime ofEpochSecond(int epochSecond, ZoneOffset zoneOffset) {
        return LocalDateTime.ofEpochSecond(epochSecond, 0, zoneOffset);
    }
}
