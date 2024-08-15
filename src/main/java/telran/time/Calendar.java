package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private final int DAYS_IN_WEEK = 7;
    private final Integer EMPTY_DAY = null;
    private int firstDayOfWeek;

    public Calendar() {
        this(DayOfWeek.MONDAY);
    }

    public Calendar(DayOfWeek firstDayOfWeek) {
        this(firstDayOfWeek.getValue());
    }

    public Calendar(int firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek; 
    }

    public int getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public Integer[][] getCalendarDates(int year, int month) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < getOffset(year, month); i++) {
            list.add(EMPTY_DAY);
        }

        for (int i = 0; i < getDaysOfMonth(year, month); i++) {
            list.add(i + 1);
        }

        int endOffset = getEndOffset(list.size());
        for (int i = 0; i < endOffset; i++) {
            list.add(EMPTY_DAY);
        }

        return groupByWeeks(list);
    }

    private int getFirstDayOfMonth(int year, int month) {
        return LocalDate.of(year, month, 1)
            .getDayOfWeek()
            .getValue();
    }

    private int getOffset(int year, int month) {
        int firstDayOfMonth = getFirstDayOfMonth(year, month);
        int offset = firstDayOfMonth - firstDayOfWeek;
        return offset < 0 ? offset + DAYS_IN_WEEK : offset;
    }

    private int getEndOffset(int total) {
        int remainder = total % DAYS_IN_WEEK;
        return remainder == 0 ? 0 : DAYS_IN_WEEK - remainder;
    }

    private int getDaysOfMonth(int year, int month) {
        return LocalDate.of(year, month, 1)
            .with(TemporalAdjusters.lastDayOfMonth())
            .getDayOfMonth();
    }

    private Integer[][] groupByWeeks(List<Integer> calendar) {
        int rows = calendar.size() / DAYS_IN_WEEK;
        Integer res[][] = new Integer[rows][DAYS_IN_WEEK];

        for (int i = 0; i < calendar.size(); i++) {
            int week = i / DAYS_IN_WEEK;
            int day = i % DAYS_IN_WEEK;
            res[week][day] = calendar.get(i);
        }
            
        return res;
    }
}
