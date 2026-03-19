# 61. TokenBucketRateLimiter

**Author:** CodeChamps0419  
**Category:** `backend`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Synchronized token bucket using monotonic clock  

## 1. Problem statement

Implement a per-process token-bucket limiter with refill over time.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Token credits refill with elapsed monotonic time and are capped at capacity.

**Time complexity:** `O(1) per request`  
**Auxiliary space complexity:** `O(1)`  

The executable implementation is in `src/TokenBucketRateLimiter.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class TokenBucket {
    final double capacity, refillPerSecond;
    double tokens;
    long lastNanos;
    TokenBucket(int capacity,double refillPerSecond) {
        if(capacity<=0 || refillPerSecond<=0) throw new IllegalArgumentException();
        this.capacity=capacity; this.refillPerSecond=refillPerSecond;
        tokens=capacity; lastNanos=System.nanoTime();
    }
    synchronized boolean allow() {
        long now=System.nanoTime();
        double elapsed=Math.max(0,now-lastNanos)/1_000_000_000.0;
        tokens=Math.min(capacity,tokens+elapsed*refillPerSecond);
        lastNanos=now;
        if(tokens<1.0) return false;
        tokens-=1.0;
        return true;
    }
}
```

## 4. Input and expected output

```text
Allowed: true
Allowed: true
Allowed: false
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Token credits refill with elapsed monotonic time and are capped at capacity.

## 6. Edge cases and common beginner pitfalls

Single JVM only; use Redis atomics/Lua for cross-pod limits.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Synchronized token bucket using monotonic clock**. Token credits refill with elapsed monotonic time and are capped at capacity. The running time is **O(1) per request**, and the extra-space requirement is **O(1)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Compare fixed window, sliding window and token bucket.

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backend/src/TokenBucketRateLimiter.java
java -cp build backend.TokenBucketRateLimiter
```

© Dynamic Duo Learners — Program author: CodeChamps0419
