package theads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Denis Areshkevich on 02.04.2017.
 */
public class Dump implements Runnable {

    private final Random random = new Random();
    private int nights = 0;

    private final List<String> trashList = new CopyOnWriteArrayList<>();

    @Override
    public void run() {
        while (nights < 100) {
            Random random = new Random(System.currentTimeMillis());
            int amount = random.nextInt(4) + 1;
            throwRandomDetails(amount);
            try {
                Thread.sleep(100);
                nights++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized List<String> getRandomDetails(int amount) {
        Random random = new Random(System.currentTimeMillis());
        List<String> details = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (trashList.isEmpty()) return details;
            int position = random.nextInt(trashList.size());
            details.add(trashList.get(position));
            trashList.remove(position);
        }
        return details;
    }

    public void throwRandomDetails(int amount) {
        int detailNumber;
        synchronized (trashList) {
            for (int i = 0; i <= amount; i++) {
                detailNumber = random.nextInt(9);
                switch (detailNumber) {
                    case 0:
                        trashList.add(new String("HEAD"));
                        break;
                    case 1:
                        trashList.add(new String("BODY"));
                        break;
                    case 2:
                        trashList.add(new String("LEFT_ARM"));
                        break;
                    case 3:
                        trashList.add(new String("RIGHT_ARM"));
                        break;
                    case 4:
                        trashList.add(new String("LEFT_LEG"));
                        break;
                    case 5:
                        trashList.add(new String("RIGHT_LEG"));
                        break;
                    case 6:
                        trashList.add(new String("CPU"));
                        break;
                    case 7:
                        trashList.add(new String("RAM"));
                        break;
                    case 8:
                        trashList.add(new String("HDD"));
                        break;

                }
            }
            System.out.println("DUMP: " + amount + " details thrown");
        }
    }

}