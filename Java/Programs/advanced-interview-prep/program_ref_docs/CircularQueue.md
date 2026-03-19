# 18. CircularQueue

**Author:** CodeChamps0419  
**Category:** `stackqueue.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Modular front/rear indexing  

## 1. Problem statement

Implement a fixed-size FIFO queue using a circular array.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Front advances modulo capacity, allowing reused array slots.

**Time complexity:** `O(1) per operation`  
**Auxiliary space complexity:** `O(capacity)`  

The executable implementation is in `src/CircularQueue.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class CircularIntQueue {
    int[] data;
    int front, size;
    CircularIntQueue(int capacity) { data = new int[capacity]; }
    boolean offer(int value) {
        if (size == data.length) return false;
        data[(front + size) % data.length] = value;
        size++;
        return true;
    }
    int poll() {
        if (size == 0) throw new NoSuchElementException();
        int value = data[front];
        front = (front + 1) % data.length;
        size--;
        return value;
    }
}
```

## 4. Input and expected output

```text
Poll: 10
Poll: 20
Poll: 30
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Front advances modulo capacity, allowing reused array slots.

## 6. Edge cases and common beginner pitfalls

Capacity must be greater than zero.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Modular front/rear indexing**. Front advances modulo capacity, allowing reused array slots. The running time is **O(1) per operation**, and the extra-space requirement is **O(capacity)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How does this differ from ArrayDeque?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/medium/src/CircularQueue.java
java -cp build stackqueue.medium.CircularQueue
```

© Dynamic Duo Learners — Program author: CodeChamps0419
