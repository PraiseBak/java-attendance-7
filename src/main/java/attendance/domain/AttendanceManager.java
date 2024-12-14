package attendance.domain;

import attendance.dto.AttendanceInfo;
import attendance.exception.AttendanceException;
import attendance.utility.NumberUtility;
import attendance.validator.UserInputValidator;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
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
        if(attendanceMap.get(key) == null){
            return false;
        }
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
            stringBuilder.append(attendanceInfo.formattedInfo()).append("\n");
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

    public String addAttendance(String attendance, String nickname) {
        LocalDate now = DateTimes.now().toLocalDate();
        if(isExistNickname(now,nickname)){
            throw new AttendanceException("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
        List<Attendance> attendances = attendanceMap.getOrDefault(now, new ArrayList<>());
        UserInputValidator.validateLocalTime(attendance.split(":"));
        String[] split = attendance.split(":");
        LocalTime localTime = LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        Attendance newAttendance = new Attendance(nickname, localTime);
        attendances.add(newAttendance);
        attendanceMap.put(now,attendances);
        return Formatter.getAttendanceInfo(newAttendance,now).formattedInfo();
    }

    public void validateDay(String day) {
        if(!NumberUtility.isNumber(day)){
            throw new AttendanceException("잘못된 형식을 입력하였습니다.");
        }
        for(LocalDate date : attendanceMap.keySet()){
            if(((date.getDayOfMonth() == Integer.parseInt(day)))){
                return;
            }
        }
        throw new AttendanceException("해당 출석날짜를 수정할 수 없어요");
    }

    public String modify(String nickname, String day, String modifyTime) {
        int lastDay = 0;
        for(LocalDate localDate : attendanceMap.keySet()){
            List<Attendance> attendances = attendanceMap.get(localDate);
            lastDay = Math.max(lastDay,localDate.getDayOfMonth());
            if(localDate.getDayOfMonth() == Integer.parseInt(day)){
                return modify(attendances,nickname,day,modifyTime,localDate);
            }
        }
        if(lastDay < Integer.parseInt(day)){
            throw new AttendanceException("아직 수정할 수 없습니다.");
        }
        throw new AttendanceException("수정할 수 있는 날짜가 없습니다.");
    }

    private String modify(List<Attendance> attendances, String nickname, String day, String modifyTime, LocalDate localDate) {
        for(Attendance attendance : attendances){
            if(attendance.isSameNickname(nickname)){
                return attendance.updateAttendance(modifyTime,localDate);
            }
        }
        throw new AttendanceException("해당 날짜로 수정할 출석이 없습니다.");
    }
}
