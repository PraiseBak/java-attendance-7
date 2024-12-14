package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;

public class Attendance {
    private final String crewName;
    private DateTimes attendanceDateTime;
    private int countLate = 0;
    private int countWarning = 0;
    private int countAbsense = 0;
    private int isWeeding = 0;

}
