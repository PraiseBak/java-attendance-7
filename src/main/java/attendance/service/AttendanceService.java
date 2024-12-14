package attendance.service;

import attendance.domain.Day;
import attendance.domain.Formatter;
import attendance.exception.AttendanceException;
import attendance.exception.ExceptionHelper;
import attendance.repostiroy.Repository;
import java.time.LocalDate;
import net.bytebuddy.asm.MemberSubstitution.WithoutSpecification.ForMatchedField;

public class AttendanceService {

    private final Repository repository;

    public AttendanceService(Repository repository) {
        this.repository = repository;
    }

    public void initDate() {
        repository.initDate();
    }

    public String getDate() {
        return repository.getNowStr();
    }

    public void validateWorkday() {
        LocalDate now = repository.getNow();
        String string = Formatter.formattedCurNow(now);
        boolean dayOff = Day.isDayOff(string);
        if(dayOff){
            String errorMessage = Formatter.getFormattedDayWeekFormat(now);
            throw new AttendanceException(Formatter.getFormattedDayOffError(errorMessage));
        }
    }
}
