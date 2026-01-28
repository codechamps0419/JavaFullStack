/* @Author: Dynamic Duo Learners */

import java.util.*;
import java.util.concurrent.*;

public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception {

        BlockingQueue<String> queue =
                new ArrayBlockingQueue<>(5);

        // add()
        queue.add("Java");
        queue.add("Spring");

        System.out.println("1. add(): " + queue);

        // offer()
        queue.offer("Kafka");

        System.out.println("2. offer(): " + queue);

        // put()
        queue.put("Redis");

        System.out.println("3. put(): " + queue);

        // add more
        queue.offer("Docker");

        System.out.println("4. after more additions: "
                + queue);

        // peek()
        System.out.println("5. peek(): "
                + queue.peek());

        // element()
        System.out.println("6. element(): "
                + queue.element());

        // contains()
        System.out.println("7. contains(Spring): "
                + queue.contains("Spring"));

        // containsAll()
        System.out.println("8. containsAll(Java, Kafka): "
                + queue.containsAll(
                Arrays.asList("Java", "Kafka")));

        // size()
        System.out.println("9. size(): "
                + queue.size());

        // remainingCapacity()
        System.out.println("10. remainingCapacity(): "
                + queue.remainingCapacity());

        // iterator()
        System.out.print("11. iterator(): ");

        Iterator<String> itr = queue.iterator();

        while (itr.hasNext()) {
            System.out.print(itr.next() + " | ");
        }

        System.out.println();

        // forEach()
        System.out.print("12. forEach(): ");

        queue.forEach(e ->
                System.out.print(e + " | "));

        System.out.println();

        // toArray()
        System.out.println("13. toArray(): "
                + Arrays.toString(queue.toArray()));

        // toArray(T[])
        System.out.println("14. toArray(new String[0]): "
                + Arrays.toString(
                queue.toArray(new String[0])));

        // poll()
        System.out.println("15. poll(): "
                + queue.poll());

        System.out.println("16. after poll(): "
                + queue);

        // take()
        System.out.println("17. take(): "
                + queue.take());

        System.out.println("18. after take(): "
                + queue);

        // remove()
        queue.remove("Redis");

        System.out.println("19. remove(Redis): "
                + queue);

        // removeAll()
        queue.removeAll(Arrays.asList("Docker"));

        System.out.println("20. removeAll(Docker): "
                + queue);

        // add again
        queue.offer("RabbitMQ");
        queue.offer("MongoDB");
        queue.offer("Kubernetes");

        System.out.println("21. add more data: "
                + queue);

        // retainAll()
        queue.retainAll(
                Arrays.asList(
                        "Kafka",
                        "RabbitMQ"));

        System.out.println("22. retainAll(): "
                + queue);

        // removeIf()
        queue.removeIf(
                e -> e.startsWith("R"));

        System.out.println("23. removeIf(startsWith R): "
                + queue);

        // add again
        queue.offer("Java");
        queue.offer("Spring");

        System.out.println("24. add again: "
                + queue);

        // drainTo()
        List<String> drained =
                new ArrayList<>();

        queue.drainTo(drained);

        System.out.println("25. drainTo(): "
                + drained);

        System.out.println("26. queue after drainTo(): "
                + queue);

        // spliterator()
        queue.offer("Docker");
        queue.offer("Kafka");

        System.out.print("27. spliterator(): ");

        queue.spliterator()
                .forEachRemaining(
                        e -> System.out.print(
                                e + " | "));

        System.out.println();

        // stream()
        System.out.println("28. stream().count(): "
                + queue.stream().count());

        // parallelStream()
        System.out.println("29. parallelStream().count(): "
                + queue.parallelStream().count());

        // hashCode()
        System.out.println("30. hashCode(): "
                + queue.hashCode());

        // clear()
        queue.clear();

        System.out.println("31. clear(): "
                + queue);

        // offer when full example
        BlockingQueue<Integer> smallQueue =
                new ArrayBlockingQueue<>(2);

        smallQueue.offer(1);
        smallQueue.offer(2);

        boolean result = smallQueue.offer(3);

        System.out.println("32. offer() on full queue: "
                + result);

        // poll empty queue
        BlockingQueue<Integer> emptyQueue =
                new ArrayBlockingQueue<>(2);

        System.out.println("33. poll() on empty queue: "
                + emptyQueue.poll());

        // peek empty queue
        System.out.println("34. peek() on empty queue: "
                + emptyQueue.peek());

        // exception examples
        try {
            emptyQueue.element();
        } catch (NoSuchElementException e) {
            System.out.println(
                    "35. element() on empty queue throws NoSuchElementException");
        }

        try {
            emptyQueue.remove();
        } catch (NoSuchElementException e) {
            System.out.println(
                    "36. remove() on empty queue throws NoSuchElementException");
        }
    }
}

/*
SAMPLE OUTPUT:

1. add(): [Java, Spring]
2. offer(): [Java, Spring, Kafka]
3. put(): [Java, Spring, Kafka, Redis]
4. after more additions: [Java, Spring, Kafka, Redis, Docker]
5. peek(): Java
6. element(): Java
7. contains(Spring): true
8. containsAll(Java, Kafka): true
9. size(): 5
10. remainingCapacity(): 0
11. iterator(): Java | Spring | Kafka | Redis | Docker |
12. forEach(): Java | Spring | Kafka | Redis | Docker |
13. toArray(): [Java, Spring, Kafka, Redis, Docker]
14. toArray(new String[0]): [Java, Spring, Kafka, Redis, Docker]
15. poll(): Java
16. after poll(): [Spring, Kafka, Redis, Docker]
17. take(): Spring
18. after take(): [Kafka, Redis, Docker]
19. remove(Redis): [Kafka, Docker]
20. removeAll(Docker): [Kafka]
21. add more data: [Kafka, RabbitMQ, MongoDB, Kubernetes]
22. retainAll(): [Kafka, RabbitMQ]
23. removeIf(startsWith R): [Kafka]
24. add again: [Kafka, Java, Spring]
25. drainTo(): [Kafka, Java, Spring]
26. queue after drainTo(): []
27. spliterator(): Docker | Kafka |
28. stream().count(): 2
29. parallelStream().count(): 2
30. hashCode(): 1975012498
31. clear(): []
32. offer() on full queue: false
33. poll() on empty queue: null
34. peek() on empty queue: null
35. element() on empty queue throws NoSuchElementException
36. remove() on empty queue throws NoSuchElementException

*/
