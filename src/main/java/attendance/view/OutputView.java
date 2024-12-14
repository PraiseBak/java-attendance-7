package attendance.view;

public class OutputView {
    private final static String ERROR_PREFIX = "[ERROR] ";
    private static final String SELECT_METHOD = "1. 출석 확인\n"
            + "2. 출석 수정\n"
            + "3. 크루별 출석 기록 확인\n"
            + "4. 제적 위험자 확인\n"
            + "Q. 종료";
    private static final String NICKNAME_INPUT = "닉네임을 입력해 주세요.";
    private static final String ATTENDANCE_INPUT = "등교 시간을 입력해 주세요.\n";


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
}
