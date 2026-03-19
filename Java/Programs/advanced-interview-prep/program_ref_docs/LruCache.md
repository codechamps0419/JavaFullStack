# 60. LruCache

**Author:** CodeChamps0419  
**Category:** `backend`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** LinkedHashMap access-order eviction  

## 1. Problem statement

Build an O(1) least-recently-used cache supporting get and put.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

LinkedHashMap in access-order moves used keys to the newest end; eldest is evicted.

**Time complexity:** `O(1) expected`  
**Auxiliary space complexity:** `O(capacity)`  

The executable implementation is in `src/LruCache.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class Lru extends LinkedHashMap<Integer,Integer> {
    private final int capacity;
    Lru(int capacity) { super(16,0.75f,true); this.capacity=capacity; }
    @Override protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
        return size()>capacity;
    }
    int getValue(int key) { return getOrDefault(key,-1); }
}
```

## 4. Input and expected output

```text
Evicted key 2: -1
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** LinkedHashMap in access-order moves used keys to the newest end; eldest is evicted.

## 6. Edge cases and common beginner pitfalls

Not thread-safe by default; production caches need TTL and max weight as appropriate.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **LinkedHashMap access-order eviction**. LinkedHashMap in access-order moves used keys to the newest end; eldest is evicted. The running time is **O(1) expected**, and the extra-space requirement is **O(capacity)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Implement O(1) LRU manually using Map + doubly linked list.

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backend/src/LruCache.java
java -cp build backend.LruCache
```

© Dynamic Duo Learners — Program author: CodeChamps0419
