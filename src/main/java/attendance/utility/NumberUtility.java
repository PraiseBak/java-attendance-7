package attendance.utility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class NumberUtility {

    private final static String IS_NUMBER_REGEX = "^[0-9]+$";

    public static boolean isNumber(String leftMoney) {
        return Pattern.matches(IS_NUMBER_REGEX,leftMoney);
    }

    public static boolean isPositive(int leftMoney) {
        if(leftMoney >= 1){
            return true;
        }
        return false;
    }

    public static boolean isDuplicateNumbers(List<Integer> numberList) {
        Set<Integer> set = new HashSet<>();
        for(Integer number : numberList){
            if(numberList.contains(number)){
                return true;
            }
            set.add(number);
        }
        return false;
    }

    public static boolean isDuplicateNumbers(List<Integer> numberList,List<Integer> nextNumber) {
        Set<Integer> set = new HashSet<>(nextNumber);
        for(Integer number : numberList){
            if(set.contains(number)){
                return true;
            }
            set.add(number);
        }
        return false;
    }
    public static int countDuplicateNumbers(List<Integer> numbersA, List<Integer> numbersB){
        Set<Integer> set = new HashSet<>();
        set.addAll(numbersA);
        int count = 0;
        for(Integer number : numbersB){
            if(set.contains(number)){
                count++;
            }
        }
        return count;
    }
}

