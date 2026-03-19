# 58. AvoidDeadlockLockOrdering

**Author:** CodeChamps0419  
**Category:** `concurrency`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Global consistent lock acquisition order  

## 1. Problem statement

Avoid deadlocks when two threads require two locks.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Acquire locks in globally increasing account-id order, regardless of transfer direction.

**Time complexity:** `O(1) per pair of locks excluding contention`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/AvoidDeadlockLockOrdering.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class Account {
    final int id;
    int balance;
    final Object lock=new Object();
    Account(int id,int balance) { this.id=id; this.balance=balance; }
}
static void transfer(Account from, Account to, int amount) {
    if(from==to) return;
    Account first=from.id<to.id?from:to;
    Account second=from.id<to.id?to:from;
    synchronized(first.lock) {
        synchronized(second.lock) {
            if(from.balance<amount) throw new IllegalArgumentException("Insufficient funds");
            from.balance-=amount;
            to.balance+=amount;
        }
    }
}
```

## 4. Input and expected output

```text
Balances: 80, 120
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Acquire locks in globally increasing account-id order, regardless of transfer direction.

## 6. Edge cases and common beginner pitfalls

Account IDs must be unique and stable. This is in-memory only, not a DB transaction.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Global consistent lock acquisition order**. Acquire locks in globally increasing account-id order, regardless of transfer direction. The running time is **O(1) per pair of locks excluding contention**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** What are the Coffman deadlock conditions?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build concurrency/src/AvoidDeadlockLockOrdering.java
java -cp build concurrency.AvoidDeadlockLockOrdering
```

© Dynamic Duo Learners — Program author: CodeChamps0419
