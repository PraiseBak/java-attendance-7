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
            getServerDate();
            String method = selectMethod();
            if(method.equals("Q")){
                return;
            }
            doMethod(method);
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
            try{
                attendnaceController.validateAttendance();
            }catch (IllegalArgumentException e){
                OutputView.printError(e.getMessage());
                return;
            }
            doCheckAttendance();
        }
    }

    private static void doShowOutliner() {
    }

    private static void doShowAttendanceByCrewname() {
        while (true){
            try{
                String nickname = InputView.getNickname();
                attendnaceController.validateNickname(nickname);
                OutputView.println(attendnaceController.getAttendanceInfoByNickname(nickname));
                return;
            }catch (IllegalArgumentException e){
                OutputView.printError(e.getMessage());
            }
        }

    }

    private static void doAttendanceModify() {

    }

    private static void doCheckAttendance() {
        while (true){
            try{
                String nickname = InputView.getNickname();
                attendnaceController.validateNickname(nickname);
                String attendance = InputView.getAttendance();
                attendnaceController.checkAttendance(nickname,attendance);
            }catch (IllegalArgumentException e){
                OutputView.printError(e.getMessage());
            }
        }
    }

    private static String selectMethod() {
        while (true){
            try {
                String method = InputView.selectMethod();
                UserInputValidator.validateMethod(method);
                return method;
            }catch (IllegalArgumentException e){
                OutputView.printError(e.getMessage());
            }
        }

    }

    private static void getServerDate() {
        String date = attendnaceController.getDate();
        OutputView.println(date);
    }

    private static void initDate() {
        attendnaceController.initDate();
    }
}
