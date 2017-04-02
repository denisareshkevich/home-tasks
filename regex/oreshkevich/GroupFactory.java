package regex.oreshkevich;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Denis Areshkevich on 31.03.2017.
 */
public class GroupFactory {

    private final static String[] NOT_LECTION_KEY_WORDS = {"Упражнения", "Перерыв", "Решения", "Конец"};

    private List<Activity> lectionList = new ArrayList<>();
    private List<Activity> practiseList = new ArrayList<>();
    private List<Activity> vocationList = new ArrayList<>();

    public List<Activity> getLectionList() {
        return lectionList;
    }

    public List<Activity> getPractiseList() {
        return practiseList;
    }

    public List<Activity> getVocationList() {
        return vocationList;
    }

    public void activitiesForGroupParser(DaySchedule daySchedule) {
        Iterator<Activity> activityIterator = daySchedule.getActivities().iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();

            if (activity.getTitle().equals("Решения")) {
                practiseList.add(activity);
            } else if (activity.getTitle().equals("Перерыв")
                    | activity.getTitle().equals("Упражнения")
                    | activity.getTitle().equals("Обеденный перерыв")) {
                vocationList.add(activity);
            } else {
                lectionList.add(activity);
            }
        }
    }
}
