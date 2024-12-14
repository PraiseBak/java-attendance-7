package attendance.domain;

import attendance.dto.AttendanceInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceManager {
    private Map<LocalDate,List<Attendance>> attendanceMap = new HashMap<>();

    public AttendanceManager(Map<LocalDate, List<Attendance>> attendances) {
        this.attendanceMap = attendances;
    }

    public boolean isExistsNickname(String nickname) {
        for(LocalDate key : attendanceMap.keySet()){
            if(isExistNickname(key,nickname)){
                return true;
            }
        }
        return false;
    }

    private boolean isExistNickname(LocalDate key, String nickname) {
        for(Attendance attendance : attendanceMap.get(key)){
            if(attendance.isSameNickname(nickname)){
                return true;
            }
        }
        return false;
    }

    public void updateLateInfo(LateManager lateManager) {
        lateManager.initLateManager();
        for(LocalDate localDate : attendanceMap.keySet()){
            List<Attendance> attendances = attendanceMap.get(localDate);
            setLate(attendances,lateManager,localDate);
        }
    }

    private void setLate(List<Attendance> attendances, LateManager lateManager, LocalDate localDate) {
        for(Attendance attendance : attendances){
            if(attendance.isAbsense(localDate)){
                String name = attendance.getName();
                lateManager.updateAbsenseByName(name);
            }

            if(attendance.isLate(localDate)){
                String name = attendance.getName();
                lateManager.updateLateByName(name);
            }
        }
    }

    public String getAttendanceInfoByNickname(String nickname) {
        StringBuilder stringBuilder = new StringBuilder();
        List<LocalDate> reversedKeySet = attendanceMap.keySet().stream().toList().reversed();
        List<AttendanceInfo> attendanceInfos = new ArrayList<>();
        for(LocalDate localDate : reversedKeySet){
            List<Attendance> attendances = attendanceMap.get(localDate);
            Attendance attendanceByNickname = getAttendanceByNickname(attendances, nickname);
            AttendanceInfo attendanceInfo = Formatter.getAttendanceInfo(attendanceByNickname,localDate);
            attendanceInfos.add(attendanceInfo);
            stringBuilder.append(attendanceInfo.formattedInfo());
        }
        stringBuilder.append(Formatter.formattedResultAttendnace(attendanceInfos));
        return stringBuilder.toString();
    }



    private Attendance getAttendanceByNickname(List<Attendance> attendances, String nickname) {
        for(Attendance attendance : attendances){
            if(attendance.isSameNickname(nickname)){
                return attendance;
            }
        }
        return null;
    }
}
