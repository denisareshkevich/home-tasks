package che;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * Created by Denis Areshkevich on 17.03.2017.
 */

public class Main {

    public static void main(String[] args) {
        try {
          new String (Files.readAllBytes(Paths.get("MyFile.txt")), "UTF-8")
                    .chars().mapToObj(e->(char)e).collect(Collectors.toList()).stream()
                    .filter(e -> (e >= 'а') && (e <= 'я') )
                    .map(Character::new)
                    .collect(HashMap::new, (m, c) -> m.put(c, m.containsKey(c) ? ((Integer) (m.get(c)) + 1) : 1),
                            HashMap::putAll).entrySet().stream().sorted(Comparator.comparing(s -> s.getKey().toString()))
                  .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
