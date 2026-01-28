import java.util.*;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        vector.add("Java");
        vector.add("Spring");
        vector.add("Kafka");
        vector.addElement("Docker");
        System.out.println("1 add/addElement: " + vector);

        vector.add(1, "Microservices");
        vector.addAll(Arrays.asList("Kubernetes", "Redis"));
        vector.addAll(2, Arrays.asList("REST", "RabbitMQ"));
        System.out.println("2 add(index)/addAll: " + vector);

        System.out.println("3 get(2): " + vector.get(2));
        System.out.println("4 elementAt(2): " + vector.elementAt(2));
        System.out.println("5 firstElement(): " + vector.firstElement());
        System.out.println("6 lastElement(): " + vector.lastElement());

        vector.set(2, "REST API");
        vector.setElementAt("RabbitMQ Updated", 3);
        vector.insertElementAt("INSERTED", 2);
        System.out.println("7 set/setElementAt/insertElementAt: " + vector);

        System.out.println("8 contains(Java): " + vector.contains("Java"));
        System.out.println("9 containsAll(Java, Spring): " + vector.containsAll(Arrays.asList("Java", "Spring")));
        System.out.println("10 indexOf(Java): " + vector.indexOf("Java"));
        System.out.println("11 indexOf(Java, 1): " + vector.indexOf("Java", 1));
        System.out.println("12 lastIndexOf(Java): " + vector.lastIndexOf("Java"));
        System.out.println("13 lastIndexOf(Java, 5): " + vector.lastIndexOf("Java", 5));
        System.out.println("14 size(): " + vector.size());
        System.out.println("15 capacity(): " + vector.capacity());
        System.out.println("16 isEmpty(): " + vector.isEmpty());

        vector.ensureCapacity(50);
        System.out.println("17 ensureCapacity(50), capacity(): " + vector.capacity());

        vector.trimToSize();
        System.out.println("18 trimToSize(), capacity(): " + vector.capacity());

        System.out.println("19 toArray(): " + Arrays.toString(vector.toArray()));
        System.out.println("20 toArray(new String[0]): " + Arrays.toString(vector.toArray(new String[0])));

        System.out.print("21 elements() Enumeration: ");
        Enumeration<String> en = vector.elements();
        while (en.hasMoreElements()) System.out.print(en.nextElement() + " | ");
        System.out.println();

        System.out.print("22 iterator(): ");
        Iterator<String> itr = vector.iterator();
        while (itr.hasNext()) System.out.print(itr.next() + " | ");
        System.out.println();

        System.out.print("23 listIterator(): ");
        ListIterator<String> lItr = vector.listIterator();
        while (lItr.hasNext()) System.out.print(lItr.next() + " | ");
        System.out.println();

        System.out.println("24 subList(1, 4): " + vector.subList(1, 4));
        System.out.println("25 clone(): " + vector.clone());

        vector.remove("Java");
        vector.remove(0);
        vector.removeElement("Docker");
        vector.removeElementAt(0);
        System.out.println("26 remove variants: " + vector);

        vector.removeIf(e -> e.startsWith("R"));
        System.out.println("27 removeIf(startsWith R): " + vector);

        vector.retainAll(Arrays.asList("Spring", "Kafka", "Kubernetes", "Redis"));
        System.out.println("28 retainAll(): " + vector);

        vector.removeAll(Arrays.asList("Redis"));
        System.out.println("29 removeAll(): " + vector);

        vector.sort(Comparator.naturalOrder());
        System.out.println("30 sort(naturalOrder): " + vector);

        vector.replaceAll(String::toUpperCase);
        System.out.println("31 replaceAll(toUpperCase): " + vector);

        System.out.print("32 spliterator(): ");
        vector.spliterator().forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("33 stream().count(): " + vector.stream().count());
        System.out.println("34 parallelStream().count(): " + vector.parallelStream().count());
        System.out.println("35 hashCode(): " + vector.hashCode());

        vector.setSize(5);
        System.out.println("36 setSize(5): " + vector);

        vector.removeAllElements();
        System.out.println("37 removeAllElements(): " + vector);

        vector.clear();
        System.out.println("38 clear(): " + vector);
    }
}

/*
SAMPLE OUTPUT:

1 add/addElement: [Java, Spring, Kafka, Docker]
2 add(index)/addAll: [Java, Microservices, REST, RabbitMQ, Spring, Kafka, Docker, Kubernetes, Redis]
3 get(2): REST
4 elementAt(2): REST
5 firstElement(): Java
6 lastElement(): Redis
7 set/setElementAt/insertElementAt: [Java, Microservices, INSERTED, REST API, RabbitMQ Updated, Spring, Kafka, Docker, Kubernetes, Redis]
8 contains(Java): true
9 containsAll(Java, Spring): true
10 indexOf(Java): 0
11 indexOf(Java, 1): -1
12 lastIndexOf(Java): 0
13 lastIndexOf(Java, 5): 0
14 size(): 10
15 capacity(): 10
16 isEmpty(): false
17 ensureCapacity(50), capacity(): 50
18 trimToSize(), capacity(): 10
19 toArray(): [Java, Microservices, INSERTED, REST API, RabbitMQ Updated, Spring, Kafka, Docker, Kubernetes, Redis]
20 toArray(new String[0]): [Java, Microservices, INSERTED, REST API, RabbitMQ Updated, Spring, Kafka, Docker, Kubernetes, Redis]
21 elements() Enumeration: Java | Microservices | INSERTED | REST API | RabbitMQ Updated | Spring | Kafka | Docker | Kubernetes | Redis |
22 iterator(): Java | Microservices | INSERTED | REST API | RabbitMQ Updated | Spring | Kafka | Docker | Kubernetes | Redis |
23 listIterator(): Java | Microservices | INSERTED | REST API | RabbitMQ Updated | Spring | Kafka | Docker | Kubernetes | Redis |
24 subList(1, 4): [Microservices, INSERTED, REST API]
25 clone(): [Java, Microservices, INSERTED, REST API, RabbitMQ Updated, Spring, Kafka, Docker, Kubernetes, Redis]
26 remove variants: [REST API, RabbitMQ Updated, Spring, Kafka, Kubernetes, Redis]
27 removeIf(startsWith R): [Spring, Kafka, Kubernetes]
28 retainAll(): [Spring, Kafka, Kubernetes]
29 removeAll(): [Spring, Kafka, Kubernetes]
30 sort(naturalOrder): [Kafka, Kubernetes, Spring]
31 replaceAll(toUpperCase): [KAFKA, KUBERNETES, SPRING]
32 spliterator(): KAFKA | KUBERNETES | SPRING |
33 stream().count(): 3
34 parallelStream().count(): 3
35 hashCode(): -200183444
36 setSize(5): [KAFKA, KUBERNETES, SPRING, null, null]
37 removeAllElements(): []
38 clear(): []

*/
