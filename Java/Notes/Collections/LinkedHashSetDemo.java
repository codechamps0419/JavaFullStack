import java.util.*;

public class LinkedHashSetDemo {

    public static void main(String[] args) {

        LinkedHashSet<String> set = new LinkedHashSet<>();

        set.add("Java");
        set.add("Spring");
        set.add("Kafka");
        set.add("Redis");
        set.add("Java"); // duplicate ignored
        set.add(null);   // allows one null
        System.out.println("1. add(): " + set);

        set.addAll(Arrays.asList("Docker", "Kubernetes"));
        System.out.println("2. addAll(): " + set);

        System.out.println("3. contains(Spring): " + set.contains("Spring"));
        System.out.println("4. containsAll(Java, Kafka): " +
                set.containsAll(Arrays.asList("Java", "Kafka")));

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

        System.out.println("9. toArray(): " + Arrays.toString(set.toArray()));
        System.out.println("10. toArray(new String[0]): " +
                Arrays.toString(set.toArray(new String[0])));

        set.remove("Kafka");
        System.out.println("11. remove(Kafka): " + set);

        set.removeAll(Arrays.asList("Docker"));
        System.out.println("12. removeAll(Docker): " + set);

        set.retainAll(Arrays.asList("Java", "Spring", null));
        System.out.println("13. retainAll(Java, Spring, null): " + set);

        set.removeIf(e -> e != null && e.startsWith("S"));
        System.out.println("14. removeIf(startsWith S): " + set);

        LinkedHashSet<String> cloned = (LinkedHashSet<String>) set.clone();
        System.out.println("15. clone(): " + cloned);

        System.out.print("16. spliterator(): ");
        set.spliterator().forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("17. stream().count(): " + set.stream().count());
        System.out.println("18. parallelStream().count(): " + set.parallelStream().count());
        System.out.println("19. equals(cloned): " + set.equals(cloned));
        System.out.println("20. hashCode(): " + set.hashCode());

        set.clear();
        System.out.println("21. clear(): " + set);
    }
}

/*
SAMPLE OUTPUT:

1. add(): [Java, Spring, Kafka, Redis, null]
2. addAll(): [Java, Spring, Kafka, Redis, null, Docker, Kubernetes]
3. contains(Spring): true
4. containsAll(Java, Kafka): true
5. size(): 7
6. isEmpty(): false
7. iterator(): Java | Spring | Kafka | Redis | null | Docker | Kubernetes |
8. forEach(): Java | Spring | Kafka | Redis | null | Docker | Kubernetes |
9. toArray(): [Java, Spring, Kafka, Redis, null, Docker, Kubernetes]
10. toArray(new String[0]): [Java, Spring, Kafka, Redis, null, Docker, Kubernetes]
11. remove(Kafka): [Java, Spring, Redis, null, Docker, Kubernetes]
12. removeAll(Docker): [Java, Spring, Redis, null, Kubernetes]
13. retainAll(Java, Spring, null): [Java, Spring, null]
14. removeIf(startsWith S): [Java, null]
15. clone(): [Java, null]
16. spliterator(): Java | null |
17. stream().count(): 2
18. parallelStream().count(): 2
19. equals(cloned): true
20. hashCode(): 2301506
21. clear(): []

Note:
LinkedHashSet maintains insertion order.
*/
