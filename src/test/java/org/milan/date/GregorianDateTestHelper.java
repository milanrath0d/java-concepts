package org.milan.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Milan Rathod
 */
public class GregorianDateTestHelper {

    private final GregorianDateMatcher matcher;

    public GregorianDateTestHelper(GregorianDateMatcher matcher) {
        this.matcher = matcher;
    }

    public void assertFormat() {
        assertTrue(matcher.matches("2017-12-31"));
        assertTrue(matcher.matches("2018-01-01"));

        assertFalse(matcher.matches("2018-02"));
        assertFalse(matcher.matches("2018-02-01-01"));
        assertFalse(matcher.matches("2018-02-XX"));
        assertFalse(matcher.matches(" 2018-02-01"));
        assertFalse(matcher.matches("2018-02-01 "));
        assertFalse(matcher.matches("2020/02/28"));
        assertFalse(matcher.matches("2020.02.29"));
    }

    public void assertRange() {
        assertTrue(matcher.matches("1900-01-01"));
        assertTrue(matcher.matches("2205-05-25"));
        assertTrue(matcher.matches("2999-12-31"));

        assertFalse(matcher.matches("1899-12-31"));
        assertFalse(matcher.matches("2018-05-35"));
        assertFalse(matcher.matches("2018-13-05"));
        assertFalse(matcher.matches("3000-01-01"));
        assertFalse(matcher.matches("3200-02-29"));
    }

    public void assertFebruary29th() {
        assertTrue(matcher.matches("2000-02-29"));
        assertTrue(matcher.matches("2400-02-29"));
        assertTrue(matcher.matches("2800-02-29"));
        assertTrue(matcher.matches("2020-02-29"));
        assertTrue(matcher.matches("2024-02-29"));
        assertTrue(matcher.matches("2028-02-29"));

        assertFalse(matcher.matches("2017-02-29"));
        assertFalse(matcher.matches("2018-02-29"));
        assertFalse(matcher.matches("2019-02-29"));
        assertFalse(matcher.matches("2100-02-29"));
        assertFalse(matcher.matches("2200-02-29"));
        assertFalse(matcher.matches("2300-02-29"));
    }

    public void assertFebruaryGeneralDates() {
        assertTrue(matcher.matches("2018-02-01"));
        assertTrue(matcher.matches("2019-02-13"));
        assertTrue(matcher.matches("2020-02-25"));

        assertFalse(matcher.matches("2000-02-30"));
        assertFalse(matcher.matches("2400-02-62"));
        assertFalse(matcher.matches("2420-02-94"));
    }

    public void assertMonthsOf30Days() {
        assertTrue(matcher.matches("2018-04-30"));
        assertTrue(matcher.matches("2019-06-30"));
        assertTrue(matcher.matches("2020-09-30"));
        assertTrue(matcher.matches("2021-11-30"));

        assertTrue(matcher.matches("2022-04-02"));
        assertTrue(matcher.matches("2023-06-14"));
        assertTrue(matcher.matches("2024-09-26"));

        assertFalse(matcher.matches("2018-04-31"));
        assertFalse(matcher.matches("2019-06-31"));
        assertFalse(matcher.matches("2020-09-31"));
        assertFalse(matcher.matches("2021-11-31"));

        assertFalse(matcher.matches("2022-04-32"));
        assertFalse(matcher.matches("2023-06-64"));
        assertFalse(matcher.matches("2024-09-96"));
    }

    public void assertMonthsOf31Dates() {
        assertTrue(matcher.matches("2018-01-31"));
        assertTrue(matcher.matches("2019-03-31"));
        assertTrue(matcher.matches("2020-05-31"));
        assertTrue(matcher.matches("2021-07-31"));
        assertTrue(matcher.matches("2022-08-31"));
        assertTrue(matcher.matches("2023-10-31"));
        assertTrue(matcher.matches("2024-12-31"));

        assertTrue(matcher.matches("2025-01-03"));
        assertTrue(matcher.matches("2026-03-15"));
        assertTrue(matcher.matches("2027-05-27"));

        assertFalse(matcher.matches("2018-01-32"));
        assertFalse(matcher.matches("2019-03-64"));
        assertFalse(matcher.matches("2020-05-96"));
    }
}
