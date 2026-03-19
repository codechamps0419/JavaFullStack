# 63. ThreadSafeInventory

**Author:** CodeChamps0419  
**Category:** `backend`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** AtomicInteger CAS update  

## 1. Problem statement

Prevent overselling when multiple threads purchase limited inventory.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

CAS retries only when another update changed stock in the meantime.

**Time complexity:** `O(1) expected per attempt`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/ThreadSafeInventory.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class Inventory {
    private final AtomicInteger stock;
    Inventory(int initial) { stock=new AtomicInteger(initial); }
    boolean purchase(int quantity) {
        if(quantity<=0) throw new IllegalArgumentException();
        while(true) {
            int available=stock.get();
            if(available<quantity) return false;
            if(stock.compareAndSet(available,available-quantity)) return true;
        }
    }
    int remaining() { return stock.get(); }
}
```

## 4. Input and expected output

```text
Bought 1: true
Bought 2: false
Remaining: 1
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** CAS retries only when another update changed stock in the meantime.

## 6. Edge cases and common beginner pitfalls

Within one JVM only; multi-pod inventory needs atomic DB update or transactional locking.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **AtomicInteger CAS update**. CAS retries only when another update changed stock in the meantime. The running time is **O(1) expected per attempt**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Show atomic SQL update WHERE quantity >= requested quantity.

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backend/src/ThreadSafeInventory.java
java -cp build backend.ThreadSafeInventory
```

© Dynamic Duo Learners — Program author: CodeChamps0419
