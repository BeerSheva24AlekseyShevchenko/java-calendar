package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.stream.Collectors;
record MonthYear(int month, int year) {}
public class Main {
    public static void main(String[] args) {
        try {
            MonthYear monthYear = getMonthYear(args);
            printCalendar(monthYear);
        } catch (RuntimeException e) {
                e.printStackTrace();
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
       
    }

    private static void printCalendar(MonthYear monthYear) {
        printTitle(monthYear);
        printWeekDays();
        printDates(monthYear);
    }

    private static void printDates(MonthYear monthYear) {
        int offset = getOffset(getFirstDayOfWeek(monthYear));
        int lastDayOfMonth = getLastDayOfMonth(monthYear);
        int day = 1;

        for (int i = 0; i < offset; i++) {
            System.out.printf("%4s", " ");
        }

        while (day <= lastDayOfMonth) {
            System.out.printf("%4d", day);
            day++;
            offset++;

            if (offset % 7 == 0) System.out.println();
        }
    }

    private static void printWeekDays() {
       String weekDays = Arrays.stream(DayOfWeek.values())
            .map(day -> day.name().substring(0, 3))
            .collect(Collectors.joining(" "));
        System.out.println(weekDays);
    }

    private static void printTitle(MonthYear monthYear) {
        String title = String.format(
            "%s, %s",
            monthYear.year(),
            Month.of(monthYear.month()).name()
        );
        System.out.println(title);
    }

    private static MonthYear getMonthYear(String[] args) throws Exception {
        LocalDate currentDate = LocalDate.now();
        int year = args.length > 0 ? Integer.parseInt(args[0]) : currentDate.getMonthValue();
        int month = args.length > 1 ? Integer.parseInt(args[1]) : currentDate.getYear();

        MonthYear monthYear = new MonthYear(month, year);
        validateMonthYear(monthYear);

        return monthYear;
    }

    private static void validateMonthYear(MonthYear monthYear) throws Exception {
        YearMonth.of(monthYear.year(), monthYear.month());
    }

    private static int getFirstDayOfWeek(MonthYear monthYear) {
        return LocalDate.of(monthYear.year(), monthYear.month(), 1)
            .getDayOfWeek()
            .getValue();
    }

    private static int getOffset(int firstWeekDay) {
        return firstWeekDay - DayOfWeek.MONDAY.getValue();
    }

    private static int getLastDayOfMonth(MonthYear monthYear) {
        return LocalDate.of(monthYear.year(), monthYear.month(), 1)
            .with(TemporalAdjusters.lastDayOfMonth())
            .getDayOfMonth();
    }
}