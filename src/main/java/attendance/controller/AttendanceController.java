package attendance.controller;

import attendance.service.AttendanceService;
import attendance.validator.UserInputValidator;

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

    public String checkAttendance(String attendance, String nickname) {
        String s = attendanceService.checkAttendance(attendance, nickname);
        attendanceService.updateLateInfo();
        return s;
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

    public void validateDay(String day) {
        attendanceService.validateDay(day);
    }

    public String modify(String nickname, String day, String modifyTime) {
        validateModifyTime(modifyTime);
        String modify = attendanceService.modify(nickname, day, modifyTime);
        attendanceService.updateLateInfo();
        return modify;
    }

    private void validateModifyTime(String modifyTime) {
        UserInputValidator.validateHourAndMin(modifyTime.split(":"));
    }

    public String getShowOutliner() {
        attendanceService.updateLateInfo();
        return attendanceService.showOutliner();
    }
}
