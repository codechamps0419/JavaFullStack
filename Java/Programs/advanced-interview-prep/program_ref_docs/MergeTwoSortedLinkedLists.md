# 05. MergeTwoSortedLinkedLists

**Author:** CodeChamps0419  
**Category:** `linkedlist.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative dummy-head merging  

## 1. Problem statement

Merge two ascending linked lists without allocating new data nodes.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Attach the smaller current node and advance the corresponding input pointer.

**Time complexity:** `O(n + m)`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/MergeTwoSortedLinkedLists.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node merge(Node a, Node b) {
    Node dummy = new Node(0), tail = dummy;
    while (a != null && b != null) {
        if (a.value <= b.value) {
            tail.next = a;
            a = a.next;
        } else {
            tail.next = b;
            b = b.next;
        }
        tail = tail.next;
    }
    tail.next = a != null ? a : b;
    return dummy.next;
}
```

## 4. Input and expected output

```text
1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Attach the smaller current node and advance the corresponding input pointer.

## 6. Edge cases and common beginner pitfalls

Both input lists must be sorted; original links are reused.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative dummy-head merging**. Attach the smaller current node and advance the corresponding input pointer. The running time is **O(n + m)**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How does merge sort for linked lists use this method?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build linkedlist/basic/src/MergeTwoSortedLinkedLists.java
java -cp build linkedlist.basic.MergeTwoSortedLinkedLists
```

© Dynamic Duo Learners — Program author: CodeChamps0419
