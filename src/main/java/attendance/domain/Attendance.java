package attendance.domain;

import attendance.dto.AttendanceInfo;
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

    public String updateAttendance(String modifyTime, LocalDate localDate) {
        String[] split = modifyTime.split(":");
        AttendanceInfo attendanceInfo = Formatter.getAttendanceInfo(this,localDate);
        this.attendanceTime = LocalTime.of(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
        AttendanceInfo after = Formatter.getAttendanceInfo(this,localDate);
        return attendanceInfo.formattedInfo() + " -> " + onlyDateTime(after) + " 수정 완료!\n";
    }

    private static String onlyDateTime(AttendanceInfo after) {
        String[] split = after.formattedInfo().split(" ");
        return split[3] + " " + split[4];
    }
}
