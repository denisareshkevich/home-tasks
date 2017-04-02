package regex.oreshkevich;

import java.time.temporal.ChronoField;
import java.util.List;

/**
 * Created by Denis Areshkevich on 28.03.2017.
 */
public class Reporter {
    public static String formTimePeriodReport(List<DaySchedule> schedules) {
        StringBuffer stringBuffer = new StringBuffer();
        for (DaySchedule daySchedule : schedules) {
            for (Activity activity : daySchedule.getActivities()) {
                stringBuffer.append(activity.toString());
                stringBuffer.append("\n");
            }

            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public static String percentedTimePeriodActivityReport(List<DaySchedule> schedules) {
        StringBuffer stringBuffer = new StringBuffer();
        for (DaySchedule daySchedule : schedules) {
            int lectionsMinutes = daySchedule.getLectionsTime().get(ChronoField.MINUTE_OF_DAY);
            int practiceMinutes = daySchedule.getPracticeTime().get(ChronoField.MINUTE_OF_DAY);
            int vocationMinutes = daySchedule.getVacationTime().get(ChronoField.MINUTE_OF_DAY);
            stringBuffer.append("Лекции: ")
                    .append(lectionsMinutes)
                    .append(" минут ")
                    .append(getPersentOfDay(lectionsMinutes))
                    .append("%\n")
                    .append("Решения: ")
                    .append(practiceMinutes)
                    .append(" минут ")
                    .append(getPersentOfDay(practiceMinutes))
                    .append("%\n")
                    .append("Перерыв: ")
                    .append(vocationMinutes)
                    .append(" минут ")
                    .append(getPersentOfDay(vocationMinutes))
                    .append("%\n\n");
        }
        return stringBuffer.toString();
    }

    private static double getPersentOfDay(int x) {
        return ((double) x) / (24*60) * 100;
    }
}
