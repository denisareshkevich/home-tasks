package regex.oreshkevich;

import java.time.LocalTime;

/**
 * Created by Denis Areshkevich on 28.03.2017.
 */
public class Activity {

    private LocalTime startTime;
    private String title;
    private LocalTime duration;
    private LocalTime endTime;

    public Activity(LocalTime startTime, String title) {
        this.startTime = startTime;
        this.title = title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startTime.getHour())
                .append(":");
        if (startTime.getMinute() == 0){
            stringBuilder.append("00");
        }else stringBuilder.append(startTime.getMinute());
        if (endTime != null && endTime.getMinute() == 0) {
            stringBuilder.append("-")
                    .append(endTime.getHour())
                    .append(":");
            stringBuilder.append("00");
        }else if (endTime != null) {
            stringBuilder.append("-")
                    .append(endTime.getHour())
                    .append(":")
                    .append(endTime.getMinute());
        }

        stringBuilder.append(" ")
                .append(title);
        return stringBuilder.toString();
    }
}
