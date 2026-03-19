# 57. CombineCompletableFutures

**Author:** CodeChamps0419  
**Category:** `concurrency`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** CompletableFuture.supplyAsync + thenCombine  

## 1. Problem statement

Run two independent computations concurrently and combine results.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

thenCombine waits for two independent results and applies a combining function.

**Time complexity:** `O(max(task times)) wall time ideally`  
**Auxiliary space complexity:** `O(1) result; executor overhead`  

The executable implementation is in `src/CombineCompletableFutures.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int combined() {
    ExecutorService pool=Executors.newFixedThreadPool(2);
    try {
        CompletableFuture<Integer> a=CompletableFuture.supplyAsync(()->20,pool);
        CompletableFuture<Integer> b=CompletableFuture.supplyAsync(()->22,pool);
        return a.thenCombine(b,Integer::sum).join();
    } finally {
        pool.shutdown();
    }
}
```

## 4. Input and expected output

```text
Combined: 42
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** thenCombine waits for two independent results and applies a combining function.

## 6. Edge cases and common beginner pitfalls

Always manage executors and handle exceptional completion in applications.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **CompletableFuture.supplyAsync + thenCombine**. thenCombine waits for two independent results and applies a combining function. The running time is **O(max(task times)) wall time ideally**, and the extra-space requirement is **O(1) result; executor overhead**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** thenCompose vs thenCombine?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build concurrency/src/CombineCompletableFutures.java
java -cp build concurrency.CombineCompletableFutures
```

© Dynamic Duo Learners — Program author: CodeChamps0419
