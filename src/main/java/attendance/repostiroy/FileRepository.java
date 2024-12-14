package attendance.repostiroy;

import attendance.domain.Attendance;
import attendance.domain.Late;
import attendance.exception.AttendanceException;
import attendance.exception.ExceptionHelper;
import attendance.utility.DateUtility;
import attendance.validator.UserInputValidator;
import camp.nextstep.edu.missionutils.DateTimes;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileRepository {
    private final static String FILE_SRC = "./src/main/resources/attendances.csv";
    public static Map<LocalDate, List<Attendance>> getAttendances() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_SRC)));
            return getAttendancesFromReader(bufferedReader);
        } catch (IOException e) {
            throw new AttendanceException(ExceptionHelper.FAIL_FILE_LOAD);
        }
    }

    public static Map<String,Late> getLateMap() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_SRC)));
            return getLateFromReader(bufferedReader);
        } catch (IOException e) {
            throw new AttendanceException(ExceptionHelper.FAIL_FILE_LOAD);
        }
    }

    private static Map<String,Late> getLateFromReader(BufferedReader bufferedReader) throws IOException {
        String s = bufferedReader.readLine();
        Set<String> nicknames = new HashSet<>();
        Map<String,Late> lateMap = new HashMap<>();
        while (s != null){
            s = bufferedReader.readLine();
            if(s == null){
                break;
            }
            String[] sArr = s.split(",");
            nicknames.add(sArr[0]);
        }
        for(String nickname : nicknames){
            lateMap.put(nickname,new Late(nickname));
        }
        return lateMap;
    }


    private static Map<LocalDate,List<Attendance>> getAttendancesFromReader(BufferedReader bufferedReader) throws IOException {
        String s = bufferedReader.readLine();
        Map<LocalDate,List<Attendance>> map = new HashMap<>();
        while (s != null){
            s = bufferedReader.readLine();
            if(s == null){
                break;
            }
            String[] sArr = s.split(",");
            validateAndPutAttendance(sArr, map);
        }
        return map;
    }

    private static void validateAndPutAttendance(String[] sArr, Map<LocalDate, List<Attendance>> map) {
        UserInputValidator.validateAttendance(sArr);
        LocalDate localDateFromString = DateUtility.getLocalDateFromString(sArr[1].split(" ")[0]);
        LocalTime localTime = DateUtility.getLocalTimeFromString(sArr[1].split(" ")[1]);
        List<Attendance> list = map.getOrDefault(localDateFromString, new ArrayList<>());
        if(localDateFromString.isEqual(ChronoLocalDate.from(DateTimes.now())) || localDateFromString.isAfter(ChronoLocalDate.from(DateTimes.now()))){
            return;
        }
        list.add(new Attendance(sArr[0],localTime));
        map.put(localDateFromString,list);
    }
}
