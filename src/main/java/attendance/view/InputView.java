package attendance.view;


import attendance.validator.UserInputValidator;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static String input() {
        return Console.readLine();
    }

    private static boolean inputBoolean() {
        String s = input();
        UserInputValidator.validateString(s);
        UserInputValidator.validateBoolean(s);
        return s.equals("Y");

    }

    public static String selectMethod() {
        OutputView.selectMethod();
        return input();
    }

    public static String getNickname() {
        OutputView.printGetNickname();
        return input();
    }

    public static String getAttendance() {
        OutputView.printGetAttendance();
        return input();
    }

    public static String getModifyNickname() {
        OutputView.printModifyNickname();
        return input();
    }

    public static String getDay() {
        OutputView.printGetDay();
        return input();
    }

    public static String moddifyTime() {
        OutputView.printModifyTime();
        return input();
    }
}
