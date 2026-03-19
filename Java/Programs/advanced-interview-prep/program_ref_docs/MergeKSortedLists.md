# 34. MergeKSortedLists

**Author:** CodeChamps0419  
**Category:** `heap`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Min heap of current list heads  

## 1. Problem statement

Merge k sorted linked lists into one sorted list.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

The heap tracks the smallest available element among k list heads.

**Time complexity:** `O(N log k)`  
**Auxiliary space complexity:** `O(k)`  

The executable implementation is in `src/MergeKSortedLists.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node merge(Node[] lists) {
    PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));
    for (Node node : lists) if (node != null) heap.offer(node);
    Node dummy = new Node(0), tail = dummy;
    while (!heap.isEmpty()) {
        Node node = heap.poll();
        if (node.next != null) heap.offer(node.next);
        tail.next = node;
        tail = node;
    }
    tail.next = null;
    return dummy.next;
}
```

## 4. Input and expected output

```text
1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6 -> null
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** The heap tracks the smallest available element among k list heads.

## 6. Edge cases and common beginner pitfalls

Input nodes are relinked, not copied.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Min heap of current list heads**. The heap tracks the smallest available element among k list heads. The running time is **O(N log k)**, and the extra-space requirement is **O(k)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Compare divide-and-conquer pairwise merging.

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build heap/src/MergeKSortedLists.java
java -cp build heap.MergeKSortedLists
```

© Dynamic Duo Learners — Program author: CodeChamps0419
