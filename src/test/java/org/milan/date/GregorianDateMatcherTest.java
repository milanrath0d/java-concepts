package org.milan.date;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link GregorianDateMatcher}
 *
 * @author Milan Rathod
 */
class GregorianDateMatcherTest {

    private final GregorianDateMatcher matcher = new GregorianDateMatcher();

    private final GregorianDateTestHelper testHelper = new GregorianDateTestHelper(matcher);

    @Test
    public void whenUsingGregorianDateMatcher_thenFormatConstraintsSatisfied() {
        testHelper.assertFormat();
    }

    @Test
    public void whenUsingGregorianDateMatcher_thenRangeConstraintsSatisfied() {
        testHelper.assertRange();
    }

    @Test
    public void whenYearIsLeap_thenFebruaryHas29Days() {
        testHelper.assertFebruary29th();
    }

    @Test
    public void whenMonthIsFebruary_thenMonthContainsUpTo28Days() {
        testHelper.assertFebruaryGeneralDates();
    }

    @Test
    public void whenMonthIsShort_thenMonthContainsUpTo30Days() {
        testHelper.assertMonthsOf30Days();
    }

    @Test
    public void whenMonthIsLong_thenMonthContainsUpTo31Days() {
        testHelper.assertMonthsOf31Dates();
    }

}