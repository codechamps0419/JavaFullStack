import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("Java");
        list.add("Spring");
        list.add("Kafka");
        System.out.println("1 add(): " + list);

        list.add(1, "Microservices");
        System.out.println("2 add(index, element): " + list);

        list.addAll(Arrays.asList("Docker", "Kubernetes"));
        list.addAll(2, Arrays.asList("REST", "RabbitMQ"));
        System.out.println("3 addAll(): " + list);

        System.out.println("4 get(2): " + list.get(2));
        list.set(2, "REST API");
        System.out.println("5 set(): " + list);

        list.addFirst("FIRST");
        list.addLast("LAST");
        System.out.println("6 addFirst/addLast: " + list);

        System.out.println("7 getFirst(): " + list.getFirst());
        System.out.println("8 getLast(): " + list.getLast());
        System.out.println("9 peek(): " + list.peek());
        System.out.println("10 peekFirst(): " + list.peekFirst());
        System.out.println("11 peekLast(): " + list.peekLast());

        list.offer("OFFER");
        list.offerFirst("OFFER_FIRST");
        list.offerLast("OFFER_LAST");
        System.out.println("12 offer/offerFirst/offerLast: " + list);

        list.push("PUSH");
        System.out.println("13 push(): " + list);

        System.out.println("14 pop(): " + list.pop());
        System.out.println("15 poll(): " + list.poll());
        System.out.println("16 pollFirst(): " + list.pollFirst());
        System.out.println("17 pollLast(): " + list.pollLast());
        System.out.println("18 after poll operations: " + list);

        System.out.println("19 element(): " + list.element());
        System.out.println("20 contains(Java): " + list.contains("Java"));
        System.out.println("21 indexOf(Java): " + list.indexOf("Java"));
        System.out.println("22 lastIndexOf(Java): " + list.lastIndexOf("Java"));
        System.out.println("23 size(): " + list.size());
        System.out.println("24 isEmpty(): " + list.isEmpty());

        list.add("Java");
        list.add("Spring");
        System.out.println("25 removeFirstOccurrence(Java): " + list.removeFirstOccurrence("Java"));
        System.out.println("26 removeLastOccurrence(Spring): " + list.removeLastOccurrence("Spring"));
        System.out.println("27 after occurrence removals: " + list);

        System.out.print("28 iterator(): ");
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) System.out.print(itr.next() + " | ");
        System.out.println();

        System.out.print("29 descendingIterator(): ");
        Iterator<String> dItr = list.descendingIterator();
        while (dItr.hasNext()) System.out.print(dItr.next() + " | ");
        System.out.println();

        System.out.print("30 listIterator(): ");
        ListIterator<String> lItr = list.listIterator();
        while (lItr.hasNext()) System.out.print(lItr.next() + " | ");
        System.out.println();

        System.out.println("31 subList(1, 4): " + list.subList(1, 4));
        System.out.println("32 toArray(): " + Arrays.toString(list.toArray()));
        System.out.println("33 clone(): " + list.clone());

        list.sort(Comparator.naturalOrder());
        System.out.println("34 sort(naturalOrder): " + list);

        list.replaceAll(String::toUpperCase);
        System.out.println("35 replaceAll(toUpperCase): " + list);

        list.remove("JAVA");
        list.remove(0);
        System.out.println("36 remove(Object) + remove(index): " + list);

        System.out.println("37 removeFirst(): " + list.removeFirst());
        System.out.println("38 removeLast(): " + list.removeLast());
        System.out.println("39 after removeFirst/removeLast: " + list);

        list.removeIf(e -> e.startsWith("R"));
        System.out.println("40 removeIf(startsWith R): " + list);

        list.retainAll(Arrays.asList("KAFKA", "DOCKER", "SPRING"));
        System.out.println("41 retainAll(): " + list);

        list.removeAll(Arrays.asList("DOCKER"));
        System.out.println("42 removeAll(): " + list);

        System.out.print("43 spliterator(): ");
        list.spliterator().forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("44 stream().count(): " + list.stream().count());
        System.out.println("45 hashCode(): " + list.hashCode());

        list.clear();
        System.out.println("46 clear(): " + list);
    }
}


/*
SAMPLE OUTPUT:

1 add(): [Java, Spring, Kafka]
2 add(index, element): [Java, Microservices, Spring, Kafka]
3 addAll(): [Java, Microservices, REST, RabbitMQ, Spring, Kafka, Docker, Kubernetes]
4 get(2): REST
5 set(): [Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Docker, Kubernetes]
6 addFirst/addLast: [FIRST, Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Docker, Kubernetes, LAST]
7 getFirst(): FIRST
8 getLast(): LAST
9 peek(): FIRST
10 peekFirst(): FIRST
11 peekLast(): LAST
12 offer/offerFirst/offerLast: [OFFER_FIRST, FIRST, Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Docker, Kubernetes, LAST, OFFER, OFFER_LAST]
13 push(): [PUSH, OFFER_FIRST, FIRST, Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Docker, Kubernetes, LAST, OFFER, OFFER_LAST]
14 pop(): PUSH
15 poll(): OFFER_FIRST
16 pollFirst(): FIRST
17 pollLast(): OFFER_LAST
18 after poll operations: [Java, Microservices, REST API, RabbitMQ, Spring, Kafka, Docker, Kubernetes, LAST, OFFER]
19 element(): Java
20 contains(Java): true
21 indexOf(Java): 0
22 lastIndexOf(Java): 0
23 size(): 10
24 isEmpty(): false
25 removeFirstOccurrence(Java): true
26 removeLastOccurrence(Spring): true
27 after occurrence removals: [Microservices, REST API, RabbitMQ, Spring, Kafka, Docker, Kubernetes, LAST, OFFER, Java]
28 iterator(): Microservices | REST API | RabbitMQ | Spring | Kafka | Docker | Kubernetes | LAST | OFFER | Java |
29 descendingIterator(): Java | OFFER | LAST | Kubernetes | Docker | Kafka | Spring | RabbitMQ | REST API | Microservices |
30 listIterator(): Microservices | REST API | RabbitMQ | Spring | Kafka | Docker | Kubernetes | LAST | OFFER | Java |
31 subList(1, 4): [REST API, RabbitMQ, Spring]
32 toArray(): [Microservices, REST API, RabbitMQ, Spring, Kafka, Docker, Kubernetes, LAST, OFFER, Java]
33 clone(): [Microservices, REST API, RabbitMQ, Spring, Kafka, Docker, Kubernetes, LAST, OFFER, Java]
34 sort(naturalOrder): [Docker, Java, Kafka, Kubernetes, LAST, Microservices, OFFER, REST API, RabbitMQ, Spring]
35 replaceAll(toUpperCase): [DOCKER, JAVA, KAFKA, KUBERNETES, LAST, MICROSERVICES, OFFER, REST API, RABBITMQ, SPRING]
36 remove(Object) + remove(index): [KAFKA, KUBERNETES, LAST, MICROSERVICES, OFFER, REST API, RABBITMQ, SPRING]
37 removeFirst(): KAFKA
38 removeLast(): SPRING
39 after removeFirst/removeLast: [KUBERNETES, LAST, MICROSERVICES, OFFER, REST API, RABBITMQ]
40 removeIf(startsWith R): [KUBERNETES, LAST, MICROSERVICES, OFFER]
41 retainAll(): []
42 removeAll(): []
43 spliterator():
44 stream().count(): 0
45 hashCode(): 1
46 clear(): []

*/
