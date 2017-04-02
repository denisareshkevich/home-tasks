package regex.oreshkevich;

import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Denis Areshkevich on 28.03.2017.
 */
public class Parser {

    private final ActivityHelper activityHelper = new ActivityHelper();

    private String text;

    private List<String> entriesForDays;
    private List<DaySchedule> dayScheduleList;

    public Parser(String text) {
        this.text = text;
        dayScheduleList = new ArrayList<>();
    }

    public void parse() {
        splitDays();
        splitActivities();
        activityHelper.setActivitiesEndTime(dayScheduleList);
        activityHelper.calculateActivitiesDuration(dayScheduleList);
        calculateActivitiesTotalTime();
    }

    public void calculateActivitiesTotalTime() {
        for (DaySchedule daySchedule : dayScheduleList) {
            GroupFactory groupFactory = new GroupFactory();
            groupFactory.activitiesForGroupParser(daySchedule);
            LocalTime lectionsTime = activityHelper
                    .calculateActivitiesTotalTime(groupFactory.getLectionList());
            LocalTime vacationTime = activityHelper
                    .calculateActivitiesTotalTime(groupFactory.getVocationList());
            LocalTime practiceTime = activityHelper
                    .calculateActivitiesTotalTime(groupFactory.getPractiseList());
            daySchedule.setLectionsTime(lectionsTime);
            daySchedule.setPracticeTime(practiceTime);
            daySchedule.setVacationTime(vacationTime);

        }
    }

    private void splitDays() {
        entriesForDays = Arrays.asList(text.split("\n\r"));
    }

    private void splitActivities() {
        Pattern pattern = Pattern.compile("(\\d{2}:\\d{2}) (.+)");
        for (int i = 0; i < entriesForDays.size(); i++) {
            List<Activity> activities = new ArrayList<>();
            Matcher matcher = pattern.matcher(entriesForDays.get(i));
            while (matcher.find()) {
                Activity activity = new Activity(LocalTime.parse(matcher.group(1)),
                        matcher.group(2));
                activities.add(activity);
            }
            DaySchedule daySchedule = new DaySchedule();
            daySchedule.setActivities(activities);
            dayScheduleList.add(daySchedule);
        }
    }

    public List<DaySchedule> getDayScheduleList() {
        return dayScheduleList;
    }

}
