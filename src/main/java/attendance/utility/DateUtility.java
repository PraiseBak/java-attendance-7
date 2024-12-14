package attendance.utility;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateUtility {

    private static int OFF_CASE_MONTH = 12;
    private static int OFF_CASE_DAY = 25;

    public static LocalDate getLocalDateFromString(String s) {
        String[] split = s.split("-");
        int y = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int d = Integer.parseInt(split[2]);
        return LocalDate.of(y,m,d);
    }

    public static LocalTime getLocalTimeFromString(String s) {
        String[] split = s.split(":");
        int h = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        return LocalTime.of(h,m);
    }

    public static boolean isSpecialWeek(LocalDate now) {
        if(now.getMonthValue() == OFF_CASE_MONTH && now.getDayOfMonth() == OFF_CASE_DAY){
            return true;
        }
        return false;
    }
}
