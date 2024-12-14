package attendance.service;

import attendance.repostiroy.Repository;

public class AttendanceService {

    private final Repository repository;

    public AttendanceService(Repository repository) {
        this.repository = repository;
    }

    public void initDate() {
        repository.initDate();
    }

    public String getDate() {
        return repository.getNow();
    }
}
