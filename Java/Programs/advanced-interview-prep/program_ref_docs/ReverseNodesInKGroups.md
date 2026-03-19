# 10. ReverseNodesInKGroups

**Author:** CodeChamps0419  
**Category:** `linkedlist.advanced`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative group reversal using boundaries  

## 1. Problem statement

Reverse nodes in groups of k; leave a short final group unchanged.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Find k nodes, reverse their links, reconnect the previous group, and advance.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/ReverseNodesInKGroups.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node reverseKGroup(Node head, int k) {
    if (k <= 0) throw new IllegalArgumentException("k must be positive");
    Node dummy = new Node(0);
    dummy.next = head;
    Node groupPrev = dummy;
    while (true) {
        Node kth = groupPrev;
        for (int i = 0; i < k && kth != null; i++) kth = kth.next;
        if (kth == null) break;
        Node groupNext = kth.next;
        Node prev = groupNext, cur = groupPrev.next;
        while (cur != groupNext) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        Node oldHead = groupPrev.next;
        groupPrev.next = kth;
        groupPrev = oldHead;
    }
    return dummy.next;
}
```

## 4. Input and expected output

```text
2 -> 1 -> 4 -> 3 -> 5 -> null
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Find k nodes, reverse their links, reconnect the previous group, and advance.

## 6. Edge cases and common beginner pitfalls

k=1 unchanged; short tail preserved.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative group reversal using boundaries**. Find k nodes, reverse their links, reconnect the previous group, and advance. The running time is **O(n)**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why does this avoid recursion stack space?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build linkedlist/advanced/src/ReverseNodesInKGroups.java
java -cp build linkedlist.advanced.ReverseNodesInKGroups
```

© Dynamic Duo Learners — Program author: CodeChamps0419
