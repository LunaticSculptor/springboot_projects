import java.util.List;
import java.util.stream.Collectors;

public class FunctionalExample {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");
        List<String> longNames = names.stream()
            .filter(name->name.length()>4)
            .collect(Collectors.toList());
        System.out.println(longNames);
    }
}