package attendance.domain;

import java.time.DayOfWeek;

public enum Day {
    MONDAY(DayOfWeek.MONDAY.toString(),"월요일"),
    TUESDAY(DayOfWeek.TUESDAY.toString(),"화요일"),
    WEDNESDAY(DayOfWeek.WEDNESDAY.toString(),"수요일"),
    THURSDAY(DayOfWeek.THURSDAY.toString(),"목요일"),
    FRIDAY(DayOfWeek.FRIDAY.toString(),"금요일"),
    SATURDAY(DayOfWeek.SATURDAY.toString(),"토요일"),
    SUNDAY(DayOfWeek.SUNDAY.toString(),"일요일"),
    ;
    private final String dayOfWeek;
    private final String krDayOfWeek;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getKrDayOfWeek() {
        return krDayOfWeek;
    }

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
        if(dayByDayOfWeek == SATURDAY || dayByDayOfWeek == SUNDAY){
            return true;
        }
        return false;
    }
}
