package attendance.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LateManager {
    private Map<String,Late> lateMap = new HashMap<>();

    public LateManager(Map<String,Late> lateMap) {
        this.lateMap = lateMap;
    }

    public void updateAbsenseByName(String name) {
        lateMap.get(name).updateAbsense();
    }

    public void initLateManager() {
        for(String s : lateMap.keySet()){
            lateMap.put(s, new Late(s));
        }
    }

    public void updateLateByName(String name) {
        lateMap.get(name).updateLate();
    }

    public List<Late> getLates(){
        ArrayList<Late> objects = new ArrayList<>();

        for(String s : lateMap.keySet()){
            Late late = lateMap.get(s);
            if(late.isMeetNeed() || late.isWarning() || late.isWeeding()){
                objects.add(late);
            }
        }
        return objects;
    }
}
