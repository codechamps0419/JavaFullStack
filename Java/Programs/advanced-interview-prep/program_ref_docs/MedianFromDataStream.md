# 35. MedianFromDataStream

**Author:** CodeChamps0419  
**Category:** `heap`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Max heap for lower half and min heap for upper half  

## 1. Problem statement

Maintain median under repeated integer insertion.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Balance heaps so lower has equal or one more value than upper.

**Time complexity:** `O(log n) add, O(1) median`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/MedianFromDataStream.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class MedianFinder {
    PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> upper = new PriorityQueue<>();
    void add(int value) {
        lower.offer(value);
        upper.offer(lower.poll());
        if (upper.size() > lower.size()) lower.offer(upper.poll());
    }
    double median() {
        if (lower.isEmpty()) throw new NoSuchElementException("No values");
        return lower.size() > upper.size() ? lower.peek()
                : ((long)lower.peek() + upper.peek()) / 2.0;
    }
}
```

## 4. Input and expected output

```text
Median: 1.5
Median: 2.0
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Balance heaps so lower has equal or one more value than upper.

## 6. Edge cases and common beginner pitfalls

Use long for sum of two ints before division.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Max heap for lower half and min heap for upper half**. Balance heaps so lower has equal or one more value than upper. The running time is **O(log n) add, O(1) median**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How would you support deletions?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build heap/src/MedianFromDataStream.java
java -cp build heap.MedianFromDataStream
```

© Dynamic Duo Learners — Program author: CodeChamps0419
