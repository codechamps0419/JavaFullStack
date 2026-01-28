import java.util.*;

public class StackDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("Java");
        stack.push("Spring");
        stack.push("Kafka");
        System.out.println("1 push(): " + stack);

        stack.add("Docker");
        stack.add(1, "Microservices");
        stack.addElement("Kubernetes");
        stack.addAll(Arrays.asList("Redis", "AWS"));
        System.out.println("2 add/addElement/addAll: " + stack);

        System.out.println("3 peek(): " + stack.peek());
        System.out.println("4 pop(): " + stack.pop());
        System.out.println("5 after pop(): " + stack);
        System.out.println("6 search(Spring): " + stack.search("Spring"));
        System.out.println("7 empty(): " + stack.empty());

        System.out.println("8 get(2): " + stack.get(2));
        System.out.println("9 elementAt(2): " + stack.elementAt(2));
        System.out.println("10 firstElement(): " + stack.firstElement());
        System.out.println("11 lastElement(): " + stack.lastElement());

        stack.set(2, "Kafka Updated");
        stack.setElementAt("Spring Boot", 1);
        stack.insertElementAt("REST", 2);
        System.out.println("12 set/setElementAt/insertElementAt: " + stack);

        System.out.println("13 contains(Java): " + stack.contains("Java"));
        System.out.println("14 indexOf(Java): " + stack.indexOf("Java"));
        System.out.println("15 lastIndexOf(Java): " + stack.lastIndexOf("Java"));
        System.out.println("16 size(): " + stack.size());
        System.out.println("17 capacity(): " + stack.capacity());

        stack.ensureCapacity(40);
        System.out.println("18 ensureCapacity(40), capacity(): " + stack.capacity());

        stack.trimToSize();
        System.out.println("19 trimToSize(), capacity(): " + stack.capacity());

        System.out.println("20 toArray(): " + Arrays.toString(stack.toArray()));
        System.out.println("21 toArray(new String[0]): " + Arrays.toString(stack.toArray(new String[0])));

        System.out.print("22 elements() Enumeration: ");
        Enumeration<String> en = stack.elements();
        while (en.hasMoreElements()) System.out.print(en.nextElement() + " | ");
        System.out.println();

        System.out.print("23 iterator(): ");
        Iterator<String> itr = stack.iterator();
        while (itr.hasNext()) System.out.print(itr.next() + " | ");
        System.out.println();

        System.out.print("24 listIterator(): ");
        ListIterator<String> lItr = stack.listIterator();
        while (lItr.hasNext()) System.out.print(lItr.next() + " | ");
        System.out.println();

        System.out.println("25 subList(1, 4): " + stack.subList(1, 4));
        System.out.println("26 clone(): " + stack.clone());

        stack.sort(Comparator.naturalOrder());
        System.out.println("27 sort(naturalOrder): " + stack);

        stack.replaceAll(String::toUpperCase);
        System.out.println("28 replaceAll(toUpperCase): " + stack);

        stack.remove("JAVA");
        stack.remove(0);
        stack.removeElement("DOCKER");
        stack.removeElementAt(0);
        System.out.println("29 remove variants: " + stack);

        stack.removeIf(e -> e.startsWith("R"));
        System.out.println("30 removeIf(startsWith R): " + stack);

        stack.retainAll(Arrays.asList("SPRING BOOT", "KAFKA UPDATED", "AWS"));
        System.out.println("31 retainAll(): " + stack);

        stack.removeAll(Arrays.asList("AWS"));
        System.out.println("32 removeAll(): " + stack);

        System.out.print("33 spliterator(): ");
        stack.spliterator().forEachRemaining(e -> System.out.print(e + " | "));
        System.out.println();

        System.out.println("34 stream().count(): " + stack.stream().count());
        System.out.println("35 parallelStream().count(): " + stack.parallelStream().count());
        System.out.println("36 hashCode(): " + stack.hashCode());

        stack.removeAllElements();
        System.out.println("37 removeAllElements(): " + stack);

        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("38 pop() on empty stack: EmptyStackException");
        }

        stack.clear();
        System.out.println("39 clear(): " + stack);
    }
}

/*
SAMPLE OUTPUT:

1 push(): [Java, Spring, Kafka]
2 add/addElement/addAll: [Java, Microservices, Spring, Kafka, Docker, Kubernetes, Redis, AWS]
3 peek(): AWS
4 pop(): AWS
5 after pop(): [Java, Microservices, Spring, Kafka, Docker, Kubernetes, Redis]
6 search(Spring): 5
7 empty(): false
8 get(2): Spring
9 elementAt(2): Spring
10 firstElement(): Java
11 lastElement(): Redis
12 set/setElementAt/insertElementAt: [Java, Spring Boot, REST, Kafka Updated, Kafka, Docker, Kubernetes, Redis]
13 contains(Java): true
14 indexOf(Java): 0
15 lastIndexOf(Java): 0
16 size(): 8
17 capacity(): 10
18 ensureCapacity(40), capacity(): 40
19 trimToSize(), capacity(): 8
20 toArray(): [Java, Spring Boot, REST, Kafka Updated, Kafka, Docker, Kubernetes, Redis]
21 toArray(new String[0]): [Java, Spring Boot, REST, Kafka Updated, Kafka, Docker, Kubernetes, Redis]
22 elements() Enumeration: Java | Spring Boot | REST | Kafka Updated | Kafka | Docker | Kubernetes | Redis |
23 iterator(): Java | Spring Boot | REST | Kafka Updated | Kafka | Docker | Kubernetes | Redis |
24 listIterator(): Java | Spring Boot | REST | Kafka Updated | Kafka | Docker | Kubernetes | Redis |
25 subList(1, 4): [Spring Boot, REST, Kafka Updated]
26 clone(): [Java, Spring Boot, REST, Kafka Updated, Kafka, Docker, Kubernetes, Redis]
27 sort(naturalOrder): [Docker, Java, Kafka, Kafka Updated, Kubernetes, REST, Redis, Spring Boot]
28 replaceAll(toUpperCase): [DOCKER, JAVA, KAFKA, KAFKA UPDATED, KUBERNETES, REST, REDIS, SPRING BOOT]
29 remove variants: [KAFKA UPDATED, KUBERNETES, REST, REDIS, SPRING BOOT]
30 removeIf(startsWith R): [KAFKA UPDATED, KUBERNETES, SPRING BOOT]
31 retainAll(): [KAFKA UPDATED, SPRING BOOT]
32 removeAll(): [KAFKA UPDATED, SPRING BOOT]
33 spliterator(): KAFKA UPDATED | SPRING BOOT |
34 stream().count(): 2
35 parallelStream().count(): 2
36 hashCode(): -722731131
37 removeAllElements(): []
38 pop() on empty stack: EmptyStackException
39 clear(): []

*/
