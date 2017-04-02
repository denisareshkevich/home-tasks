package regex.oreshkevich;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by Denis Areshkevich on 28.03.2017.
 */
public class DaySchedule {

    private List<Activity> activities;
    private LocalTime totalTime;
    private LocalTime lectionsTime;
    private LocalTime practiceTime;
    private LocalTime vacationTime;

    public LocalTime getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(LocalTime totalTime) {
        this.totalTime = totalTime;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public LocalTime getLectionsTime() {
        return lectionsTime;
    }

    public void setLectionsTime(LocalTime lectionsTime) {
        this.lectionsTime = lectionsTime;
    }

    public LocalTime getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(LocalTime practiceTime) {
        this.practiceTime = practiceTime;
    }

    public LocalTime getVacationTime() {
        return vacationTime;
    }

    public void setVacationTime(LocalTime vacationTime) {
        this.vacationTime = vacationTime;
    }
}
