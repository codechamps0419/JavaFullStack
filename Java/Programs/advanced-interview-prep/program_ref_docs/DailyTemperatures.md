# 16. DailyTemperatures

**Author:** CodeChamps0419  
**Category:** `stackqueue.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Monotonic stack of unresolved day indices  

## 1. Problem statement

Return the days until a warmer temperature for each day.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

A warmer day resolves earlier cooler days from the stack.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/DailyTemperatures.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int[] warmerDays(int[] t) {
    int[] result = new int[t.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int day = 0; day < t.length; day++) {
        while (!stack.isEmpty() && t[day] > t[stack.peek()]) {
            int previousDay = stack.pop();
            result[previousDay] = day - previousDay;
        }
        stack.push(day);
    }
    return result;
}
```

## 4. Input and expected output

```text
[1, 1, 4, 2, 1, 1, 0, 0]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** A warmer day resolves earlier cooler days from the stack.

## 6. Edge cases and common beginner pitfalls

Equal temperatures are not warmer.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Monotonic stack of unresolved day indices**. A warmer day resolves earlier cooler days from the stack. The running time is **O(n)**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How is this related to Next Greater Element?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/medium/src/DailyTemperatures.java
java -cp build stackqueue.medium.DailyTemperatures
```

© Dynamic Duo Learners — Program author: CodeChamps0419
