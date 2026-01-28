import java.util.*;

public class PriorityQueueDemo {

    public static void main(String[] args) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(30);
        queue.offer(10);
        queue.offer(20);
        queue.offer(40);
        queue.offer(50);
        System.out.println("1. add()/offer(): " + queue);

        queue.addAll(Arrays.asList(5, 60));
        System.out.println("2. addAll(): " + queue);

        System.out.println("3. peek(): " + queue.peek());
        System.out.println("4. element(): " + queue.element());

        System.out.println("5. poll(): " + queue.poll());
        System.out.println("6. remove(): " + queue.remove());
        System.out.println("7. after poll/remove: " + queue);

        System.out.println("8. contains(30): " + queue.contains(30));
        System.out.println("9. containsAll(30,40): " +
                queue.containsAll(Arrays.asList(30, 40)));
        System.out.println("10. size(): " + queue.size());
        System.out.println("11. isEmpty(): " + queue.isEmpty());

        System.out.print("12. iterator(): ");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " | ");
        }
        System.out.println();

        System.out.print("13. forEach(): ");
        queue.forEach(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("14. toArray(): " + Arrays.toString(queue.toArray()));
        System.out.println("15. toArray(new Integer[0]): " +
                Arrays.toString(queue.toArray(new Integer[0])));

        queue.remove(40);
        System.out.println("16. remove(Object 40): " + queue);

        queue.removeAll(Arrays.asList(50));
        System.out.println("17. removeAll(50): " + queue);

        queue.retainAll(Arrays.asList(20, 30, 60));
        System.out.println("18. retainAll(20,30,60): " + queue);

        queue.removeIf(e -> e > 30);
        System.out.println("19. removeIf(greater than 30): " + queue);

        System.out.print("20. spliterator(): ");
        queue.spliterator().forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("21. stream().count(): " + queue.stream().count());
        System.out.println("22. parallelStream().count(): " + queue.parallelStream().count());
        System.out.println("23. comparator(): " + queue.comparator());
        System.out.println("24. hashCode(): " + queue.hashCode());

        queue.clear();
        System.out.println("25. clear(): " + queue);

        System.out.println("26. peek() on empty queue: " + queue.peek());
        System.out.println("27. poll() on empty queue: " + queue.poll());

        try {
            queue.element();
        } catch (NoSuchElementException e) {
            System.out.println("28. element() on empty queue throws NoSuchElementException");
        }

        try {
            queue.remove();
        } catch (NoSuchElementException e) {
            System.out.println("29. remove() on empty queue throws NoSuchElementException");
        }
    }
}

/*
SAMPLE OUTPUT:
1. add()/offer(): [10, 30, 20, 40, 50]
2. addAll(): [5, 30, 10, 40, 50, 20, 60]
...
29. remove() on empty queue throws NoSuchElementException

Note:
PriorityQueue internal display order may differ, but poll() always returns priority element.
*/
