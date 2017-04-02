package regex.oreshkevich;

import java.io.IOException;

/**
 * Created by Denis Areshkevich on 28.03.2017.
 */
public class Main {

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        try {
            String text = fileManager.readText("text.txt");
            Parser parser = new Parser(text);
            parser.parse();
            fileManager.writeText(Reporter.formTimePeriodReport(parser.getDayScheduleList()), "time-period-report.txt");
            fileManager.writeText(Reporter.percentedTimePeriodActivityReport(parser.getDayScheduleList()), "percented-time-period-report.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
