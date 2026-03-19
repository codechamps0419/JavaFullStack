# 64. KeyValueStoreWithTtl

**Author:** CodeChamps0419  
**Category:** `backend`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** ConcurrentHashMap + expiration timestamp  

## 1. Problem statement

Create a key-value store that lazily expires entries after a TTL.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Read compares current monotonic time against expiry, removing expired value opportunistically.

**Time complexity:** `O(1) expected get/put`  
**Auxiliary space complexity:** `O(number of entries)`  

The executable implementation is in `src/KeyValueStoreWithTtl.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
record Entry(String value,long expiresAtNanos) { }
static class Store {
    final ConcurrentHashMap<String,Entry> data=new ConcurrentHashMap<>();
    void put(String key,String value,long ttlMillis) {
        if(ttlMillis<=0) throw new IllegalArgumentException();
        data.put(key,new Entry(value,System.nanoTime()+TimeUnit.MILLISECONDS.toNanos(ttlMillis)));
    }
    String get(String key) {
        Entry entry=data.get(key);
        if(entry==null) return null;
        if(System.nanoTime()-entry.expiresAtNanos()>=0) {
            data.remove(key,entry);
            return null;
        }
        return entry.value();
    }
}
```

## 4. Input and expected output

```text
Value: Java
Missing: null
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Read compares current monotonic time against expiry, removing expired value opportunistically.

## 6. Edge cases and common beginner pitfalls

Expired keys not accessed still occupy memory; add periodic cleanup for production.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **ConcurrentHashMap + expiration timestamp**. Read compares current monotonic time against expiry, removing expired value opportunistically. The running time is **O(1) expected get/put**, and the extra-space requirement is **O(number of entries)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How would you implement TTL eviction without full scans?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backend/src/KeyValueStoreWithTtl.java
java -cp build backend.KeyValueStoreWithTtl
```

© Dynamic Duo Learners — Program author: CodeChamps0419
