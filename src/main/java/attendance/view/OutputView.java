package attendance.view;

public class OutputView {
    private final static String ERROR_PREFIX = "[ERROR] ";
    private static final String SELECT_METHOD = "1. 출석 확인\n"
            + "2. 출석 수정\n"
            + "3. 크루별 출석 기록 확인\n"
            + "4. 제적 위험자 확인\n"
            + "Q. 종료";
    private static final String NICKNAME_INPUT = "닉네임을 입력해 주세요.";
    private static final String ATTENDANCE_INPUT = "등교 시간을 입력해 주세요.";
    private static final String MODIFY_NICKNAME = "출석을 수정하려는 크루의 닉네임을 입력해 주세요.";
    private static final String GET_DAY = "수정하려는 날짜(일)를 입력해 주세요.";
    private static final String MODIFY_TIME = "언제로 변경하겠습니까?";


    public static void println(String message) {
        System.out.println(message);
    }

    public static void printError(String message) {
        println(ERROR_PREFIX + message);
    }

    public static void selectMethod() {
        println(SELECT_METHOD);
    }

    public static void printGetNickname() {
        println(NICKNAME_INPUT);
    }

    public static void printGetAttendance() {
        println(ATTENDANCE_INPUT);
    }

    public static void printModifyNickname() {
        println(MODIFY_NICKNAME);
    }

    public static void printGetDay() {
        println(GET_DAY);
    }

    public static void printModifyTime() {
        println(MODIFY_TIME);
    }
}
