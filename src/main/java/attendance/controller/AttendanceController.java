package attendance.controller;

import attendance.domain.Formatter;
import attendance.domain.LateCalculator;
import attendance.exception.AttendanceException;
import attendance.exception.ExceptionHelper;
import attendance.service.AttendanceService;

public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public void initDate() {
        attendanceService.initDate();
    }

    public String getDate() {
        return attendanceService.getDate();
    }

    public void validateAttendance() {
        attendanceService.validateWorkday();
    }

    public void checkAttendance(String attendance, String nickname) {
        attendanceService.checkAttendance(attendance,nickname);
    }

    public void validateNickname(String nickname) {
        attendanceService.validateNickname(nickname);
    }

    public void initAttendance() {
        attendanceService.initAttendance();
        attendanceService.updateLateInfo();
    }

    public String getAttendanceInfoByNickname(String nickname) {
        return attendanceService.getAttendanceInfoByNickname(nickname);
    }
}
