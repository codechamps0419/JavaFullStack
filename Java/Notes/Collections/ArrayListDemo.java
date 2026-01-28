import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("Java");
        list.add("Spring");
        list.add("Kafka");
        list.add("Java");
        System.out.println("1 add(): " + list);

        list.add(1, "Microservices");
        System.out.println("2 add(index, element): " + list);

        list.addAll(Arrays.asList("Docker", "Kubernetes"));
        System.out.println("3 addAll(Collection): " + list);

        list.addAll(2, Arrays.asList("REST", "RabbitMQ"));
        System.out.println("4 addAll(index, Collection): " + list);

        System.out.println("5 get(2): " + list.get(2));

        list.set(2, "REST API");
        System.out.println("6 set(2, REST API): " + list);

        System.out.println("7 contains(Java): " + list.contains("Java"));
        System.out.println("8 containsAll(Java, Spring): " + list.containsAll(Arrays.asList("Java", "Spring")));
        System.out.println("9 indexOf(Java): " + list.indexOf("Java"));
        System.out.println("10 lastIndexOf(Java): " + list.lastIndexOf("Java"));
        System.out.println("11 size(): " + list.size());
        System.out.println("12 isEmpty(): " + list.isEmpty());

        System.out.println("13 toArray(): " + Arrays.toString(list.toArray()));
        System.out.println("14 toArray(new String[0]): " + Arrays.toString(list.toArray(new String[0])));

        System.out.print("15 forEach(): ");
        list.forEach(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.print("16 iterator(): ");
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) System.out.print(itr.next() + " | ");
        System.out.println();

        System.out.print("17 listIterator(): ");
        ListIterator<String> listItr = list.listIterator();
        while (listItr.hasNext()) System.out.print(listItr.next() + " | ");
        System.out.println();

        System.out.print("18 listIterator(3): ");
        ListIterator<String> indexItr = list.listIterator(3);
        while (indexItr.hasNext()) System.out.print(indexItr.next() + " | ");
        System.out.println();

        System.out.println("19 subList(1, 4): " + list.subList(1, 4));

        ArrayList<String> cloned = (ArrayList<String>) list.clone();
        System.out.println("20 clone(): " + cloned);

        list.sort(Comparator.naturalOrder());
        System.out.println("21 sort(naturalOrder): " + list);

        list.sort(Comparator.reverseOrder());
        System.out.println("22 sort(reverseOrder): " + list);

        list.replaceAll(String::toUpperCase);
        System.out.println("23 replaceAll(toUpperCase): " + list);

        list.remove("JAVA");
        System.out.println("24 remove(Object JAVA): " + list);

        list.remove(0);
        System.out.println("25 remove(index 0): " + list);

        list.removeIf(e -> e.startsWith("R"));
        System.out.println("26 removeIf(startsWith R): " + list);

        list.retainAll(Arrays.asList("SPRING", "KAFKA", "DOCKER"));
        System.out.println("27 retainAll(): " + list);

        list.removeAll(Arrays.asList("DOCKER"));
        System.out.println("28 removeAll(DOCKER): " + list);

        list.ensureCapacity(50);
        System.out.println("29 ensureCapacity(50): internal capacity ensured");

        list.trimToSize();
        System.out.println("30 trimToSize(): internal capacity trimmed");

        System.out.print("31 spliterator(): ");
        Spliterator<String> sp = list.spliterator();
        sp.forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("32 stream().count(): " + list.stream().count());
        System.out.println("33 parallelStream().count(): " + list.parallelStream().count());
        System.out.println("34 hashCode(): " + list.hashCode());
        System.out.println("35 equals(cloned): " + list.equals(cloned));

        list.clear();
        System.out.println("36 clear(): " + list);
    }
}

/*
SAMPLE OUTPUT:

1 add(): [Java, Spring, Kafka, Java]
2 add(index, element): [Java, Microservices, Spring, Kafka, Java]
3 addAll(Collection): [Java, Microservices, Spring, Kafka, Java, Docker, Kubernetes]
4 addAll(index, Collection): [Java, Microservices, REST, RabbitMQ, Spring, Kafka, Java, Docker, Kubernetes]
5 get(2): REST
6 set(2, REST API): [Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Java, Docker, Kubernetes]
7 contains(Java): true
8 containsAll(Java, Spring): true
9 indexOf(Java): 0
10 lastIndexOf(Java): 6
11 size(): 9
12 isEmpty(): false
13 toArray(): [Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Java, Docker, Kubernetes]
14 toArray(new String[0]): [Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Java, Docker, Kubernetes]
15 forEach(): Java | Microservices | REST API | RabbitMQ | Spring | Kafka | Java | Docker | Kubernetes |
16 iterator(): Java | Microservices | REST API | RabbitMQ | Spring | Kafka | Java | Docker | Kubernetes |
17 listIterator(): Java | Microservices | REST API | RabbitMQ | Spring | Kafka | Java | Docker | Kubernetes |
18 listIterator(3): RabbitMQ | Spring | Kafka | Java | Docker | Kubernetes |
19 subList(1, 4): [Microservices, REST API, RabbitMQ]
20 clone(): [Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Java, Docker, Kubernetes]
21 sort(naturalOrder): [Docker, Java, Java, Kafka, Kubernetes, Microservices, REST API, RabbitMQ, Spring]
22 sort(reverseOrder): [Spring, RabbitMQ, REST API, Microservices, Kubernetes, Kafka, Java, Java, Docker]
23 replaceAll(toUpperCase): [SPRING, RABBITMQ, REST API, MICROSERVICES, KUBERNETES, KAFKA, JAVA, JAVA, DOCKER]
24 remove(Object JAVA): [SPRING, RABBITMQ, REST API, MICROSERVICES, KUBERNETES, KAFKA, JAVA, DOCKER]
25 remove(index 0): [RABBITMQ, REST API, MICROSERVICES, KUBERNETES, KAFKA, JAVA, DOCKER]
26 removeIf(startsWith R): [MICROSERVICES, KUBERNETES, KAFKA, JAVA, DOCKER]
27 retainAll(): [KAFKA, DOCKER]
28 removeAll(DOCKER): [KAFKA]
29 ensureCapacity(50): internal capacity ensured
30 trimToSize(): internal capacity trimmed
31 spliterator(): KAFKA |
32 stream().count(): 1
33 parallelStream().count(): 1
34 hashCode(): 71270181
35 equals(cloned): false
36 clear(): []

*/
