package attendance.repostiroy;

import attendance.domain.Formatter;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import net.bytebuddy.asm.MemberSubstitution.WithoutSpecification.ForMatchedField;

public class Repository {

    private LocalDate now;

    public void initDate() {
        now = DateTimes.now().toLocalDate();
    }

    public LocalDate getNow(){
        return now;
    }

    public String getNowStr(){
        return Formatter.formattedCurNow(now);
    }

}
