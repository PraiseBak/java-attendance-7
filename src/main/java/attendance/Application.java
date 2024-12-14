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
        while (true){
            getServerDate();
            String method = selectMethod();
            if(method.equals("Q")){
                return;
            }
            doMethod(method);
        }
    }

    private static void doMethod(String method) {
        if(method.equals("1")){
            doCheckAttendance();
        }
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

    private static void doShowOutliner() {
    }

    private static void doShowAttendanceByCrewname() {
    }

    private static void doAttendanceModify() {
    }

    private static void doCheckAttendance() {
        while (true){
            try{
                attendnaceController.validateAttendance();
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
