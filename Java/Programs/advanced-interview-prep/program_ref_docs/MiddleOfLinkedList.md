# 02. MiddleOfLinkedList

**Author:** CodeChamps0419  
**Category:** `linkedlist.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Slow and fast pointers  

## 1. Problem statement

Find the middle node; for even length, return the second middle.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Fast walks twice as quickly as slow, so slow is at the midpoint when fast finishes.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/MiddleOfLinkedList.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node middle(Node head) {
    Node slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}
```

## 4. Input and expected output

```text
Middle: 30
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Fast walks twice as quickly as slow, so slow is at the midpoint when fast finishes.

## 6. Edge cases and common beginner pitfalls

For even length, slow lands on the second middle; empty list returns null.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Slow and fast pointers**. Fast walks twice as quickly as slow, so slow is at the midpoint when fast finishes. The running time is **O(n)**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How to return first middle instead?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build linkedlist/basic/src/MiddleOfLinkedList.java
java -cp build linkedlist.basic.MiddleOfLinkedList
```

© Dynamic Duo Learners — Program author: CodeChamps0419
