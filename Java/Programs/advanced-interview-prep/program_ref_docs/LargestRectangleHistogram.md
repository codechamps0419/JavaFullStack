# 20. LargestRectangleHistogram

**Author:** CodeChamps0419  
**Category:** `stackqueue.advanced`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Monotonic increasing stack with sentinel  

## 1. Problem statement

Find the largest rectangle area in a histogram.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

When current height drops, pop taller bars and compute their maximal widths.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/LargestRectangleHistogram.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int largestRectangle(int[] heights) {
    Deque<Integer> stack = new ArrayDeque<>();
    int max = 0;
    for (int i = 0; i <= heights.length; i++) {
        int height = i == heights.length ? 0 : heights[i];
        while (!stack.isEmpty() && heights[stack.peek()] > height) {
            int bar = heights[stack.pop()];
            int left = stack.isEmpty() ? -1 : stack.peek();
            int width = i - left - 1;
            max = Math.max(max, bar * width);
        }
        stack.push(i);
    }
    return max;
}
```

## 4. Input and expected output

```text
Largest area: 10
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** When current height drops, pop taller bars and compute their maximal widths.

## 6. Edge cases and common beginner pitfalls

Potential integer overflow if heights/width are huge; use long in production.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Monotonic increasing stack with sentinel**. When current height drops, pop taller bars and compute their maximal widths. The running time is **O(n)**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why append a virtual zero-height bar?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/advanced/src/LargestRectangleHistogram.java
java -cp build stackqueue.advanced.LargestRectangleHistogram
```

© Dynamic Duo Learners — Program author: CodeChamps0419
