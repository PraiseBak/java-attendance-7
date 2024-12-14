package attendance.validator;

import attendance.exception.ExceptionHelper;
import attendance.exception.AttendanceException;
import attendance.utility.NumberUtility;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserInputValidator {

    private static final String INVALID_STR = "유효하지 않은 문자열 입력입니다.";
    private static String s;

    public static void validatePositive(int buyMoney) {
        if(!NumberUtility.isPositive(buyMoney)){
            throw new AttendanceException(ExceptionHelper.INVALID_NUMBER);
        }
    }

    public static void validateNumber(String s) {
        if(!NumberUtility.isNumber(s)){
            throw new AttendanceException(ExceptionHelper.INVALID_NUMBER);
        }

    }

    public static void validateDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>();
        for(Integer num : numbers){
            if(set.contains(num)){
                throw new AttendanceException(ExceptionHelper.INVALID_NUMBER);
            }
            set.add(num);
        }
    }

    public static void validateString(String s) {
        if(s == null || s.isEmpty()){
            throw new AttendanceException(INVALID_STR);
        }
    }

    public static void validateBoolean(String s) {
        if(s.equals("Y") || s.equals("N")){
            return;
        }
        throw new AttendanceException(INVALID_STR);
    }

    public static void validateMethod(String method) {
        validateString(method);
        if(method.equals("1") || method.equals("2") || method.equals("3") || method.equals("4")){
            return;
        }
        if(method.equals("Q")){
            return;
        }
    }

    public static void validateAttendance(String[] sArr) {
        validateString(sArr[0]);
        validateLocalDateTime(sArr[1]);

    }

    public static void validateLocalDateTime(String s) {
        String[] sArr = s.split(" ");
        String localDate = sArr[0];
        String localTime = sArr[1];
        validateLocalDate(localDate.split("-"));
        validateLocalTime(localTime.split(":"));
    }

    public static void validateLocalTime(String[] localTime) {
        if(localTime.length != 2){
            throw new AttendanceException(ExceptionHelper.FAIL_FILE_LOAD);
        }
        for(String s : localTime){
            validateNumber(s);
        }
        validateHourAndMin(localTime);
    }

    public static void validateHourAndMin(String[] localTime) {
        if(localTime.length != 2){
            throw new AttendanceException("잘못된 형식을 입력하였습니다.");
        }
        int hour = Integer.parseInt(localTime[0]);
        int min  = Integer.parseInt(localTime[1]);
        if(hour >= 24){
            throw new AttendanceException("잘못된 형식을 입력하였습니다.");
        }
        if(min >= 60){
            throw new AttendanceException("잘못된 형식을 입력하였습니다.");
        }
    }

    private static void validateLocalDate(String[] localDate) {
        if(localDate.length != 3){
            throw new AttendanceException(ExceptionHelper.FAIL_FILE_LOAD);
        }
        for(String s : localDate){
            validateNumber(s);
        }
    }
}
