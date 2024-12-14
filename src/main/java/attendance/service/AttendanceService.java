package attendance.service;

import attendance.domain.AttendanceManager;
import attendance.domain.Day;
import attendance.domain.Formatter;
import attendance.domain.Late;
import attendance.domain.LateCalculator;
import attendance.domain.LateManager;
import attendance.exception.AttendanceException;
import attendance.exception.ExceptionHelper;
import attendance.repostiroy.Repository;
import attendance.utility.DateUtility;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

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

    public String checkAttendance(String attendance, String nickname) {
        validateNotOpenSchool(DateUtility.getLocalTimeFromString(attendance));
        AttendanceManager attendanceManager = repository.getAttendanceManager();
        return attendanceManager.addAttendance(attendance,nickname);

    }
    private void validateNotOpenSchool(LocalTime now) {
        if(LateCalculator.isNotOpenSchool(now)) {
            throw new AttendanceException(ExceptionHelper.NOT_OPEN_SCHOOL);
        }
    }

    public String getAttendanceInfoByNickname(String nickname) {
        AttendanceManager attendanceManager = repository.getAttendanceManager();
        LateManager lateManager = repository.getLateManager();
        return attendanceManager.getAttendanceInfoByNickname(nickname,lateManager);
    }

    public String modify(String nickname, String day, String modifyTime) {
        AttendanceManager attendanceManager = repository.getAttendanceManager();
        return attendanceManager.modify(nickname,day,modifyTime);
    }

    public void validateDay(String day) {
        AttendanceManager attendanceManager = repository.getAttendanceManager();
        attendanceManager.validateDay(day);
    }

    public String showOutliner() {
        LateManager lateManager = repository.getLateManager();
        List<Late> lates = lateManager.getLates();
        Collections.sort(lates);
        StringBuilder stringBuilder = new StringBuilder("제적 위험자 조회 결과\n");
        for(Late late : lates){
            stringBuilder.append("- " + late.getCrewName() + ": " + "결석 " + late.getCountAbsense() +"회" + "," + " " + "지각 " + late.getCountLate() + "회" + " " + Formatter.getLisk(late.getCountAbsense()));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
