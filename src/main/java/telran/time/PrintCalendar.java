package telran.time;

import java.time.Month;

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
        Integer[][] dates = calendar.getCalendarDates(monthYear.year(), monthYear.month());
        for (Integer[] week: dates) {
            printWeek(week);
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
        String[] daysOfWeek = calendar.getCalendarDaysOfWeek((s) -> s.substring(0, 3));

        System.out.println(" " + String.join(" ", daysOfWeek));
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
