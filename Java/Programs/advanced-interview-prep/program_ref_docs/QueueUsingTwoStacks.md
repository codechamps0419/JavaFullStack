# 12. QueueUsingTwoStacks

**Author:** CodeChamps0419  
**Category:** `stackqueue.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Two stacks with amortized transfers  

## 1. Problem statement

Implement a FIFO queue using two LIFO stacks.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Transfer input to output only when output becomes empty, reversing the order.

**Time complexity:** `Amortized O(1) per operation`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/QueueUsingTwoStacks.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class TwoStackQueue {
    Deque<Integer> input = new ArrayDeque<>();
    Deque<Integer> output = new ArrayDeque<>();
    void offer(int value) { input.push(value); }
    int poll() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) output.push(input.pop());
        }
        if (output.isEmpty()) throw new NoSuchElementException();
        return output.pop();
    }
}
```

## 4. Input and expected output

```text
Poll: 10
Poll: 20
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Transfer input to output only when output becomes empty, reversing the order.

## 6. Edge cases and common beginner pitfalls

Each element moves between stacks at most once.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Two stacks with amortized transfers**. Transfer input to output only when output becomes empty, reversing the order. The running time is **Amortized O(1) per operation**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Explain amortized O(1) despite an O(n) transfer.

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/basic/src/QueueUsingTwoStacks.java
java -cp build stackqueue.basic.QueueUsingTwoStacks
```

© Dynamic Duo Learners — Program author: CodeChamps0419
