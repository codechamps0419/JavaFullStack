# 15. MinStack

**Author:** CodeChamps0419  
**Category:** `stackqueue.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Stack of values paired with running minimum  

## 1. Problem statement

Support push, pop, top and minimum lookup in constant time.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Store the minimum so far with every pushed value; popping restores the prior minimum.

**Time complexity:** `O(1) per operation`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/MinStack.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class MinStackImpl {
    record Entry(int value, int min) { }
    Deque<Entry> stack = new ArrayDeque<>();
    void push(int value) {
        int min = stack.isEmpty() ? value : Math.min(value, stack.peek().min());
        stack.push(new Entry(value, min));
    }
    int pop() { return stack.pop().value(); }
    int top() { return stack.peek().value(); }
    int min() { return stack.peek().min(); }
}
```

## 4. Input and expected output

```text
Min: 2
Min after pops: 5
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Store the minimum so far with every pushed value; popping restores the prior minimum.

## 6. Edge cases and common beginner pitfalls

Peek on empty stack should throw; define API contract.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Stack of values paired with running minimum**. Store the minimum so far with every pushed value; popping restores the prior minimum. The running time is **O(1) per operation**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Can you use two stacks instead?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/medium/src/MinStack.java
java -cp build stackqueue.medium.MinStack
```

© Dynamic Duo Learners — Program author: CodeChamps0419
