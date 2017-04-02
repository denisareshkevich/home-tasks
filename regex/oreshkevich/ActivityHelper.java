package regex.oreshkevich;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;

/**
 * Created by Denis Areshkevich on 30.03.2017.
 */
public class ActivityHelper {

    public void calculateActivitiesDuration(List<DaySchedule> schedules) {
        for (DaySchedule daySchedule : schedules) {
            for (int i = 1; i < daySchedule.getActivities().size(); i++) {
                Activity firstActivity = daySchedule.getActivities().get(i - 1);
                Activity secondActivity = daySchedule.getActivities().get(i);
                int activityMinutes = secondActivity.getStartTime().get(ChronoField.MINUTE_OF_DAY) -
                        firstActivity.getStartTime().get(ChronoField.MINUTE_OF_DAY);
                LocalTime duration = LocalTime.of(activityMinutes/60, activityMinutes%60);
                firstActivity.setDuration(duration);
            }

        }
    }

    public void setActivitiesEndTime(List<DaySchedule> schedules) {
        for (DaySchedule daySchedule : schedules) {
            for (int i = 1; i < daySchedule.getActivities().size(); i++) {
                Activity firstActivity = daySchedule.getActivities().get(i - 1);
                Activity secondActivity = daySchedule.getActivities().get(i);
                firstActivity.setEndTime(secondActivity.getStartTime());
            }

        }
    }

    public LocalTime calculateActivitiesTotalTime(List<Activity> activities) {
        LocalTime total = LocalTime.of(0, 0);
        for (Activity activity : activities) {
            if (activity.getDuration() != null) {
                LocalTime duration = activity.getDuration();
                total = total.plusMinutes(duration.getMinute()).plusHours(duration.getHour());
            }
        }
        return total;
    }
}
