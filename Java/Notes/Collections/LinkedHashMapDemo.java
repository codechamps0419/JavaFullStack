import java.util.*;

public class LinkedHashMapDemo {

    public static void main(String[] args) {

        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");
        map.put(3, "Kafka");
        map.put(4, null);
        map.put(null, "NullKey");
        System.out.println("1. put(): " + map);

        map.putAll(Map.of(5, "Docker", 6, "Kubernetes"));
        System.out.println("2. putAll(): " + map);

        map.putIfAbsent(7, "Redis");
        System.out.println("3. putIfAbsent(): " + map);

        System.out.println("4. get(2): " + map.get(2));
        System.out.println("5. getOrDefault(100, Default): " + map.getOrDefault(100, "Default"));
        System.out.println("6. containsKey(1): " + map.containsKey(1));
        System.out.println("7. containsValue(Spring): " + map.containsValue("Spring"));

        System.out.println("8. keySet(): " + map.keySet());
        System.out.println("9. values(): " + map.values());
        System.out.println("10. entrySet(): " + map.entrySet());

        map.replace(1, "Java Updated");
        map.replace(2, "Spring", "Spring Boot");
        System.out.println("11. replace variants: " + map);

        map.replaceAll((k, v) -> v == null ? "EMPTY" : v.toUpperCase());
        System.out.println("12. replaceAll(): " + map);

        map.compute(1, (k, v) -> v + " COMPUTED");
        map.computeIfAbsent(8, k -> "RabbitMQ");
        map.computeIfPresent(3, (k, v) -> v + " PRESENT");
        System.out.println("13. compute variants: " + map);

        map.merge(9, "MongoDB", String::concat);
        map.merge(9, " Atlas", String::concat);
        System.out.println("14. merge(): " + map);

        System.out.print("15. forEach(): ");
        map.forEach((k, v) -> System.out.print(k + "=" + v + " | "));
        System.out.println();

        System.out.println("16. size(): " + map.size());
        System.out.println("17. isEmpty(): " + map.isEmpty());

        LinkedHashMap<Integer, String> cloned = (LinkedHashMap<Integer, String>) map.clone();
        System.out.println("18. clone(): " + cloned);
        System.out.println("19. equals(cloned): " + map.equals(cloned));
        System.out.println("20. hashCode(): " + map.hashCode());

        map.remove(9);
        map.remove(8, "RabbitMQ");
        System.out.println("21. remove variants: " + map);

        map.clear();
        System.out.println("22. clear(): " + map);
    }
}

/*
SAMPLE OUTPUT:

1. put(): {1=Java, 2=Spring, 3=Kafka, 4=null, null=NullKey}
2. putAll(): {1=Java, 2=Spring, 3=Kafka, 4=null, null=NullKey, 5=Docker, 6=Kubernetes}
3. putIfAbsent(): {1=Java, 2=Spring, 3=Kafka, 4=null, null=NullKey, 5=Docker, 6=Kubernetes, 7=Redis}
4. get(2): Spring
5. getOrDefault(100, Default): Default
6. containsKey(1): true
7. containsValue(Spring): true
8. keySet(): [1, 2, 3, 4, null, 5, 6, 7]
9. values(): [Java, Spring, Kafka, null, NullKey, Docker, Kubernetes, Redis]
10. entrySet(): [1=Java, 2=Spring, 3=Kafka, 4=null, null=NullKey, 5=Docker, 6=Kubernetes, 7=Redis]
11. replace variants: {1=Java Updated, 2=Spring Boot, 3=Kafka, 4=null, null=NullKey, 5=Docker, 6=Kubernetes, 7=Redis}
12. replaceAll(): {1=JAVA UPDATED, 2=SPRING BOOT, 3=KAFKA, 4=EMPTY, null=NULLKEY, 5=DOCKER, 6=KUBERNETES, 7=REDIS}
13. compute variants: {1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=EMPTY, null=NULLKEY, 5=DOCKER, 6=KUBERNETES, 7=REDIS, 8=RabbitMQ}
14. merge(): {1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=EMPTY, null=NULLKEY, 5=DOCKER, 6=KUBERNETES, 7=REDIS, 8=RabbitMQ, 9=MongoDB Atlas}
15. forEach(): 1=JAVA UPDATED COMPUTED | 2=SPRING BOOT | 3=KAFKA PRESENT | 4=EMPTY | null=NULLKEY | 5=DOCKER | 6=KUBERNETES | 7=REDIS | 8=RabbitMQ | 9=MongoDB Atlas |
16. size(): 10
17. isEmpty(): false
18. clone(): {1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=EMPTY, null=NULLKEY, 5=DOCKER, 6=KUBERNETES, 7=REDIS, 8=RabbitMQ, 9=MongoDB Atlas}
19. equals(cloned): true
20. hashCode(): 171250566
21. remove variants: {1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=EMPTY, null=NULLKEY, 5=DOCKER, 6=KUBERNETES, 7=REDIS}
22. clear(): {}

*/
