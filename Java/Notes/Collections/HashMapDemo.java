import java.util.*;

public class HashMapDemo {

    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");
        map.put(3, "Kafka");
        map.put(null, "NullKey");
        map.put(4, null);
        System.out.println("1. put(): " + map);

        Map<Integer, String> extra = new HashMap<>();
        extra.put(5, "Docker");
        extra.put(6, "Kubernetes");
        map.putAll(extra);
        System.out.println("2. putAll(): " + map);

        map.putIfAbsent(7, "Redis");
        map.putIfAbsent(4, "WillNotReplaceNull?");
        System.out.println("3. putIfAbsent(): " + map);

        System.out.println("4. get(2): " + map.get(2));
        System.out.println("5. getOrDefault(100, Default): " + map.getOrDefault(100, "Default"));

        System.out.println("6. containsKey(1): " + map.containsKey(1));
        System.out.println("7. containsValue(Spring): " + map.containsValue("Spring"));

        System.out.println("8. keySet(): " + map.keySet());
        System.out.println("9. values(): " + map.values());
        System.out.println("10. entrySet(): " + map.entrySet());

        map.replace(1, "Java Updated");
        System.out.println("11. replace(1, Java Updated): " + map);

        map.replace(2, "Spring", "Spring Boot");
        System.out.println("12. replace(2, old, new): " + map);

        map.replaceAll((k, v) -> v == null ? "EMPTY" : v.toUpperCase());
        System.out.println("13. replaceAll(): " + map);

        map.compute(1, (k, v) -> v + " COMPUTED");
        System.out.println("14. compute(): " + map);

        map.computeIfAbsent(8, k -> "RabbitMQ");
        System.out.println("15. computeIfAbsent(): " + map);

        map.computeIfPresent(3, (k, v) -> v + " PRESENT");
        System.out.println("16. computeIfPresent(): " + map);

        map.merge(9, "MongoDB", (oldVal, newVal) -> oldVal + newVal);
        map.merge(9, " Atlas", (oldVal, newVal) -> oldVal + newVal);
        System.out.println("17. merge(): " + map);

        System.out.print("18. forEach(): ");
        map.forEach((k, v) -> System.out.print(k + "=" + v + " | "));
        System.out.println();

        System.out.println("19. size(): " + map.size());
        System.out.println("20. isEmpty(): " + map.isEmpty());

        HashMap<Integer, String> cloned = (HashMap<Integer, String>) map.clone();
        System.out.println("21. clone(): " + cloned);

        System.out.println("22. equals(cloned): " + map.equals(cloned));
        System.out.println("23. hashCode(): " + map.hashCode());

        map.remove(9);
        System.out.println("24. remove(key 9): " + map);

        map.remove(8, "RabbitMQ");
        System.out.println("25. remove(key,value): " + map);

        map.clear();
        System.out.println("26. clear(): " + map);
    }
}

/*
SAMPLE OUTPUT:

1. put(): {null=NullKey, 1=Java, 2=Spring, 3=Kafka, 4=null}
2. putAll(): {null=NullKey, 1=Java, 2=Spring, 3=Kafka, 4=null, 5=Docker, 6=Kubernetes}
3. putIfAbsent(): {null=NullKey, 1=Java, 2=Spring, 3=Kafka, 4=WillNotReplaceNull?, 5=Docker, 6=Kubernetes, 7=Redis}
4. get(2): Spring
5. getOrDefault(100, Default): Default
6. containsKey(1): true
7. containsValue(Spring): true
8. keySet(): [null, 1, 2, 3, 4, 5, 6, 7]
9. values(): [NullKey, Java, Spring, Kafka, WillNotReplaceNull?, Docker, Kubernetes, Redis]
10. entrySet(): [null=NullKey, 1=Java, 2=Spring, 3=Kafka, 4=WillNotReplaceNull?, 5=Docker, 6=Kubernetes, 7=Redis]
11. replace(1, Java Updated): {null=NullKey, 1=Java Updated, 2=Spring, 3=Kafka, 4=WillNotReplaceNull?, 5=Docker, 6=Kubernetes, 7=Redis}
12. replace(2, old, new): {null=NullKey, 1=Java Updated, 2=Spring Boot, 3=Kafka, 4=WillNotReplaceNull?, 5=Docker, 6=Kubernetes, 7=Redis}
13. replaceAll(): {null=NULLKEY, 1=JAVA UPDATED, 2=SPRING BOOT, 3=KAFKA, 4=WILLNOTREPLACENULL?, 5=DOCKER, 6=KUBERNETES, 7=REDIS}
14. compute(): {null=NULLKEY, 1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA, 4=WILLNOTREPLACENULL?, 5=DOCKER, 6=KUBERNETES, 7=REDIS}
15. computeIfAbsent(): {null=NULLKEY, 1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA, 4=WILLNOTREPLACENULL?, 5=DOCKER, 6=KUBERNETES, 7=REDIS, 8=RabbitMQ}
16. computeIfPresent(): {null=NULLKEY, 1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=WILLNOTREPLACENULL?, 5=DOCKER, 6=KUBERNETES, 7=REDIS, 8=RabbitMQ}
17. merge(): {null=NULLKEY, 1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=WILLNOTREPLACENULL?, 5=DOCKER, 6=KUBERNETES, 7=REDIS, 8=RabbitMQ, 9=MongoDB Atlas}
18. forEach(): null=NULLKEY | 1=JAVA UPDATED COMPUTED | 2=SPRING BOOT | 3=KAFKA PRESENT | 4=WILLNOTREPLACENULL? | 5=DOCKER | 6=KUBERNETES | 7=REDIS | 8=RabbitMQ | 9=MongoDB Atlas |
19. size(): 10
20. isEmpty(): false
21. clone(): {null=NULLKEY, 1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=WILLNOTREPLACENULL?, 5=DOCKER, 6=KUBERNETES, 7=REDIS, 8=RabbitMQ, 9=MongoDB Atlas}
22. equals(cloned): true
23. hashCode(): -533107714
24. remove(key 9): {null=NULLKEY, 1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=WILLNOTREPLACENULL?, 5=DOCKER, 6=KUBERNETES, 7=REDIS, 8=RabbitMQ}
25. remove(key,value): {null=NULLKEY, 1=JAVA UPDATED COMPUTED, 2=SPRING BOOT, 3=KAFKA PRESENT, 4=WILLNOTREPLACENULL?, 5=DOCKER, 6=KUBERNETES, 7=REDIS}
26. clear(): {}

Note:
HashMap order is not guaranteed.
*/
