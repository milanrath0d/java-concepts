package org.milan.date;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Milan Rathod
 */
public class DayPeriodDemo {
    public static void main(String[] args) {
        LocalTime date = LocalTime.parse("15:25:08.690791");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h BBBBB");
        System.out.println(date.format(formatter));
    }
}
