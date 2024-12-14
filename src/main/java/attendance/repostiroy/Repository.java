package attendance.repostiroy;

import attendance.domain.Attendance;
import attendance.domain.AttendanceManager;
import attendance.domain.Formatter;
import attendance.domain.Late;
import attendance.domain.LateManager;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Repository {

    private LocalDate now;
    private AttendanceManager attendanceManager = null;
    private LateManager lateManager = null;

    public void initDate() {
        now = DateTimes.now().toLocalDate();
    }

    public LocalDate getNow(){
        return now;
    }

    public String getNowStr(){
        return Formatter.formattedCurNow(now);
    }

    public AttendanceManager getAttendanceManager() {
        return attendanceManager;
    }

    public void initAttendance() {
        Map<LocalDate, List<Attendance>> attendances = FileRepository.getAttendances();
        attendanceManager = new AttendanceManager(attendances);
        Map<String, Late> lateMap = FileRepository.getLateMap();
        lateManager = new LateManager(lateMap);
    }

    public void updateLate(){
        attendanceManager.updateLateInfo(lateManager);

    }
}
