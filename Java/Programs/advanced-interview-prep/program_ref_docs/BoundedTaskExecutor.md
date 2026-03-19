# 65. BoundedTaskExecutor

**Author:** CodeChamps0419  
**Category:** `backend`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** ThreadPoolExecutor with AbortPolicy  

## 1. Problem statement

Run tasks using a limited worker count and bounded queue.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Pool limits active threads and queued work; rejection protects against unlimited backlog.

**Time complexity:** `O(1) submission, task-dependent execution`  
**Auxiliary space complexity:** `O(workers + queue capacity)`  

The executable implementation is in `src/BoundedTaskExecutor.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static List<String> executeDemo() throws InterruptedException, ExecutionException {
    ThreadPoolExecutor pool=new ThreadPoolExecutor(2,2,0,TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2),new ThreadPoolExecutor.AbortPolicy());
    try {
        List<Future<String>> results=new ArrayList<>();
        for(int i=1;i<=3;i++) {
            final int task=i;
            results.add(pool.submit(()->"task-"+task));
        }
        List<String> output=new ArrayList<>();
        for(Future<String> result:results) output.add(result.get());
        return output;
    } finally {
        pool.shutdown();
    }
}
```

## 4. Input and expected output

```text
Completed: [task-1, task-2, task-3]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Pool limits active threads and queued work; rejection protects against unlimited backlog.

## 6. Edge cases and common beginner pitfalls

Submit too many simultaneous tasks to observe RejectedExecutionException.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **ThreadPoolExecutor with AbortPolicy**. Pool limits active threads and queued work; rejection protects against unlimited backlog. The running time is **O(1) submission, task-dependent execution**, and the extra-space requirement is **O(workers + queue capacity)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Compare AbortPolicy, CallerRunsPolicy and DiscardPolicy.

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backend/src/BoundedTaskExecutor.java
java -cp build backend.BoundedTaskExecutor
```

© Dynamic Duo Learners — Program author: CodeChamps0419
