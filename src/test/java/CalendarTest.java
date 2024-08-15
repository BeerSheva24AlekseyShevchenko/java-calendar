
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import telran.time.Calendar;

public class CalendarTest {
    Calendar calendarMon = new Calendar();
    Calendar calendarSun = new Calendar(DayOfWeek.SUNDAY);

    @Test
    void testGetCalendar() {
        Integer[][] arr1 = calendarMon.getCalendarDates(2024, 8);
        Integer[][] expend1 = new Integer[][]{
            { null, null, null, 1, 2, 3, 4},
            { 5, 6, 7, 8, 9, 10, 11},
            { 12, 13, 14, 15, 16, 17, 18},
            { 19, 20, 21, 22, 23, 24, 25},
            { 26, 27, 28, 29, 30, 31, null},
        };

        for (int i = 0; i < arr1.length; i++) {
            assertArrayEquals(arr1[i], expend1[i]);
        }

        Integer[][] arr2 = calendarSun.getCalendarDates(2024, 8);
        Integer[][] expend2 = new Integer[][]{
            { null, null, null, null, 1, 2, 3},
            { 4, 5, 6, 7, 8, 9, 10},
            { 11, 12, 13, 14, 15, 16, 17},
            { 18, 19, 20, 21, 22, 23, 24},
            { 25, 26, 27, 28, 29, 30, 31},
        };

        for (int i = 0; i < arr2.length; i++) {
            assertArrayEquals(arr2[i], expend2[i]);
        }
    }

    @Test
    void daysOfWeekTest() {
        String[] extended1 = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        assertArrayEquals(extended1, calendarMon.getCalendarDaysOfWeek());

        String[] extended2 = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
        assertArrayEquals(extended2, calendarSun.getCalendarDaysOfWeek((s) -> s.substring(0, 3)));
    }
}
