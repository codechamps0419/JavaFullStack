# 14. NextGreaterElement

**Author:** CodeChamps0419  
**Category:** `stackqueue.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Monotonic decreasing stack of indices  

## 1. Problem statement

For every array value, return the next greater value to its right or -1.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Unresolved indices remain on the stack until a greater value appears.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/NextGreaterElement.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int[] nextGreater(int[] values) {
    int[] answer = new int[values.length];
    Arrays.fill(answer, -1);
    Deque<Integer> indices = new ArrayDeque<>();
    for (int i = 0; i < values.length; i++) {
        while (!indices.isEmpty() && values[i] > values[indices.peek()]) {
            answer[indices.pop()] = values[i];
        }
        indices.push(i);
    }
    return answer;
}
```

## 4. Input and expected output

```text
[5, 10, 10, -1, -1]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Unresolved indices remain on the stack until a greater value appears.

## 6. Edge cases and common beginner pitfalls

Strictly greater: equal values do not qualify.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Monotonic decreasing stack of indices**. Unresolved indices remain on the stack until a greater value appears. The running time is **O(n)**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why are each index push and pop O(1) amortized?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/basic/src/NextGreaterElement.java
java -cp build stackqueue.basic.NextGreaterElement
```

© Dynamic Duo Learners — Program author: CodeChamps0419
