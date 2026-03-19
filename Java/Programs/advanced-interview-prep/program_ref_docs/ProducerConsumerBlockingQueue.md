# 55. ProducerConsumerBlockingQueue

**Author:** CodeChamps0419  
**Category:** `concurrency`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** ArrayBlockingQueue with poison pill  

## 1. Problem statement

Pass produced items safely from one thread to a consumer.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

BlockingQueue supplies thread-safe coordination and backpressure.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(capacity)`  

The executable implementation is in `src/ProducerConsumerBlockingQueue.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static List<Integer> runDemo() throws InterruptedException {
    BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(2);
    List<Integer> received=Collections.synchronizedList(new ArrayList<>());
    Thread producer=new Thread(()-> {
        try {
            for(int i=1;i<=5;i++) queue.put(i);
            queue.put(-1); // End-of-stream marker.
        } catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
    });
    Thread consumer=new Thread(()-> {
        try {
            while(true) {
                int v=queue.take();
                if(v==-1) break;
                received.add(v);
            }
        } catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
    });
    producer.start(); consumer.start();
    producer.join(); consumer.join();
    return received;
}
```

## 4. Input and expected output

```text
Consumed: [1, 2, 3, 4, 5]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** BlockingQueue supplies thread-safe coordination and backpressure.

## 6. Edge cases and common beginner pitfalls

Poison pill must not overlap valid domain values.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **ArrayBlockingQueue with poison pill**. BlockingQueue supplies thread-safe coordination and backpressure. The running time is **O(n)**, and the extra-space requirement is **O(capacity)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How do you signal completion with multiple consumers?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build concurrency/src/ProducerConsumerBlockingQueue.java
java -cp build concurrency.ProducerConsumerBlockingQueue
```

© Dynamic Duo Learners — Program author: CodeChamps0419
