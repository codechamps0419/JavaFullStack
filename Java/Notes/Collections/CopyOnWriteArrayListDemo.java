import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {

        // Create CopyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();

        // add()
        list.add("Java");
        list.add("Spring");
        list.add("Kafka");

        System.out.println("1. add(): " + list);

        // add(index, element)
        list.add(1, "Microservices");

        System.out.println("2. add(index, element): " + list);

        // addIfAbsent()
        ((CopyOnWriteArrayList<String>) list)
                .addIfAbsent("Redis");

        System.out.println("3. addIfAbsent(Redis): " + list);

        // addAll()
        list.addAll(java.util.Arrays.asList(
                "Docker", "Kubernetes"));

        System.out.println("4. addAll(): " + list);

        // get()
        System.out.println("5. get(2): " + list.get(2));

        // set()
        list.set(2, "Spring Boot");

        System.out.println("6. set(): " + list);

        // contains()
        System.out.println("7. contains(Java): "
                + list.contains("Java"));

        // indexOf()
        System.out.println("8. indexOf(Java): "
                + list.indexOf("Java"));

        // lastIndexOf()
        System.out.println("9. lastIndexOf(Java): "
                + list.lastIndexOf("Java"));

        // size()
        System.out.println("10. size(): "
                + list.size());

        // isEmpty()
        System.out.println("11. isEmpty(): "
                + list.isEmpty());

        // iterator()
        System.out.print("12. iterator(): ");

        Iterator<String> itr = list.iterator();

        while (itr.hasNext()) {
            System.out.print(itr.next() + " | ");
        }

        System.out.println();

        // forEach()
        System.out.print("13. forEach(): ");

        list.forEach(e ->
                System.out.print(e + " | "));

        System.out.println();

        // remove(object)
        list.remove("Kafka");

        System.out.println("14. remove(Kafka): "
                + list);

        // remove(index)
        list.remove(0);

        System.out.println("15. remove(index 0): "
                + list);

        // removeIf()
        list.removeIf(e ->
                e.startsWith("D"));

        System.out.println("16. removeIf(startsWith D): "
                + list);

        // retainAll()
        list.retainAll(
                java.util.Arrays.asList(
                        "Spring Boot",
                        "Redis"));

        System.out.println("17. retainAll(): "
                + list);

        // add more data
        list.add("Java");
        list.add("Kafka");

        System.out.println("18. add more: "
                + list);

        // removeAll()
        list.removeAll(
                java.util.Arrays.asList(
                        "Kafka"));

        System.out.println("19. removeAll(Kafka): "
                + list);

        // replaceAll()
        list.replaceAll(String::toUpperCase);

        System.out.println("20. replaceAll(toUpperCase): "
                + list);

        // sort()
        list.sort(String::compareTo);

        System.out.println("21. sort(): "
                + list);

        // subList()
        System.out.println("22. subList(0,1): "
                + list.subList(0, 1));

        // toArray()
        Object[] arr = list.toArray();

        System.out.println("23. toArray(): "
                + java.util.Arrays.toString(arr));

        // clone()
        CopyOnWriteArrayList<String> cloned =
                new CopyOnWriteArrayList<>(list);

        System.out.println("24. clone/copy: "
                + cloned);

        // spliterator()
        System.out.print("25. spliterator(): ");

        list.spliterator()
                .forEachRemaining(
                        e -> System.out.print(e + " | "));

        System.out.println();

        // stream()
        System.out.println("26. stream().count(): "
                + list.stream().count());

        // parallelStream()
        System.out.println("27. parallelStream().count(): "
                + list.parallelStream().count());

	//Fail-Safe Iterator
	while (itr.hasNext()) {

            String value = itr.next();

            System.out.println(value);

            // Modifying during iteration
            list.add("Docker");
        }
        System.out.println("28. List after modifying during iteration: "+ list);

        // clear()
        list.clear();

        System.out.println("29. clear(): "
                + list);
    }
}

/*
SAMPLE OUTPUT:

1. add(): [Java, Spring, Kafka]
2. add(index, element): [Java, Microservices, Spring, Kafka]
3. addIfAbsent(Redis): [Java, Microservices, Spring, Kafka, Redis]
4. addAll(): [Java, Microservices, Spring, Kafka, Redis, Docker, Kubernetes]
5. get(2): Spring
6. set(): [Java, Microservices, Spring Boot, Kafka, Redis, Docker, Kubernetes]
7. contains(Java): true
8. indexOf(Java): 0
9. lastIndexOf(Java): 0
10. size(): 7
11. isEmpty(): false
12. iterator(): Java | Microservices | Spring Boot | Kafka | Redis | Docker | Kubernetes |
13. forEach(): Java | Microservices | Spring Boot | Kafka | Redis | Docker | Kubernetes |
14. remove(Kafka): [Java, Microservices, Spring Boot, Redis, Docker, Kubernetes]
15. remove(index 0): [Microservices, Spring Boot, Redis, Docker, Kubernetes]
16. removeIf(startsWith D): [Microservices, Spring Boot, Redis, Kubernetes]
17. retainAll(): [Spring Boot, Redis]
18. add more: [Spring Boot, Redis, Java, Kafka]
19. removeAll(Kafka): [Spring Boot, Redis, Java]
20. replaceAll(toUpperCase): [SPRING BOOT, REDIS, JAVA]
21. sort(): [JAVA, REDIS, SPRING BOOT]
22. subList(0,1): [JAVA]
23. toArray(): [JAVA, REDIS, SPRING BOOT]
24. clone/copy: [JAVA, REDIS, SPRING BOOT]
25. spliterator(): JAVA | REDIS | SPRING BOOT |
26. stream().count(): 3
27. parallelStream().count(): 3
28. List after modifying during iteration: [JAVA, REDIS, SPRING BOOT]
29. clear(): []

*/