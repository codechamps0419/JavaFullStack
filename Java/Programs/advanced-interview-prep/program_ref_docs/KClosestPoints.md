# 33. KClosestPoints

**Author:** CodeChamps0419  
**Category:** `heap`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Max heap of size k on squared distance  

## 1. Problem statement

Find k 2D points with shortest Euclidean distance from origin.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Use squared distances to avoid floating-point square roots.

**Time complexity:** `O(n log k)`  
**Auxiliary space complexity:** `O(k)`  

The executable implementation is in `src/KClosestPoints.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static List<String> closest(int[][] points, int k) {
    if (k < 0 || k > points.length) throw new IllegalArgumentException("Invalid k");
    PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> Long.compare(dist(b), dist(a)));
    for (int[] point : points) {
        heap.offer(point);
        if (heap.size() > k) heap.poll();
    }
    List<int[]> selected = new ArrayList<>(heap);
    selected.sort(Comparator.comparingLong(KClosestPoints::dist));
    List<String> result = new ArrayList<>();
    for (int[] p : selected) result.add(Arrays.toString(p));
    return result;
}
static long dist(int[] p) { return (long)p[0]*p[0] + (long)p[1]*p[1]; }
```

## 4. Input and expected output

```text
Closest: [[-2, 2]]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Use squared distances to avoid floating-point square roots.

## 6. Edge cases and common beginner pitfalls

Use long to avoid overflow when squaring int coordinates.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Max heap of size k on squared distance**. Use squared distances to avoid floating-point square roots. The running time is **O(n log k)**, and the extra-space requirement is **O(k)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** What if many points have exactly equal distance?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build heap/src/KClosestPoints.java
java -cp build heap.KClosestPoints
```

© Dynamic Duo Learners — Program author: CodeChamps0419
