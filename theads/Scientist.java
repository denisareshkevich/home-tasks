import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Denis Areshkevich on 02.04.2017.
 */
public class Scientist implements Runnable {

    private String name;
    private Dump dump;
    private List<String> ownDetails;
    private int robotCount = 0;
    private int nights = 0;
    private final List<String> robotParts = Arrays.asList("HEAD", "BODY", "LEFT_ARM", "RIGHT_ARM",
            "LEFT_LEG", "RIGHT_LEG", "CPU", "RAM", "HDD");

    public Scientist(Dump dump, String name) {
        ownDetails = new ArrayList<>();
        this.dump = dump;
        this.name = name;
    }

    public void takeDetails() {
        Random random = new Random(System.currentTimeMillis());
        int amount = random.nextInt(4) + 1;
        ownDetails.addAll(dump.getRandomDetails(amount));
        System.out.println(name + " took details");
    }

    private synchronized void createRobots() {
        if (ownDetails.containsAll(robotParts)) {
            robotCount++;
            ownDetails.remove("HEAD");
            ownDetails.remove("BODY");
            ownDetails.remove("LEFT_ARM");
            ownDetails.remove("RIGHT_ARM");
            ownDetails.remove("LEFT_LEG");
            ownDetails.remove("RIGHT_LEG");
            ownDetails.remove("CPU");
            ownDetails.remove("RAM");
            ownDetails.remove("HDD");
            System.out.println(name + " created a robot");
        }
    }

    @Override
    public void run() {
        while (nights < 100) {
            takeDetails();
            createRobots();
            try {
                Thread.sleep(100);
                nights++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public int getRobotCount() {
        return robotCount;
    }
}
