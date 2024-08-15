package telran.time;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.function.Supplier;
record MonthYear(int month, int year) {}
public class Main {
    public static void main(String[] args) {
        try {
            MonthYear monthYear = getMonthYear(args);
            Calendar calendar = new Calendar(7);
            PrintCalendar printCalendar = new PrintCalendar(calendar);
            printCalendar.print(monthYear);
        } catch (RuntimeException e) {
                e.printStackTrace();
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

    private static MonthYear getMonthYear(String[] args) throws Exception {
        try {
            int year = getIntValue(args, 0, () -> LocalDate.now().getYear());
            int month = getIntValue(args, 1, () -> LocalDate.now().getMonthValue());

            validateYear(year);
            validateMonth(month);

            return new MonthYear(month, year);
        } catch (Exception e) {
            throw new Exception("Invalid input");
        }
    }

    private static Integer getIntValue(String[] args, int index, Supplier<Integer> defaultValue) throws Exception {
        return args.length > index ? Integer.parseInt(args[index]) : defaultValue.get();
    }

    private static void validateYear(int year) throws Exception {
        Year.parse(Integer.toString(year));
    }

    private static void validateMonth(int month) throws Exception {
        Month.of(month);
    }
}