import java.util.*;

public class TreeMapDemo {

    public static void main(String[] args) {

        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(30, "Kafka");
        map.put(10, "Java");
        map.put(20, "Spring");
        map.put(40, "Docker");
        map.put(50, "Redis");
        System.out.println("1. put(): " + map);

        map.putAll(Map.of(60, "Kubernetes", 70, "RabbitMQ"));
        System.out.println("2. putAll(): " + map);

        map.putIfAbsent(80, "MongoDB");
        System.out.println("3. putIfAbsent(): " + map);

        System.out.println("4. get(20): " + map.get(20));
        System.out.println("5. getOrDefault(100, Default): " + map.getOrDefault(100, "Default"));

        System.out.println("6. firstKey(): " + map.firstKey());
        System.out.println("7. lastKey(): " + map.lastKey());
        System.out.println("8. firstEntry(): " + map.firstEntry());
        System.out.println("9. lastEntry(): " + map.lastEntry());

        System.out.println("10. lowerKey(30): " + map.lowerKey(30));
        System.out.println("11. floorKey(30): " + map.floorKey(30));
        System.out.println("12. ceilingKey(35): " + map.ceilingKey(35));
        System.out.println("13. higherKey(30): " + map.higherKey(30));

        System.out.println("14. lowerEntry(30): " + map.lowerEntry(30));
        System.out.println("15. floorEntry(30): " + map.floorEntry(30));
        System.out.println("16. ceilingEntry(35): " + map.ceilingEntry(35));
        System.out.println("17. higherEntry(30): " + map.higherEntry(30));

        System.out.println("18. headMap(40): " + map.headMap(40));
        System.out.println("19. headMap(40,true): " + map.headMap(40, true));
        System.out.println("20. tailMap(40): " + map.tailMap(40));
        System.out.println("21. tailMap(40,false): " + map.tailMap(40, false));
        System.out.println("22. subMap(20,60): " + map.subMap(20, 60));
        System.out.println("23. subMap(20,true,60,true): " + map.subMap(20, true, 60, true));

        System.out.println("24. descendingMap(): " + map.descendingMap());
        System.out.println("25. navigableKeySet(): " + map.navigableKeySet());
        System.out.println("26. descendingKeySet(): " + map.descendingKeySet());

        System.out.println("27. keySet(): " + map.keySet());
        System.out.println("28. values(): " + map.values());
        System.out.println("29. entrySet(): " + map.entrySet());

        System.out.println("30. containsKey(10): " + map.containsKey(10));
        System.out.println("31. containsValue(Java): " + map.containsValue("Java"));

        map.replace(10, "Java Updated");
        map.replace(20, "Spring", "Spring Boot");
        System.out.println("32. replace variants: " + map);

        map.replaceAll((k, v) -> v.toUpperCase());
        System.out.println("33. replaceAll(): " + map);

        map.compute(10, (k, v) -> v + " COMPUTED");
        map.computeIfAbsent(90, k -> "AWS");
        map.computeIfPresent(30, (k, v) -> v + " PRESENT");
        System.out.println("34. compute variants: " + map);

        map.merge(100, "GCP", String::concat);
        map.merge(100, " CLOUD", String::concat);
        System.out.println("35. merge(): " + map);

        System.out.println("36. pollFirstEntry(): " + map.pollFirstEntry());
        System.out.println("37. pollLastEntry(): " + map.pollLastEntry());
        System.out.println("38. after poll entries: " + map);

        TreeMap<Integer, String> cloned = (TreeMap<Integer, String>) map.clone();
        System.out.println("39. clone(): " + cloned);
        System.out.println("40. comparator(): " + map.comparator());
        System.out.println("41. size(): " + map.size());
        System.out.println("42. isEmpty(): " + map.isEmpty());

        map.remove(90);
        map.remove(100, "GCP CLOUD");
        System.out.println("43. remove variants: " + map);

        map.clear();
        System.out.println("44. clear(): " + map);
    }
}

/*
SAMPLE OUTPUT:

1. put(): {10=Java, 20=Spring, 30=Kafka, 40=Docker, 50=Redis}
2. putAll(): {10=Java, 20=Spring, 30=Kafka, 40=Docker, 50=Redis, 60=Kubernetes, 70=RabbitMQ}
3. putIfAbsent(): {10=Java, 20=Spring, 30=Kafka, 40=Docker, 50=Redis, 60=Kubernetes, 70=RabbitMQ, 80=MongoDB}
4. get(20): Spring
5. getOrDefault(100, Default): Default
6. firstKey(): 10
7. lastKey(): 80
8. firstEntry(): 10=Java
9. lastEntry(): 80=MongoDB
10. lowerKey(30): 20
11. floorKey(30): 30
12. ceilingKey(35): 40
13. higherKey(30): 40
14. lowerEntry(30): 20=Spring
15. floorEntry(30): 30=Kafka
16. ceilingEntry(35): 40=Docker
17. higherEntry(30): 40=Docker
18. headMap(40): {10=Java, 20=Spring, 30=Kafka}
19. headMap(40,true): {10=Java, 20=Spring, 30=Kafka, 40=Docker}
20. tailMap(40): {40=Docker, 50=Redis, 60=Kubernetes, 70=RabbitMQ, 80=MongoDB}
21. tailMap(40,false): {50=Redis, 60=Kubernetes, 70=RabbitMQ, 80=MongoDB}
22. subMap(20,60): {20=Spring, 30=Kafka, 40=Docker, 50=Redis}
23. subMap(20,true,60,true): {20=Spring, 30=Kafka, 40=Docker, 50=Redis, 60=Kubernetes}
24. descendingMap(): {80=MongoDB, 70=RabbitMQ, 60=Kubernetes, 50=Redis, 40=Docker, 30=Kafka, 20=Spring, 10=Java}
25. navigableKeySet(): [10, 20, 30, 40, 50, 60, 70, 80]
26. descendingKeySet(): [80, 70, 60, 50, 40, 30, 20, 10]
27. keySet(): [10, 20, 30, 40, 50, 60, 70, 80]
28. values(): [Java, Spring, Kafka, Docker, Redis, Kubernetes, RabbitMQ, MongoDB]
29. entrySet(): [10=Java, 20=Spring, 30=Kafka, 40=Docker, 50=Redis, 60=Kubernetes, 70=RabbitMQ, 80=MongoDB]
30. containsKey(10): true
31. containsValue(Java): true
32. replace variants: {10=Java Updated, 20=Spring Boot, 30=Kafka, 40=Docker, 50=Redis, 60=Kubernetes, 70=RabbitMQ, 80=MongoDB}
33. replaceAll(): {10=JAVA UPDATED, 20=SPRING BOOT, 30=KAFKA, 40=DOCKER, 50=REDIS, 60=KUBERNETES, 70=RABBITMQ, 80=MONGODB}
34. compute variants: {10=JAVA UPDATED COMPUTED, 20=SPRING BOOT, 30=KAFKA PRESENT, 40=DOCKER, 50=REDIS, 60=KUBERNETES, 70=RABBITMQ, 80=MONGODB, 90=AWS}
35. merge(): {10=JAVA UPDATED COMPUTED, 20=SPRING BOOT, 30=KAFKA PRESENT, 40=DOCKER, 50=REDIS, 60=KUBERNETES, 70=RABBITMQ, 80=MONGODB, 90=AWS, 100=GCP CLOUD}
36. pollFirstEntry(): 10=JAVA UPDATED COMPUTED
37. pollLastEntry(): 100=GCP CLOUD
38. after poll entries: {20=SPRING BOOT, 30=KAFKA PRESENT, 40=DOCKER, 50=REDIS, 60=KUBERNETES, 70=RABBITMQ, 80=MONGODB, 90=AWS}
39. clone(): {20=SPRING BOOT, 30=KAFKA PRESENT, 40=DOCKER, 50=REDIS, 60=KUBERNETES, 70=RABBITMQ, 80=MONGODB, 90=AWS}
40. comparator(): null
41. size(): 8
42. isEmpty(): false
43. remove variants: {20=SPRING BOOT, 30=KAFKA PRESENT, 40=DOCKER, 50=REDIS, 60=KUBERNETES, 70=RABBITMQ, 80=MONGODB}
44. clear(): {}

*/
