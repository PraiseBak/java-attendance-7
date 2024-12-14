package attendance.domain;

import java.time.DayOfWeek;

public enum Day {
    MON(DayOfWeek.MONDAY.toString(),"월요일"),
    THU(DayOfWeek.THURSDAY.toString(),"화요일"),
    WED(DayOfWeek.WEDNESDAY.toString(),"수요일"),
    THR(DayOfWeek.THURSDAY.toString(),"목요일"),
    FRI(DayOfWeek.FRIDAY.toString(),"금요일"),
    SAT(DayOfWeek.SATURDAY.toString(),"토요일"),
    SUN(DayOfWeek.SUNDAY.toString(),"일요일"),
    ;
    private final String dayOfWeek;
    private final String krDayOfWeek;

    Day(String dayOfWeek, String krDayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.krDayOfWeek = krDayOfWeek;
    }

    public static String getKrDayByDayOfWeek(String string) {
        Day day = getDayByDayOfWeek(string);
        if (day != null) {
            return day.krDayOfWeek;
        }
        return "";
    }

    private static Day getDayByDayOfWeek(String string) {
        for(Day day : Day.values()){
            if(day.dayOfWeek.equals(string)){
                return day;
            }
        }
        return null;
    }

    public static boolean isDayOff(String string) {
        Day dayByDayOfWeek = getDayByDayOfWeek(string);
        if(dayByDayOfWeek == SAT || dayByDayOfWeek == SUN){
            return true;
        }
        return false;
    }
}
