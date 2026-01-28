import java.util.*;

public class ArrayDequeDemo {

    public static void main(String[] args) {

        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.add("Java");
        deque.offer("Spring");
        deque.addFirst("Kafka");
        deque.addLast("Redis");
        deque.offerFirst("Docker");
        deque.offerLast("Kubernetes");
        System.out.println("1. add/offer/addFirst/addLast/offerFirst/offerLast: " + deque);

        deque.push("PUSHED");
        System.out.println("2. push(): " + deque);

        System.out.println("3. getFirst(): " + deque.getFirst());
        System.out.println("4. getLast(): " + deque.getLast());

        System.out.println("5. peek(): " + deque.peek());
        System.out.println("6. peekFirst(): " + deque.peekFirst());
        System.out.println("7. peekLast(): " + deque.peekLast());

        System.out.println("8. element(): " + deque.element());

        System.out.println("9. pop(): " + deque.pop());
        System.out.println("10. poll(): " + deque.poll());
        System.out.println("11. pollFirst(): " + deque.pollFirst());
        System.out.println("12. pollLast(): " + deque.pollLast());
        System.out.println("13. after poll methods: " + deque);

        deque.addAll(Arrays.asList("RabbitMQ", "MongoDB", "RabbitMQ"));
        System.out.println("14. addAll(): " + deque);

        System.out.println("15. contains(Spring): " + deque.contains("Spring"));
        System.out.println("16. containsAll(Spring, Redis): " +
                deque.containsAll(Arrays.asList("Spring", "Redis")));
        System.out.println("17. size(): " + deque.size());
        System.out.println("18. isEmpty(): " + deque.isEmpty());

        System.out.println("19. removeFirstOccurrence(RabbitMQ): " +
                deque.removeFirstOccurrence("RabbitMQ"));
        System.out.println("20. removeLastOccurrence(RabbitMQ): " +
                deque.removeLastOccurrence("RabbitMQ"));
        System.out.println("21. after occurrence removes: " + deque);

        System.out.print("22. iterator(): ");
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " | ");
        }
        System.out.println();

        System.out.print("23. descendingIterator(): ");
        Iterator<String> desc = deque.descendingIterator();
        while (desc.hasNext()) {
            System.out.print(desc.next() + " | ");
        }
        System.out.println();

        System.out.print("24. forEach(): ");
        deque.forEach(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("25. toArray(): " + Arrays.toString(deque.toArray()));
        System.out.println("26. toArray(new String[0]): " +
                Arrays.toString(deque.toArray(new String[0])));

        deque.remove("Spring");
        System.out.println("27. remove(Object Spring): " + deque);

        deque.removeAll(Arrays.asList("Redis"));
        System.out.println("28. removeAll(Redis): " + deque);

        deque.retainAll(Arrays.asList("Java", "MongoDB", "Kubernetes"));
        System.out.println("29. retainAll(Java, MongoDB, Kubernetes): " + deque);

        deque.removeIf(e -> e.startsWith("M"));
        System.out.println("30. removeIf(startsWith M): " + deque);

        ArrayDeque<String> cloned = deque.clone();
        System.out.println("31. clone(): " + cloned);

        System.out.print("32. spliterator(): ");
        deque.spliterator().forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("33. stream().count(): " + deque.stream().count());
        System.out.println("34. parallelStream().count(): " + deque.parallelStream().count());
        System.out.println("35. equals(cloned): " + deque.equals(cloned));
        System.out.println("36. hashCode(): " + deque.hashCode());

        deque.clear();
        System.out.println("37. clear(): " + deque);

        System.out.println("38. peekFirst() on empty: " + deque.peekFirst());
        System.out.println("39. pollFirst() on empty: " + deque.pollFirst());

        try {
            deque.getFirst();
        } catch (NoSuchElementException e) {
            System.out.println("40. getFirst() on empty throws NoSuchElementException");
        }
    }
}

/*
SAMPLE OUTPUT:

1. add/offer/addFirst/addLast/offerFirst/offerLast: [Docker, Kafka, Java, Spring, Redis, Kubernetes]
2. push(): [PUSHED, Docker, Kafka, Java, Spring, Redis, Kubernetes]
3. getFirst(): PUSHED
4. getLast(): Kubernetes
5. peek(): PUSHED
6. peekFirst(): PUSHED
7. peekLast(): Kubernetes
8. element(): PUSHED
9. pop(): PUSHED
10. poll(): Docker
11. pollFirst(): Kafka
12. pollLast(): Kubernetes
13. after poll methods: [Java, Spring, Redis]
14. addAll(): [Java, Spring, Redis, RabbitMQ, MongoDB, RabbitMQ]
15. contains(Spring): true
16. containsAll(Spring, Redis): true
17. size(): 6
18. isEmpty(): false
19. removeFirstOccurrence(RabbitMQ): true
20. removeLastOccurrence(RabbitMQ): true
21. after occurrence removes: [Java, Spring, Redis, MongoDB]
22. iterator(): Java | Spring | Redis | MongoDB |
23. descendingIterator(): MongoDB | Redis | Spring | Java |
24. forEach(): Java | Spring | Redis | MongoDB |
25. toArray(): [Java, Spring, Redis, MongoDB]
26. toArray(new String[0]): [Java, Spring, Redis, MongoDB]
27. remove(Object Spring): [Java, Redis, MongoDB]
28. removeAll(Redis): [Java, MongoDB]
29. retainAll(Java, MongoDB, Kubernetes): [Java, MongoDB]
30. removeIf(startsWith M): [Java]
31. clone(): [Java]
32. spliterator(): Java |
33. stream().count(): 1
34. parallelStream().count(): 1
35. equals(cloned): false
36. hashCode(): 1829164700
37. clear(): []
38. peekFirst() on empty: null
39. pollFirst() on empty: null
40. getFirst() on empty throws NoSuchElementException

Note:
ArrayDeque does not allow null.
*/
