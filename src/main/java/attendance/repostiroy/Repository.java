package attendance.repostiroy;

import attendance.domain.Formatter;
import camp.nextstep.edu.missionutils.DateTimes;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Repository {

    private LocalDate now;

    public void initDate() {
        now = DateTimes.now().toLocalDate();
    }

    public String getNow(){
        return Formatter.formattedNow(now);
    }
}
