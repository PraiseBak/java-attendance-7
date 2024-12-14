package attendance.config;


import attendance.controller.AttendanceController;
import attendance.repostiroy.Repository;
import attendance.service.AttendanceService;

public class AppConfig {
    private static final AttendanceController attendanceController = new AttendanceController(new AttendanceService(new Repository()));

    public static AttendanceController getController() {
        return attendanceController;
    }
}
