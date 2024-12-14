package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class LateCalculator {
    private final static LocalTime MONDAY_START = LocalTime.of(13,0);
    private final static LocalTime MONDAY_END = LocalTime.of(18,0);
    private final static LocalTime ELSE_START = LocalTime.of(10,0);
    private final static LocalTime ELSE_END = LocalTime.of(18,0);
    private static final int CLOSE_HOUR = 23;
    private static final int OPEN_HOUR = 8;


    //지각 로직
    public static boolean isLate(LocalTime time, String krDayOfWeek){
        if(krDayOfWeek.equals(Day.MONDAY.getKrDayOfWeek())){
            LocalTime late = LocalTime.of(MONDAY_START.getHour(),MONDAY_START.getMinute()+5);
            return time.compareTo(late) > 0;
        }
        LocalTime late = LocalTime.of(ELSE_START.getHour(),ELSE_END.getMinute()+5);
        return time.compareTo(late) > 0;
    }

    public static boolean isAbsense(LocalTime time, String krDayOfWeek){
        if(krDayOfWeek.equals(Day.MONDAY.getKrDayOfWeek())){
            LocalTime late = LocalTime.of(MONDAY_START.getHour(),MONDAY_START.getMinute()+30);
            return time.compareTo(late) > 0;
        }
        LocalTime late = LocalTime.of(ELSE_START.getHour(),ELSE_END.getMinute()+30);
        return time.compareTo(late) > 0;
    }

    public static boolean isNotOpenSchool(LocalTime now) {
        if(now.getHour() < OPEN_HOUR || now.getHour() >= CLOSE_HOUR){
            return true;
        }
        return false;
    }
}
