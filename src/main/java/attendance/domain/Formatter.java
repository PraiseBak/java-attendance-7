package attendance.domain;

import attendance.dto.AttendanceInfo;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Formatter{
    private final static String DAY_WEEK_FORMAT = "%d월 %d일 %s";

    private final static String NOW_FORMAT = "오늘은 %d월 %d일 %s입니다. 기능을 선택해 주세요.\n";
    private static final String DAY_OFF_ERROR_FORMAT = "%s은 등교일이 아닙니다.\n";
    private static final String ATTENDANCE_INFO_FORMAT = "%s %s (%s)\n";
    private static final String ABSENSE_FORMAT = "--:--";
    private static final String RESULT_ATTENDANCE = "\n출석: %d회\n"
            + "지각: %d회\n"
            + "결석: %d회";


    public static String formattedCurNow(LocalDate now) {
        int dayOfMonth = now.getDayOfMonth();
        int monthValue = now.getMonthValue();
        String day = getFormattedDayOfWeek(now);
        return String.format(NOW_FORMAT,monthValue,dayOfMonth,day);
    }



    private static String getFormattedDayOfWeek(LocalDate now) {
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        String day = Day.getKrDayByDayOfWeek(dayOfWeek.toString());
        return day;
    }

    public static String getFormattedDayInfoFormat(LocalDate now){
        int dayOfMonth = now.getDayOfMonth();
        int monthValue = now.getMonthValue();
        String day = getFormattedDayOfWeek(now);
        return String.format(DAY_WEEK_FORMAT,monthValue,dayOfMonth,day);
    }

    public static String getFormattedDayOffError(String errorMessage) {
        return String.format(DAY_OFF_ERROR_FORMAT,errorMessage);
    }

    public static AttendanceInfo getAttendanceInfo(Attendance attendanceByNickname, LocalDate now) {
        String formattedDayInfoFormat = getFormattedDayInfoFormat(now);
        String attendantTime = ABSENSE_FORMAT;
        boolean isLate = false;
        boolean isAbsense = true;
        if(attendanceByNickname != null){
            attendantTime = attendanceByNickname.getAttendantFormat();
            isLate = attendanceByNickname.isLate(now);
            isAbsense = attendanceByNickname.isAbsense(now);
        }
        String info = getAttendanceStateInfo(isLate, isAbsense);
        return new AttendanceInfo(String.format(ATTENDANCE_INFO_FORMAT,formattedDayInfoFormat,attendantTime,info),isLate,isAbsense,!isLate && !isAbsense);
    }

    private static String getAttendanceStateInfo(boolean isLate, boolean isAbsense) {
        String info = "출석";
        if(isLate){
            info = "지각";
        }
        if(isAbsense){
            info = "결석";
        }
        return info;
    }

    public static String getAttendantFormat(LocalTime attendanceTime) {
        return attendanceTime.toString();
    }

    public static String formattedResultAttendnace(List<AttendanceInfo> attendanceInfos) {
        int attendance = 0;
        int absense = 0;
        int late = 0;
        for(AttendanceInfo attendanceInfo : attendanceInfos){
            if(attendanceInfo.isAttendant()){
                attendance++;
            }
            if(attendanceInfo.isAbsense()){
                absense++;
            }
            if(attendanceInfo.isLate()){
                late++;
            }
        }

        return String.format(RESULT_ATTENDANCE,attendance,late,absense) + "\n\n" + getResultAttendantInfo(attendance,absense,late) + "\n";
    }

    private static String getResultAttendantInfo(int attendance, int absense, int late) {
        if(absense > 5){
            return "제적 대상자입니다.";
        }
        if(absense >= 3){
            return "면담 대상자입니다.";
        }
        if(absense >= 2){
            return "경고 대상자입니다.";
        }
        return "";
    }
}

//package org.praiseutil.domain;
//
//
//public class LottoFormatter {
//
//    private static final String BUY_LOTTO_PREFIX = "%d개를 구매했습니다.\n";
//
//    private static final String CALCULATED_LOTTO_RESULT_FORMAT_PREFIX = "당첨 통계\n---\n";
//    private static final String CALCULATED_LOTTO_RESULT_FORMAT = "%d개 일치 (%,d원) - %d개\n";;
//    private static final String BONUS_CALCULATED_LOTTO_RESULT_FORMAT = WinningNumbers.SPEICAL_CASE + "개 일치, 보너스 볼 일치 (%,d원) - %d개\n";
//    private static final String WIN_RATIO_FORMAT = "총 수익률은 %.1f%%입니다.";
//
//
//
//    public static String getResultBuyLottosPrefix(int size) {
//        return String.format(BUY_LOTTO_PREFIX,size);
//    }
//
//    public static String formattedCalculateLottoResult() {
//        return CALCULATED_LOTTO_RESULT_FORMAT_PREFIX;
//    }
//
//    public static String getCalculateLottoResult(int winCase, int count, int money) {
//        if(winCase == WinningNumbers.BONUS_CASE_IDX){
//            return String.format(BONUS_CALCULATED_LOTTO_RESULT_FORMAT,money,count);
//        }
//        return String.format(CALCULATED_LOTTO_RESULT_FORMAT,winCase,money,count);
//    }
//
//    public static String lottoWinRatio(double ratio) {
//        return String.format(WIN_RATIO_FORMAT,ratio);
//    }
//
//    private static final String WIN_RATIO_FORMAT = "총 수익률은 %.1f%%입니다.";
//
//
//    private static double calculateRatio(int sum, int lottoBuyMoney) {
//        double v = (double) sum / (double) lottoBuyMoney;
//        double result = (v * 100) ;
//        return result;
//    }
//}
