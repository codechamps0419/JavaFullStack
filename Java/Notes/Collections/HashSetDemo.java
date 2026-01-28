import java.util.*;
import java.util.stream.Collectors;

public class HashSetDemo {

    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>();

        set.add("Java");
        set.add("Spring");
        set.add("Kafka");
        set.add("Redis");
        set.add("Java"); // duplicate ignored
        set.add(null);   // HashSet allows one null
        System.out.println("1. add(): " + set);

        set.addAll(Arrays.asList("Docker", "Kubernetes"));
        System.out.println("2. addAll(): " + set);

        System.out.println("3. contains(Java): " + set.contains("Java"));

        System.out.println("4. containsAll(Java, Spring): " +
                set.containsAll(Arrays.asList("Java", "Spring")));

        System.out.println("5. size(): " + set.size());

        System.out.println("6. isEmpty(): " + set.isEmpty());

        System.out.print("7. iterator(): ");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " | ");
        }
        System.out.println();

        System.out.print("8. forEach(): ");
        set.forEach(e -> System.out.print(e + " | "));
        System.out.println();

        Object[] objectArray = set.toArray();
        System.out.println("9. toArray(): " + Arrays.toString(objectArray));

        String[] stringArray = set.toArray(new String[0]);
        System.out.println("10. toArray(new String[0]): " + Arrays.toString(stringArray));

        set.remove("Kafka");
        System.out.println("11. remove(Kafka): " + set);

        set.removeAll(Arrays.asList("Docker", "Unknown"));
        System.out.println("12. removeAll(Docker, Unknown): " + set);

        set.retainAll(Arrays.asList("Java", "Spring", "Redis", null));
        System.out.println("13. retainAll(Java, Spring, Redis, null): " + set);

        set.removeIf(e -> e != null && e.startsWith("R"));
        System.out.println("14. removeIf(startsWith R): " + set);

        HashSet<String> cloned = (HashSet<String>) set.clone();
        System.out.println("15. clone(): " + cloned);

        System.out.print("16. spliterator(): ");
        Spliterator<String> spliterator = set.spliterator();
        spliterator.forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        long count = set.stream().count();
        System.out.println("17. stream().count(): " + count);

        long parallelCount = set.parallelStream().count();
        System.out.println("18. parallelStream().count(): " + parallelCount);

        String joined = set.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining(","));
        System.out.println("19. stream collect joining: " + joined);

        System.out.println("20. equals(cloned): " + set.equals(cloned));

        System.out.println("21. hashCode(): " + set.hashCode());

        set.clear();
        System.out.println("22. clear(): " + set);

        System.out.println("23. isEmpty() after clear: " + set.isEmpty());
    }
}

/*
SAMPLE OUTPUT:

1. add(): [null, Java, Kafka, Redis, Spring]
2. addAll(): [null, Java, Docker, Kafka, Redis, Spring, Kubernetes]
3. contains(Java): true
4. containsAll(Java, Spring): true
5. size(): 7
6. isEmpty(): false
7. iterator(): null | Java | Docker | Kafka | Redis | Spring | Kubernetes |
8. forEach(): null | Java | Docker | Kafka | Redis | Spring | Kubernetes |
9. toArray(): [null, Java, Docker, Kafka, Redis, Spring, Kubernetes]
10. toArray(new String[0]): [null, Java, Docker, Kafka, Redis, Spring, Kubernetes]
11. remove(Kafka): [null, Java, Docker, Redis, Spring, Kubernetes]
12. removeAll(Docker, Unknown): [null, Java, Redis, Spring, Kubernetes]
13. retainAll(Java, Spring, Redis, null): [null, Java, Redis, Spring]
14. removeIf(startsWith R): [null, Java, Spring]
15. clone(): [null, Spring, Java]
16. spliterator(): null | Java | Spring |
17. stream().count(): 3
18. parallelStream().count(): 3
19. stream collect joining: Java,Spring
20. equals(cloned): true
21. hashCode(): -1809511313
22. clear(): []
23. isEmpty() after clear: true

Note:
HashSet output order may change because HashSet does not maintain insertion order.

*/
