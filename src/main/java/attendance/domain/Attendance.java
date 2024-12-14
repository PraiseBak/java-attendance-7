package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private final String crewName;
    private LocalTime attendanceTime;

    public Attendance(String crewName, LocalTime attendanceTime) {
        this.crewName = crewName;
        this.attendanceTime = attendanceTime;
    }

    public boolean isSameNickname(String nickname) {
        return nickname.equals(crewName);
    }

    public String getName() {
        return crewName;
    }

    public boolean isLate(LocalDate localDate) {
        return LateCalculator.isLate(attendanceTime, Day.getKrDayByDayOfWeek(localDate.getDayOfWeek().toString()));
    }

    public boolean isAbsense(LocalDate localDate) {
        return LateCalculator.isAbsense(attendanceTime, Day.getKrDayByDayOfWeek(localDate.getDayOfWeek().toString()));

    }

    public String getAttendantFormat() {
        return Formatter.getAttendantFormat(attendanceTime);
    }
}
