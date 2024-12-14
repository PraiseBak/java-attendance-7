package attendance.controller;

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
}
