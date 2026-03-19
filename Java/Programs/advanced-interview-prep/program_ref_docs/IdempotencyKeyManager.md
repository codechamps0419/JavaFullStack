# 62. IdempotencyKeyManager

**Author:** CodeChamps0419  
**Category:** `backend`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** ConcurrentHashMap computeIfAbsent  

## 1. Problem statement

Return original result when the same operation key is retried.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

computeIfAbsent computes one response per key within a single map instance.

**Time complexity:** `O(1) expected per key`  
**Auxiliary space complexity:** `O(number of unique keys)`  

The executable implementation is in `src/IdempotencyKeyManager.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class Manager {
    private final ConcurrentHashMap<String,String> responses=new ConcurrentHashMap<>();
    String execute(String key,Supplier<String> operation) {
        return responses.computeIfAbsent(key,k->operation.get());
    }
}
```

## 4. Input and expected output

```text
receipt-1
receipt-1
Executions: 1
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** computeIfAbsent computes one response per key within a single map instance.

## 6. Edge cases and common beginner pitfalls

Demonstration only: add request hashes, expiration, durable storage, failure states and atomic DB effects in production.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **ConcurrentHashMap computeIfAbsent**. computeIfAbsent computes one response per key within a single map instance. The running time is **O(1) expected per key**, and the extra-space requirement is **O(number of unique keys)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** What happens if process crashes after payment but before storing response?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backend/src/IdempotencyKeyManager.java
java -cp build backend.IdempotencyKeyManager
```

© Dynamic Duo Learners — Program author: CodeChamps0419
