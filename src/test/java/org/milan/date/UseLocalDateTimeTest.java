package org.milan.date;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link UseLocalDateTime}
 *
 * @author Milan Rathod
 */
class UseLocalDateTimeTest {

    private final UseLocalDateTime useLocalDateTime = new UseLocalDateTime();

    @Test
    public void givenString_whenUsingParse_thenLocalDateTime() {
        assertEquals(LocalDate.of(2016, Month.MAY, 10), useLocalDateTime.getLocalDateTimeUsingParseMethod("2016-05-10T06:30")
                .toLocalDate());
        assertEquals(LocalTime.of(6, 30), useLocalDateTime.getLocalDateTimeUsingParseMethod("2016-05-10T06:30")
                .toLocalTime());
    }

    @Test
    public void givenLocalDateTime_whenSettingEndOfDay_thenReturnLastMomentOfDay() {
        LocalDateTime givenTimed = LocalDateTime.parse("2018-06-23T05:55:55");

        LocalDateTime endOfDayFromGivenDirectly = useLocalDateTime.getEndOfDayFromLocalDateTimeDirectly(givenTimed);
        LocalDateTime endOfDayFromGiven = useLocalDateTime.getEndOfDayFromLocalDateTime(givenTimed);

        assertEquals(endOfDayFromGiven, endOfDayFromGivenDirectly);
        assertEquals(LocalTime.MAX, endOfDayFromGivenDirectly.toLocalTime());
        assertEquals("2018-06-23T23:59:59.999999999", endOfDayFromGivenDirectly.toString());
    }

    @Test
    public void givenLocalDateTimeInFebruary_whenRequestingMonth_thenMonthIsFebruary() {
        LocalDateTime givenLocalDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 6, 30);

        assertEquals(Month.FEBRUARY, givenLocalDateTime.getMonth());
    }

    @Test
    public void givenLocalDateTime_whenManipulating_thenResultIsAsExpected() {
        LocalDateTime givenLocalDateTime = LocalDateTime.parse("2015-02-20T06:30:00");

        LocalDateTime manipulatedLocalDateTime = givenLocalDateTime.plusDays(1);
        manipulatedLocalDateTime = manipulatedLocalDateTime.minusHours(2);

        assertEquals(LocalDateTime.of(2015, Month.FEBRUARY, 21, 4, 30), manipulatedLocalDateTime);
    }

    @Test
    public void whenRequestTimeFromEpoch_thenResultIsAsExpected() {
        LocalDateTime result = useLocalDateTime.ofEpochSecond(1465817690, ZoneOffset.UTC);

        assertEquals("2016-06-13T11:34:50", result.toString());
    }

}