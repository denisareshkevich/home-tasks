package by.oreshkevich.dz1;

import java.util.*;

public class Unique {
    public static void countEqualWords(String text) {
        List<String> splittedList = getSplittedText(text);
        Map<String, Integer> finalArrayWords = new HashMap<>();

        int defaultCount = 1;
        for (int i = 0; i < splittedList.size(); i++) {
            if (finalArrayWords.containsKey(splittedList.get(i))) {
                int repeatCount = finalArrayWords.get(splittedList.get(i)) + 1;
                finalArrayWords.put(splittedList.get(i), repeatCount);
            }
            finalArrayWords.putIfAbsent(splittedList.get(i), defaultCount);
        }
        System.out.println(finalArrayWords);
    }

    private static List<String> getSplittedText(String text) {
        List<String> arrayWords = new ArrayList<>();
        for (String word : text.split("\\s+")) {
            arrayWords.add(word);
        }
        return arrayWords;
    }
}

