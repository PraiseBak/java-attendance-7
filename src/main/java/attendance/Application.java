package attendance;

import attendance.config.AppConfig;
import attendance.controller.AttendanceController;
import attendance.validator.UserInputValidator;
import attendance.view.InputView;
import attendance.view.OutputView;

public class Application {
    private final static AttendanceController attendnaceController = AppConfig.getController();
    public static void main(String[] args) {
        initDate();
        initAttendance();
        while (true){
            try{
                getServerDate();
                String method = selectMethod();
                if(method.equals("Q")){
                    return;
                }
                doMethod(method);
            }catch (IllegalArgumentException e){
                OutputView.printError(e.getMessage());
                throw new IllegalArgumentException("[ERROR] " + e.getMessage());
            }
        }
    }

    private static void initAttendance() {
        attendnaceController.initAttendance();
    }

    private static void doMethod(String method) {
        checkAttendance(method);
        if(method.equals("2")){
            doAttendanceModify();
        }
        if(method.equals("3")){
            doShowAttendanceByCrewname();
        }
        if(method.equals("4")){
            doShowOutliner();
        }
    }

    private static void checkAttendance(String method) {
        if(method.equals("1")){
            attendnaceController.validateAttendance();
            doCheckAttendance();
        }
    }

    private static void doShowOutliner() {
        OutputView.println(attendnaceController.getShowOutliner());
    }

    private static void doShowAttendanceByCrewname() {
        String nickname = InputView.getNickname();
        attendnaceController.validateNickname(nickname);
        OutputView.println(attendnaceController.getAttendanceInfoByNickname(nickname));
    }

    private static void doAttendanceModify() {
        String nickname = InputView.getModifyNickname();
        attendnaceController.validateNickname(nickname);
        String day = InputView.getDay();
        attendnaceController.validateDay(day);
        String modifyTime = InputView.moddifyTime();
        OutputView.println(attendnaceController.modify(nickname,day,modifyTime));
    }

    private static void doCheckAttendance() {
        String nickname = InputView.getNickname();
        attendnaceController.validateNickname(nickname);
        String attendance = InputView.getAttendance();
        UserInputValidator.validateHourAndMin(attendance.split(":"));
        OutputView.println(attendnaceController.checkAttendance(attendance,nickname));
    }

    private static String selectMethod() {
        String method = InputView.selectMethod();
        UserInputValidator.validateMethod(method);
        return method;
    }

    private static void getServerDate() {
        String date = attendnaceController.getDate();
        OutputView.println(date);
    }

    private static void initDate() {
        attendnaceController.initDate();
    }
}
