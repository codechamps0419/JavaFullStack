# 08. PalindromeLinkedList

**Author:** CodeChamps0419  
**Category:** `linkedlist.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Fast/slow pointers + in-place reversal of second half  

## 1. Problem statement

Determine whether the list reads the same forward and backward.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Reverse the second half, compare corresponding values, then restore the links.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/PalindromeLinkedList.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static boolean isPalindrome(Node head) {
    if (head == null || head.next == null) return true;
    Node slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    Node second = reverse(slow.next);
    Node restoreHead = second;
    Node first = head;
    boolean equal = true;
    while (second != null) {
        if (first.value != second.value) { equal = false; break; }
        first = first.next;
        second = second.next;
    }
    slow.next = reverse(restoreHead); // Restore caller's original list.
    return equal;
}
static Node reverse(Node head) {
    Node prev = null;
    while (head != null) {
        Node next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}
```

## 4. Input and expected output

```text
Palindrome: true
Restored  : 1 -> 2 -> 3 -> 2 -> 1 -> null
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Reverse the second half, compare corresponding values, then restore the links.

## 6. Edge cases and common beginner pitfalls

Do not accidentally mutate caller input permanently.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Fast/slow pointers + in-place reversal of second half**. Reverse the second half, compare corresponding values, then restore the links. The running time is **O(n)**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why restore the list after comparison?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build linkedlist/medium/src/PalindromeLinkedList.java
java -cp build linkedlist.medium.PalindromeLinkedList
```

© Dynamic Duo Learners — Program author: CodeChamps0419
