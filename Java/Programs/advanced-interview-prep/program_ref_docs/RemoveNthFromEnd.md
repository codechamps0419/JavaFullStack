# 06. RemoveNthFromEnd

**Author:** CodeChamps0419  
**Category:** `linkedlist.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Two pointers and dummy head  

## 1. Problem statement

Remove the nth node counted backward from the tail.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Maintain a gap of n nodes between fast and slow; when fast reaches tail, unlink slow.next.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/RemoveNthFromEnd.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node removeNth(Node head, int n) {
    if (n <= 0) throw new IllegalArgumentException("n must be positive");
    Node dummy = new Node(0);
    dummy.next = head;
    Node fast = dummy, slow = dummy;
    for (int i = 0; i < n; i++) {
        fast = fast.next;
        if (fast == null) throw new IllegalArgumentException("n exceeds list length");
    }
    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }
    slow.next = slow.next.next;
    return dummy.next;
}
```

## 4. Input and expected output

```text
10 -> 20 -> 30 -> 50 -> null
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Maintain a gap of n nodes between fast and slow; when fast reaches tail, unlink slow.next.

## 6. Edge cases and common beginner pitfalls

n must be within 1..length; deleting head is supported.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Two pointers and dummy head**. Maintain a gap of n nodes between fast and slow; when fast reaches tail, unlink slow.next. The running time is **O(n)**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why is a dummy head useful?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build linkedlist/medium/src/RemoveNthFromEnd.java
java -cp build linkedlist.medium.RemoveNthFromEnd
```

© Dynamic Duo Learners — Program author: CodeChamps0419
