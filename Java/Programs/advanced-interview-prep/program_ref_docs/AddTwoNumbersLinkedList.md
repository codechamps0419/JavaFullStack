# 09. AddTwoNumbersLinkedList

**Author:** CodeChamps0419  
**Category:** `linkedlist.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative addition with carry  

## 1. Problem statement

Add numbers stored in reverse-digit order in two linked lists.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Add corresponding digits plus carry, creating a result node for each digit.

**Time complexity:** `O(max(n,m))`  
**Auxiliary space complexity:** `O(max(n,m)) output nodes`  

The executable implementation is in `src/AddTwoNumbersLinkedList.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node addNumbers(Node a, Node b) {
    Node dummy = new Node(0), tail = dummy;
    int carry = 0;
    while (a != null || b != null || carry != 0) {
        int sum = carry;
        if (a != null) { sum += a.value; a = a.next; }
        if (b != null) { sum += b.value; b = b.next; }
        tail.next = new Node(sum % 10);
        tail = tail.next;
        carry = sum / 10;
    }
    return dummy.next;
}
```

## 4. Input and expected output

```text
7 -> 0 -> 8 -> null
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Add corresponding digits plus carry, creating a result node for each digit.

## 6. Edge cases and common beginner pitfalls

Digit values must be 0..9; 342 + 465 = 807.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative addition with carry**. Add corresponding digits plus carry, creating a result node for each digit. The running time is **O(max(n,m))**, and the extra-space requirement is **O(max(n,m)) output nodes**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How would forward-order digits change your solution?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build linkedlist/medium/src/AddTwoNumbersLinkedList.java
java -cp build linkedlist.medium.AddTwoNumbersLinkedList
```

© Dynamic Duo Learners — Program author: CodeChamps0419
