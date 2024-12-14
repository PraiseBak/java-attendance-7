package attendance.domain;

public class Late {
    private final String crewName;
    private int countLate = 0;
    private int countAbsense = 0;
    private boolean isWarning = false;
    private boolean isMeetNeed = false;
    private boolean isWeeding = false;
    private final static int MEET_CASE = 3;

    private final static int WARNING_CASE = 2;
    private final static int ABSENSE_CASE = 5;

    public Late(String crewName) {
        this.crewName = crewName;
    }

    public void updateAbsense() {
        countAbsense++;
        if(countAbsense > ABSENSE_CASE){
            isWeeding = true;
        }
        if(countAbsense >= WARNING_CASE){
            isWarning = true;
        }
        if(countAbsense >= MEET_CASE){
            isMeetNeed = true;
        }
    }

    public void updateLate(){
        countLate++;
        if(countLate == 3){
            updateAbsense();
            countLate = 0;
        }
    }
}
