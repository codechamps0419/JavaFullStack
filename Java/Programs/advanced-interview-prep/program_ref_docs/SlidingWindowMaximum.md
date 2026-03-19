# 19. SlidingWindowMaximum

**Author:** CodeChamps0419  
**Category:** `stackqueue.advanced`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Monotonic deque of indices  

## 1. Problem statement

Return the maximum of every consecutive window of size k.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Remove expired indices from the front and inferior values from the back.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(k)`  

The executable implementation is in `src/SlidingWindowMaximum.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int[] slidingMax(int[] nums, int k) {
    if (k < 1 || k > nums.length) throw new IllegalArgumentException("Invalid k");
    int[] answer = new int[nums.length - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < nums.length; i++) {
        while (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.removeFirst();
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.removeLast();
        deque.addLast(i);
        if (i >= k - 1) answer[i - k + 1] = nums[deque.peekFirst()];
    }
    return answer;
}
```

## 4. Input and expected output

```text
[3, 3, 5, 5, 6, 7]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Remove expired indices from the front and inferior values from the back.

## 6. Edge cases and common beginner pitfalls

Deque stores indices, not values, so window expiry is trackable.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Monotonic deque of indices**. Remove expired indices from the front and inferior values from the back. The running time is **O(n)**, and the extra-space requirement is **O(k)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why does each index enter and exit once?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/advanced/src/SlidingWindowMaximum.java
java -cp build stackqueue.advanced.SlidingWindowMaximum
```

© Dynamic Duo Learners — Program author: CodeChamps0419
