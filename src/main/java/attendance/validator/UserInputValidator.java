package attendance.validator;

import attendance.exception.ExceptionHelper;
import attendance.exception.AttendanceException;
import attendance.utility.NumberUtility;
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
}
