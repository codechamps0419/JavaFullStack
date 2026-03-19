# 01. ReverseLinkedList

**Author:** CodeChamps0419  
**Category:** `linkedlist.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative three-pointer reversal  

## 1. Problem statement

Reverse the links of a singly linked list and return its new head.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Keep previous, current, and next references; point each node back, then advance.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/ReverseLinkedList.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node reverse(Node head) {
    Node previous = null, current = head;
    while (current != null) {
        Node next = current.next;   // Preserve the remaining nodes.
        current.next = previous;    // Reverse this link.
        previous = current;
        current = next;
    }
    return previous;
}
```

## 4. Input and expected output

```text
Before : 10 -> 20 -> 30 -> 40 -> 50 -> null
After  : 50 -> 40 -> 30 -> 20 -> 10 -> null
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Keep previous, current, and next references; point each node back, then advance.

## 6. Edge cases and common beginner pitfalls

Empty/single node: unchanged.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative three-pointer reversal**. Keep previous, current, and next references; point each node back, then advance. The running time is **O(n)**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why iterative O(1) extra space?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

**Optional recursive approach:** The Java file also includes a separately labeled recursive alternative after the iterative method. It is not the primary solution.

## 10. Run command

From the project root:

```bash
javac -d build linkedlist/basic/src/ReverseLinkedList.java
java -cp build linkedlist.basic.ReverseLinkedList
```

© Dynamic Duo Learners — Program author: CodeChamps0419
