package che;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * Created by Denis Areshkevich on 17.03.2017.
 */

public class Main {

    public static void main(String[] args) {
        Map<Character, Long> result = null;
        try {
            result = new String (Files.readAllBytes(Paths.get("MyFile.txt")), "UTF-8")
                    .chars().mapToObj(e->(char)e).collect(Collectors.toList()).stream()
                    .filter(e -> (e >= 'а') && (e <= 'я') )
                    .map(Character::new)
                    .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);

    }
}
