package attendance.service;

import attendance.domain.AttendanceManager;
import attendance.domain.Day;
import attendance.domain.Formatter;
import attendance.domain.LateCalculator;
import attendance.exception.AttendanceException;
import attendance.exception.ExceptionHelper;
import attendance.repostiroy.Repository;
import attendance.utility.DateUtility;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceService {

    private final Repository repository;

    public AttendanceService(Repository repository) {
        this.repository = repository;
    }

    public void initDate() {
        repository.initDate();
    }

    public String getDate() {
        return repository.getNowStr();
    }

    public void validateWorkday() {
        LocalDate now = repository.getNow();
        String string = Formatter.getFormattedDayInfoFormat(now);
        boolean dayOff = Day.isDayOff(now.getDayOfWeek().toString());
        if(dayOff || DateUtility.isSpecialWeek(now)){
            String errorMessage = Formatter.getFormattedDayInfoFormat(now);
            throw new AttendanceException(Formatter.getFormattedDayOffError(errorMessage));
        }
    }

    public void validateNickname(String nickname) {
        AttendanceManager attendanceManager = repository.getAttendanceManager();
        if(!attendanceManager.isExistsNickname(nickname)){
            throw new AttendanceException(ExceptionHelper.INVALID_NICKNAME);
        }
    }

    public void initAttendance() {
        repository.initAttendance();
    }

    public void updateLateInfo() {
        repository.updateLate();
    }

    public void checkAttendance(String attendance, String nickname) {
        validateNotOpenSchool(DateUtility.getLocalTimeFromString(attendance));
    }
    private void validateNotOpenSchool(LocalTime now) {
        if(LateCalculator.isNotOpenSchool(now)) {
            throw new AttendanceException(ExceptionHelper.NOT_OPEN_SCHOOL);
        }
    }

    public String getAttendanceInfoByNickname(String nickname) {
        AttendanceManager attendanceManager = repository.getAttendanceManager();
        return attendanceManager.getAttendanceInfoByNickname(nickname);
    }
}
