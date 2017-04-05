package theads;

import theads.Dump;

/**
 * Created by Denis Areshkevich on 02.04.2017.
 */
public class Main {

    public static void main(String[] args) {
        Dump dump = new Dump();
        Scientist pasha = new Scientist(dump, "Pasha");
        Scientist kolya = new Scientist(dump, "Kolya");
        dump.throwRandomDetails(20);
        Thread dumpThread = new Thread(dump);
        Thread firstScientistThread = new Thread(pasha);
        Thread secondScientistThread = new Thread(kolya);
        dumpThread.start();
        firstScientistThread.start();
        secondScientistThread.start();

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Pasha's robots: " + pasha.getRobotCount());
        System.out.println("Kolya's robots: " + kolya.getRobotCount());
    }

}
