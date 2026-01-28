import java.util.*;

public class HashtableDemo {

    public static void main(String[] args) {

        Hashtable<Integer, String> table = new Hashtable<>();

        table.put(1, "Java");
        table.put(2, "Spring");
        table.put(3, "Kafka");
        System.out.println("1. put(): " + table);

        table.putAll(Map.of(4, "Docker", 5, "Redis"));
        System.out.println("2. putAll(): " + table);

        table.putIfAbsent(6, "Kubernetes");
        System.out.println("3. putIfAbsent(): " + table);

        System.out.println("4. get(2): " + table.get(2));
        System.out.println("5. getOrDefault(10, Default): " + table.getOrDefault(10, "Default"));

        System.out.println("6. containsKey(1): " + table.containsKey(1));
        System.out.println("7. containsValue(Spring): " + table.containsValue("Spring"));
        System.out.println("8. contains(Spring): " + table.contains("Spring")); // legacy

        System.out.println("9. keySet(): " + table.keySet());
        System.out.println("10. values(): " + table.values());
        System.out.println("11. entrySet(): " + table.entrySet());

        System.out.print("12. keys() Enumeration: ");
        Enumeration<Integer> keys = table.keys();
        while (keys.hasMoreElements()) {
            System.out.print(keys.nextElement() + " | ");
        }
        System.out.println();

        System.out.print("13. elements() Enumeration: ");
        Enumeration<String> elements = table.elements();
        while (elements.hasMoreElements()) {
            System.out.print(elements.nextElement() + " | ");
        }
        System.out.println();

        table.replace(1, "Java Updated");
        table.replace(2, "Spring", "Spring Boot");
        System.out.println("14. replace variants: " + table);

        table.replaceAll((k, v) -> v.toUpperCase());
        System.out.println("15. replaceAll(): " + table);

        table.compute(1, (k, v) -> v + " COMPUTED");
        table.computeIfAbsent(7, k -> "RabbitMQ");
        table.computeIfPresent(3, (k, v) -> v + " PRESENT");
        System.out.println("16. compute variants: " + table);

        table.merge(8, "MongoDB", String::concat);
        table.merge(8, " Atlas", String::concat);
        System.out.println("17. merge(): " + table);

        Hashtable<Integer, String> cloned = (Hashtable<Integer, String>) table.clone();
        System.out.println("18. clone(): " + cloned);

        System.out.println("19. size(): " + table.size());
        System.out.println("20. isEmpty(): " + table.isEmpty());
        System.out.println("21. equals(cloned): " + table.equals(cloned));
        System.out.println("22. hashCode(): " + table.hashCode());

        table.remove(8);
        table.remove(7, "RabbitMQ");
        System.out.println("23. remove variants: " + table);

        table.clear();
        System.out.println("24. clear(): " + table);
    }
}

/*
SAMPLE OUTPUT:
Hashtable is synchronized legacy Map and does not allow null key/value.

1. put(): {3=Kafka, 2=Spring, 1=Java}
2. putAll(): {5=Redis, 4=Docker, 3=Kafka, 2=Spring, 1=Java}
3. putIfAbsent(): {6=Kubernetes, 5=Redis, 4=Docker, 3=Kafka, 2=Spring, 1=Java}
4. get(2): Spring
5. getOrDefault(10, Default): Default
6. containsKey(1): true
7. containsValue(Spring): true
8. contains(Spring): true
9. keySet(): [6, 5, 4, 3, 2, 1]
10. values(): [Kubernetes, Redis, Docker, Kafka, Spring, Java]
11. entrySet(): [6=Kubernetes, 5=Redis, 4=Docker, 3=Kafka, 2=Spring, 1=Java]
12. keys() Enumeration: 6 | 5 | 4 | 3 | 2 | 1 |
13. elements() Enumeration: Kubernetes | Redis | Docker | Kafka | Spring | Java |
14. replace variants: {6=Kubernetes, 5=Redis, 4=Docker, 3=Kafka, 2=Spring Boot, 1=Java Updated}
15. replaceAll(): {6=KUBERNETES, 5=REDIS, 4=DOCKER, 3=KAFKA, 2=SPRING BOOT, 1=JAVA UPDATED}
16. compute variants: {7=RabbitMQ, 6=KUBERNETES, 5=REDIS, 4=DOCKER, 3=KAFKA PRESENT, 2=SPRING BOOT, 1=JAVA UPDATED COMPUTED}
17. merge(): {8=MongoDB Atlas, 7=RabbitMQ, 6=KUBERNETES, 5=REDIS, 4=DOCKER, 3=KAFKA PRESENT, 2=SPRING BOOT, 1=JAVA UPDATED COMPUTED}
18. clone(): {8=MongoDB Atlas, 7=RabbitMQ, 6=KUBERNETES, 5=REDIS, 4=DOCKER, 3=KAFKA PRESENT, 2=SPRING BOOT, 1=JAVA UPDATED COMPUTED}
19. size(): 8
20. isEmpty(): false
21. equals(cloned): true
22. hashCode(): 1388307210
23. remove variants: {6=KUBERNETES, 5=REDIS, 4=DOCKER, 3=KAFKA PRESENT, 2=SPRING BOOT, 1=JAVA UPDATED COMPUTED}
24. clear(): {}

*/
