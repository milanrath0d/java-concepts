package org.milan.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.FormatStyle;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link UseDateTimeFormatter}
 *
 * @author Milan Rathod
 */
class UseDateTimeFormatterTest {

    private final UseDateTimeFormatter subject = new UseDateTimeFormatter();
    private final LocalDateTime localDateTime = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);

    @Test
    public void givenALocalDate_whenFormattingAsIso_thenPass() {
        String result = subject.formatAsIsoDate(localDateTime);

        assertEquals("2015-01-25", result);
    }

    @Test
    public void givenALocalDate_whenFormattingWithPattern_thenPass() {
        String result = subject.formatCustom(localDateTime, "yyyy/MM/dd");

        assertEquals("2015/01/25", result);
    }

    @Test
    public void givenALocalDate_whenFormattingWithStyleAndLocale_thenPass() {
        String result = subject.formatWithStyleAndLocale(localDateTime, FormatStyle.MEDIUM, Locale.UK);

        assertEquals("25 Jan 2015, 06:30:00", result);
    }

}