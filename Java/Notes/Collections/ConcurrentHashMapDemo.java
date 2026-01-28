import java.util.*;
import java.util.concurrent.*;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");
        map.put(3, "Kafka");
        System.out.println("1. put(): " + map);

        map.putAll(Map.of(4, "Docker", 5, "Redis"));
        System.out.println("2. putAll(): " + map);

        map.putIfAbsent(6, "Kubernetes");
        System.out.println("3. putIfAbsent(): " + map);

        System.out.println("4. get(2): " + map.get(2));
        System.out.println("5. getOrDefault(10, Default): " + map.getOrDefault(10, "Default"));
        System.out.println("6. containsKey(1): " + map.containsKey(1));
        System.out.println("7. containsValue(Spring): " + map.containsValue("Spring"));

        System.out.println("8. keySet(): " + map.keySet());
        System.out.println("9. values(): " + map.values());
        System.out.println("10. entrySet(): " + map.entrySet());

        map.replace(1, "Java Updated");
        map.replace(2, "Spring", "Spring Boot");
        System.out.println("11. replace variants: " + map);

        map.replaceAll((k, v) -> v.toUpperCase());
        System.out.println("12. replaceAll(): " + map);

        map.compute(1, (k, v) -> v + " COMPUTED");
        map.computeIfAbsent(7, k -> "RabbitMQ");
        map.computeIfPresent(3, (k, v) -> v + " PRESENT");
        System.out.println("13. compute variants: " + map);

        map.merge(8, "MongoDB", String::concat);
        map.merge(8, " Atlas", String::concat);
        System.out.println("14. merge(): " + map);

        System.out.print("15. forEach(): ");
        map.forEach((k, v) -> System.out.print(k + "=" + v + " | "));
        System.out.println();

        // ConcurrentHashMap bulk operations
        map.forEach(1L, (k, v) -> System.out.print(""));
        System.out.println("16. forEach(parallelismThreshold): executed");

        String searchResult = map.search(1L,
                (k, v) -> v.contains("JAVA") ? v : null);
        System.out.println("17. search(): " + searchResult);

        Integer reduceKeys = map.reduceKeys(1L, Integer::sum);
        System.out.println("18. reduceKeys(sum): " + reduceKeys);

        String reduceValues = map.reduceValues(1L,
                (v1, v2) -> v1 + "," + v2);
        System.out.println("19. reduceValues(): " + reduceValues);

        System.out.println("20. mappingCount(): " + map.mappingCount());
        System.out.println("21. size(): " + map.size());
        System.out.println("22. isEmpty(): " + map.isEmpty());
        System.out.println("23. hashCode(): " + map.hashCode());

        map.remove(8);
        map.remove(7, "RabbitMQ");
        System.out.println("24. remove variants: " + map);

        map.clear();
        System.out.println("25. clear(): " + map);
    }
}

/*
SAMPLE OUTPUT:
ConcurrentHashMap is thread-safe and does not allow null key/value.

1. put(): {1=Java, 2=Spring, 3=Kafka}
2. putAll(): {1=Java, 2=Spring, 3=Kafka, 4=Docker, 5=Redis}
3. putIfAbsent(): {1=Java, 2=Spring, 3=Kafka, 4=Docker, 5=Redis, 6=Kubernetes}
4. get(2): Spring
5. getOrDefault(10, Default): Default
6. containsKey(1): true
7. containsValue(Spring): true
8. keySet(): [1, 2, 3, 4, 5, 6]
9. values(): [Java, Spring, Kafka, Docker, Redis, Kubernetes]
10. entrySet(): [1=Java, 2=Spring, 3=Kafka, 4=Docker, 5=Redis, 6=Kubernetes]
11. replace variants: {1=Java Updated, 2=Spring Boot, 3=Kafka, 4=Docker, 5=Redis, 6=Kubernetes}
12. replaceAll(): {1=JAVA UPDATED, 2=SPRING BOOT, 3=KAFKA, 4=DOCKER, 5=REDIS, 6=KUBERNETES}
13. compute variants: {1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=DOCKER, 5=REDIS, 6=KUBERNETES, 7=RabbitMQ}
14. merge(): {1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=DOCKER, 5=REDIS, 6=KUBERNETES, 7=RabbitMQ, 8=MongoDB Atlas}
15. forEach(): 1=JAVA UPDATED COMPUTED | 2=SPRING BOOT | 3=KAFKA PRESENT | 4=DOCKER | 5=REDIS | 6=KUBERNETES | 7=RabbitMQ | 8=MongoDB Atlas |
16. forEach(parallelismThreshold): executed
17. search(): JAVA UPDATED COMPUTED
18. reduceKeys(sum): 36
19. reduceValues(): JAVA UPDATED COMPUTED,SPRING BOOT,KAFKA PRESENT,DOCKER,REDIS,KUBERNETES,RabbitMQ,MongoDB Atlas
20. mappingCount(): 8
21. size(): 8
22. isEmpty(): false
23. hashCode(): 1388307210
24. remove variants: {1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=DOCKER, 5=REDIS, 6=KUBERNETES}
25. clear(): {}

*/
