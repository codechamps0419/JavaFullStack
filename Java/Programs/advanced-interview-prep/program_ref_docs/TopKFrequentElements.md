# 32. TopKFrequentElements

**Author:** CodeChamps0419  
**Category:** `heap`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Frequency HashMap + min heap  

## 1. Problem statement

Return the k most frequent integer values.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Count values; keep only k highest-frequency candidates.

**Time complexity:** `O(n log k)`  
**Auxiliary space complexity:** `O(u + k)`  

The executable implementation is in `src/TopKFrequentElements.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
record Entry(int value, int count) { }
static List<Integer> topK(int[] values, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int value : values) freq.merge(value, 1, Integer::sum);
    if (k < 1 || k > freq.size()) throw new IllegalArgumentException("Invalid k");
    PriorityQueue<Entry> heap = new PriorityQueue<>(Comparator
            .comparingInt(Entry::count).thenComparing(Entry::value));
    for (var e : freq.entrySet()) {
        heap.offer(new Entry(e.getKey(), e.getValue()));
        if (heap.size() > k) heap.poll();
    }
    List<Integer> result = new ArrayList<>();
    while (!heap.isEmpty()) result.add(heap.poll().value());
    Collections.reverse(result);
    return result;
}
```

## 4. Input and expected output

```text
Top 2: [1, 2]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Count values; keep only k highest-frequency candidates.

## 6. Edge cases and common beginner pitfalls

Tie ordering is a documented implementation choice, not universal.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Frequency HashMap + min heap**. Count values; keep only k highest-frequency candidates. The running time is **O(n log k)**, and the extra-space requirement is **O(u + k)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How could bucket sort achieve O(n)?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build heap/src/TopKFrequentElements.java
java -cp build heap.TopKFrequentElements
```

© Dynamic Duo Learners — Program author: CodeChamps0419
