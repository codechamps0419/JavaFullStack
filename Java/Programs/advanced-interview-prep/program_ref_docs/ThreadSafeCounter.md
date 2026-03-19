# 56. ThreadSafeCounter

**Author:** CodeChamps0419  
**Category:** `concurrency`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** AtomicInteger compare-and-swap operations  

## 1. Problem statement

Increment shared count safely across several threads.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

AtomicInteger makes read-modify-write atomic without a synchronized block.

**Time complexity:** `O(1) expected per increment`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/ThreadSafeCounter.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int count(int threads, int increments) throws InterruptedException {
    AtomicInteger counter=new AtomicInteger();
    List<Thread> workers=new ArrayList<>();
    for(int i=0;i<threads;i++) {
        Thread t=new Thread(()-> {
            for(int j=0;j<increments;j++) counter.incrementAndGet();
        });
        workers.add(t); t.start();
    }
    for(Thread t:workers) t.join();
    return counter.get();
}
```

## 4. Input and expected output

```text
Count: 4000
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** AtomicInteger makes read-modify-write atomic without a synchronized block.

## 6. Edge cases and common beginner pitfalls

A plain volatile int still cannot make x++ atomic.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **AtomicInteger compare-and-swap operations**. AtomicInteger makes read-modify-write atomic without a synchronized block. The running time is **O(1) expected per increment**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** When would LongAdder be better?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build concurrency/src/ThreadSafeCounter.java
java -cp build concurrency.ThreadSafeCounter
```

© Dynamic Duo Learners — Program author: CodeChamps0419
