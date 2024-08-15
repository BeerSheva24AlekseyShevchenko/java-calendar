package telran.time;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class PrintCalendar {
    private Calendar calendar;

    public PrintCalendar() {
        this(new Calendar());
    }

    public PrintCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void print(MonthYear monthYear) {
        printTitle(monthYear);
        printWeekDays();
        printDates(monthYear);
    }

    private void printDates(MonthYear monthYear) {
        Integer[][] days = calendar.getCalendarDates(monthYear.year(), monthYear.month());
        for (int i = 0; i < days.length; i++) {
            printWeek(days[i]);
        }
    }

    private void printWeek(Integer[] days) {
        String week = "";
        for (Integer day: days) {
            if (day == null) {
                week += String.format("%4s", "");
            } else {
                week += String.format("%4d", day);
            }
        }
        System.out.println(week);
    }

    private void printWeekDays() {
        DayOfWeek[] days = DayOfWeek.values();
        List<String> weekDays = new ArrayList<>();
        for (int i = 0; i < days.length; i++) {
            int dayIndex = (calendar.getFirstDayOfWeek() - 1 + i) % days.length;
            String dayName = days[dayIndex].name().substring(0, 3);
            weekDays.add(dayName);
        }

        System.out.println(" " + String.join(" ", weekDays));
    }

    private static void printTitle(MonthYear monthYear) {
        String title = String.format(
            "%s, %s",
            monthYear.year(),
            Month.of(monthYear.month()).name()
        );
        System.out.println(title);
    }
}
