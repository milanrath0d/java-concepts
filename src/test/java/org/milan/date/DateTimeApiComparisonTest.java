package org.milan.date;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Examples of Date and Time comparison based on DateTime apis
 *
 * @author Milan Rathod
 */
public class DateTimeApiComparisonTest {

    @Test
    public void givenLocalDates_whenComparing_thenAssertsPass() {
        LocalDate firstDate = LocalDate.of(2019, 8, 10);
        LocalDate secondDate = LocalDate.of(2019, 7, 1);
        LocalDate thirdDate = LocalDate.of(2019, 7, 1); // same date as secondDate

        assertTrue(firstDate.isAfter(secondDate));
        assertFalse(firstDate.isBefore(secondDate));
        assertFalse(firstDate.isEqual(secondDate));
        assertNotEquals(firstDate, secondDate);

        assertEquals(1, firstDate.compareTo(secondDate));
        assertEquals(-1, secondDate.compareTo(firstDate));

        assertFalse(secondDate.isAfter(thirdDate));
        assertFalse(secondDate.isBefore(thirdDate));
        assertTrue(secondDate.isEqual(thirdDate));
        assertEquals(secondDate, thirdDate);
        assertEquals(0, secondDate.compareTo(thirdDate));
    }

    @Test
    public void givenLocalDateTimes_whenComparing_thenAssertsPass() {
        LocalDateTime firstTimestamp = LocalDateTime.of(2019, 8, 10, 11, 30, 0);
        LocalDateTime secondTimestamp = LocalDateTime.of(2019, 8, 10, 11, 15, 0);
        LocalDateTime thirdTimestamp = LocalDateTime.of(2019, 8, 10, 11, 15, 0); // same as secondTimestamp

        assertTrue(firstTimestamp.isAfter(secondTimestamp));
        assertFalse(firstTimestamp.isBefore(secondTimestamp));

        assertFalse(firstTimestamp.isEqual(secondTimestamp));
        assertNotEquals(firstTimestamp, secondTimestamp);

        assertEquals(1, firstTimestamp.compareTo(secondTimestamp));
        assertEquals(-1, secondTimestamp.compareTo(firstTimestamp));

        assertFalse(secondTimestamp.isAfter(thirdTimestamp));
        assertFalse(secondTimestamp.isBefore(thirdTimestamp));
        assertTrue(secondTimestamp.isEqual(thirdTimestamp));
        assertEquals(0, secondTimestamp.compareTo(thirdTimestamp));
    }

    @Test
    public void givenZonedDateTimes_whenComparing_thenAssertsPass() {
        ZonedDateTime timeInNewYork = ZonedDateTime.of(2019, 8, 10, 8, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime timeInBerlin = ZonedDateTime.of(2019, 8, 10, 14, 0, 0, 0, ZoneId.of("Europe/Berlin"));

        assertFalse(timeInNewYork.isAfter(timeInBerlin));
        assertFalse(timeInNewYork.isBefore(timeInBerlin));

        assertTrue(timeInNewYork.isEqual(timeInBerlin));
        assertNotEquals(timeInNewYork, timeInBerlin);

        assertEquals(-1, timeInNewYork.compareTo(timeInBerlin));
    }

    @Test
    public void givenLocalTimes_whenComparing_thenAssertsPass() {
        LocalTime firstTime = LocalTime.of(8, 30);
        LocalTime secondTime = LocalTime.of(9, 45);

        assertFalse(firstTime.isAfter(secondTime));
        assertTrue(firstTime.isBefore(secondTime));

        assertNotEquals(firstTime, secondTime);
        assertEquals(-1, firstTime.compareTo(secondTime));
    }

    @Test
    public void givenMinMaxLocalTimes_whenComparing_thenAssertsPass() {
        LocalTime minTime = LocalTime.MIN;
        LocalTime time = LocalTime.of(8, 30);
        LocalTime maxTime = LocalTime.MAX;

        assertTrue(minTime.isBefore(time));
        assertTrue(time.isBefore(maxTime));
    }
}
