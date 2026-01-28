import java.util.*;

public class TreeSetDemo {

    public static void main(String[] args) {

        TreeSet<Integer> set = new TreeSet<>(); //It will sort elements by asc order
	//TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder()); //To sort elements in reverse/desc order

        set.add(30);
        set.add(10);
        set.add(20);
        set.add(40);
        set.add(50);
        set.add(20); // duplicate ignored
        System.out.println("1. add(): " + set);

        set.addAll(Arrays.asList(60, 70, 80));
        System.out.println("2. addAll(): " + set);

        System.out.println("3. contains(30): " + set.contains(30));
        System.out.println("4. containsAll(10, 20): " +
                set.containsAll(Arrays.asList(10, 20)));
        System.out.println("5. size(): " + set.size());
        System.out.println("6. isEmpty(): " + set.isEmpty());

        System.out.println("7. first(): " + set.first());
        System.out.println("8. last(): " + set.last());

        System.out.println("9. lower(30): " + set.lower(30));
        System.out.println("10. floor(30): " + set.floor(30));
        System.out.println("11. ceiling(35): " + set.ceiling(35));
        System.out.println("12. higher(30): " + set.higher(30));

        System.out.println("13. headSet(40): " + set.headSet(40));
        System.out.println("14. headSet(40, true): " + set.headSet(40, true));

        System.out.println("15. tailSet(40): " + set.tailSet(40));
        System.out.println("16. tailSet(40, false): " + set.tailSet(40, false));

        System.out.println("17. subSet(20, 70): " + set.subSet(20, 70));
        System.out.println("18. subSet(20,true,70,true): " +
                set.subSet(20, true, 70, true));

        System.out.println("19. descendingSet(): " + set.descendingSet());

        System.out.print("20. descendingIterator(): ");
        Iterator<Integer> descItr = set.descendingIterator();
        while (descItr.hasNext()) {
            System.out.print(descItr.next() + " | ");
        }
        System.out.println();

        NavigableSet<Integer> descending = set.descendingSet();
        System.out.println("21. descendingSet first(): " + descending.first());

        System.out.println("22. pollFirst(): " + set.pollFirst());
        System.out.println("23. pollLast(): " + set.pollLast());
        System.out.println("24. after pollFirst/pollLast: " + set);

        System.out.print("25. iterator(): ");
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " | ");
        }
        System.out.println();

        System.out.print("26. forEach(): ");
        set.forEach(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("27. toArray(): " + Arrays.toString(set.toArray()));
        System.out.println("28. toArray(new Integer[0]): " +
                Arrays.toString(set.toArray(new Integer[0])));

        set.remove(40);
        System.out.println("29. remove(40): " + set);

        set.removeAll(Arrays.asList(60));
        System.out.println("30. removeAll(60): " + set);

        set.retainAll(Arrays.asList(20, 30, 50));
        System.out.println("31. retainAll(20,30,50): " + set);

        set.removeIf(e -> e > 30);
        System.out.println("32. removeIf(greater than 30): " + set);

        TreeSet<Integer> cloned = (TreeSet<Integer>) set.clone();
        System.out.println("33. clone(): " + cloned);

        System.out.println("34. comparator(): " + set.comparator());

        System.out.print("35. spliterator(): ");
        set.spliterator().forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("36. stream().count(): " + set.stream().count());
        System.out.println("37. parallelStream().count(): " + set.parallelStream().count());
        System.out.println("38. equals(cloned): " + set.equals(cloned));
        System.out.println("39. hashCode(): " + set.hashCode());

        set.clear();
        System.out.println("40. clear(): " + set);
    }
}

/*
SAMPLE OUTPUT:

1. add(): [10, 20, 30, 40, 50]
2. addAll(): [10, 20, 30, 40, 50, 60, 70, 80]
3. contains(30): true
4. containsAll(10, 20): true
5. size(): 8
6. isEmpty(): false
7. first(): 10
8. last(): 80
9. lower(30): 20
10. floor(30): 30
11. ceiling(35): 40
12. higher(30): 40
13. headSet(40): [10, 20, 30]
14. headSet(40, true): [10, 20, 30, 40]
15. tailSet(40): [40, 50, 60, 70, 80]
16. tailSet(40, false): [50, 60, 70, 80]
17. subSet(20, 70): [20, 30, 40, 50, 60]
18. subSet(20,true,70,true): [20, 30, 40, 50, 60, 70]
19. descendingSet(): [80, 70, 60, 50, 40, 30, 20, 10]
20. descendingIterator(): 80 | 70 | 60 | 50 | 40 | 30 | 20 | 10 |
21. descendingSet first(): 80
22. pollFirst(): 10
23. pollLast(): 80
24. after pollFirst/pollLast: [20, 30, 40, 50, 60, 70]
25. iterator(): 20 | 30 | 40 | 50 | 60 | 70 |
26. forEach(): 20 | 30 | 40 | 50 | 60 | 70 |
27. toArray(): [20, 30, 40, 50, 60, 70]
28. toArray(new Integer[0]): [20, 30, 40, 50, 60, 70]
29. remove(40): [20, 30, 50, 60, 70]
30. removeAll(60): [20, 30, 50, 70]
31. retainAll(20,30,50): [20, 30, 50]
32. removeIf(greater than 30): [20, 30]
33. clone(): [20, 30]
34. comparator(): null
35. spliterator(): 20 | 30 |
36. stream().count(): 2
37. parallelStream().count(): 2
38. equals(cloned): true
39. hashCode(): 50
40. clear(): []

Note:
TreeSet keeps elements sorted and does not allow null in modern Java.
*/
