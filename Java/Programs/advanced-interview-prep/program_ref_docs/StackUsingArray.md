# 11. StackUsingArray

**Author:** CodeChamps0419  
**Category:** `stackqueue.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Array-backed bounded stack  

## 1. Problem statement

Implement push, pop and peek with an array.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

The top index is size - 1; push increases size and pop decreases it.

**Time complexity:** `O(1) per operation`  
**Auxiliary space complexity:** `O(capacity)`  

The executable implementation is in `src/StackUsingArray.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class IntStack {
    private final int[] data;
    private int size;
    IntStack(int capacity) { data = new int[capacity]; }
    void push(int value) {
        if (size == data.length) throw new IllegalStateException("Stack full");
        data[size++] = value;
    }
    int pop() {
        if (size == 0) throw new NoSuchElementException("Stack empty");
        return data[--size];
    }
    int peek() {
        if (size == 0) throw new NoSuchElementException("Stack empty");
        return data[size - 1];
    }
}
```

## 4. Input and expected output

```text
Top: 20
Pop: 20
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** The top index is size - 1; push increases size and pop decreases it.

## 6. Edge cases and common beginner pitfalls

Overflow and underflow must be checked.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Array-backed bounded stack**. The top index is size - 1; push increases size and pop decreases it. The running time is **O(1) per operation**, and the extra-space requirement is **O(capacity)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How could you grow the array dynamically?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/basic/src/StackUsingArray.java
java -cp build stackqueue.basic.StackUsingArray
```

© Dynamic Duo Learners — Program author: CodeChamps0419
