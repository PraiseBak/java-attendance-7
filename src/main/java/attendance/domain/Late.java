package attendance.domain;

import java.util.Comparator;

public class Late implements Comparable<Late> {
    private final String crewName;
    private int countLate = 0;
    private int countAbsense = 0;

    public String getCrewName() {
        return crewName;
    }

    public int getCountLate() {
        return countLate;
    }

    public void setCountLate(int countLate) {
        this.countLate = countLate;
    }

    public int getCountAbsense() {
        return countAbsense;
    }

    public void setCountAbsense(int countAbsense) {
        this.countAbsense = countAbsense;
    }

    public boolean isWarning() {
        return isWarning;
    }

    public void setWarning(boolean warning) {
        isWarning = warning;
    }

    public boolean isMeetNeed() {
        return isMeetNeed;
    }

    public void setMeetNeed(boolean meetNeed) {
        isMeetNeed = meetNeed;
    }

    public boolean isWeeding() {
        return isWeeding;
    }

    public void setWeeding(boolean weeding) {
        isWeeding = weeding;
    }

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
        if(countLate % 3 == 0){
            updateAbsense();
        }
    }


    @Override
    public int compareTo(Late o2) {
        Late o1 = this;
        int weight = 0;
        int weight2 = 0;
        if(o1.isWeeding){
            weight = 1;
        }
        if(o2.isWeeding){
            weight2 = 1;
        }
        if(weight == weight2){
            weight = 0;
            weight2 = 1;
            if(o1.isMeetNeed){
                weight = 1;
            }
            if(o2.isMeetNeed){
                weight2 = 1;
            }
            if(weight == weight2){
                weight = 0;
                weight2 = 0;
                if(o1.isWarning){
                    weight = 1;
                }
                if(o2.isWarning){
                    weight2 = 1;
                }
                if(weight == weight2){
                    weight = o1.getCountLate() / 3 + o1.getCountAbsense();
                    weight2 = o2.getCountLate() / 3 + o2.getCountAbsense();

                    if(weight == weight2){
                        return o2.getCrewName().compareTo(o1.getCrewName());
                    }
                    return weight2 - weight;
                }
                return weight2 - weight;
            }
            return weight2 - weight;
        }
        return weight2 - weight;
    }

    public void update(int absense, int late) {
        this.countAbsense = absense-1;
        this.countLate = late;
        updateAbsense();
    }
}
